package com.xacheliangroup.check.moduleCarComment.fragment;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.base.BaseRecycler.BaseRecyclerAdapter;
import com.xacheliangroup.check.common.base.pullrefresh.RefreshLayout;
import com.xacheliangroup.check.common.base.pullrefresh.core.OnPullListener;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.moduleCarComment.mvp.CommentPresenter;
import com.xacheliangroup.check.moduleCarComment.mvp.bean.CarCommentBean;
import com.xacheliangroup.check.utils.Measure_Utils;
import com.xacheliangroup.check.utils.ProgressHelp;

import java.util.List;

import butterknife.BindView;

/**
 * author:yz
 * data: 2018/12/18,13:58
 */
public class CarCommentFragment extends BaseFragment implements IActionListener.ViewAction {
    private int mPageSize =1;
    private boolean isOnRefresh=true;
    @BindView(R.id.module_cbb_car_comment_rfl)
    RefreshLayout mRefreshLayout;
    @BindView(R.id.module_cbb_car_comment_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.module_cbb_car_comment_status_bar_tx)
    TextView mStatusBarTx;
    CommentPresenter mCommentPresenter;
    BaseRecyclerAdapter<CarCommentBean> mBaseRecyclerAdapter;

    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        mRefreshLayout.stopRefresh(true);
        mRefreshLayout.stopLoadMore(true);
        switch (type){
            case CommentPresenter.TO_GET_CAR_COMMENT_LIST_DATA:
                if(obj!=null&& obj instanceof List){
                    List<CarCommentBean> list= (List<CarCommentBean>) obj;
                    if (isOnRefresh || mPageSize == 1) {
                        mBaseRecyclerAdapter.init(list);
                        mRecyclerView.smoothScrollToPosition(0);
                    } else {
                        mBaseRecyclerAdapter.addAll(list);
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
        return R.layout.module_cbb_fragment_car_comment;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        initView();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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

    private void initView() {
        LinearLayout.LayoutParams mStatusBarTxLayoutParams = (LinearLayout.LayoutParams) mStatusBarTx.getLayoutParams();
        mStatusBarTxLayoutParams.height= Measure_Utils.getStatusBarHeight(getContext());
        mStatusBarTx.setLayoutParams(mStatusBarTxLayoutParams);
        mStatusBarTx.setBackgroundColor(Color.WHITE);
    }

    @Override
    protected void initPresenter() {
        mCommentPresenter=new CommentPresenter(getContext(),this);
        addPresenter(mCommentPresenter);
    }

    @Override
    protected void PresenterGetData() {
        ProgressHelp.showProgress(getContext());
        mCommentPresenter.toGetCarCommentListData(mPageSize+"");
    }


}
