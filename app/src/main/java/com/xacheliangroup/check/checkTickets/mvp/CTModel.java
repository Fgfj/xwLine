package com.xacheliangroup.check.checkTickets.mvp;

import com.xacheliangroup.check.checkTickets.mvp.bean.CTBackCheckBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTBackLocationBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTCarDetailBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTCarListBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTIdOrQrcodeCheckBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTPersonCheckBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTUserListBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTVersionBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * author:yz
 * data: 2019/6/5,15:53
 */
public interface CTModel {
    /**
     * 获取班次列表 待发车 离位
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("PALX-TC/TPalxCheckticket_getIDCardClass.action")
    Observable<CTCarListBean> toGetCarList(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);

    /**
     * 获取班次详情
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("PALX-TC/TPalxCheckticket_getTickectCheckSum.action")
    Observable<List<CTCarDetailBean>> toGetCarDetail(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);

    /**
     * 检票接口 扫码或者身份证识别
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("PALX-TC/TPalxCheckticket_ckGetIDDetail.action")
    Observable<CTIdOrQrcodeCheckBean> toCheckForIdOrQrCode(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);


    /**
     *人工检票 混剪  检票接口没有直接检票
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("PALX-TC/TPalxCheckticket_ckCheckTicket.action")
    Observable<CTPersonCheckBean> toPersonCheck(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);


    /**
     * 退检
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("PALX-TC/TPalxCheckticket_ckUndoCheckTicket.action")
    Observable<CTBackCheckBean> toBackCheck(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);

    /**
     * 已检乘客列表
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("PALX-TC/TPalxCheckticket_ckTicketList.action")
    Observable<List<CTUserListBean>> toGetCheckedUserList(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);

    /**
     * 版本跟新
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("PALX-TC/TZcarEdition_getEdition.action")
    Observable<CTVersionBean> toGetVersion(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);




    /**
     * 离位接口
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("PALX-TC/TPalxCheckticket_setTicketCheckSum.action")
    Observable<CTBackLocationBean> toBackLocation(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);

}
