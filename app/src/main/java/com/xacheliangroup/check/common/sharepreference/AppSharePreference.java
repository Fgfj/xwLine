package com.xacheliangroup.check.common.sharepreference;


import com.xacheliangroup.check.common.base.BaseSharePreference;
import com.xacheliangroup.check.common.flag.Flag;

/**
 * Created by Afra55 on 2017/10/19.
 * Smile is the best name card.
 */

public class AppSharePreference extends BaseSharePreference {

    private static AppSharePreference instance = null;

    public static AppSharePreference getInstance() {
        if (instance == null) {
            synchronized (AppSharePreference.class) {
                instance = new AppSharePreference();
            }
        }
        return instance;
    }

    private AppSharePreference() {
        super();
    }
    public void setUserId(String userId){
        setValue(Flag.SHAREPREFERENCE.APP_USER_ID,userId);
    }
    public String getUserId(){
        return getStringValue(Flag.SHAREPREFERENCE.APP_USER_ID);
    }

    public void setTest(String Content){
        setValue(Flag.SHAREPREFERENCE.TEST,Content);
    }
    public String getTest(){
        return getStringValue(Flag.SHAREPREFERENCE.TEST);
    }

    public void setUserName(String userName){
        setValue(Flag.SHAREPREFERENCE.APP_USER_NAME,userName);
    }
    public String getUserName(){
        return getStringValue(Flag.SHAREPREFERENCE.APP_USER_NAME);
    }

    public void setUserSign(String userSign){
        setValue(Flag.SHAREPREFERENCE.APP_USER_SIGN,userSign);
    }
    public String getUserSign(){
        return getStringValue(Flag.SHAREPREFERENCE.APP_USER_SIGN);
    }

    public void setUserIcon(String userIcon){
        setValue(Flag.SHAREPREFERENCE.APP_USER_ICON,userIcon);
    }
    public String getUserIcon(){
        return getStringValue(Flag.SHAREPREFERENCE.APP_USER_ICON);
    }

    public boolean isAPKAlreadyOpen(String path) {
        return getBooleanValue("APK_ALREADY_OPED" + path, false);
    }

    public void setAPKAlreadyOpen(String path, boolean value) {
        setValue("APK_ALREADY_OPED" + path, value);
    }

    public void setAppHttpURl(String type){
        setValue(Flag.SHAREPREFERENCE.APP_HTTP_RUL,type);
    }

    public String getAppHttpURl(){
        return getStringValue(Flag.SHAREPREFERENCE.APP_HTTP_RUL);
    }
}
