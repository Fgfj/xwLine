package com.xacheliangroup.check.moduleMall.fragment;

import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.banner.BannerUtils;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.moduleMall.activity.GoodsDetailActivity;
import com.xacheliangroup.check.moduleMall.activity.SubmitOrderActivity;
import com.xacheliangroup.check.moduleMall.mvp.MallPresenter;
import com.xacheliangroup.check.moduleMall.mvp.bean.GoodsDetailBean;
import com.xacheliangroup.check.utils.ProgressHelp;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/2/21,10:20
 */
public class GoodsDetailFragment extends BaseFragment implements IActionListener.ViewAction {
    MallPresenter mMallPresenter;
    @BindView(R.id.module_cbb_goods_content_tx)
    TextView mGoodsContentTx;
    @BindView(R.id.module_cbb_goods_title_tx)
    TextView mGoodsNameTx;
    @BindView(R.id.module_cbb_goods_price_tx)
    TextView mGoodsPriceTx;
    @BindView(R.id.module_cbb_goods_detail_top_title_tx)
    TextView mTitleTx;
    @BindView(R.id.module_cbb_goods_detail_top_bg_tx)
    TextView mBgTx;
    @BindView(R.id.module_cbb_goods_detail_ns)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.module_cbb_goods_detail_wv)
    WebView mWebView;
    @BindView(R.id.module_cbb_goods_detail_vp)
    ViewPager mViewPager;
    @BindView(R.id.module_cbb_goods_detail_rg)
    RadioGroup mRadioGroup;
    private String mGoodsId;
    private GoodsDetailBean mGoodsDetailBean;


    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type){
            case MallPresenter.TO_GET_GOODS_DETAIL_DATA:
                if(obj!=null&&obj instanceof GoodsDetailBean){
                    mGoodsDetailBean = (GoodsDetailBean) obj;
                    changeView(mGoodsDetailBean);
                }
                break;
        }
    }

    private void changeView(GoodsDetailBean goodsDetailBean) {
        initBannerData(goodsDetailBean.getMdseBannerList());
        mGoodsPriceTx.setText("¥ "+goodsDetailBean.getMdsePrice());
        mGoodsNameTx.setText(goodsDetailBean.getMdseTitle());
        mGoodsContentTx.setText(goodsDetailBean.getMdseContent());
        mWebView.loadUrl(goodsDetailBean.getDetailUrl());
    }

    private void initBannerData(List<String> mdseBannerList) {
        BannerUtils.initListUrlBanner(getContext(),mdseBannerList,mViewPager,mRadioGroup);
    }

    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_cbb_fragment_goods_detail;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        initWebView();
        initNestedScrollView();
        getData();
    }

    private void getData() {
        Intent intent = getActivity().getIntent();
        if(intent!=null){
            mGoodsId = intent.getStringExtra(GoodsDetailActivity.GOODS_ID);
            ProgressHelp.showProgress(getContext());
            mMallPresenter.toGetGoodsDetailData(mGoodsId);
        }
    }

    private void initNestedScrollView() {
        mBgTx.setAlpha(0);
        mTitleTx.setAlpha(0);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            mNestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View view, int i, int i1, int i2, int i3) {
                    int[] ints = new int[2];
                    mViewPager.getLocationOnScreen(ints);
//                    int y = -ints[1] - getStatusBar();
                    int y = -ints[1];
                    //mImage这个view的高度
                    int imageHeight = mViewPager.getHeight();
                    if (mViewPager != null && imageHeight > 0) {
                        //如果“图片”没有向上滑动，设置为全透明
                        if (y < 0) {
                            mBgTx.setAlpha(0);
                            mTitleTx.setAlpha(0);
                        } else {
                            //“图片”已经滑动，而且还没有全部滑出屏幕，根据滑出高度的比例设置透明度的比例
                            if (y < imageHeight) {
                                mBgTx.setAlpha(1.0F * y / imageHeight);
                                mTitleTx.setAlpha(1.0F * y / imageHeight);
                            } else {
                                //“图片”全部滑出屏幕的时候，设为完全不透明
                                mBgTx.setAlpha(1);
                                mTitleTx.setAlpha(1);
                            }
                        }
                    }
                }
            });
        }
    }

    private void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        mWebView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_INSET);//滚动条风格

        //如果不设置WebViewClient，请求会跳转系统浏览器
        mWebView.setWebViewClient(new MyWebViewClient());
    }

    @Override
    protected void initPresenter() {
        mMallPresenter=new MallPresenter(getContext(),this);
        addPresenter(mMallPresenter);
    }

    @Override
    protected void PresenterGetData() {

    }
    @OnClick(R.id.module_cbb_goods_detail_back_img)
    public void toBack(){
        getActivity().finish();
    }


    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            int w = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
            int h = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
            // 重新测量
            view.measure(w, h);
        }
    }
    @OnClick(R.id.module_cbb_goods_detail_submit_rl)
    public void toSubmit(){
        SubmitOrderActivity.launch(getContext(),getActivity(), mGoodsDetailBean);
    }
}
