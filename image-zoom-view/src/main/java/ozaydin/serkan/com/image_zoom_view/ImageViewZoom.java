package ozaydin.serkan.com.image_zoom_view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hsmnzaydn on 04.08.2018.
 */


public class ImageViewZoom extends AppCompatImageView implements View.OnClickListener {

    public ImageViewZoom(Context context) {
        super(context);
        setOnClickListener(this);
    }

    public ImageViewZoom(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    public ImageViewZoom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (getDrawable() != null) {
            new Dialog().show(((FragmentActivity) getContext()).getSupportFragmentManager(),((BitmapDrawable) getDrawable()).getBitmap());
        }}
}
