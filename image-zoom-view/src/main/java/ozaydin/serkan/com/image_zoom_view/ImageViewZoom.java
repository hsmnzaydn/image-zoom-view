package ozaydin.serkan.com.image_zoom_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;



/**
 * Created by hsmnzaydn on 04.08.2018.
 */


public class ImageViewZoom extends AppCompatImageView implements View.OnClickListener {
    private Boolean isCircle = false;

    public ImageViewZoom(Context context) {
        super(context);
        setOnClickListener(this);

    }



    public ImageViewZoom(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.ImageViewZoom,
                0, 0);

        try {
            this.isCircle = a.getBoolean(R.styleable.ImageViewZoom_circle, false);
        } finally {
            a.recycle();
        }


    }

    public ImageViewZoom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOnClickListener(this);

    }


    public String getAsBase64(){
        return ImageProperties.bitmapToBase64(((BitmapDrawable)this.getDrawable()).getBitmap());
    }



    @Override
    public void onClick(View view) {
        if (getDrawable() != null) {
            new Dialog().show(((FragmentActivity) getContext()).getSupportFragmentManager(), ((BitmapDrawable) getDrawable()).getBitmap());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (isCircle) {
            drawAsCircle(canvas);

        } else {
            super.onDraw(canvas);

        }


    }


    public void drawAsCircle(Canvas canvas) {
        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }
        Bitmap b = ((BitmapDrawable) drawable).getBitmap();
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth();
        @SuppressWarnings("unused")
        int h = getHeight();

        Bitmap roundBitmap = getCroppedBitmap(bitmap, w);
        canvas.drawBitmap(roundBitmap, 0, 0, null);
    }


    public Bitmap getCroppedBitmap(Bitmap bmp, int radius) {
        Bitmap sbmp;

        if (bmp.getWidth() != radius || bmp.getHeight() != radius) {
            float smallest = Math.min(bmp.getWidth(), bmp.getHeight());
            float factor = smallest / radius;
            sbmp = Bitmap.createScaledBitmap(bmp,
                    (int) (bmp.getWidth() / factor),
                    (int) (bmp.getHeight() / factor), false);
        } else {
            sbmp = bmp;
        }

        Bitmap output = Bitmap.createBitmap(radius, radius, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);


        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, radius, radius);

        paint.setAntiAlias(true);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawCircle(radius / 2 + 0.7f, radius / 2 + 0.7f,
                radius / 2 + 0.1f, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(sbmp, rect, rect, paint);

        return output;
    }


    public Boolean getCircle() {
        return isCircle;
    }

    public void setCircle(Boolean circle) {
        isCircle = circle;
    }

}
