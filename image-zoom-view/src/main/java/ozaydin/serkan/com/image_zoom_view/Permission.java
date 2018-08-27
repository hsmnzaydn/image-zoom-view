package ozaydin.serkan.com.image_zoom_view;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import static android.support.v4.app.ActivityCompat.requestPermissions;

public class Permission {

    public static boolean askPermissionForActivity(Activity activity, String permission, int requestCode){
        List<String> permissionList = new ArrayList<>();
        int permissionId = ContextCompat.checkSelfPermission(activity.getApplicationContext(), permission);
        if (permissionId != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(permission);
            requestPermissions(activity, permissionList.toArray(new String[permissionList.size()]), requestCode);
        }else {
            return true;
        }
        return false;
    }

    public static boolean askPermissionForFragment(Activity activity,Fragment fragment, String permission, int requestCode){
        int permissionId = ContextCompat.checkSelfPermission(activity.getApplicationContext(), permission);
        if (permissionId != PackageManager.PERMISSION_GRANTED) {
            fragment.requestPermissions(new String[]{permission},requestCode);
        }else {
            return true;
        }
        return false;
    }

}
