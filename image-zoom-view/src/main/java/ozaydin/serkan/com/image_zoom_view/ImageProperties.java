package ozaydin.serkan.com.image_zoom_view;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.UUID;

public class ImageProperties {

    public static String bitmapToBase64(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return resizeBase64Image(Base64.encodeToString(byteArray, Base64.NO_WRAP));
    }

    public static String resizeBase64Image(String base64image) {
        byte[] encodeByte = Base64.decode(base64image.getBytes(), Base64.NO_WRAP);
        BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap image = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length, options);


        if (image.getHeight() <= 400 && image.getWidth() <= 400) {
            return base64image;
        }
        image = Bitmap.createScaledBitmap(image, 400, 400, false);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, baos);

        byte[] b = baos.toByteArray();
        System.gc();
        return Base64.encodeToString(b, Base64.NO_WRAP);

    }

    public static void saveImage(Bitmap bitmap, String folderName, String filename, Bitmap.CompressFormat compressFormat, SaveFileListener saveFileListener) {
        saveImage(null, bitmap, folderName, filename, compressFormat, saveFileListener);
    }

    public static void saveImage(Context context, Bitmap bitmap, String folderName, String filename, Bitmap.CompressFormat compressFormat, SaveFileListener saveFileListener) {

        filename = filename + UUID.randomUUID();
        
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && context != null) {
            // Use MediaStore for Android 10+
            saveImageWithMediaStore(context, bitmap, folderName, filename, compressFormat, saveFileListener);
        } else {
            // Use legacy method for older versions
            saveImageLegacy(bitmap, folderName, filename, compressFormat, saveFileListener);
        }
    }

    private static void saveImageWithMediaStore(Context context, Bitmap bitmap, String folderName, String filename, Bitmap.CompressFormat compressFormat, SaveFileListener saveFileListener) {
        ContentResolver resolver = context.getContentResolver();
        ContentValues contentValues = new ContentValues();
        
        String mimeType = getMimeType(compressFormat);
        String extension = getExtension(compressFormat);
        
        contentValues.put(MediaStore.MediaColumns.DISPLAY_NAME, filename + extension);
        contentValues.put(MediaStore.MediaColumns.MIME_TYPE, mimeType);
        contentValues.put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES + File.separator + folderName);
        
        Uri imageUri = null;
        try {
            imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            if (imageUri != null) {
                OutputStream outputStream = resolver.openOutputStream(imageUri);
                if (outputStream != null) {
                    bitmap.compress(compressFormat, 90, outputStream);
                    outputStream.flush();
                    outputStream.close();
                    // Create a virtual file for callback compatibility
                    File virtualFile = new File(Environment.DIRECTORY_PICTURES + File.separator + folderName + File.separator + filename + extension);
                    saveFileListener.onSuccess(virtualFile);
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            Log.e("ImageViewZoom", exception.getMessage());
            saveFileListener.onFail(exception);
        }
    }

    @SuppressWarnings("deprecation")
    private static void saveImageLegacy(Bitmap bitmap, String folderName, String filename, Bitmap.CompressFormat compressFormat, SaveFileListener saveFileListener) {
        String extension = getExtension(compressFormat);
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + folderName);
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + folderName + File.separator + filename + extension);
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(compressFormat, 90, out);
            out.flush();
            out.close();
            saveFileListener.onSuccess(file);
        } catch (Exception exception) {
            exception.printStackTrace();
            Log.e("ImageViewZoom", exception.getMessage());
            saveFileListener.onFail(exception);

        }
    }

    private static String getMimeType(Bitmap.CompressFormat compressFormat) {
        switch (compressFormat) {
            case PNG:
                return "image/png";
            case WEBP:
            case WEBP_LOSSY:
            case WEBP_LOSSLESS:
                return "image/webp";
            case JPEG:
            default:
                return "image/jpeg";
        }
    }

    private static String getExtension(Bitmap.CompressFormat compressFormat) {
        switch (compressFormat) {
            case PNG:
                return ".png";
            case WEBP:
            case WEBP_LOSSY:
            case WEBP_LOSSLESS:
                return ".webp";
            case JPEG:
            default:
                return ".jpg";
        }
    }
}
