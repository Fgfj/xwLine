package com.xacheliangroup.check.utils;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

/**
 * author:yz
 * data: 2018/12/14,10:32
 */
public class ProgressHelp {
    private static KProgressHUD mProgress;

    public static void showProgress(Context context){
        try {
            if (mProgress == null) {
                mProgress = KProgressHUD.create(context)
                        .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                        .setCancellable(true)
                        .setAnimationSpeed(2)
                        .setDimAmount(0.5f)
                        .show();
            } else {
                mProgress.show();
            }
        } catch (Exception e) {
            mProgress = KProgressHUD.create(context)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setCancellable(true)
                    .setAnimationSpeed(2)
                    .setDimAmount(0.5f)
                    .show();
        }
    }

    public static void dismissProgress(){
        if (mProgress!=null){
            mProgress.dismiss();
        }
        mProgress=null;
    }
}
