package com.xacheliangroup.check.utils;

import android.content.Context;
import android.widget.Toast;

import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.LogUtils;

/**
 * author:yz
 * data: 2018/12/14,10:40
 */
public class ToastUtils {

    private static final String TAG = ToastUtils.class.getSimpleName();

    //Toast公共方法
    private static Toast toast = null;

    public static void showToast(BaseActivity context, String message) {

        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    public static void showToast(Context context, String message) {
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }
    public static void ShowToastTest(Context context, String message){
        if (toast == null) {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    public static <T> T showToast(BaseActivity context, T what) {
        try {
            showToast(context, String.valueOf(what));
        } catch (Exception e) {
            LogUtils.e(TAG, e.toString());
        }
        return what;
    }

    public static void showToast(BaseActivity context, String message, int showLength) {

        if (toast == null) {
            toast = Toast.makeText(context, message, showLength);
        } else {
            toast.setDuration(showLength);
            toast.setText(message);
        }
        toast.show();
    }
}
