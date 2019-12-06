package com.xacheliangroup.check.moduleOther.mvp;

import com.xacheliangroup.check.moduleOther.mvp.bean.UserLoginBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * author:yz
 * data: 2018/12/24,16:56
 */
public interface OtherModel {
    /**
     * 获取验证码
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TZcarAppuser_CBBgetCode.action")
    Observable<Object> toGetPhoneCodeData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
    /**
     * 登录
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TZcarAppuser_CBBLogin.action")
    Observable<UserLoginBean> toLoginData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
    /**
     * 退出登录
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TZcarAppuser_appIntroduction.action")
    Observable<Object> toLogout(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
}
