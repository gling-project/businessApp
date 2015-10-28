package be.gling.businessApp.view.listAdapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;

import be.gling.businessApp.R;
import be.gling.businessApp.model.PublicationTypeEnum;
import be.gling.businessApp.model.dto.AbstractPublicationDTO;
import be.gling.businessApp.model.dto.PromotionDTO;
import be.gling.businessApp.model.util.Storage;
import be.gling.businessApp.model.util.StringUtil;
import be.gling.businessApp.model.util.externalRequest.ImageLoader;
import be.gling.businessApp.view.widget.customer.GlingIcon;

/**
 * Created by florian on 18/02/15.
 */
public class PublicationList extends ArrayAdapter<AbstractPublicationDTO> {

    private Context context;


    public PublicationList(Context context) {
        super(context, R.layout.list_element_publication);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //load image


        AbstractPublicationDTO publication = getItem(position);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.list_element_publication, parent, false);
//
        //set text
        new ImageLoader(((ImageView) convertView.findViewById(R.id.business_illustration)), Storage.getBusiness().getIllustration());

        //business name
        ((TextView) convertView.findViewById(R.id.business_title)).setText(Storage.getBusiness().getName());

        //date and distance TODO format date
        ((TextView) convertView.findViewById(R.id.business_address)).setText("Publié le " + format.format(publication.getStartDate()) + " - " + "xx km");

        //titre
        ((TextView) convertView.findViewById(R.id.publication_title)).setText(publication.getTitle());

        //interest
        if (publication.getInterest() != null) {
            String iconName = "gling_icon_" + publication.getInterest().getName().replace("-", "_");
            int iconRef = getStringIdentifier(context, iconName);
            ((GlingIcon) convertView.findViewById(R.id.publication_interest)).setText(iconRef);
        } else {
            convertView.findViewById(R.id.publication_interest).setVisibility(View.GONE);
        }

        //promotion data
        if (publication.getType().equals(PublicationTypeEnum.PROMOTION)) {
            ((TextView) convertView.findViewById(R.id.publication_promotion_date)).setText("> " + format.format(publication.getEndDate()));
            ((TextView) convertView.findViewById(R.id.publication_promotion_percent)).setText("- " + StringUtil.toDouble(((PromotionDTO) publication).getOffPercent() * 100, false) + " %");
            if (((PromotionDTO) publication).getOriginalPrice() != null) {
                double v = ((PromotionDTO) publication).getOriginalPrice() * (1.0 - ((PromotionDTO) publication).getOffPercent());
                ((TextView) convertView.findViewById(R.id.publication_promotion_new_price)).setText(StringUtil.toDouble(v, true) + "€");
                TextView oldPrice = (TextView) convertView.findViewById(R.id.publication_promotion_old_price);
                oldPrice.setText(StringUtil.toDouble(((PromotionDTO) publication).getOriginalPrice(), true) + "€");
                //srike text
                oldPrice.setPaintFlags(oldPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            } else {
                convertView.findViewById(R.id.publication_promotion_price).setVisibility(View.GONE);
            }
        } else {
            convertView.findViewById(R.id.promotion_data).setVisibility(View.GONE);
        }

        //description
        if (publication.getDescription() != null) {
            ((TextView) convertView.findViewById(R.id.publication_description)).setText(publication.getDescription());
        } else {
            convertView.findViewById(R.id.publication_description).setVisibility(View.GONE);
        }

        //picture
        if (publication.getPictures().size() > 0) {
            new ImageLoader((ImageView) convertView.findViewById(R.id.publication_illustration), publication.getPictures().get(0));
            if(publication.getPictures().size() > 1) {
                ((TextView)convertView.findViewById(R.id.publication_illustration_more_picture)).setText("+"+(publication.getPictures().size()-1));
            }
            else{
                convertView.findViewById(R.id.publication_illustration_more_picture_box).setVisibility(View.GONE);
            }
        } else {
            convertView.findViewById(R.id.publication_illustration_box).setVisibility(View.GONE);
        }


        return convertView;
    }

    public static int getStringIdentifier(Context context, String name) {
        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }
}
