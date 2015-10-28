package be.gling.businessApp.view.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facebook.login.LoginManager;

import be.gling.businessApp.R;
import be.gling.businessApp.model.dto.ListPublicationDTO;
import be.gling.businessApp.model.dto.technical.DTO;
import be.gling.businessApp.model.util.Storage;
import be.gling.businessApp.model.util.externalRequest.Request;
import be.gling.businessApp.model.util.externalRequest.RequestEnum;
import be.gling.businessApp.model.util.externalRequest.WebClient;
import be.gling.businessApp.view.RequestActionInterface;
import be.gling.businessApp.view.activity.technical.AbstractActivity;
import be.gling.businessApp.view.dialog.DialogConstructor;
import be.gling.businessApp.view.listAdapter.PublicationList;


public class MainActivity extends ActionBarActivity implements RequestActionInterface {

    private PublicationList adapter;
//    private Dialog loadingDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //sliderBar = new SliderBar();
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        loadingDialog = DialogConstructor.dialogLoading(this);


        //load publication list
        adapter = new PublicationList(this);

        //recover list
        ListView listView = (ListView) findViewById(R.id.list_insertion);
        listView.setAdapter(adapter);

        //load
        WebClient<ListPublicationDTO> webClient = new WebClient<ListPublicationDTO>(RequestEnum.LOAD_ALL_PUBLICATION,ListPublicationDTO.class);

        webClient.setParams("businessId",Storage.getBusiness().getId()+"");
        webClient.setParams("page","0");

        Request request = new Request(this,webClient);

        request.execute();


        // to create promotion
        findViewById(R.id.b_create_promotion).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CreatePromotionActivity.class));
                finish();
            }
        });

//        // to create business notification
//        findViewById(R.id.b_create_business_notification).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, CreatePromotionActivity.class));
//                finish();
//            }
//        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        //reload data
        if (!Storage.testStorage()) {
            Intent intent = new Intent(this, WelcomeActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_logout:
                LoginManager.getInstance().logOut();
                Storage.clean(this);
                Intent intent = new Intent(this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void displayErrorMessage(String errorMessage) {
        findViewById(R.id.error_message_container).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.error_message)).setText(errorMessage);
    }

    @Override
    public void loadingAction(boolean loading) {

//        if (loading) {
//            loadingDialog.show();
//
//            // create animation and add to the refresh item
////            findViewById(R.id.error_message_container).setVisibility(View.GONE);
//        } else {
//            loadingDialog.cancel();
//        }

    }

    @Override
    public void successAction(DTO successDTO) {

        ListPublicationDTO listPublicationDTO = (ListPublicationDTO) successDTO;

        adapter.addAll(listPublicationDTO.getList());

    }
}
