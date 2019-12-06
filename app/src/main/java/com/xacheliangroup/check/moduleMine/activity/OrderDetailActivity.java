package com.xacheliangroup.check.moduleMine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleMine.fragment.OrderDetailFragment;
import com.xacheliangroup.check.moduleMine.mvp.bean.OrderListItemBean;

/**
 * author:yz
 * data: 2019/2/28,11:47
 */
public class OrderDetailActivity extends BaseActivity {
    public static final String ORDER_LIST_BEAN="ORDER_LIST_BEAN";
    OrderDetailFragment mOrderDetailFragment;
    public static void launch(Context context, Activity activity, OrderListItemBean orderListItemBean){
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(ORDER_LIST_BEAN,orderListItemBean);
        context.startActivity(intent);
        if(activity!=null){
            activity.overridePendingTransition(R.anim.act_anim_in, R.anim.act_anim_out);
        }else {
            CbbLogUtils.debugLog("launch***OrderDetailActivity***isnull");
        }
    }
    @Override
    protected int getContentViewId() {
        return R.layout.module_cbb_all_activity;
    }



    @Override
    protected void onCreateInit() {
        mOrderDetailFragment=new OrderDetailFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,mOrderDetailFragment).commit();

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
