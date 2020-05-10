package ozaydin.serkan.com.image_zoom_view;

import java.util.stream.Stream;

public class ImageViewZoomConfig {
    private boolean isSave=false;
    private boolean isTakePhoto=false;
    private boolean isGetImageFromGalery=false;
    private ImageViewZoomConfigSaveMethod imageViewZoomConfigSaveMethod=ImageViewZoomConfigSaveMethod.onlyOnDialog;
    public ImageViewZoomConfig(Builder builder) {
        this.isSave = builder.isSave;
        this.isTakePhoto = builder.isTakePhoto;
        this.isGetImageFromGalery = builder.isGetImageFromGalery;
        this.imageViewZoomConfigSaveMethod = builder.imageViewZoomConfigSaveMethod;
    }

    public ImageViewZoomConfigSaveMethod getImageViewZoomConfigSaveMethod() {
        return imageViewZoomConfigSaveMethod;
    }

    public void setImageViewZoomConfigSaveMethod(ImageViewZoomConfigSaveMethod imageViewZoomConfigSaveMethod) {
        this.imageViewZoomConfigSaveMethod = imageViewZoomConfigSaveMethod;
    }

    public void saveProperty(boolean value){
        this.isSave=value;
    }

    public boolean getIsSave(){
        return this.isSave;
    }

    public void takePhoto(boolean value){
        this.isTakePhoto=value;
    }

    public boolean getTakePhoto(){
        return this.isTakePhoto;
    }

    public boolean isGetImageFromGalery() {
        return isGetImageFromGalery;
    }

    public void setGetImageFromGalery(boolean value) {
        isGetImageFromGalery = value;
    }

    public enum ImageViewZoomConfigSaveMethod{
        onlyOnDialog(0),
        always(1);

        private int type;
        ImageViewZoomConfigSaveMethod(int type) {
            this.type = type;
        }
        public int getType() {
            return type;
        }
    }


    public static class Builder{

        private boolean isSave=false;
        private boolean isTakePhoto=false;
        private boolean isGetImageFromGalery=false;
        private ImageViewZoomConfigSaveMethod imageViewZoomConfigSaveMethod=ImageViewZoomConfigSaveMethod.onlyOnDialog;

        public Builder(){ }

        public Builder saveProperty(boolean isSave){
            this.isSave = isSave;
            return this;
        }

        public Builder takePhoto(boolean isTakePhoto){
            this.isTakePhoto = isTakePhoto;
            return this;
        }

        public Builder isGetImageFromGalery(boolean isGetImageFromGalery){
            this.isGetImageFromGalery = isGetImageFromGalery;
            return this;
        }

        public Builder saveMethod(ImageViewZoomConfigSaveMethod saveConfig){
            this.imageViewZoomConfigSaveMethod = saveConfig;
            return this;
        }

        public ImageViewZoomConfig build(){
            return new ImageViewZoomConfig(this);
        }

    }
}
