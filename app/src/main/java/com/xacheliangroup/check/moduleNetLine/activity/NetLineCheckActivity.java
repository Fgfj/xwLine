package com.xacheliangroup.check.moduleNetLine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleNetLine.fragment.NetLineCheckFragment;

/**
 * author:yz
 * data: 2019/9/3,21:43
 */
public class NetLineCheckActivity extends BaseActivity {
    NetLineCheckFragment mNetLineCheckFragment;
    public static final String QR_CODE="QR_CODE";
    public static void launch(Context context, Activity activity,String code){
        Intent intent = new Intent(context, NetLineCheckActivity.class);
        intent.putExtra(QR_CODE,code);
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
        mNetLineCheckFragment=new NetLineCheckFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,mNetLineCheckFragment).commit();
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
