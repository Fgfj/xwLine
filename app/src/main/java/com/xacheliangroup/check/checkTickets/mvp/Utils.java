package com.xacheliangroup.check.checkTickets.mvp;

import android.app.Activity;
import android.telephony.TelephonyManager;

import java.lang.reflect.Method;

import static android.content.Context.TELEPHONY_SERVICE;

/**
 * author:yz
 * data: 2019/9/23,09:42
 */
public class Utils {
    public static String getSNumber(){
        String serial = null;
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class);
            serial = (String) get.invoke(c, "ro.serialno");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return serial;

    }
    public static String getImei(Activity activity){
        TelephonyManager TelephonyMgr =(TelephonyManager)activity.getSystemService(TELEPHONY_SERVICE);//need user permission
        String imei = TelephonyMgr.getDeviceId();
        return imei;
    }
    public static String getDeviceSN(){

        String serialNumber = android.os.Build.SERIAL;

        return serialNumber;
    }
}
