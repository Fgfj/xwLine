package com.xacheliangroup.check.common.mvp;

import android.content.Context;

import com.xacheliangroup.check.common.http.okhttp.BaseDisposableObserver;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.http.okhttp.RequestField;
import com.xacheliangroup.check.common.http.okhttp.RequestQuery;
import com.xacheliangroup.check.common.http.okhttp.RetrofitHelper;
import com.xacheliangroup.check.common.http.okhttp.RxPresenter;
import com.xacheliangroup.check.common.mvp.bean.AdverBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author:yz
 * data: 2018/12/14,13:42
 */
public class UtilsPresenter extends RxPresenter {
    public static final String TO_GET_ADVERT_DATA="TO_GET_ADVERT_DATA";
    UtilsModel model;
    public UtilsPresenter(Context context, IActionListener.ViewAction view) {
        super(context, view);
        model= RetrofitHelper.createService(UtilsModel.class);
    }

    public void toGetAdvertData(){
        model.toGetAdvertData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<AdverBean>() {

                    @Override
                    public void onSuccess(AdverBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_ADVERT_DATA, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_ADVERT_DATA, null);
                        }
                    }
                }));
    }
}
