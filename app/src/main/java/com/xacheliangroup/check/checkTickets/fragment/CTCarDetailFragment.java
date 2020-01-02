package com.xacheliangroup.check.checkTickets.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.checkTickets.activity.CTCarDetailActivity;
import com.xacheliangroup.check.checkTickets.activity.CTCheckActivity;
import com.xacheliangroup.check.checkTickets.mvp.CTPresenter;
import com.xacheliangroup.check.checkTickets.mvp.Utils;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTCarListBean;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.base.BaseRecycler.BaseRecyclerAdapter;
import com.xacheliangroup.check.common.base.BaseRecycler.OnItemClickListener;
import com.xacheliangroup.check.common.eventbus.MessageEvent;
import com.xacheliangroup.check.common.flag.Flag;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.utils.ProgressHelp;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/5/15,16:10
 */
public class CTCarDetailFragment extends BaseFragment implements IActionListener.ViewAction {
    private Intent intent;
    private String mIDCardNo;
    @BindView(R.id.module_ctt_car_detail_rv)
    RecyclerView mRecyclerView;
    @BindView(R.id.module_ct_up_car_fl)
    FrameLayout mUpCicrelFl;
    @BindView(R.id.module_ct_live_car_fl)
    FrameLayout mLiveCicrelFl;

    BaseRecyclerAdapter<CTCarListBean.DataBean> mBaseRecyclerAdapter;

    CTPresenter mCtPresenter;
    public String mType="0";

    private static final String SN=Utils.getDeviceSN();

    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type){
            case CTPresenter.TO_GET_CAT_LIST:
                if(obj!=null){
                    CTCarListBean ctCarListBean= (CTCarListBean) obj;
                    List<CTCarListBean.DataBean> listBeans= ctCarListBean.getData();
                    CbbLogUtils.debugLog(listBeans);
                    for(int i=0;i<listBeans.size();i++){
                        listBeans.get(i).setType(mType);
                        listBeans.get(i).setStationName(ctCarListBean.getStationname());
                    }
                    mBaseRecyclerAdapter.init(listBeans);
                    mBaseRecyclerAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case Flag.CHECK.UPDATE_CAR_LIST:
                    toGetCarList();
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
        return R.layout.module_ct_fragment_car_detail;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        setEventBusAction();
        getData();
//        getDeviceSn();
        initRecyclerView();
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBaseRecyclerAdapter=new BaseRecyclerAdapter<>(getContext());
        mRecyclerView.setAdapter(mBaseRecyclerAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);

        mBaseRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position, Object obj) {
                if(obj!=null&&obj instanceof  CTCarListBean.DataBean){
                    CTCarListBean.DataBean ctCarListBean= (CTCarListBean.DataBean) obj;
                    toCheck(ctCarListBean);
                }

            }

            @Override
            public boolean onItemLongClicked(int position, Object obj) {
                return false;
            }
        });
    }

    private void getData() {
        intent = getActivity().getIntent();
        if(intent!=null&&intent.hasExtra(CTCarDetailActivity.ID_CARD_NO)){
            mIDCardNo = intent.getStringExtra(CTCarDetailActivity.ID_CARD_NO);
//            ToastUtils.showToast(getContext(),"身份证号码"+mIDCardNo);
        }
    }

    private void getDeviceSn() {

    }

    @Override
    protected void initPresenter() {

        mCtPresenter=new CTPresenter(getContext(),this);
        addPresenter(mCtPresenter);
    }

    @Override
    protected void PresenterGetData() {
        getDeviceSn();
        toGetCarList();
    }

    public void toGetCarList(){
        ProgressHelp.showProgress(getContext());
        //0证件号1结算单（报班单）号2车号
        String searchsource="0";
        if(mIDCardNo.length()==18){
            searchsource="0";
        }else if(mIDCardNo.length()==7||mIDCardNo.length()==8){
            searchsource="2";
        }
        if(TextUtils.equals(mIDCardNo,"0609")){
            mCtPresenter.toGetCarList(SN,"610109199905289956",mType,searchsource);
        }else {
            mCtPresenter.toGetCarList(SN,mIDCardNo,mType,searchsource);
        }
    }
    @OnClick(R.id.module_ct_car_detail_back_img)
    public void toBack(){
        getActivity().finish();
    }

    public void toCheck(CTCarListBean.DataBean ctCarListBean){
        CTCheckActivity.launch(getContext(),getActivity(),ctCarListBean,mType);
    }
    @OnClick(R.id.module_ct_up_car_ly)
    public void toUpCarOnClick(){
        mType="0";
        mUpCicrelFl.setVisibility(View.VISIBLE);
        mLiveCicrelFl.setVisibility(View.INVISIBLE);
        toGetCarList();
    }
    @OnClick(R.id.module_ct_live_car_ly)
    public void toLiveCarOnclick(){
        mType="1";
        mLiveCicrelFl.setVisibility(View.VISIBLE);
        mUpCicrelFl.setVisibility(View.INVISIBLE);
        toGetCarList();
    }

    @Override
    public void onResume() {
        super.onResume();

    }


}
