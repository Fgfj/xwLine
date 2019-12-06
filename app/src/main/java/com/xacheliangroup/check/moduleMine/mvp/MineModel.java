package com.xacheliangroup.check.moduleMine.mvp;

import com.xacheliangroup.check.moduleMine.mvp.bean.AddressItemBean;
import com.xacheliangroup.check.moduleMine.mvp.bean.OrderDetailBean;
import com.xacheliangroup.check.moduleMine.mvp.bean.OrderListItemBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * author:yz
 * data: 2018/12/19,11:34
 */
public interface MineModel {
    /**
     * 获取地址列表
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TZcarAddress_CBBgetAddressList.action")
    Observable<List<AddressItemBean>> toGetAddressListData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
    /**
     * 添加地址
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TZcarAddress_addAddress.action")
    Observable<Object> toAddAddress(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
    /**
     * 修改地址
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TZcarAddress_CBBupdateAddress.action")
    Observable<Object> toUpdateAddress(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
    /**
     * 订单列表
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TZcarOrder_getOrderList.action")
    Observable<List<OrderListItemBean>> toGetOrderListData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
    /**
     * 订单详情
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("carbb/TZcarOrder_orderDetailed.action")
    Observable<OrderDetailBean> toGetOrderDetailData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);

}
