package ozaydin.serkan.com.image_zoom_view;

import java.io.File;

public interface SaveFileListener {

void onSuccess(File file);
void onFail(Exception exception);

}
