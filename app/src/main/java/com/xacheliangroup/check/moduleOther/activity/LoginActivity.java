package com.xacheliangroup.check.moduleOther.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleOther.fragment.LoginFragment;

/**
 * author:yz
 * data: 2018/12/24,16:57
 */
public class LoginActivity extends BaseActivity {
    LoginFragment loginFragment;
    public static void launch(Context context, Activity activity){
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
        if(activity!=null){
            activity.overridePendingTransition(R.anim.act_anim_in, R.anim.act_anim_out);
        }else {
            CbbLogUtils.debugLog("launch***ActActivity***isnull");
        }
    }
    @Override
    protected int getContentViewId() {
        return R.layout.module_cbb_all_activity;
    }

    @Override
    protected void onCreateInit() {
        loginFragment=new LoginFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,loginFragment).commit();


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
