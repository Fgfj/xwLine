package com.xacheliangroup.check.moduleNetLine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleNetLine.fragment.NetLineListFragment;

/**
 * author:yz
 * data: 2019/9/2,09:13
 */
public class NetLineListActivity extends BaseActivity {
    NetLineListFragment mNetLineListFragment;
    public static final String CAR_ID="CAR_ID";
    public static final String DIVER_ID="DIVER_ID";
    public static void launch(Context context, Activity activity,int cId,int dId){
        Intent intent = new Intent(context, NetLineListActivity.class);
        intent.putExtra(CAR_ID,cId);
        intent.putExtra(DIVER_ID,dId);
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
        mNetLineListFragment=new NetLineListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,mNetLineListFragment).commit();

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
