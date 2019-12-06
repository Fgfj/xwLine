package com.xacheliangroup.check.common.mvp;

import com.xacheliangroup.check.common.mvp.bean.AdverBean;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * author:yz
 * data: 2018/12/14,13:42
 */
public interface UtilsModel {
    /**
     * 获取广告图
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("4S-web/TCbbAdvertising_getAdvertising.action")
    Observable<AdverBean> toGetAdvertData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);

}
