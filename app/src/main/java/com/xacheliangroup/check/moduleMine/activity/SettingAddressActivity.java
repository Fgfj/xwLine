package com.xacheliangroup.check.moduleMine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.common.type.AddressEnum;
import com.xacheliangroup.check.moduleMine.fragment.SettingAddressFragment;

/**
 * author:yz
 * data: 2019/2/26,16:03
 */
public class SettingAddressActivity extends BaseActivity {
    SettingAddressFragment mSettingAddressFragment;
    public static final String ADDRESS_IN_ENUM="ADDRESS_IN_ENUM";
    public static void launch(Context context, Activity activity, AddressEnum addressEnum){
        Intent intent = new Intent(context, SettingAddressActivity.class);
        intent.putExtra(ADDRESS_IN_ENUM,addressEnum);
        context.startActivity(intent);
        if(activity!=null){
            activity.overridePendingTransition(R.anim.act_anim_in, R.anim.act_anim_out);
        }else {
            CbbLogUtils.debugLog("launch***SettingAddressActivity***isnull");
        }
    }
    @Override
    protected int getContentViewId() {
        return R.layout.module_cbb_all_activity;
    }


    @Override
    protected void onCreateInit() {
        mSettingAddressFragment=new SettingAddressFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,mSettingAddressFragment).commit();

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
