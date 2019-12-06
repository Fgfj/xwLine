package com.xacheliangroup.check.moduleCarCircle.mvp;

import android.content.Context;

import com.xacheliangroup.check.common.http.okhttp.BaseDisposableObserver;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.http.okhttp.RequestField;
import com.xacheliangroup.check.common.http.okhttp.RequestQuery;
import com.xacheliangroup.check.common.http.okhttp.RetrofitHelper;
import com.xacheliangroup.check.common.http.okhttp.RxPresenter;
import com.xacheliangroup.check.moduleCarCircle.mvp.bean.CarCircleItemBean;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author:yz
 * data: 2018/12/24,09:41
 */
public class CirclePresenter extends RxPresenter{
    public static final String TO_GET_CIRCLE_LIST_DATA="TO_GET_CIRCLE_LIST_DATA";
    CircleModel model;
    public CirclePresenter(Context context, IActionListener.ViewAction view) {
        super(context, view);
        model= RetrofitHelper.createService(CircleModel.class);
    }

    public void toGetCarCircleListData(String type,String pageindex){
        model.toGetCarCircleListData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("appUserId","")
                        .withParam("type",type)
                        .withParam("pageindex",pageindex)
                        .withParam("pagesize","20")
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<List<CarCircleItemBean>>() {

                    @Override
                    public void onSuccess(List<CarCircleItemBean> bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_CIRCLE_LIST_DATA, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_CIRCLE_LIST_DATA, null);
                        }
                    }
                }));
    }
}
