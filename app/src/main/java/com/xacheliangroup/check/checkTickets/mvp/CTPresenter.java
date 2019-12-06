package com.xacheliangroup.check.checkTickets.mvp;

import android.content.Context;

import com.xacheliangroup.check.checkTickets.mvp.bean.CTBackCheckBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTBackLocationBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTCarDetailBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTCarListBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTIdOrQrcodeCheckBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTPersonCheckBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTUserListBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTVersionBean;
import com.xacheliangroup.check.common.http.okhttp.BaseDisposableObserver;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.http.okhttp.RequestField;
import com.xacheliangroup.check.common.http.okhttp.RequestQuery;
import com.xacheliangroup.check.common.http.okhttp.RetrofitHelper;
import com.xacheliangroup.check.common.http.okhttp.RxPresenter;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author:yz
 * data: 2019/6/5,15:53
 */
public class CTPresenter extends RxPresenter {

    public  static final String  TO_GET_CAT_LIST="TO_GET_CAT_LIST";
    public  static final String  TO_GET_CAR_DETAIL_DATA="TO_GET_CAR_DETAIL_DATA";

    public static final String TO_CHECK_FOR_IDCARD_QRCODE="TO_CHECK_FOR_IDCARD_QRCODE";
    public static final String TO_CHECK_FOR_PERSON="TO_CHECK_FOR_PERSON";
    public static final String TO_BACK_CHECk="TO_BACK_CHECk";
    public static final String TO_GET_CHECKED_USER_LIST="TO_GET_CHECKED_USER_LIST";

    public static final String TO_GET_APP_VERSION="TO_GET_APP_VERSION";

    public static final String TO_GET_BACK_LOCATION="TO_GET_BACK_LOCATION";

    CTModel model;
    public CTPresenter(Context context, IActionListener.ViewAction view) {
        super(context, view);
        model= RetrofitHelper.createService(CTModel.class);
    }


    public void toBackLocation(String sumid,String classid,String sn,String classDate,String opertype){
        model.toBackLocation(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("sumid",sumid)
                        .withParam("classid",classid)
                        .withParam("sn",sn)
                        .withParam("classdate",classDate)
                        .withParam("opertype",opertype)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<CTBackLocationBean>() {

                    @Override
                    public void onSuccess(CTBackLocationBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_BACK_LOCATION, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_BACK_LOCATION, null);
                        }
                    }
                }));
    }
    public void toGetVersion(){
        model.toGetVersion(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("phoneXh","android")
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<CTVersionBean>() {

                    @Override
                    public void onSuccess(CTVersionBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_APP_VERSION, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_APP_VERSION, null);
                        }
                    }
                }));
    }

    /**
     * 乘客列表
     * @param sn
     * @param classid
     * @param sumid
     */
    public void toGetCheckedUserList(String sn,String classid,String sumid){
        model.toGetCheckedUserList(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("sn",sn)
                        .withParam("classid",classid)
                        .withParam("sumid",sumid)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<List<CTUserListBean>>() {

                    @Override
                    public void onSuccess(List<CTUserListBean> bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_CHECKED_USER_LIST, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_CHECKED_USER_LIST, null);
                        }
                    }
                }));
    }

    /**
     * 检票身份证号码或者条形码
     * @param sn
     * @param bcId
     * @param jpbctId
     * @param idCardNo
     * @param time
     */
    public void toCheckForIdOrQrCode(String sn,String bcId,String jpbctId,String idCardNo,
                                     String time,String lineid,String isfree,String busCode){
        model.toCheckForIdOrQrCode(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("sn",sn)
                        .withParam("sumid",jpbctId)
                        .withParam("classid",bcId)
                        .withParam("ticketBill",idCardNo)
                        .withParam("classdate",time)
                        .withParam("lineid",lineid)
                        .withParam("isfree",isfree)
                        .withParam("busCode",busCode)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<CTIdOrQrcodeCheckBean>() {

                    @Override
                    public void onSuccess(CTIdOrQrcodeCheckBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_CHECK_FOR_IDCARD_QRCODE, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_CHECK_FOR_IDCARD_QRCODE, errorMsg);
                        }
                    }
                }));
    }

    /**
     * 人工检票
     * @param sn
     * @param jpbcId
     * @param ticketId
     * @param time
     */
    public void toPersonCheck(String sn,String jpbcId,String ticketId,String time,String classid,
                              String ticketBill,String isfree,String busCode){
        model.toPersonCheck(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("sn",sn)
                        .withParam("sumid",jpbcId)
                        .withParam("ticketID",ticketId)
                        .withParam("classdate",time)
                        .withParam("classid",classid)
                        .withParam("ticketBill",ticketBill)
                        .withParam("busCode",busCode)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<CTPersonCheckBean>() {

                    @Override
                    public void onSuccess(CTPersonCheckBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_CHECK_FOR_PERSON, null);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_CHECK_FOR_PERSON, errorMsg);
                        }
                    }
                }));
    }

    /**
     * 退票
     * @param sn
     * @param jpId
     * @param time
     * @param ticketBill
     */
    public void toBackCheck(String sn,String jpId,String time,String ticketBill,String checkid
    ,String seat,String classid,String checkType,String checkBill){
        model.toBackCheck(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("sn",sn)
                        .withParam("sumid",jpId)
                        .withParam("classdate",time)
//                        .withParam("ticketBill",ticketBill)
                        .withParam("checkBill",checkBill)
                        .withParam("opertype","1")
                        .withParam("checkid",checkid)
//                        .withParam("seat",seat)
//                        .withParam("classid",classid)
//                        .withParam("checkType",checkType)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<CTBackCheckBean>() {

                    @Override
                    public void onSuccess(CTBackCheckBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_BACK_CHECk, null);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_BACK_CHECk, errorMsg);
                        }
                    }
                }));
    }
//0证件号1结算单（报班单）号2车号
    public void toGetCarList(String sn,String idCardNo,String type,String searchsource){
        model.toGetCarList(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("sn",sn)
                        .withParam("operIdCard",idCardNo)
                        .withParam("opertype",type)
                        .withParam("searchsource",searchsource)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<CTCarListBean>() {

                    @Override
                    public void onSuccess(CTCarListBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_CAT_LIST, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_CAT_LIST, null);
                        }
                    }
                }));
    }

    public void toGetCarDetail(String sn,String type,String checkId,String carId,String classdate){
        model.toGetCarDetail(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("sn",sn)
                        .withParam("opertype",type)
                        .withParam("sumid",checkId)
                        .withParam("classid",carId)
                        .withParam("classdate",classdate)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<List<CTCarDetailBean>>() {

                    @Override
                    public void onSuccess(List<CTCarDetailBean> bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_CAR_DETAIL_DATA, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_CAR_DETAIL_DATA, null);
                        }
                    }
                }));
    }
}
