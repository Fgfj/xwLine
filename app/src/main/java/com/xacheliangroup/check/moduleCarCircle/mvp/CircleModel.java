package com.xacheliangroup.check.moduleCarCircle.mvp;

import com.xacheliangroup.check.moduleCarCircle.mvp.bean.CarCircleItemBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * author:yz
 * data: 2018/12/18,13:56
 */
public interface CircleModel {
    /**
     * 获取车圈列表
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TZcarPost_getCBBPostList.action")
    Observable<List<CarCircleItemBean>> toGetCarCircleListData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);

}
