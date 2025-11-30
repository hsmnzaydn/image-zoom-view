package ozaydin.serkan.com.image_zoom_view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;

import androidx.fragment.app.FragmentActivity;
import androidx.appcompat.widget.AppCompatImageView;

import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Objects;


/**
 * Created by hsmnzaydn on 04.08.2018.
 */


public class ImageViewZoom extends AppCompatImageView implements View.OnClickListener {
    private Boolean isCircle = false;
    private ImageViewZoomConfig imageViewZoomConfig;
    private ImageSaveProperties imageSaveProperties;

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

    /**
     * @return ImageViewZoom's base64
     */
    public String getBase64() {
        Bitmap bitmap = getBitmap();
        if (bitmap == null) {
            return null;
        }
        return ImageProperties.bitmapToBase64(bitmap);
    }

    /**
     * Return bitmap of ImageViewZoom
     *
     * @return Bitmap representation of the current drawable
     */
    public Bitmap getBitmap() {
        Drawable drawable = this.getDrawable();
        if (drawable == null) {
            return null;
        }
        return drawableToBitmap(drawable);
    }

    /**
     * Save image
     *
     * @param activity
     * @param folderName            Folder name to save
     * @param fileName              By what name to save
     * @param compressFormat        Compress Format
     * @param permissionRequestCode Runtime permission code
     * @param saveFileListener
     */
    public void saveImage(Activity activity, String folderName, String fileName, Bitmap.CompressFormat compressFormat, int permissionRequestCode, ImageViewZoomConfig imageViewZoomConfig, SaveFileListener saveFileListener) {


        if (imageViewZoomConfig.getImageViewZoomConfigSaveMethod() == null) {
            Log.e("ImageViewZoom", "Please set ImageViewZoomConfig save method\n\n");
            throw new RuntimeException();
        } else {
            if (imageViewZoomConfig.getImageViewZoomConfigSaveMethod() == ImageViewZoomConfig.ImageViewZoomConfigSaveMethod.always) {
                // On Android 10+ we don't need WRITE_EXTERNAL_STORAGE permission for MediaStore
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    ImageProperties.saveImage(activity, getBitmap(), folderName, fileName, compressFormat, saveFileListener);
                    imageSaveProperties = new ImageSaveProperties(folderName, fileName, compressFormat, permissionRequestCode, saveFileListener);
                } else if (Permission.askPermissionForActivity(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE, permissionRequestCode)) {
                    ImageProperties.saveImage(activity, getBitmap(), folderName, fileName, compressFormat, saveFileListener);
                    imageSaveProperties = new ImageSaveProperties(folderName, fileName, compressFormat, permissionRequestCode, saveFileListener);
                }
            } else {
                imageSaveProperties = new ImageSaveProperties(folderName, fileName, compressFormat, permissionRequestCode, saveFileListener);
            }
        }
    }

    /**
     * Set imageViewZoom configuration
     *
     * @param config ImageViewZoomConfig object
     */
    public void setConfig(ImageViewZoomConfig config) {
        this.imageViewZoomConfig = config;
    }


    /**
     * When click ImageViewZoom
     *
     * @param view
     */
    @Override
    public void onClick(View view) {
        if (getDrawable() != null) {
            if (imageSaveProperties != null) {
                new Dialog().show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), getBitmap(), this.imageViewZoomConfig, imageSaveProperties);
            } else {
                new Dialog().show(Objects.requireNonNull(getActivity()).getSupportFragmentManager(), getBitmap(), this.imageViewZoomConfig);
            }
        }
    }

    /**
     * Return the activity which might be needed as context object but is not given by View#getContext
     * as with support library <23.0.0 it might just be a wrapper.
     * <p>
     * Solution from https://stackoverflow.com/a/38814443/8524651
     *
     * @return the correct activity context
     */
    private FragmentActivity getActivity() {
        Context context = getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof FragmentActivity) {
                return (FragmentActivity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (isCircle) {
            drawAsCircle(canvas);

        } else {
            super.onDraw(canvas);

        }

    }

    /**
     * Draws ImageViewZoom on view
     *
     * @param canvas
     */
    public void drawAsCircle(Canvas canvas) {
        Drawable drawable = getDrawable();

        if (drawable == null) {
            return;
        }

        if (getWidth() == 0 || getHeight() == 0) {
            return;
        }

        Bitmap b = drawableToBitmap(drawable);
        if (b == null) {
            return;
        }
        Bitmap bitmap = b.copy(Bitmap.Config.ARGB_8888, true);

        int w = getWidth();
        @SuppressWarnings("unused")
        int h = getHeight();

        Bitmap roundBitmap = getCroppedBitmap(bitmap, w);
        canvas.drawBitmap(roundBitmap, 0, 0, null);
    }

    /**
     * Converts any Drawable to a Bitmap
     *
     * @param drawable The drawable to convert
     * @return Bitmap representation of the drawable
     */
    private Bitmap drawableToBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();

        if (width <= 0 || height <= 0) {
            width = getWidth();
            height = getHeight();
        }

        if (width <= 0 || height <= 0) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas bitmapCanvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, bitmapCanvas.getWidth(), bitmapCanvas.getHeight());
        drawable.draw(bitmapCanvas);
        return bitmap;
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
