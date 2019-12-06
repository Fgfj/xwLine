package com.xacheliangroup.check.moduleMine.mvp;

import android.content.Context;

import com.xacheliangroup.check.common.http.okhttp.BaseDisposableObserver;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.http.okhttp.RequestField;
import com.xacheliangroup.check.common.http.okhttp.RequestQuery;
import com.xacheliangroup.check.common.http.okhttp.RetrofitHelper;
import com.xacheliangroup.check.common.http.okhttp.RxPresenter;
import com.xacheliangroup.check.common.sharepreference.AppSharePreference;
import com.xacheliangroup.check.moduleMine.mvp.bean.AddressItemBean;
import com.xacheliangroup.check.moduleMine.mvp.bean.OrderDetailBean;
import com.xacheliangroup.check.moduleMine.mvp.bean.OrderListItemBean;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author:yz
 * data: 2018/12/19,11:35
 */
public class MinePresenter extends RxPresenter {
    public static final String TO_GET_ADDRESS_LIST_DATA="TO_GET_ADDRESS_LIST_DATA";
    public static final String TO_ADD_ADDRESS="TO_ADD_ADDRESS";
    public static final String TO_UPDATE_ADDRESS="TO_UPDATE_ADDRESS";
    public static final String TO_GET_ORDER_LIST_DATA="TO_GET_ORDER_LIST_DATA";
    public static final String TO_GET_ORDER_DETAIL_DATA="TO_GET_ORDER_DETAIL_DATA";
    MineModel model;
    public MinePresenter(Context context, IActionListener.ViewAction view) {
        super(context, view);
        model= RetrofitHelper.createService(MineModel.class);
    }
    public void toGetAddressListData(){
        model.toGetAddressListData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("appUserId", AppSharePreference.getInstance().getUserId())
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<List<AddressItemBean>>() {

                    @Override
                    public void onSuccess(List<AddressItemBean> bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_ADDRESS_LIST_DATA, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_ADDRESS_LIST_DATA, null);
                        }
                    }
                }));
    }
    public void toAddAddress(String name,String ph,String address,String ifDefault){
        model.toAddAddress(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("appUserId", AppSharePreference.getInstance().getUserId())
                        .withParam("phoneNumber", ph)
                        .withParam("name", name)
                        .withParam("address", address)
                        .withParam("ifDefault", ifDefault)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<Object>() {

                    @Override
                    public void onSuccess(Object bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_ADD_ADDRESS, "success");
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_ADD_ADDRESS, null);
                        }
                    }
                }));
    }
    public void toUpdateAddress(String addressId,String name,String ph,String address,String ifDefault){
        model.toUpdateAddress(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("appUserId", AppSharePreference.getInstance().getUserId())
                        .withParam("phoneNumber", ph)
                        .withParam("name", name)
                        .withParam("address", address)
                        .withParam("ifDefault", ifDefault)
                        .withParam("addressId", addressId)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<Object>() {

                    @Override
                    public void onSuccess(Object bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_UPDATE_ADDRESS, "success");
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_UPDATE_ADDRESS, null);
                        }
                    }
                }));
    }
    public void toGetOrderListData(String orderType){
        model.toGetOrderListData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("appUserId", AppSharePreference.getInstance().getUserId())
                        .withParam("orderType", orderType)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<List<OrderListItemBean>>() {

                    @Override
                    public void onSuccess(List<OrderListItemBean> bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_ORDER_LIST_DATA, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_ORDER_LIST_DATA, null);
                        }
                    }
                }));
    }
    public void toGetOrderDetailData(String orderId){
        model.toGetOrderDetailData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("orderId", orderId)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<OrderDetailBean>() {

                    @Override
                    public void onSuccess(OrderDetailBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_ORDER_DETAIL_DATA, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_ORDER_DETAIL_DATA, null);
                        }
                    }
                }));
    }
}
