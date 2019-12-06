package com.xacheliangroup.check.common.log;

import com.orhanobut.logger.BuildConfig;
import com.orhanobut.logger.Logger;

/**
 * author:yz
 * data: 2018/12/14,13:12
 */
public class CbbLogUtils {
    public static void logJson(String json){
        Logger.json(json);
    }
    public static void debugLog(Object object){
        if(BuildConfig.DEBUG){
            return;
        }
        Logger.d(object);
    }
    public static void infoLog(String message,Object object){
        if(BuildConfig.DEBUG){
            return;
        }
        Logger.i(message,object);
    }
}
