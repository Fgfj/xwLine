package com.xacheliangroup.check.moduleMall.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleMall.fragment.PayOrderFragment;

/**
 * author:yz
 * data: 2019/2/22,11:26
 */
public class PayOrderActivity extends BaseActivity {
    PayOrderFragment mPayOrderFragment;
    public static void launch(Context context, Activity activity){
        Intent intent = new Intent(context, PayOrderActivity.class);
        context.startActivity(intent);
        if(activity!=null){
            activity.overridePendingTransition(R.anim.act_anim_in, R.anim.act_anim_out);
        }else {
            CbbLogUtils.debugLog("launch***PayOrderActivity***isnull");
        }
    }
    @Override
    protected int getContentViewId() {
        return R.layout.module_cbb_all_activity;
    }

    @Override
    protected void onCreateInit() {
        mPayOrderFragment=new PayOrderFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,mPayOrderFragment).commit();

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
