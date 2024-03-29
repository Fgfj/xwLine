package com.xacheliangroup.check.moduleMine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleMine.fragment.OrderListFragment;

/**
 * author:yz
 * data: 2019/2/28,09:48
 */
public class OrderListActivity extends BaseActivity {
    OrderListFragment mOrderListFragment;
    public static void launch(Context context, Activity activity){
        Intent intent = new Intent(context, OrderListActivity.class);
        context.startActivity(intent);
        if(activity!=null){
            activity.overridePendingTransition(R.anim.act_anim_in, R.anim.act_anim_out);
        }else {
            CbbLogUtils.debugLog("launch***OrderListActivity***isnull");
        }
    }
    @Override
    protected int getContentViewId() {
        return R.layout.module_cbb_all_activity;
    }


    @Override
    protected void onCreateInit() {
        mOrderListFragment=new OrderListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,mOrderListFragment).commit();

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
