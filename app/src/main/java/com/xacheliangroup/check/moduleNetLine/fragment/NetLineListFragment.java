package com.xacheliangroup.check.moduleNetLine.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.base.BaseRecycler.BaseRecyclerAdapter;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.moduleNetLine.activity.NetLineListActivity;
import com.xacheliangroup.check.moduleNetLine.mvp.NetLinePresenter;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.NetLineListBean;
import com.xacheliangroup.check.utils.ProgressHelp;
import com.xacheliangroup.check.utils.ToastUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/9/2,09:14
 */
public class NetLineListFragment extends BaseFragment implements IActionListener.ViewAction {
    @BindView(R.id.module_nl_rcv)
    RecyclerView mRecyclerView;
    @BindView(R.id.module_his_date_tx)
    TextView mDateTx;
    BaseRecyclerAdapter<NetLineListBean> mBaseRecyclerAdapter;
    NetLinePresenter mNetLinePresenter;
    private int mCarId;
    private int mDiverId;

    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type){
            case NetLinePresenter.TO_SELECT_HISTORY:
                if(obj!=null&&obj instanceof List){
                    List<NetLineListBean> lineHistoryBeans= (List<NetLineListBean>) obj;
                    mBaseRecyclerAdapter.init(lineHistoryBeans);
                    mBaseRecyclerAdapter.notifyDataSetChanged();
                    if(lineHistoryBeans.isEmpty()){
                        ToastUtils.showToast(getContext(),"暂无数据");
                    }
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
        return R.layout.module_nl_fragment_list;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        initRecyclerView();
        getData();
    }

    private void getData() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
        String nowDate=formatter.format(date);
        mDateTx.setText(nowDate);
        Intent intent = getActivity().getIntent();
        if(intent!=null){
            mCarId = intent.getIntExtra(NetLineListActivity.CAR_ID, -1);
            mDiverId = intent.getIntExtra(NetLineListActivity.DIVER_ID, -1);
            toGetHistory(mCarId, mDiverId,nowDate);
        }else {
            ToastUtils.showToast(getContext(),"查询失败");
            getActivity().finish();
        }
    }

    private void toGetHistory(int mCarId, int mDiverId,String data) {
        ProgressHelp.showProgress(getContext());
        mNetLinePresenter.toSelectHistory(mCarId,mDiverId,data);

    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBaseRecyclerAdapter = new BaseRecyclerAdapter<>(getContext());
        mRecyclerView.setAdapter(mBaseRecyclerAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initPresenter() {
        mNetLinePresenter=new NetLinePresenter(getContext(),this);
        addPresenter(mNetLinePresenter);
    }

    @Override
    protected void PresenterGetData() {

    }
    @OnClick(R.id.module_nl_back_img)
    public void toBack(){
        getActivity().finish();
    }
    private boolean[] type = new boolean[]{true, true, false, false, false, false};
    @OnClick(R.id.module_nl_rl)
    public void showData(){
//时间选择器
        TimePickerView pvTime = new TimePickerView.Builder(getContext(), new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调

                SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM");
                Date dd = new Date(date.getTime());
                mDateTx.setText(formatter.format(dd));
                toGetHistory(mCarId, mDiverId,formatter.format(dd));
            }
        }).setType(type).build();
        pvTime.setDate(Calendar.getInstance());//注：根据需求来决定是否使用该方法（一般是精确到秒的情况），此项可以在弹出选择器的时候重新设置当前时间，避免在初始化之后由于时间已经设定，导致选中时间与当前时间不匹配的问题。
        pvTime.show();
    }


}
