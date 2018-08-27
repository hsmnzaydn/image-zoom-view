package ozaydin.serkan.com.image_zoom_view;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class ImageProperties {

    public static String bitmapToBase64(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return resizeBase64Image( Base64.encodeToString(byteArray, Base64.NO_WRAP));
    }

    public static String resizeBase64Image(String base64image){
        byte [] encodeByte=Base64.decode(base64image.getBytes(),Base64.NO_WRAP);
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inPurgeable = true;
        Bitmap image = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length,options);


        if(image.getHeight() <= 400 && image.getWidth() <= 400){
            return base64image;
        }
        image = Bitmap.createScaledBitmap(image, 400, 400, false);

        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG,100, baos);

        byte [] b=baos.toByteArray();
        System.gc();
        return Base64.encodeToString(b, Base64.NO_WRAP);

    }

    public static void saveImage(Bitmap bitmap,String folderName,String filename,Bitmap.CompressFormat compressFormat,SaveFileListener saveFileListener){

        filename= filename + UUID.randomUUID();
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + folderName);
        if (!file.exists()) {
            file.mkdir();
        }
        file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + File.separator + folderName + File.separator +filename + ".jpg");
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(compressFormat, 90, out);
            out.flush();
            out.close();
            saveFileListener.onSuccess(file);
        } catch (Exception exception) {
            exception.printStackTrace();
            Log.e("ImageViewZoom",exception.getMessage());
            saveFileListener.onFail(exception);

        }
    }



}
