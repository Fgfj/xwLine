package com.xacheliangroup.check.moduleNetLine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleNetLine.fragment.NetLineMapFragment;

/**
 * author:yz
 * data: 2019/9/2,09:13
 */
public class NetLineMapActivity extends BaseActivity {
    public static final String POINT_RID="POINT_RID";

    NetLineMapFragment mNetLineMapFragment;
    public static void launch(Context context, Activity activity,String rid){
        Intent intent = new Intent(context, NetLineMapActivity.class);
        intent.putExtra(POINT_RID,rid);
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
        mNetLineMapFragment=new NetLineMapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,mNetLineMapFragment).commit();

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
