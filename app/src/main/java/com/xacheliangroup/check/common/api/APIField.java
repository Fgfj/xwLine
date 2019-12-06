package com.xacheliangroup.check.common.api;


import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.sharepreference.AppSharePreference;


/**
 * Created by yangshuai in the 9:47 of 2016.01.07 .
 */
public class APIField {

    private static APIField instance;


    public static APIField getInstance() {
        if (instance == null) {
            instance = new APIField();
        }
        return instance;
    }

    public APIField() {
        String type = getMetaData(AppCache.getContext(), "APP_ENV");
        switch (type) {
            case "UAT":
                useUAT();
                break;
            case "DEVELOP":
                useDEV();
                break;
            case "RELEASE":
                usePro();
                break;
            default:
                useUAT();
                break;
        }
    }


    public void updateUrl(){
        String type = getMetaData(AppCache.getContext(), "APP_ENV");
        switch (type) {
            case "UAT":
                useUAT();
                break;
            case "DEVELOP":
                useDEV();
                break;
            case "RELEASE":
                usePro();
                break;
            default:
                useUAT();
                break;
        }
    }
    private String APP_SERVICE_URL="";

    private String getAppUrl() {
        return APP_SERVICE_URL;
    }

    public static String getAppServiceUrl() {

        return getInstance().getAppUrl();
    }

    /**
     * UAT环境
     */
    private void useUAT() {
        APP_SERVICE_URL = AppCache.getContext().getResources().getStringArray(R.array.UAT)[0];
    }

    /**
     * 开发环境
     */
    private void useDEV() {
        APP_SERVICE_URL = AppCache.getContext().getResources().getStringArray(R.array.DEV)[0];
    }


    /**
     * 产线环境
     */
    private void usePro() {

        switch (AppSharePreference.getInstance().getAppHttpURl()){
            case "0":
                APP_SERVICE_URL = AppCache.getContext().getResources().getStringArray(R.array.CX)[0];
                break;
            case "1":
                APP_SERVICE_URL = AppCache.getContext().getResources().getStringArray(R.array.FZC)[0];
                break;
            case "2" :
                APP_SERVICE_URL = AppCache.getContext().getResources().getStringArray(R.array.SFW)[0];
                break;
            case "3":
                APP_SERVICE_URL = AppCache.getContext().getResources().getStringArray(R.array.YL)[0];
                break;
            default:
                APP_SERVICE_URL = AppCache.getContext().getResources().getStringArray(R.array.RELEASE)[0];
                break;
        }

    }

    public static String getCurrentPhotoUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        } else if (url.startsWith("http")) {
            return url;
        } else {
            String appServiceUrl = getAppServiceUrl();
            if (!appServiceUrl.endsWith("/") && !url.startsWith("/")) {
                url = "/" + url;
            } else if (appServiceUrl.endsWith("/") && url.startsWith("/") && url.length() > 1) {
                url = url.substring(1);
            }
            return appServiceUrl + url;
        }
    }

    public static String getUploadPhotoUrl(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        } else if (url.startsWith(getAppServiceUrl())) {
            url = url.replaceFirst(getAppServiceUrl(), "");
            if (!url.startsWith("/")) {
                url = "/" + url;
            }
            return url;
        } else {
            return url;
        }
    }

    public static String getPhotoFullName(String url) {
        if (TextUtils.isEmpty(url)) {
            return "temp.jpg";
        }
        if (url.contains("/")) {
            String name = url.substring(url.lastIndexOf("/"));
            if (!name.contains(".")) {
                name += ".jpg";
            }
            return name;
        }
        return "temp.jpg";
    }

    public static String getMetaData(Context context, String name) {
        try {
            ApplicationInfo appInfo = context.getPackageManager()
                    .getApplicationInfo(context.getPackageName(),
                            PackageManager.GET_META_DATA);

            return appInfo.metaData.getString(name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }
}
