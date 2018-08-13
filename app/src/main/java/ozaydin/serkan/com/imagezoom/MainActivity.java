package ozaydin.serkan.com.imagezoom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ozaydin.serkan.com.image_zoom_view.ImageViewZoom;

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
        imageViewZoom.getAsBase64();
    }
}
