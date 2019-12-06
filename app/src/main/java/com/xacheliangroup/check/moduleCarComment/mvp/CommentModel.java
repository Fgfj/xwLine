package com.xacheliangroup.check.moduleCarComment.mvp;

import com.xacheliangroup.check.moduleCarComment.mvp.bean.CarCommentBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * author:yz
 * data: 2018/12/18,13:57
 */
public interface CommentModel {
    /**
     * 获取车评主页列表
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TCbbReview_getReviews.action")
    Observable<List<CarCommentBean>> toGetCarCommentListData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);

}
