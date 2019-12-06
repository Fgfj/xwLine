package com.xacheliangroup.check.moduleNetLine.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleNetLine.fragment.NetLineHomeFragment;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.LoginBean;

/**
 * author:yz
 * data: 2019/9/2,09:12
 */
public class NetLineHomeActivity extends BaseActivity {
    NetLineHomeFragment mNetLineHomeFragment;
    public static final String LOGIN_BEAN="LOGIN_BEAN";
    public static void launch(Context context, Activity activity, LoginBean loginBean){
        Intent intent = new Intent(context, NetLineHomeActivity.class);
        intent.putExtra(LOGIN_BEAN,loginBean);
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
        mNetLineHomeFragment=new NetLineHomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,mNetLineHomeFragment).commit();

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
