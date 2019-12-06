package com.xacheliangroup.check.moduleNetLine.mvp;

import android.content.Context;

import com.xacheliangroup.check.common.http.okhttp.BaseDisposableObserver;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.http.okhttp.RequestField;
import com.xacheliangroup.check.common.http.okhttp.RequestQuery;
import com.xacheliangroup.check.common.http.okhttp.RetrofitHelper;
import com.xacheliangroup.check.common.http.okhttp.RxPresenter;
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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author:yz
 * data: 2019/9/2,16:43
 */
public class NetLinePresenter extends RxPresenter {

    NetLineModel model;
    public static final String TO_GET_PHONE_NUMBER="TO_GET_PHONE_NUMBER";

    public static final String TO_BIND_CAR="TO_BIND_CAR";

    public static final String TO_GET_USER_LIST="TO_GET_USER_LIST";

    public static final String TO_GET_POINT_LIST="TO_GET_POINT_LIST";

    public static final String TO_GET_TICKET_INFO="TO_GET_TICKET_INFO";

    public static final String TO_CHECK="TO_CHECK";

    public static final String TO_GET_CAR_LINE_LIST="TO_GET_CAR_LINE_LIST";

    public static final String TO_POST_CAR="TO_POST_CAR";

    public static final String TO_GO_ADN_SEND_OVER="TO_GO_ADN_SEND_OVER";

    public static final String TO_CHANGE_CAR_TYPE="TO_CHANGE_CAR_TYPE";

    public static final String TO_SELECT_HISTORY="TO_SELECT_HISTORY";

    public static final String TO_UP_CAR="TO_UP_CAR";
    public NetLinePresenter(Context context, IActionListener.ViewAction view) {
        super(context, view);
        model= RetrofitHelper.createService(NetLineModel.class);
    }
    public void toLogin(String phoneNumber,String passWord){
        model.toGetAddressListData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("mobile", phoneNumber)
                        .withParam("passwd", passWord)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<LoginBean>() {

                    @Override
                    public void onSuccess(LoginBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_PHONE_NUMBER, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_PHONE_NUMBER, null);
                        }
                    }
                }));
    }
    public void toBindCar(String carNumber,int dId){
        model.toBindCar(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("carnumber", carNumber)
                        .withParam("driverid", dId)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<LoginBean.CarsBean>() {

                    @Override
                    public void onSuccess(LoginBean.CarsBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_BIND_CAR, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_BIND_CAR, null);
                        }
                    }
                }));
    }

    public void toGetUserList(int carid,int driverid){
        model.toGetUserList(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("carid", carid)
                        .withParam("driverid", driverid)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<NetLineUserListBean>() {

                    @Override
                    public void onSuccess(NetLineUserListBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_USER_LIST, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_USER_LIST, null);
                        }
                    }
                }));
    }
    public void toGetPointList(String rid){
        model.toGetPointList(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("recordid", rid)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<NetLinePointListBean>() {

                    @Override
                    public void onSuccess(NetLinePointListBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_POINT_LIST, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_POINT_LIST, null);
                        }
                    }
                }));
    }
    public void toTicketInfo(String code){
        model.toTicketInfo(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("ticketno", code)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<TicketBean>() {

                    @Override
                    public void onSuccess(TicketBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_TICKET_INFO, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_TICKET_INFO, null);
                        }
                    }
                }));
    }

    public void toCheck(String ticketid){
        model.toCheck(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("ticketid", ticketid)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<CheckTicketBean>() {

                    @Override
                    public void onSuccess(CheckTicketBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_CHECK, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_CHECK, null);
                        }
                    }
                }));
    }
    public void toSelectLineList(){
        model.toSelectLineList(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<List<NetLineCarListBean>>() {

                    @Override
                    public void onSuccess(List<NetLineCarListBean> bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_CAR_LINE_LIST, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_CAR_LINE_LIST, null);
                        }
                    }
                }));
    }
    public void toPostCar(int cId,int dId,int lineId){
        model.toPostCar(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("carid", cId)
                        .withParam("driverid", dId)
                        .withParam("lineid", lineId)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<PostCarBean>() {

                    @Override
                    public void onSuccess(PostCarBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_POST_CAR, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_POST_CAR, null);
                        }
                    }
                }));
    }
    public void toGoAndSendOver(int rId,String status,int cId){
        model.toGoAndSendOver(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("recordid", rId)
                        .withParam("status", status)
                        .withParam("carid", cId)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<CarStatusBean>() {

                    @Override
                    public void onSuccess(CarStatusBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GO_ADN_SEND_OVER, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GO_ADN_SEND_OVER, null);
                        }
                    }
                }));
    }
    public void toChangeCarType(int  cId,String type){
        model.toChangeCarType(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("carid", cId)
                        .withParam("status", type)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<ChangeCarTypeBean>() {

                    @Override
                    public void onSuccess(ChangeCarTypeBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_CHANGE_CAR_TYPE, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_CHANGE_CAR_TYPE, null);
                        }
                    }
                }));
    }

    public void toSelectHistory(int cId,int dId,String mdate){
        model.toSelectHistory(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("carid", cId)
                        .withParam("driverid", dId)
                        .withParam("mdate", mdate)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<List<NetLineListBean>>() {

                    @Override
                    public void onSuccess(List<NetLineListBean> bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_SELECT_HISTORY, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_SELECT_HISTORY, null);
                        }
                    }
                }));
    }
    public void toUpCar(String orderId){
        model.toUpCar(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("orderid", orderId)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<Object>() {

                    @Override
                    public void onSuccess(Object bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_UP_CAR, "SUCCESS");
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_UP_CAR, null);
                        }
                    }
                }));
    }
}
