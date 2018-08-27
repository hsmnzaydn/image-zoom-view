package ozaydin.serkan.com.image_zoom_view;

import android.graphics.Bitmap;

public class ImageSaveProperties {
    private String folderName;
    private String fileName;
    private Bitmap.CompressFormat compressFormat;
    private int permissionRequestCode;
    private SaveFileListener saveFileListener;

    public ImageSaveProperties(String folderName, String fileName, Bitmap.CompressFormat compressFormat, int permissionRequestCode, SaveFileListener saveFileListener) {
        this.folderName = folderName;
        this.fileName = fileName;
        this.compressFormat = compressFormat;
        this.permissionRequestCode = permissionRequestCode;
        this.saveFileListener = saveFileListener;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Bitmap.CompressFormat getCompressFormat() {
        return compressFormat;
    }

    public void setCompressFormat(Bitmap.CompressFormat compressFormat) {
        this.compressFormat = compressFormat;
    }

    public int getPermissionRequestCode() {
        return permissionRequestCode;
    }

    public void setPermissionRequestCode(int permissionRequestCode) {
        this.permissionRequestCode = permissionRequestCode;
    }

    public SaveFileListener getSaveFileListener() {
        return saveFileListener;
    }

    public void setSaveFileListener(SaveFileListener saveFileListener) {
        this.saveFileListener = saveFileListener;
    }
}
