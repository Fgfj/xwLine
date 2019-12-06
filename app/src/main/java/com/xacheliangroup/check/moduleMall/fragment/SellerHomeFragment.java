package com.xacheliangroup.check.moduleMall.fragment;

import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.banner.BannerUtils;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.base.BaseRecycler.BaseRecyclerAdapter;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.moduleMall.activity.SellerHomeActivity;
import com.xacheliangroup.check.moduleMall.mvp.MallPresenter;
import com.xacheliangroup.check.moduleMall.mvp.bean.SellerDetailBean;
import com.xacheliangroup.check.moduleMall.mvp.bean.StarItemBean;
import com.xacheliangroup.check.utils.ProgressHelp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/2/18,15:46
 */
public class SellerHomeFragment extends BaseFragment implements IActionListener.ViewAction {

    @BindView(R.id.module_cbb_seller_home_tab_1_rl)
    RelativeLayout mSellerTap1Rl;
    @BindView(R.id.module_cbb_seller_home_tab_2_rl)
    RelativeLayout mSellerTap2Rl;
    @BindView(R.id.module_cbb_seller_home_tab_3_rl)
    RelativeLayout mSellerTap3Rl;
    @BindView(R.id.module_cbb_seller_home_tab_1_tx)
    TextView mSellerTap1Tx;
    @BindView(R.id.module_cbb_seller_home_tab_2_tx)
    TextView mSellerTap2Tx;
    @BindView(R.id.module_cbb_seller_home_tab_3_tx)
    TextView mSellerTap3Tx;
    @BindView(R.id.module_cbb_seller_home_seller_distance_tx)
    TextView mSellerDistanceTx;
    @BindView(R.id.module_cbb_seller_home_star_tx)
    TextView mSellerStarTx;
    @BindView(R.id.module_cbb_seller_home_title_tx)
    TextView mSellerTitleTx;
    @BindView(R.id.module_cbb_seller_home_phone_number_tx)
    TextView mSellerPhoneNumberTx;
    @BindView(R.id.module_cbb_seller_home_seller_address_tx)
    TextView mSellerAddressTx;
    MallPresenter mMallPresenter;
    @BindView(R.id.module_cbb_seller_home_top_title_tx)
    TextView mTitleTx;
    @BindView(R.id.module_cbb_seller_home_ns)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.module_cbb_seller_home_top_bg_tx)
    TextView mBgTx;
    @BindView(R.id.module_cbb_seller_home_star_rv)
    RecyclerView mStarRecyclerView;
    @BindView(R.id.module_cbb_seller_home_vp)
    ViewPager mViewPager;
    @BindView(R.id.module_cbb_seller_home_rg)
    RadioGroup mRadioGroup;

    BaseRecyclerAdapter<StarItemBean> mStartBaseRecyclerAdapter;
    private String mSellerId;

    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type) {
            case MallPresenter.TO_GET_SELLER_DETAIL_DATA:
                if (obj != null && obj instanceof SellerDetailBean) {
                    SellerDetailBean sellerDetailBean = (SellerDetailBean) obj;
                    setView(sellerDetailBean);
                }
                break;
        }
    }

    private void setView(SellerDetailBean sellerDetailBean) {
        initBannerView(sellerDetailBean.getStoreBannerList());
        mSellerTitleTx.setText(sellerDetailBean.getStoreFrontName());
        mSellerPhoneNumberTx.setText(sellerDetailBean.getStorePhoneNumber());
        mSellerAddressTx.setText(sellerDetailBean.getStoreAddress());
        mSellerStarTx.setText(sellerDetailBean.getStoreStarNumber());
        mSellerDistanceTx.setText(sellerDetailBean.getStoreMinLi() + "  km");

        if (TextUtils.isEmpty(sellerDetailBean.getStorePushLabel1())) {
            mSellerTap1Rl.setVisibility(View.GONE);
        } else {
            mSellerTap1Rl.setVisibility(View.VISIBLE);
            mSellerTap1Tx.setText(sellerDetailBean.getStorePushLabel1());
        }

        if (TextUtils.isEmpty(sellerDetailBean.getStorePushLabel2())) {
            mSellerTap2Rl.setVisibility(View.GONE);
        } else {
            mSellerTap2Rl.setVisibility(View.VISIBLE);
            mSellerTap2Tx.setText(sellerDetailBean.getStorePushLabel2());
        }

        if (TextUtils.isEmpty(sellerDetailBean.getStorePushLabel3())) {
            mSellerTap3Rl.setVisibility(View.GONE);
        } else {
            mSellerTap3Rl.setVisibility(View.VISIBLE);
            mSellerTap3Tx.setText(sellerDetailBean.getStorePushLabel3());
        }
        ChangeStarRv();
    }

    private void ChangeStarRv() {
        List<StarItemBean> mStarList = new ArrayList<>();
        mStarList.add(new StarItemBean(true));
        mStarList.add(new StarItemBean(true));
        mStarList.add(new StarItemBean(true));
        mStarList.add(new StarItemBean(false));
        mStarList.add(new StarItemBean(false));
        mStartBaseRecyclerAdapter.init(mStarList);
        mStartBaseRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_cbb_fragment_seller_home;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        initRecyclerView();
        initNestedScrollView();
        getData();
    }

    private void getData() {
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            mSellerId = intent.getStringExtra(SellerHomeActivity.SELLER_ID);
            ProgressHelp.showProgress(getContext());
            mMallPresenter.toGetSellerDetailData(mSellerId);
        }

    }

    private void initNestedScrollView() {
        mBgTx.setAlpha(0);
        mTitleTx.setAlpha(0);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
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

    private void initBannerView(List<String> storeBannerList) {
        BannerUtils.initListUrlBanner(getContext(), storeBannerList, mViewPager, mRadioGroup);
    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mStarRecyclerView.setLayoutManager(linearLayoutManager);
        mStartBaseRecyclerAdapter = new BaseRecyclerAdapter<>(getActivity());
        mStarRecyclerView.setAdapter(mStartBaseRecyclerAdapter);
        mStarRecyclerView.setNestedScrollingEnabled(false);

    }

    @Override
    protected void initPresenter() {
        mMallPresenter = new MallPresenter(getContext(), this);
        addPresenter(mMallPresenter);
    }

    @Override
    protected void PresenterGetData() {

    }

    @OnClick(R.id.module_cbb_seller_home_back_img)
    public void back() {
        getActivity().finish();
    }

}
