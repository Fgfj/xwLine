package com.xacheliangroup.check.moduleNetLine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleNetLine.fragment.NetLineLoginFragment;

/**
 * author:yz
 * data: 2019/9/2,09:11
 */
public class NetLineLoginActivity extends BaseActivity {
    NetLineLoginFragment mNetLineLoginFragment;
    public static void launch(Context context, Activity activity){
        Intent intent = new Intent(context, NetLineLoginActivity.class);
        context.startActivity(intent);
        if(activity!=null){
            activity.overridePendingTransition(R.anim.act_anim_in, R.anim.act_anim_out);
        }else {
            CbbLogUtils.debugLog("launch***GoodsDetailActivity***isnull");
        }
    }
    @Override
    protected int getContentViewId() {
        return R.layout.module_cbb_all_activity;
    }

    @Override
    protected void onCreateInit() {
        mNetLineLoginFragment=new NetLineLoginFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,mNetLineLoginFragment).commit();

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
