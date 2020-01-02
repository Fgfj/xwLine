package com.xacheliangroup.check.checkTickets.download;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;

import java.io.File;

/**
 * author:yz
 * data: 2019/6/10,14:59
 */
public class InstallUtil {
    private static final String TAG = "InstallUtil";

    private static int versionCode;

    private static String versionName;
    private static Uri uri;

    /**
     * 是否已安装app
     *
     * @param context
     * @param packageName
     * @return
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            if (TextUtils.isEmpty(packageName))
                return false;
            return context.getPackageManager().getPackageInfo(packageName, PackageManager.GET_ACTIVITIES) != null;
        } catch (PackageManager.NameNotFoundException localNameNotFoundException) {
            return false;
        }
    }


    /**
     * 某个app的版本号，未安装时返回null
     */
    public static final String getVersionName(Context context, String packageName) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(packageName, 0);
            if (pi != null) {
                return pi.versionName;
            } else {
                return null;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    public static final int getVersionCode(Context context) {
        if (versionCode == 0) {
            loadVersionInfo(context);
        }

        return versionCode;
    }


    public static final String getVersionName(Context context) {
        if (TextUtils.isEmpty(versionName)) {
            loadVersionInfo(context);
        }

        return versionName;
    }

    private static final void loadVersionInfo(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            if (pi != null) {
                versionCode = pi.versionCode;
                versionName = pi.versionName;
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 安装apk文件
     */
    public static void installApk(Context context, String filepath) {
        context.startActivity(getInstallApkIntent(filepath));
    }

    /**
     * 安装apk文件
     */
    public static Intent getInstallApkIntent(String filepath) {
//          Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        File file = new File(filepath);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
//            //android7.0 文件路径适配
//            ContentValues contentValues = new ContentValues(1);
//            contentValues.put(MediaStore.Images.Media.DATA, filepath);
//            uri = AppCache.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
//        } else {
//            uri = Uri.fromFile(file);
//        }
//        intent.setDataAndType(uri, "application/vnd.android.package-archive");







        Intent intent = new Intent();
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        File file = new File(filepath);

        /**
         * pda不能加判断
         */

//        if (Build.VERSION.SDK_INT >= 24) {
//            ContentValues contentValues = new ContentValues(1);
//           contentValues.put(MediaStore.Images.Media.DATA, filepath);
//            Uri apkUri = AppCache.getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//            intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
//        } else {
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
//        }
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        return intent;
    }

    /**
     * 卸载指定包名的App
     *
     * @param context     上下文
     * @param packageName 包名
     */
    public void uninstallApp(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
