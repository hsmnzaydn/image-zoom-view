package ozaydin.serkan.com.imagezoom;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Gallery;
import android.widget.Toast;

import java.io.File;

import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;
import ozaydin.serkan.com.image_zoom_view.ImageViewZoomConfig;
import ozaydin.serkan.com.image_zoom_view.SaveFileListener;

/**
 * Created by hsmnzaydn on 04.08.2018.
 */

public class MainActivity extends AppCompatActivity {

    ImageViewZoom imageViewZoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageViewZoom=findViewById(R.id.activity_main_image_view);
        // Return Image's base 64 code
        imageViewZoom.getAsBase64();

        // ImageViewZoomConfig
        ImageViewZoomConfig imageViewZoomConfig=new ImageViewZoomConfig();
        imageViewZoomConfig.saveProperty(true);
        imageViewZoom.setConfig(imageViewZoomConfig);


        // Save Image
        imageViewZoom.saveImage(MainActivity.this, "ImageViewZoom", "test", Bitmap.CompressFormat.JPEG, 1, new SaveFileListener() {
            @Override
            public void onSuccess(File file) {
                Toast.makeText(MainActivity.this,"Success",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFail(Exception excepti) {
                Toast.makeText(MainActivity.this,"Error Save",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
