package com.xacheliangroup.check.moduleMine.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.eventbus.MessageEvent;
import com.xacheliangroup.check.common.flag.Flag;
import com.xacheliangroup.check.common.glide.GlideUtils;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.common.type.AddressEnum;
import com.xacheliangroup.check.moduleMine.activity.UpdateAddressActivity;
import com.xacheliangroup.check.moduleMine.mvp.MinePresenter;
import com.xacheliangroup.check.moduleMine.mvp.bean.AddressItemBean;
import com.xacheliangroup.check.utils.ProgressHelp;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/2/27,10:01
 */
public class UpdateAddressFragment extends BaseFragment implements IActionListener.ViewAction {

    MinePresenter mMinePresenter;
    private boolean isChoiceAddress;
    @BindView(R.id.module_fra_mine_update_address_address_et)
    EditText mAddressEt;
    @BindView(R.id.module_fra_mine_update_address_phone_et)
    EditText mPhoneEt;
    @BindView(R.id.module_fra_mine_update_address_name_et)
    EditText mNameEt;
    @BindView(R.id.module_fra_mine_update_address_choice_img)
    ImageView mChoiceImg;

    private AddressEnum mAddressEnum;
    private AddressItemBean mAddressItemBean;
    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type){
            case MinePresenter.TO_ADD_ADDRESS:
                if(obj!=null){
                    updateAddressList();
                }
                break;
            case MinePresenter.TO_UPDATE_ADDRESS:
                if(obj!=null){
                    updateAddressList();
                }
                break;
        }
    }

    private void updateAddressList() {
        EventBus.getDefault().post(new MessageEvent(Flag.EVENT.UPDATE_ADDRESS,null));
        getActivity().finish();
    }

    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_cbb_fragment_update_address;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        getData();

    }

    private void getData() {
        Intent intent = getActivity().getIntent();
         if(intent!=null&&intent.hasExtra(UpdateAddressActivity.ADDRESS_ENUM)){
             mAddressEnum = (AddressEnum) intent.getSerializableExtra(UpdateAddressActivity.ADDRESS_ENUM);
             switch (mAddressEnum){
                 case UPDATE_ADDRESS:
                        if(intent.hasExtra(UpdateAddressActivity.ADDRESS_DATA_BEAN)){
                            mAddressItemBean = (AddressItemBean) intent.getSerializableExtra(UpdateAddressActivity.ADDRESS_DATA_BEAN);
                            changeUpdateAddressView(mAddressItemBean);
                        }else {
                            CbbLogUtils.debugLog("修改地址无bean");
                        }
                     break;
                 case ADD_ADDRESS:

                     break;
             }
         }else {
             CbbLogUtils.debugLog("地址进入分类错误");
         }
    }

    private void changeUpdateAddressView(AddressItemBean mAddressItemBean) {
        mNameEt.setText(mAddressItemBean.getName());
        mPhoneEt.setText(mAddressItemBean.getPhoneNumber());
        mAddressEt.setText(mAddressItemBean.getAddress());
        if(TextUtils.equals(mAddressItemBean.getIfDefault(),"0")){
            GlideUtils.loadIntRectImage(getContext(),R.drawable.module_cbb_mine_update_address_choice_icon,mChoiceImg);
            isChoiceAddress=true;
        }else {
            GlideUtils.loadIntRectImage(getContext(),R.drawable.module_cbb_mine_update_address_default_icon,mChoiceImg);
        }
    }

    @Override
    protected void initPresenter() {
        mMinePresenter=new MinePresenter(getContext(),this);
        addPresenter(mMinePresenter);
    }

    @Override
    protected void PresenterGetData() {

    }
    @OnClick(R.id.module_fra_mine_update_address_back_fl)
    public void toBack(){
        getActivity().finish();
    }
    @OnClick(R.id.module_fra_mine_update_address_submit_rl)
    public void toSubmitRl(){
        ProgressHelp.showProgress(getContext());
        switch (mAddressEnum){
            case ADD_ADDRESS:
                mMinePresenter.toAddAddress(mNameEt.getText().toString(),mPhoneEt.getText().toString(),
                        mAddressEt.getText().toString(),(isChoiceAddress)? "0":"1");
                break;
            case UPDATE_ADDRESS:
                mMinePresenter.toUpdateAddress(mAddressItemBean.getAddressId(),mNameEt.getText().toString(),mPhoneEt.getText().toString(),
                        mAddressEt.getText().toString(),(isChoiceAddress)? "0":"1");
                break;
        }
    }
    @OnClick(R.id.module_fra_mine_update_address_choice_img)
    public void choiceDefaultAddress(){
         if(isChoiceAddress){
             isChoiceAddress=false;
             GlideUtils.loadIntRectImage(getContext(),R.drawable.module_cbb_mine_update_address_default_icon,mChoiceImg);
         }else {
             isChoiceAddress=true;
             GlideUtils.loadIntRectImage(getContext(),R.drawable.module_cbb_mine_update_address_choice_icon,mChoiceImg);
         }
    }


}
