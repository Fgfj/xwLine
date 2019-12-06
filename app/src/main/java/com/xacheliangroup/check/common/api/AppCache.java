package com.xacheliangroup.check.common.api;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.xacheliangroup.check.common.sharepreference.AppSharePreference;

/**
 * author:yz
 * data: 2018/12/14,10:23
 */
public class AppCache {
    private static Context context;
    private static boolean isDebugMode;


    public static Context getContext() {
        return context;
    }

    public static void setContext(Context context) {
        AppCache.context = context;
    }

    public static boolean isDebugMode() {
        return isDebugMode;
    }

    public static void setIsDebugMode(boolean isDebugMode) {
        AppCache.isDebugMode = isDebugMode;
    }

    public static String getToken() {
        return "";
    }

    public static String getUserId() {
        return AppSharePreference.getInstance().getUserId();
    }

    public static String getString(@StringRes int res) {
        if (context != null) {
            context.getString(res);
        }
        return "";
    }

    public static boolean hasLogged() {
        return !TextUtils.isEmpty(getUserId()) && !getUserId().equals("0");
    }

    public static @ColorInt
    int getColor(@ColorRes int colorRes) {
        if (context != null) {
            return context.getResources().getColor(colorRes);
        }
        return Color.TRANSPARENT;
    }
}
