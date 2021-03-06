package com.yooiistudios.common.permission;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.view.View;

import com.sulga.yooiitable.R;


/**
 * Created by Wooseong Kim in MorningKit from Yooii Studios Co., LTD. on 2015. 12. 14.
 *
 * PermissionUtils
 *  안드로이드 6.0 이상에서 권한을 요청하는 것을 도와주는 유틸 클래스
 */
public class PermissionUtils {
    private PermissionUtils() { throw new AssertionError("Must not create this class!"); }

    public static boolean hasPermission(Context context, @NonNull String permission) {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void requestPermission(@NonNull final Activity activity, @NonNull View view,
                                         @NonNull final String permission, int resId, final int requestCode) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
            Snackbar.make(view, resId, Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
                }
            }).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static void requestPermissions(@NonNull final Activity activity,
                                          @NonNull final String[] permissions, final int requestCode) {
        ActivityCompat.requestPermissions(activity, permissions, requestCode);
    }

    public static boolean isPermissionGranted(@NonNull int[] grantResults) {
        if (grantResults.length == 2) {
            return grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                    grantResults[1] == PackageManager.PERMISSION_GRANTED;
        } else {
            return grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
    }
}
