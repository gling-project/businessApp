package be.gling.businessApp.view.widget.customer;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by florian on 14/10/15.
 */
public class GlingIcon extends TextView {


    private static Typeface typeface = null;

    public GlingIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        setCustomFont();
    }

    public GlingIcon(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setCustomFont();
    }

    private void setCustomFont() {

        if (typeface == null) {
            typeface = Typeface.createFromAsset(getContext().getApplicationContext().getAssets(), "fonts/glingicon.ttf");
        }
        this.setTypeface(typeface);
    }
}

