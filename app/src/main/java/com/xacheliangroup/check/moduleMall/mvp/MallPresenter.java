package com.xacheliangroup.check.moduleMall.mvp;

import android.content.Context;

import com.xacheliangroup.check.common.http.okhttp.BaseDisposableObserver;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.http.okhttp.RequestField;
import com.xacheliangroup.check.common.http.okhttp.RequestQuery;
import com.xacheliangroup.check.common.http.okhttp.RetrofitHelper;
import com.xacheliangroup.check.common.http.okhttp.RxPresenter;
import com.xacheliangroup.check.moduleMall.mvp.bean.GoodsDetailBean;
import com.xacheliangroup.check.moduleMall.mvp.bean.GoodsItemBean;
import com.xacheliangroup.check.moduleMall.mvp.bean.MallHeadBean;
import com.xacheliangroup.check.moduleMall.mvp.bean.SellerDetailBean;
import com.xacheliangroup.check.moduleMall.mvp.bean.SellerItemBean;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author:yz
 * data: 2018/12/14,14:47
 */
public class MallPresenter extends RxPresenter {
    public static final String TO_GET_MALL_HEAD_DATA="TO_GET_MALL_HEAD_DATA";
    public static final String TO_GET_SELLER_LISE_DATA="TO_GET_SELLER_LISE_DATA";
    public static final String TO_GET_SELLER_DETAIL_DATA="TO_GET_SELLER_DETAIL_DATA";
    public static final String TO_GET_MARKETING_SELLER_LIST_DATA="TO_GET_MARKETING_SELLER_LIST_DATA";
    public static final String TO_GET_MARKETING_GOODS_LIST_DATA="TO_GET_MARKETING_GOODS_LIST_DATA";
    public static final String TO_GET_GOODS_DETAIL_DATA="TO_GET_GOODS_DETAIL_DATA";

    MallModel model;
    public MallPresenter(Context context, IActionListener.ViewAction view) {
        super(context, view);
        model= RetrofitHelper.createService(MallModel.class);
    }

    public void toGetSellerListData(String type,String pageIndex){
        model.toGetSellerListData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("pagesize","20")
                        .withParam("jingdu","0")
                        .withParam("weidu","0")
                        .withParam("pageindex",pageIndex)
                        .withParam("type",type)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<List<SellerItemBean>>() {

                    @Override
                    public void onSuccess(List<SellerItemBean> bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_SELLER_LISE_DATA, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_SELLER_LISE_DATA, null);
                        }
                    }
                }));
    }
    public void toGetMallData(){
        model.toGetMallData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<MallHeadBean>() {

                    @Override
                    public void onSuccess(MallHeadBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_MALL_HEAD_DATA, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_MALL_HEAD_DATA, null);
                        }
                    }
                }));
    }

    public void toGetSellerDetailData(String sellerId){
        model.toGetSellerDetailData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("storefrontid",sellerId)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<SellerDetailBean>() {

                    @Override
                    public void onSuccess(SellerDetailBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_SELLER_DETAIL_DATA, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_SELLER_DETAIL_DATA, null);
                        }
                    }
                }));
    }
    public void toGetMarketingSellerListData(String gameId){
        model.toGetMarketingSellerListData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("game",gameId)
                        .withParam("long","0")
                        .withParam("lat","0")
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<List<SellerItemBean>>() {

                    @Override
                    public void onSuccess(List<SellerItemBean> bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_MARKETING_SELLER_LIST_DATA, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_MARKETING_SELLER_LIST_DATA, null);
                        }
                    }
                }));
    }
    public void toGetMarketingGoodsListData(String mdseTypeId){
        model.toGetMarketingGoodsListData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("mdseTypeId",mdseTypeId)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<List<GoodsItemBean>>() {

                    @Override
                    public void onSuccess(List<GoodsItemBean> bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_MARKETING_GOODS_LIST_DATA, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_MARKETING_GOODS_LIST_DATA, null);
                        }
                    }
                }));
    }
    public void toGetGoodsDetailData(String goodsId){
        model.toGetGoodsDetailData(RequestQuery.getBuildInstance()
                        .build()
                , RequestField.getBuilderInstance()
                        .withParam("mdseId",goodsId)
                        .build().getMap())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(addSubscriber(new BaseDisposableObserver<GoodsDetailBean>() {

                    @Override
                    public void onSuccess(GoodsDetailBean bean) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_GOODS_DETAIL_DATA, bean);
                        }
                    }

                    @Override
                    public void onFailure(int errorCode, String errorMsg) {
                        if (viewAction != null) {
                            viewAction.showInfoView(TO_GET_GOODS_DETAIL_DATA, null);
                        }
                    }
                }));
    }
}
