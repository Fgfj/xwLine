package com.xacheliangroup.check.moduleMine.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.base.BaseRecycler.BaseRecyclerAdapter;
import com.xacheliangroup.check.common.base.BaseRecycler.OnItemClickListener;
import com.xacheliangroup.check.common.eventbus.MessageEvent;
import com.xacheliangroup.check.common.flag.Flag;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.type.AddressEnum;
import com.xacheliangroup.check.moduleMine.activity.SettingAddressActivity;
import com.xacheliangroup.check.moduleMine.activity.UpdateAddressActivity;
import com.xacheliangroup.check.moduleMine.mvp.MinePresenter;
import com.xacheliangroup.check.moduleMine.mvp.bean.AddressItemBean;
import com.xacheliangroup.check.utils.ProgressHelp;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/2/26,16:04
 */
public class SettingAddressFragment extends BaseFragment implements IActionListener.ViewAction {
    MinePresenter mMinePresenter;
    @BindView(R.id.module_fra_mine_setting_address_rv)
    RecyclerView mRecyclerView;
    BaseRecyclerAdapter<AddressItemBean> mBeanBaseRecyclerAdapter;
    private AddressEnum mAddressEnum;


    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type){
            case MinePresenter.TO_GET_ADDRESS_LIST_DATA:
                if(obj!=null&&obj instanceof List){
                    List<AddressItemBean> list= (List<AddressItemBean>) obj;
                    mBeanBaseRecyclerAdapter.init(list);
                    mBeanBaseRecyclerAdapter.notifyDataSetChanged();
                }
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case Flag.EVENT.UPDATE_ADDRESS:
                PresenterGetData();
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
        return R.layout.module_cbb_fragment_setting_address;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        setEventBusAction();
        initRecyclerView();
        getData();
    }

    private void getData() {
        Intent intent = getActivity().getIntent();
         if(intent!=null&&intent.hasExtra(SettingAddressActivity.ADDRESS_IN_ENUM)){
             mAddressEnum = (AddressEnum) intent.getSerializableExtra(SettingAddressActivity.ADDRESS_IN_ENUM);
         }
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBeanBaseRecyclerAdapter=new BaseRecyclerAdapter<>(getActivity());
        mRecyclerView.setAdapter(mBeanBaseRecyclerAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
        mBeanBaseRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClicked(int position, Object obj) {
                if(obj!=null&&obj instanceof AddressItemBean){
                    AddressItemBean addressItemBean= (AddressItemBean) obj;
                    //下单选择用户地址
                    switch (mAddressEnum){
                        case PAY_ORDER_IN:
                            EventBus.getDefault().post(new MessageEvent(Flag.EVENT.PAY_GOODS_CHOICE_ADDRESS,addressItemBean));
                            getActivity().finish();
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

    @Override
    protected void initPresenter() {
        mMinePresenter=new MinePresenter(getContext(),this);
        addPresenter(mMinePresenter);
    }

    @Override
    protected void PresenterGetData() {
        ProgressHelp.showProgress(getContext());
        mMinePresenter.toGetAddressListData();

    }
    @OnClick(R.id.module_fra_mine_setting_address_back_fl)
    public void toBack(){
        getActivity().finish();
    }
    @OnClick(R.id.module_fra_mine_to_update_address_rl)
    public void toUpdateAddress(){
        UpdateAddressActivity.launch(getContext(),getActivity(), AddressEnum.ADD_ADDRESS);
    }

}
