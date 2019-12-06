package com.xacheliangroup.check.moduleNetLine.mvp;

import com.xacheliangroup.check.moduleNetLine.mvp.bean.CarStatusBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.ChangeCarTypeBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.CheckTicketBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.LoginBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.NetLineCarListBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.NetLineListBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.NetLinePointListBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.NetLineUserListBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.PostCarBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.TicketBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * author:yz
 * data: 2019/9/2,16:43
 */
public interface NetLineModel {
    /**
     * 登录
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("intfaceJson/PalxSldriverJson/land")
    Observable<LoginBean> toGetAddressListData(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);

    /**
     * 绑定车辆
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("intfaceJson/PalxSlcarJson/bindingCar")
    Observable<LoginBean.CarsBean> toBindCar(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
    /**
     * 获取用户列表
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("intfaceJson/PalxSltransrecordJson/intransit")
    Observable<NetLineUserListBean> toGetUserList(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
    /**
     * 获取坐标点集合
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("intfaceJson/PalxSlorderJson/findfareLoaction")
    Observable<NetLinePointListBean> toGetPointList(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
    /**
     * 扫描二维码获取信息
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("intfaceJson/PalxSlticketJson/ticketdetails")
    Observable<TicketBean> toTicketInfo(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);


    /**
     * 检票上车
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("intfaceJson/PalxSlticketJson/writeoff")
    Observable<CheckTicketBean> toCheck(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);

    /**
     * 线路查询
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("intfaceJson/PalxSllineJson/findAllLine")
    Observable<List<NetLineCarListBean>> toSelectLineList(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);


    /**
     * 报班
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("intfaceJson/PalxSltransrecordJson/newspaper")
    Observable<PostCarBean> toPostCar(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);

    /**
     * 出发送达
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("intfaceJson/PalxSltransrecordJson/updatetransrecord")
    Observable<CarStatusBean> toGoAndSendOver(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);


    /**
     * 司机报修，休息，退出登录接口
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("intfaceJson/PalxSlcarJson/updateCarStauts")
    Observable<ChangeCarTypeBean> toChangeCarType(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);



    /**
     * 记录查询
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("intfaceJson/PalxSltransrecordJson/list")
    Observable<List<NetLineListBean>> toSelectHistory(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);

    /**
     * 上车
     * @param queryMap
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("intfaceJson/PalxSlorderJson/DriverArrive")
    Observable<Object> toUpCar(@QueryMap Map<String, Object> queryMap, @FieldMap Map<String, String> map);
}
