package com.xacheliangroup.check.moduleCarComment.mvp;

import android.content.Context;

import com.xacheliangroup.check.common.http.okhttp.BaseDisposableObserver;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.http.okhttp.RequestField;
import com.xacheliangroup.check.common.http.okhttp.RequestQuery;
import com.xacheliangroup.check.common.http.okhttp.RetrofitHelper;
import com.xacheliangroup.check.common.http.okhttp.RxPresenter;
import com.xacheliangroup.check.moduleCarComment.mvp.bean.CarCommentBean;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author:yz
 * data: 2018/12/19,16:15
 */
public class CommentPresenter extends RxPresenter {
    public static final String TO_GET_CAR_COMMENT_LIST_DATA="TO_GET_CAR_COMMENT_LIST_DATA";
    CommentModel model;
    public CommentPresenter(Context context, IActionListener.ViewAction view) {
        super(context, view);
        model= RetrofitHelper.createService(CommentModel.class);
    }

    public void toGetCarCommentListData(String pageIndex){
        model.toGetCarCommentListData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("pagesize","20")
                        .withParam("pageindex",pageIndex)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<List<CarCommentBean>>() {

                    @Override
                    public void onSuccess(List<CarCommentBean> bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_CAR_COMMENT_LIST_DATA, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_CAR_COMMENT_LIST_DATA, null);
                        }
                    }
                }));
    }
}
