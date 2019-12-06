package com.xacheliangroup.check.moduleMine.fragment;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.base.BaseRecycler.BaseRecyclerAdapter;
import com.xacheliangroup.check.common.base.BaseRecycler.OnItemClickListener;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.type.OrderListTabEnum;
import com.xacheliangroup.check.moduleMine.activity.OrderDetailActivity;
import com.xacheliangroup.check.moduleMine.mvp.MinePresenter;
import com.xacheliangroup.check.moduleMine.mvp.bean.OrderListItemBean;
import com.xacheliangroup.check.utils.ProgressHelp;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/2/28,09:49
 */
public class OrderListFragment extends BaseFragment implements IActionListener.ViewAction {
    MinePresenter mMinePresenter;
    @BindView(R.id.module_mine_order_list_toolbar_tab_ly)
    TabLayout mTableLayout;
    @BindView(R.id.module_mine_order_list_rv)
    RecyclerView mRecyclerView;
    BaseRecyclerAdapter<OrderListItemBean> mBeanBaseRecyclerAdapter;
    public String mOrderType;
    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type){
            case MinePresenter.TO_GET_ORDER_LIST_DATA:
                if(obj!=null&&obj instanceof List){
                    List<OrderListItemBean> listItemBeans= (List<OrderListItemBean>) obj;
                    mBeanBaseRecyclerAdapter.init(listItemBeans);
                    mBeanBaseRecyclerAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_cbb_fragment_order_list;
    }

    @Override
    protected void onCreateViewInit(View parentView) {

        initTableLayout();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBeanBaseRecyclerAdapter=new BaseRecyclerAdapter<>(getActivity());
        mRecyclerView.setAdapter(mBeanBaseRecyclerAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);

        mBeanBaseRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position, Object obj) {
                if(obj!=null&&obj instanceof OrderListItemBean){
                    OrderListItemBean orderListItemBean= (OrderListItemBean) obj;
                    OrderDetailActivity.launch(getContext(),getActivity(),orderListItemBean);
                }
            }

            @Override
            public boolean onItemLongClicked(int position, Object obj) {
                return false;
            }
        });

    }

    private void initTableLayout() {
        //tab可滚动
        mTableLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
//        mTableLayout.setTabMode(TabLayout.MODE_FIXED);
        //tab居中显示
        mTableLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        //tab的字体选择器,默认黑色,选择时红色
        mTableLayout.setTabTextColors(Color.parseColor("#666666"),Color.parseColor("#333333"));
        //tab的下划线颜色,默认是粉红色,如果要自定义选中效果,则可以将下划线设置为和背景色一样.
        mTableLayout.setSelectedTabIndicatorColor(Color.parseColor("#df1f01"));
        mTableLayout.addTab(mTableLayout.newTab().setText(OrderListTabEnum.TAB_ALL_ORDER.getExplain()));
        mTableLayout.addTab(mTableLayout.newTab().setText(OrderListTabEnum.TAB_WAIT_PAY_ORDER.getExplain()));
        mTableLayout.addTab(mTableLayout.newTab().setText(OrderListTabEnum.TAB_WAIT_SEND_ORDER.getExplain()));
        mTableLayout.addTab(mTableLayout.newTab().setText(OrderListTabEnum.TAB_WAIT_GET_ORDER.getExplain() ));
        mTableLayout.addTab(mTableLayout.newTab().setText(OrderListTabEnum.TAB_WAIT_COMMENT_ORDER.getExplain()));
        mTableLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中当前位置
                isSelected(tab, true);
                mOrderType=OrderListTabEnum.getSelectTypeByPosition(tab.getPosition());
                PresenterGetData();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //tab未选中
                isSelected(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //tab重新选中
                isSelected(tab,true);
            }
        });
        isSelected(mTableLayout.getTabAt(0), true);
    }
    /**
     * 设置选中的tab是否带缩放效果
     * @param tab
     * @param isSelected
     */
    private void isSelected(TabLayout.Tab tab, boolean isSelected) {
        TextView titleTx = (TextView)(((LinearLayout) ((LinearLayout) mTableLayout.getChildAt(0)).getChildAt(tab.getPosition())).getChildAt(1));
        if(titleTx!=null){
            titleTx.setScaleX(isSelected ? 1.0f : 1.0f);
            titleTx.setScaleY(isSelected ? 1.0f : 1.0f);
        }
    }


    @Override
    protected void initPresenter() {
        mMinePresenter=new MinePresenter(getContext(),this);
        addPresenter(mMinePresenter);
    }

    @Override
    protected void PresenterGetData() {
        ProgressHelp.showProgress(getContext());
        mMinePresenter.toGetOrderListData(mOrderType);
    }
    @OnClick(R.id.module_cbb_mine_order_list_back_fl)
    public void toBack(){
        getActivity().finish();
    }


}
