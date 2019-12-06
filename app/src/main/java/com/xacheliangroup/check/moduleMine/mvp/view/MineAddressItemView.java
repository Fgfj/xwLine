package com.xacheliangroup.check.moduleMine.mvp.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;
import com.xacheliangroup.check.common.type.AddressEnum;
import com.xacheliangroup.check.moduleMine.activity.UpdateAddressActivity;
import com.xacheliangroup.check.moduleMine.mvp.bean.AddressItemBean;

import butterknife.BindView;

/**
 * author:yz
 * data: 2019/2/26,16:56
 */
public class MineAddressItemView extends ViewSugar {
    @BindView(R.id.module_cbb_address_item_name_tx)
    TextView mNameTx;
    @BindView(R.id.module_cbb_address_item_phone_tx)
    TextView mPhoenTx;
    @BindView(R.id.module_cbb_address_item_address_tx)
    TextView mAddressTx;
    @BindView(R.id.module_cbb_address_item_default_rl)
    RelativeLayout mDefaultRl;
    @BindView(R.id.module_cbb_address_item_update_img)
    ImageView mUpdateImg;
    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new MineAddressItemView(context,parent);
    }
    public MineAddressItemView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_cbb_recy_item_address;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
         if(obj!=null&&obj instanceof AddressItemBean){
             final AddressItemBean addressItemBean= (AddressItemBean) obj;
             mNameTx.setText(addressItemBean.getName());
             mPhoenTx.setText(addressItemBean.getPhoneNumber());
             mAddressTx.setText(addressItemBean.getAddress());
             if(TextUtils.equals(addressItemBean.getIfDefault(),"0")){//是否是默认地址 0：是默认地址 1：不是默认地址
                 mDefaultRl.setVisibility(View.VISIBLE);
             }else {
                 mDefaultRl.setVisibility(View.GONE);
             }
             mUpdateImg.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     UpdateAddressActivity.launch(context,(Activity) context, AddressEnum.UPDATE_ADDRESS,addressItemBean);
                 }
             });

         }
    }
}
