package be.gling.businessApp.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.joda.time.LocalDateTime;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import be.gling.businessApp.R;
import be.gling.businessApp.model.dto.AbstractPublicationDTO;
import be.gling.businessApp.model.dto.PromotionDTO;
import be.gling.businessApp.model.dto.technical.DTO;
import be.gling.businessApp.model.util.ImageCache;
import be.gling.businessApp.model.util.externalRequest.Request;
import be.gling.businessApp.model.util.externalRequest.RequestEnum;
import be.gling.businessApp.model.util.externalRequest.WebClient;
import be.gling.businessApp.view.RequestActionInterface;
import be.gling.businessApp.view.activity.technical.AbstractActivity;
import be.gling.businessApp.view.dialog.DialogConstructor;
import be.gling.businessApp.view.widget.customer.DateAndHourView;
import be.gling.businessApp.view.widget.technical.Field;
import be.gling.businessApp.view.widget.technical.ImageFieldView;

public class CreatePromotionActivity extends AbstractActivity implements RequestActionInterface {

    private final static int REQUEST_IMAGE_CAPTURE = 1;
    private final static int REQUEST_TAKE_PHOTO = 2;


    private static final String TITLE = "title";
    private static final String DESCRIPTION = "description";
    private static final String START_DATE = "startDate";
    private static final String END_DATE = "endDate";
    private static final String OFF_PERCENT = "offPercent";
    private Dialog loadingDialog;
    private Field titleField;
    private Field descriptionField;
    private Field startDateField;
    private Field endDateField;
    private Field percentOffField;
    private DateAndHourView endDate;
    private PromotionDTO promotionDTO;

    //TODO ???
    private ImageFieldView illutstrationsField;

    /**
     * build
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        //build DTO
        promotionDTO = new PromotionDTO();

        loadingDialog = DialogConstructor.dialogLoading(this);

        setContentView(R.layout.activity_promotion_form);

        LinearLayout insertionPoint = (LinearLayout) findViewById(R.id.insert_point);

        try {
            //title
            titleField = new Field(this, new Field.FieldProperties(AbstractPublicationDTO.class.getDeclaredField(TITLE), R.string.form_publication_title), promotionDTO.getTitle());

            //description
            Field.FieldProperties descriptionFieldProperties = new Field.FieldProperties(AbstractPublicationDTO.class.getDeclaredField(DESCRIPTION), R.string.form_publication_description);
            descriptionFieldProperties.setMultiline(true);
            descriptionField = new Field(this, descriptionFieldProperties, promotionDTO.getDescription());

            //startdate
            final DateAndHourView startDate = new DateAndHourView(this, 30, new LocalDateTime());

            Field.FieldProperties startDateFieldProperties = new Field.FieldProperties(AbstractPublicationDTO.class.getDeclaredField(START_DATE), R.string.form_publication_startdate, startDate);
            startDateField = new Field(this, startDateFieldProperties, promotionDTO.getStartDate());
            startDateField.setValue(new Date());

            //enddate
            endDate = new DateAndHourView(this, 14, LocalDateTime.now());
            Field.FieldProperties endDateFieldProperties = new Field.FieldProperties(AbstractPublicationDTO.class.getDeclaredField(END_DATE), R.string.form_publication_enddate, endDate);
            endDateField = new Field(this, endDateFieldProperties, promotionDTO.getEndDate());
            endDateField.setValue(LocalDateTime.now().withMillisOfSecond(0).withMinuteOfHour(0).plusDays(7));

            //percentOff
            percentOffField = new Field(this, new Field.FieldProperties(PromotionDTO.class.getDeclaredField(OFF_PERCENT), R.string.form_publication_percent));
            if (promotionDTO.getOffPercent() != null) {
                percentOffField.setValue(promotionDTO.getOffPercent() / 100);
            }

            //TODO temp
            illutstrationsField = new ImageFieldView(this);//(ImageFieldView) findViewById(R.id.publication_illustrations);


            //build form
            insertionPoint.addView(titleField);
            insertionPoint.addView(descriptionField);
            insertionPoint.addView(startDateField);
            insertionPoint.addView(endDateField);
            insertionPoint.addView(percentOffField);
            insertionPoint.addView(illutstrationsField);

            startDate.addChangeListener(new DateAndHourView.ChangeValue() {
                @Override
                public void changeValue(Date value) {
                    if (value != null) {
                        endDate.setStartDate(value);
                        int i = 0;
                    }
                }
            });

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        ((Button) findViewById(R.id.btn_submit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                create();
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        illutstrationsField.managerResult(requestCode, resultCode, data);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        titleField.saveToInstanceState(savedInstanceState);
        descriptionField.saveToInstanceState(savedInstanceState);
        startDateField.saveToInstanceState(savedInstanceState);
        endDateField.saveToInstanceState(savedInstanceState);
        percentOffField.saveToInstanceState(savedInstanceState);
        //TODO
        illutstrationsField.saveToInstanceState("illu", savedInstanceState);

        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        illutstrationsField.restoreFromInstanceState("illu", savedInstanceState);
        titleField.restoreFromInstanceState(savedInstanceState);
        descriptionField.restoreFromInstanceState(savedInstanceState);
        startDateField.restoreFromInstanceState(savedInstanceState);
        endDateField.restoreFromInstanceState(savedInstanceState);
        percentOffField.restoreFromInstanceState(savedInstanceState);
    }


    private void create() {

        boolean valid = true;

        valid = (!titleField.control()) ? false : valid;
        valid = (!descriptionField.control()) ? false : valid;
        valid = (!startDateField.control()) ? false : valid;
        valid = (!endDateField.control()) ? false : valid;
        valid = (!percentOffField.control()) ? false : valid;
        //TODO
        //valid = (!illutstrationsField.control()) ? false : valid;

        if (valid) {
            promotionDTO.setTitle((String) titleField.getValue());
            promotionDTO.setDescription((String) descriptionField.getValue());
            promotionDTO.setStartDate((Date) startDateField.getValue());
            promotionDTO.setEndDate((Date) endDateField.getValue());
            promotionDTO.setOffPercent((Double) percentOffField.getValue() / 100);

            WebClient<PromotionDTO> webClient = new WebClient<PromotionDTO>(RequestEnum.CREATE_PROMOTION, promotionDTO, PromotionDTO.class);

            //TODO illustration
            String illustrationPath = (String) illutstrationsField.getValue(null);

            Request request = new Request(this, webClient);
//            request.execute();


            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            File file = new File(illustrationPath);
            builder.addPart("file", new FileBody(file));


            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(WebClient.TARGET_URL + "rest/file");
            httppost.setEntity(builder.build());

            try {
                HttpResponse response = httpclient.execute(httppost);
                HttpEntity entity = response.getEntity();

                final String response_str = EntityUtils.toString(entity);
                if (entity != null) {
                    Log.i("RESPONSE", response_str);
                    JSONObject jobj = new JSONObject(response_str);
                    String result = jobj.getString("ResponseCode");
                    Log.e("Result", "...." + result);

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public void displayErrorMessage(String errorMessage) {
        findViewById(R.id.error_message_container).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.error_message)).setText(errorMessage);
    }


    @Override
    public void loadingAction(boolean loading) {

        if (loading) {
            loadingDialog.show();

            // create animation and add to the refresh item
            findViewById(R.id.error_message_container).setVisibility(View.GONE);
        } else {
            loadingDialog.cancel();
        }
    }

    @Override
    public void successAction(DTO successDTO) {

        PromotionDTO promotionDTO = (PromotionDTO) successDTO;

        int i = 0;
    }
}
