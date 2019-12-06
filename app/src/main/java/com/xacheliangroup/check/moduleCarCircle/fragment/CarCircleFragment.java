package com.xacheliangroup.check.moduleCarCircle.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.banner.OvFragmentStateAdapter;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.moduleCarCircle.fragment.circleHomeFragmentList.AllCircleFragment;
import com.xacheliangroup.check.moduleCarCircle.fragment.circleHomeFragmentList.HotCircleFragment;
import com.xacheliangroup.check.moduleCarCircle.fragment.circleHomeFragmentList.RecommendCircleFragment;
import com.xacheliangroup.check.utils.Measure_Utils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author:yz
 * data: 2018/12/18,13:58
 */
public class CarCircleFragment extends BaseFragment {
    @BindView(R.id.module_cbb_car_circle_home_tl)
    TabLayout mTableLayout;
    @BindView(R.id.module_cbb_car_circle_home_vp)
    ViewPager mViewPager;
    @BindView(R.id.module_cbb_car_circle_status_bar_tx)
    TextView  mStatusBarTx;
    @BindView(R.id.module_cbb_car_circle_app_bar_ly)
    LinearLayout mAppbarLy;
    private String [] mTabTitleArray={"全部","推荐","最热"};
    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_cbb_fragment_car_circle;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        initView();
    }

    private void initView() {
        LinearLayout.LayoutParams mStatusBarTxLayoutParams = (LinearLayout.LayoutParams) mStatusBarTx.getLayoutParams();
        mStatusBarTxLayoutParams.height= Measure_Utils.getStatusBarHeight(getContext());
        mStatusBarTx.setLayoutParams(mStatusBarTxLayoutParams);
        mStatusBarTx.setBackgroundColor(Color.WHITE);

        LinearLayout.LayoutParams mAppBarRlLayoutParams = (LinearLayout.LayoutParams) mAppbarLy.getLayoutParams();
        mAppBarRlLayoutParams.height=Measure_Utils.getActionBarHigh(getContext());
        mAppbarLy.setLayoutParams(mAppBarRlLayoutParams);
    }

    @Override
    protected void initPresenter() {
        initViewPager();
    }

    private void initViewPager() {
        List<Fragment> mListCircleFragment=new ArrayList<>();
        mListCircleFragment.add(new AllCircleFragment());
        mListCircleFragment.add(new RecommendCircleFragment());
        mListCircleFragment.add(new HotCircleFragment());
        mTableLayout.setTabMode(TabLayout.MODE_FIXED);
        //tab的字体选择器,默认黑色,选择时红色
        mTableLayout.setTabTextColors(getContext().getResources().getColor(R.color.secondaryTextPrimary)
                ,getContext().getResources().getColor(R.color.deepTextPrimary));
        mTableLayout.setSelectedTabIndicatorColor(getContext().getResources().getColor(R.color.transparent));
        mViewPager.setAdapter(
                new OvFragmentStateAdapter(getActivity().getSupportFragmentManager()
                        ,mListCircleFragment
                        ,mTabTitleArray));
        mViewPager.setOffscreenPageLimit(3);//左右预加载个数
        mTableLayout.setupWithViewPager(mViewPager);
        mTableLayout.getTabAt(0).select();
    }

    @Override
    protected void PresenterGetData() {

    }
}
