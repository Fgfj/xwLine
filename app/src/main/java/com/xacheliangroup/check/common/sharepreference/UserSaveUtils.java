package com.xacheliangroup.check.common.sharepreference;

import com.xacheliangroup.check.moduleOther.mvp.bean.UserLoginBean;

/**
 * author:yz
 * data: 2019/2/26,13:20
 */
public class UserSaveUtils {

    public static void saveUserInfoForLoginSuccessToSp(UserLoginBean userLoginBean){
        AppSharePreference.getInstance().setUserId(userLoginBean.getAppuserid()+"");
        AppSharePreference.getInstance().setUserName(userLoginBean.getUsername());
        AppSharePreference.getInstance().setUserSign(userLoginBean.getSignature());
        AppSharePreference.getInstance().setUserIcon(userLoginBean.getUserpic());
    }
    public static void clearUserInfoLogoutToSp(){
        AppSharePreference.getInstance().clearAll();
    }

}
