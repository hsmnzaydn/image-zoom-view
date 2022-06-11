package ozaydin.serkan.com.image_zoom_view;

import static androidx.core.app.ActivityCompat.requestPermissions;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class Permission {

    public static boolean askPermissionForActivity(Activity activity, String permission, int requestCode) {
        List<String> permissionList = new ArrayList<>();
        int permissionId = ContextCompat.checkSelfPermission(activity.getApplicationContext(), permission);
        if (permissionId != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(permission);
            requestPermissions(activity, permissionList.toArray(new String[0]), requestCode);
        } else {
            return true;
        }
        return false;
    }

    public static boolean askPermissionForFragment(Activity activity, Fragment fragment, String permission, int requestCode) {
        int permissionId = ContextCompat.checkSelfPermission(activity.getApplicationContext(), permission);
        if (permissionId != PackageManager.PERMISSION_GRANTED) {
            fragment.requestPermissions(new String[]{permission}, requestCode);
        } else {
            return true;
        }
        return false;
    }

}
