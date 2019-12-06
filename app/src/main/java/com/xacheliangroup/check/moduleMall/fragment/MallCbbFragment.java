package com.xacheliangroup.check.moduleMall.fragment;

import android.app.Activity;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.superluo.textbannerlibrary.ITextBannerItemClickListener;
import com.superluo.textbannerlibrary.TextBannerView;
import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.banner.BannerUtils;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.base.BaseRecycler.BaseRecyclerAdapter;
import com.xacheliangroup.check.common.base.BaseRecycler.OnItemClickListener;
import com.xacheliangroup.check.common.eventbus.MessageEvent;
import com.xacheliangroup.check.common.flag.Flag;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.mvp.UtilsPresenter;
import com.xacheliangroup.check.common.mvp.bean.AdverBean;
import com.xacheliangroup.check.common.type.MarketingEnum;
import com.xacheliangroup.check.common.type.WebEnum;
import com.xacheliangroup.check.common.web.WebActivity;
import com.xacheliangroup.check.moduleMall.activity.MarketingListActivity;
import com.xacheliangroup.check.moduleMall.activity.SellerHomeActivity;
import com.xacheliangroup.check.moduleMall.mvp.MallPresenter;
import com.xacheliangroup.check.moduleMall.mvp.bean.MallHeadBean;
import com.xacheliangroup.check.moduleMall.mvp.bean.SellerItemBean;
import com.xacheliangroup.check.utils.Measure_Utils;
import com.xacheliangroup.check.utils.ProgressHelp;
import com.xacheliangroup.check.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2018/12/14,13:52
 */
