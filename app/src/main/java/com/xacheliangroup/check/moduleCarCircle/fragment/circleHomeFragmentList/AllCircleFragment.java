package com.xacheliangroup.check.moduleCarCircle.fragment.circleHomeFragmentList;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.base.BaseRecycler.BaseRecyclerAdapter;
import com.xacheliangroup.check.common.base.pullrefresh.RefreshLayout;
import com.xacheliangroup.check.common.base.pullrefresh.core.OnPullListener;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.moduleCarCircle.mvp.CirclePresenter;
import com.xacheliangroup.check.moduleCarCircle.mvp.bean.CarCircleItemBean;
import com.xacheliangroup.check.utils.ProgressHelp;

import java.util.List;

import butterknife.BindView;

/**
 * author:yz
 * data: 2018/12/24,10:20
 */
public class AllCircleFragment extends BaseFragment implements IActionListener.ViewAction {
    private static final String mAllType="1";
    private int mPageSize=1;
    CirclePresenter mCirclePresenter;
    @BindView(R.id.module_Cbb_car_circle_all_rl)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.module_Cbb_car_circle_all_rv)
    RecyclerView mRecyclerView;
    BaseRecyclerAdapter<CarCircleItemBean> mBaseRecyclerAdapter;
    private boolean isOnRefresh=true;
    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        mRefreshLayout.stopRefresh(true);
        mRefreshLayout.stopLoadMore(true);
        switch (type){
            case CirclePresenter.TO_GET_CIRCLE_LIST_DATA:
                if(obj!=null&&obj instanceof List){
                    List<CarCircleItemBean> mCarCircleItemBeanList= (List<CarCircleItemBean>) obj;
                    if (isOnRefresh || mPageSize == 1) {
                        mBaseRecyclerAdapter.init(mCarCircleItemBeanList);
                    } else {
                        mBaseRecyclerAdapter.addAll(mCarCircleItemBeanList);
                    }
                    mBaseRecyclerAdapter.notifyDataSetChanged();
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
        return R.layout.module_cbb_fragment_circle_all;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        initRecyclerView();
    }

    private void initRecyclerView() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager
                .VERTICAL);
        staggeredGridLayoutManager.setAutoMeasureEnabled(true);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mBaseRecyclerAdapter=new BaseRecyclerAdapter<>(getContext());
        mRecyclerView.setAdapter(mBaseRecyclerAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRefreshLayout.setOnPullListener(new OnPullListener() {
            @Override
            public void onRefresh() {
                //刷新
                mPageSize=1;
                isOnRefresh=true;
                PresenterGetData();

            }

            @Override
            public void onLoadMore() {
                mPageSize++;
                isOnRefresh=false;
                PresenterGetData();
            }
        });
    }

    @Override
    protected void initPresenter() {
        mCirclePresenter=new CirclePresenter(getContext(),this);
        addPresenter(mCirclePresenter);
    }

    @Override
    protected void PresenterGetData() {
        ProgressHelp.showProgress(getContext());
        mCirclePresenter.toGetCarCircleListData(mAllType,mPageSize+"");
    }


}
