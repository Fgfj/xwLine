package com.xacheliangroup.check.moduleOther.mvp;

import android.content.Context;

import com.xacheliangroup.check.common.http.okhttp.BaseDisposableObserver;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.http.okhttp.RequestField;
import com.xacheliangroup.check.common.http.okhttp.RequestQuery;
import com.xacheliangroup.check.common.http.okhttp.RetrofitHelper;
import com.xacheliangroup.check.common.http.okhttp.RxPresenter;
import com.xacheliangroup.check.common.sharepreference.AppSharePreference;
import com.xacheliangroup.check.moduleOther.mvp.bean.UserLoginBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author:yz
 * data: 2018/12/24,16:56
 */
public class OtherPresenter extends RxPresenter {
    public static final String TO_GET_PHONE_CODE_LOGIN="TO_GET_PHONE_CODE_LOGIN";
    public static final String TO_LOGIN_FOR_CBB="TO_LOGIN_FOR_CBB";
    public static final String TO_LOGOUT_DATA="TO_LOGOUT_DATA";
    OtherModel model;
    public OtherPresenter(Context context, IActionListener.ViewAction view) {
        super(context, view);
        model= RetrofitHelper.createService(OtherModel.class);
    }
    public void toLoginData(String mobile,String code,String mobileid){
        model.toLoginData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("mobile",mobile)
                        .withParam("code",code)
                        .withParam("mobileid",mobileid)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<UserLoginBean>() {

                    @Override
                    public void onSuccess(UserLoginBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_LOGIN_FOR_CBB, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_LOGIN_FOR_CBB, null);
                        }
                    }
                }));
    }
    public void toGetPhoneCodeData(String mobile){
        model.toGetPhoneCodeData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("mobile",mobile)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<Object>() {

                    @Override
                    public void onSuccess(Object bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_PHONE_CODE_LOGIN, "success");
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_PHONE_CODE_LOGIN, null);
                        }
                    }
                }));
    }
    public void toLogout(){
        model.toLogout(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("appUserId", AppSharePreference.getInstance().getUserId())
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<Object>() {

                    @Override
                    public void onSuccess(Object bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_LOGOUT_DATA, "success");
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_LOGOUT_DATA, null);
                        }
                    }
                }));
    }
}
