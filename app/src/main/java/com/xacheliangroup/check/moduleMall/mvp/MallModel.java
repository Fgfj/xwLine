package com.xacheliangroup.check.moduleMall.mvp;

import com.xacheliangroup.check.moduleMall.mvp.bean.GoodsDetailBean;
import com.xacheliangroup.check.moduleMall.mvp.bean.GoodsItemBean;
import com.xacheliangroup.check.moduleMall.mvp.bean.MallHeadBean;
import com.xacheliangroup.check.moduleMall.mvp.bean.SellerDetailBean;
import com.xacheliangroup.check.moduleMall.mvp.bean.SellerItemBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * author:yz
 * data: 2018/12/14,14:47
 */
public interface MallModel {
    /**
     * 获取商城首页数据
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TCbbSuperrecommend_getIndexTopDate.action")
    Observable<MallHeadBean> toGetMallData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
    /**
     * 获取商城首页商家列表数据
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TZcarStorefront_getSFrontList.action")
    Observable<List<SellerItemBean>> toGetSellerListData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
    /**
     * 获取商家详情数据
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TZcarStorefront_getStoreFrontDetail.action")
    Observable<SellerDetailBean> toGetSellerDetailData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
    /**
     * 营销列表获取商家列表
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TZcarStorefront_getFrontListByGameId.action")
    Observable<List<SellerItemBean>> toGetMarketingSellerListData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
    /**
     * 营销列表获取商品列表
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TZcarFourmdse_getMdseListByTypeId.action")
    Observable<List<GoodsItemBean>> toGetMarketingGoodsListData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
    /**
     * 营销列表获取商品列表
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TZcarFourmdse_getMdseDetail.action")
    Observable<GoodsDetailBean> toGetGoodsDetailData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);

}
