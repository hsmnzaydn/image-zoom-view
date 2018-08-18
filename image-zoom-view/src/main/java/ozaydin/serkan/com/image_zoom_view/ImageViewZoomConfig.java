package ozaydin.serkan.com.image_zoom_view;

public class ImageViewZoomConfig {
    private boolean isSave=false;

    public ImageViewZoomConfig() {
    }


    public void saveProperty(boolean value){
        this.isSave=value;
    }

    public boolean getIsSave(){
        return this.isSave;
    }




}
