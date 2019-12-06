package com.xacheliangroup.check.moduleMall.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleMall.fragment.SubmitOrderFragment;

/**
 * author:yz
 * data: 2019/2/22,10:29
 */
public class SubmitOrderActivity extends BaseActivity {
    SubmitOrderFragment mSubmitOrderFragment;
    public static final String GOODS_DETAIL_BEAN="GOODS_DETAIL_BEAN";
    public static void launch(Context context, Activity activity, BaseBean baseBean){
        Intent intent = new Intent(context, SubmitOrderActivity.class);
        intent.putExtra(GOODS_DETAIL_BEAN,baseBean);
        context.startActivity(intent);
        if(activity!=null){
            activity.overridePendingTransition(R.anim.act_anim_in, R.anim.act_anim_out);
        }else {
            CbbLogUtils.debugLog("launch***SubmitOrderActivity***isnull");
        }
    }
    @Override
    protected int getContentViewId() {
        return R.layout.module_cbb_all_activity;
    }

    @Override
    protected void onCreateInit() {
        mSubmitOrderFragment=new SubmitOrderFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,mSubmitOrderFragment).commit();

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