public class MallCbbFragment extends BaseFragment implements IActionListener.ViewAction {
    @BindView(R.id.module_cbb_mall_four_fun_rv)
    RecyclerView mFourFunRecyclerView;
    BaseRecyclerAdapter<MallHeadBean.FuncationalBean> mFourFuncationalBeanBaseRecyclerAdapter;
    @BindView(R.id.module_cbb_mall_home_top_line_tx)
    TextView mTopLineTx;
    @BindView(R.id.module_cbb_mall_home_top_black_gradient_rl)
    TextView mTopBlackGradientTx;
    @BindView(R.id.module_cbb_mall_home_top_filter_ly)
    LinearLayout mTopFilterLy;
    @BindView(R.id.module_cbb_mall_home_top_filter_sc_ly)
    LinearLayout mScrollFilterLy;
    private boolean isShowTopDress;//如果是滑动到顶部点击 要滚动rv
    @BindView(R.id.module_cbb_mall_home_goods_rg)
    RadioGroup mGoodsRadioGroup;
    @BindView(R.id.module_cbb_mall_home_goods_vp)
    ViewPager mGoodsViewPage;
    @BindView(R.id.module_cbb_mall_home_top_news_tbv)
    TextBannerView mTxBannerView;
    @BindView(R.id.module_cbb_mall_home_title_tx)
    TextView mTitleTx;
    @BindView(R.id.module_cbb_mall_home_ns)
    NestedScrollView mNestedScrollView;
    @BindView(R.id.module_cbb_mall_home_vp)
    ViewPager mViewPager;
    @BindView(R.id.module_cbb_mall_home_rg)
    RadioGroup mRadioGroup;
    @BindView(R.id.module_cbb_mall_home_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.module_cbb_mall_home_top_bg_tx)
    TextView mBgTx;
    @BindView(R.id.module_cbb_mall_home_top_status_Bar_tx)
    TextView mStatusBarTx;
    @BindView(R.id.module_cbb_mall_home_appbar_rl)
    RelativeLayout mAppBarRl;
    BaseRecyclerAdapter<SellerItemBean> mBaseRecyclerAdapter;
    private List<String> mTopNewsList = new ArrayList<>();
    private String mSellerType = "1";
    private int mPageIndex = 1;

    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type) {
            case UtilsPresenter.TO_GET_ADVERT_DATA:
                if (obj != null && obj instanceof AdverBean) {
                    AdverBean adverBean = (AdverBean) obj;
                }
                break;
            case MallPresenter.TO_GET_MALL_HEAD_DATA:
                if (obj != null && obj instanceof MallHeadBean) {
                    MallHeadBean mallHeadBean = (MallHeadBean) obj;
                    initBanner(mallHeadBean.getShuffling());
                    initGoodsBanner(mallHeadBean.getSuperrecommend());
                    initTopNews(mallHeadBean.getHeadlines());
                    initFourFun(mallHeadBean.getFuncational());
                }
                break;
            case MallPresenter.TO_GET_SELLER_LISE_DATA:
                if (obj != null && obj instanceof List) {
                    List<SellerItemBean> list = (List<SellerItemBean>) obj;
                    mBaseRecyclerAdapter.init(list);
                    mBaseRecyclerAdapter.notifyDataSetChanged();
                }
                break;
        }
    }

    private void initFourFun(final List<MallHeadBean.FuncationalBean> funcational) {
        mFourFunRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));
        mFourFuncationalBeanBaseRecyclerAdapter = new BaseRecyclerAdapter<>(getActivity());
        mFourFunRecyclerView.setAdapter(mFourFuncationalBeanBaseRecyclerAdapter);
        mFourFunRecyclerView.setNestedScrollingEnabled(false);
        mFourFuncationalBeanBaseRecyclerAdapter.init(funcational);
        mFourFuncationalBeanBaseRecyclerAdapter.notifyDataSetChanged();

        mFourFuncationalBeanBaseRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position, Object obj) {
                if (obj != null && obj instanceof MallHeadBean.FuncationalBean) {
                    MallHeadBean.FuncationalBean funcationalBean = (MallHeadBean.FuncationalBean) obj;
                    switch (funcationalBean.getType()) {//1.保险 2.商家列表 3.商品列表)
                        case "1":
                            WebActivity.launch(getContext(), getActivity(), funcationalBean.getLinkUrl(), WebEnum.OTHER_WEB);
                            break;
                        case "2":
                            MarketingListActivity.launch(getContext(), getActivity(), MarketingEnum.SELLER_LIST, funcationalBean);
                            break;
                        case "3":
                            MarketingListActivity.launch(getContext(), getActivity(), MarketingEnum.GOODS_LIST, funcationalBean);
                            break;
                        default:
                            WebActivity.launch(getContext(), getActivity(), funcationalBean.getLinkUrl(), WebEnum.OTHER_WEB);
                            break;
                    }
                }
            }

            @Override
            public boolean onItemLongClicked(int position, Object obj) {
                return false;
            }
        });

    }

    private void initGoodsBanner(List<MallHeadBean.SuperrecommendBean> superrecommend) {
        BannerUtils.initGoodsBanner(getContext(), superrecommend, mGoodsViewPage, mGoodsRadioGroup);

    }

    private void initTopNews(List<MallHeadBean.HeadlinesBean> headlines) {
        mTopNewsList.clear();
        for (MallHeadBean.HeadlinesBean headlinesBean : headlines) {
            mTopNewsList.add(headlinesBean.getNewstitle());
        }
        mTxBannerView.setDatas(mTopNewsList);
    }

    private void initBanner(List<MallHeadBean.ShufflingBean> list) {

        BannerUtils.initBanner(getContext(), list, mViewPager, mRadioGroup);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case Flag.MALL.MALL_TEST:
                ToastUtils.showToast(getContext(), "asdsad");
                break;
        }
    }

    UtilsPresenter mUtilsPresenter;
    MallPresenter mMallPresenter;

    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_cbb_fragnment_mall;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        setEventBusAction();
        initRecyclerView();
        initTopView();
        initNestedScrollView();
        initTopBannerOnClick();
        initFilterView();
    }

    private void initFilterView() {
        mFilter1Tx.getPaint().setFakeBoldText(true);
        mFilterTop1Tx.getPaint().setFakeBoldText(true);
    }

    private void initTopBannerOnClick() {
        mTxBannerView.setItemOnClickListener(new ITextBannerItemClickListener() {
            @Override
            public void onItemClick(String data, int position) {
                ToastUtils.showToast(getContext(), data);
            }
        });
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
                                setAndroidNativeLightStatusBar(getActivity(), false);
                            } else {
                                //“图片”全部滑出屏幕的时候，设为完全不透明
                                mBgTx.setAlpha(1);
                                mTitleTx.setAlpha(1);
                                setAndroidNativeLightStatusBar(getActivity(), true);
                            }
                        }
                    }


                    int[] intScroll = new int[2];
                    mScrollFilterLy.getLocationOnScreen(intScroll);
                    int ScrollY = -ints[1];
                    //mImage这个view的高度
                    int imageScrollHeight = mScrollFilterLy.getHeight();
                    if (mScrollFilterLy != null && imageHeight > 0) {
                        //如果“图片”没有向上滑动，设置为全透明
                        if (ScrollY < 0) {
                            mTopFilterLy.setVisibility(View.GONE);
                            mTopLineTx.setVisibility(View.GONE);
                            isShowTopDress = false;
                        } else {
                            //“图片”已经滑动，而且还没有全部滑出屏幕，根据滑出高度的比例设置透明度的比例
                            if (ScrollY < imageScrollHeight - (Measure_Utils.getActionBarHigh(getContext()) + Measure_Utils.getStatusBarHeight(getContext()))) {
                                mTopFilterLy.setVisibility(View.GONE);
                                mTopLineTx.setVisibility(View.GONE);
                                isShowTopDress = false;
                            } else {
                                //“图片”全部滑出屏幕的时候，设为完全不透明
                                mTopFilterLy.setVisibility(View.VISIBLE);
                                mTopLineTx.setVisibility(View.VISIBLE);
                                isShowTopDress = true;
                            }
                        }
                    }


                }
            });
        }
    }

    private static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    private void initTopView() {
        RelativeLayout.LayoutParams mStatusBarTxLayoutParams = (RelativeLayout.LayoutParams) mStatusBarTx.getLayoutParams();
        mStatusBarTxLayoutParams.height = Measure_Utils.getStatusBarHeight(getContext());
        mStatusBarTx.setLayoutParams(mStatusBarTxLayoutParams);

        RelativeLayout.LayoutParams mAppBarRlLayoutParams = (RelativeLayout.LayoutParams) mAppBarRl.getLayoutParams();
        mAppBarRlLayoutParams.height = Measure_Utils.getActionBarHigh(getContext());
        mAppBarRl.setLayoutParams(mAppBarRlLayoutParams);

        RelativeLayout.LayoutParams mBgTxLayoutParams = (RelativeLayout.LayoutParams) mBgTx.getLayoutParams();
        mBgTxLayoutParams.height = Measure_Utils.getActionBarHigh(getContext()) + Measure_Utils.getStatusBarHeight(getContext());
        mBgTx.setLayoutParams(mBgTxLayoutParams);


        RelativeLayout.LayoutParams mBlackGradientLayoutParams = (RelativeLayout.LayoutParams) mTopBlackGradientTx.getLayoutParams();
        mBlackGradientLayoutParams.height = Measure_Utils.getActionBarHigh(getContext()) + Measure_Utils.getStatusBarHeight(getContext());
        mTopBlackGradientTx.setLayoutParams(mBlackGradientLayoutParams);
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBaseRecyclerAdapter = new BaseRecyclerAdapter<>(getContext());
        mRecyclerView.setAdapter(mBaseRecyclerAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);

        mBaseRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position, Object obj) {
                if (obj != null && obj instanceof SellerItemBean) {
                    SellerItemBean sellerItemBean = (SellerItemBean) obj;
                    SellerHomeActivity.launch(getContext(), getActivity(), sellerItemBean.getStorefrontid());
                }
            }

            @Override
            public boolean onItemLongClicked(int position, Object obj) {
                return false;
            }
        });
    }

    @Override
    protected void initPresenter() {
        mUtilsPresenter = new UtilsPresenter(getContext(), this);
        addPresenter(mUtilsPresenter);

        mMallPresenter = new MallPresenter(getContext(), this);
        addPresenter(mMallPresenter);

    }

    @Override
    protected void PresenterGetData() {
        mUtilsPresenter.toGetAdvertData();
        mMallPresenter.toGetMallData();
        toGetSellerHomeListData();
    }

    private void toGetSellerHomeListData() {
        ProgressHelp.showProgress(getContext());
        mMallPresenter.toGetSellerListData(mSellerType, mPageIndex + "");
    }

    @OnClick(R.id.module_cbb_mall_home_filter_1_Tx)
    public void toGetFilter1Data() {
        toFilter1Click();
    }

    @OnClick(R.id.module_cbb_mall_home_filter_2_Tx)
    public void toGetFilter2Data() {
        toFilter2Click();

    }

    @OnClick(R.id.module_cbb_mall_home_filter_3_Tx)
    public void toGetFilter3Data() {
        toFilter3Click();

    }

    @OnClick(R.id.module_cbb_mall_home_filter_4_Tx)
    public void toGetFilter4Data() {
        toFilter4Click();

    }

    @OnClick(R.id.module_cbb_mall_home_filter_5_Tx)
    public void toGetFilter5ata() {
        toFilter5Click();

    }

    @OnClick(R.id.module_cbb_mall_home_filter_top_1_Tx)
    public void toGetFilter1DataTop() {
        toFilter1Click();
        toTopDialogClickScrollView();
    }

    @OnClick(R.id.module_cbb_mall_home_filter_top_2_Tx)
    public void toGetFilter2DataTop() {
        toFilter2Click();
        toTopDialogClickScrollView();

    }

    @OnClick(R.id.module_cbb_mall_home_filter_top_3_Tx)
    public void toGetFilter3DataTop() {
        toFilter3Click();
        toTopDialogClickScrollView();

    }

    @OnClick(R.id.module_cbb_mall_home_filter_top_4_Tx)
    public void toGetFilter4DataTop() {
        toFilter4Click();
        toTopDialogClickScrollView();

    }

    @OnClick(R.id.module_cbb_mall_home_filter_top_5_Tx)
    public void toGetFilter5ataTop() {
        toFilter5Click();
        toTopDialogClickScrollView();

    }

    public void toTopDialogClickScrollView() {
        if (isShowTopDress) {//如果现实上面筛选view 则滚动
            mNestedScrollView.scrollTo(0, mScrollFilterLy.getHeight() - (Measure_Utils.getActionBarHigh(getContext()) + Measure_Utils.getStatusBarHeight(getContext())));
        }
    }

    private void toFilter1Click() {
        mSellerType = "1";
        toGetSellerHomeListData();
        toSetAllFilterTx();
        mFilter1Tx.setTextColor(getContext().getResources().getColor(R.color.deepTextPrimary));
        mFilterTop1Tx.setTextColor(getContext().getResources().getColor(R.color.deepTextPrimary));
        mFilter1Tx.getPaint().setFakeBoldText(true);
        mFilterTop1Tx.getPaint().setFakeBoldText(true);
    }

    private void toFilter2Click() {
        mSellerType = "2";
        toGetSellerHomeListData();
        toSetAllFilterTx();
        mFilter2Tx.setTextColor(getContext().getResources().getColor(R.color.deepTextPrimary));
        mFilterTop2Tx.setTextColor(getContext().getResources().getColor(R.color.deepTextPrimary));
        mFilter2Tx.getPaint().setFakeBoldText(true);
        mFilterTop2Tx.getPaint().setFakeBoldText(true);
    }


    private void toFilter3Click() {
        mSellerType = "3";
        toGetSellerHomeListData();
        toSetAllFilterTx();
        mFilter3Tx.setTextColor(getContext().getResources().getColor(R.color.deepTextPrimary));
        mFilterTop3Tx.setTextColor(getContext().getResources().getColor(R.color.deepTextPrimary));
        mFilter3Tx.getPaint().setFakeBoldText(true);
        mFilterTop3Tx.getPaint().setFakeBoldText(true);
    }


    private void toFilter4Click() {
        mSellerType = "4";
        toGetSellerHomeListData();
        toSetAllFilterTx();
        mFilter4Tx.setTextColor(getContext().getResources().getColor(R.color.deepTextPrimary));
        mFilterTop4Tx.setTextColor(getContext().getResources().getColor(R.color.deepTextPrimary));
        mFilter4Tx.getPaint().setFakeBoldText(true);
        mFilterTop4Tx.getPaint().setFakeBoldText(true);

    }


    private void toFilter5Click() {
        mSellerType = "5";
        toGetSellerHomeListData();
        toSetAllFilterTx();
        mFilter5Tx.setTextColor(getContext().getResources().getColor(R.color.deepTextPrimary));
        mFilterTop5Tx.setTextColor(getContext().getResources().getColor(R.color.deepTextPrimary));
        mFilter5Tx.getPaint().setFakeBoldText(true);
        mFilterTop5Tx.getPaint().setFakeBoldText(true);
    }

    @BindView(R.id.module_cbb_mall_home_filter_1_Tx)
    TextView mFilter1Tx;
    @BindView(R.id.module_cbb_mall_home_filter_2_Tx)
    TextView mFilter2Tx;
    @BindView(R.id.module_cbb_mall_home_filter_3_Tx)
    TextView mFilter3Tx;
    @BindView(R.id.module_cbb_mall_home_filter_4_Tx)
    TextView mFilter4Tx;
    @BindView(R.id.module_cbb_mall_home_filter_5_Tx)
    TextView mFilter5Tx;
    @BindView(R.id.module_cbb_mall_home_filter_top_1_Tx)
    TextView mFilterTop1Tx;
    @BindView(R.id.module_cbb_mall_home_filter_top_2_Tx)
    TextView mFilterTop2Tx;
    @BindView(R.id.module_cbb_mall_home_filter_top_3_Tx)
    TextView mFilterTop3Tx;
    @BindView(R.id.module_cbb_mall_home_filter_top_4_Tx)
    TextView mFilterTop4Tx;
    @BindView(R.id.module_cbb_mall_home_filter_top_5_Tx)
    TextView mFilterTop5Tx;

    private void toSetAllFilterTx() {
        mFilter1Tx.setTextColor(getContext().getResources().getColor(R.color.secondaryTextPrimary));
        mFilter2Tx.setTextColor(getContext().getResources().getColor(R.color.secondaryTextPrimary));
        mFilter3Tx.setTextColor(getContext().getResources().getColor(R.color.secondaryTextPrimary));
        mFilter4Tx.setTextColor(getContext().getResources().getColor(R.color.secondaryTextPrimary));
        mFilter5Tx.setTextColor(getContext().getResources().getColor(R.color.secondaryTextPrimary));
        mFilterTop1Tx.setTextColor(getContext().getResources().getColor(R.color.secondaryTextPrimary));
        mFilterTop2Tx.setTextColor(getContext().getResources().getColor(R.color.secondaryTextPrimary));
        mFilterTop3Tx.setTextColor(getContext().getResources().getColor(R.color.secondaryTextPrimary));
        mFilterTop4Tx.setTextColor(getContext().getResources().getColor(R.color.secondaryTextPrimary));
        mFilterTop5Tx.setTextColor(getContext().getResources().getColor(R.color.secondaryTextPrimary));
        mFilter1Tx.getPaint().setFakeBoldText(false);
        mFilterTop1Tx.getPaint().setFakeBoldText(false);
        mFilter2Tx.getPaint().setFakeBoldText(false);
        mFilterTop2Tx.getPaint().setFakeBoldText(false);
        mFilter3Tx.getPaint().setFakeBoldText(false);
        mFilterTop3Tx.getPaint().setFakeBoldText(false);
        mFilter4Tx.getPaint().setFakeBoldText(false);
        mFilterTop4Tx.getPaint().setFakeBoldText(false);
        mFilter5Tx.getPaint().setFakeBoldText(false);
        mFilterTop5Tx.getPaint().setFakeBoldText(false);
    }
}
