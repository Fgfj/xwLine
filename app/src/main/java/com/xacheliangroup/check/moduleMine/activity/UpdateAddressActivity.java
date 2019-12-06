package com.xacheliangroup.check.moduleMine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.common.type.AddressEnum;
import com.xacheliangroup.check.moduleMine.fragment.UpdateAddressFragment;

/**
 * author:yz
 * data: 2019/2/27,10:00
 */
public class UpdateAddressActivity extends BaseActivity {
    UpdateAddressFragment mUpdateAddressFragment;
    public static final String ADDRESS_ENUM="ADDRESS_ENUM";
    public static final String ADDRESS_DATA_BEAN="ADDRESS_DATA_BEAN";
    public static void launch(Context context, Activity activity,AddressEnum addressEnum){
        Intent intent = new Intent(context, UpdateAddressActivity.class);
        intent.putExtra(ADDRESS_ENUM,addressEnum);
        context.startActivity(intent);
        if(activity!=null){
            activity.overridePendingTransition(R.anim.act_anim_in, R.anim.act_anim_out);
        }else {
            CbbLogUtils.debugLog("launch***UpdateAddressActivity***isnull");
        }
    }
    public static void launch(Context context, Activity activity, AddressEnum addressEnum, BaseBean updateAddressBase){
        Intent intent = new Intent(context, UpdateAddressActivity.class);
        intent.putExtra(ADDRESS_ENUM,addressEnum);
        intent.putExtra(ADDRESS_DATA_BEAN,updateAddressBase);
        context.startActivity(intent);
        if(activity!=null){
            activity.overridePendingTransition(R.anim.act_anim_in, R.anim.act_anim_out);
        }else {
            CbbLogUtils.debugLog("launch***UpdateAddressActivity***isnull");
        }
    }
    @Override
    protected int getContentViewId() {
        return R.layout.module_cbb_all_activity;
    }

    @Override
    protected void onCreateInit() {
        mUpdateAddressFragment=new UpdateAddressFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,mUpdateAddressFragment).commit();

    }

    @Override
    protected void onCreateRequestConnected() {

    }

    @Override
    protected void onCreateRequestNoConnected() {

    }

    @Override
    protected void setFullSreen() {

    }
}
