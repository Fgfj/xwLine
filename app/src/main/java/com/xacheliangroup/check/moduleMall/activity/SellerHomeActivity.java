package com.xacheliangroup.check.moduleMall.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleMall.fragment.SellerHomeFragment;

/**
 * author:yz
 * data: 2019/2/18,15:45
 */
public class SellerHomeActivity extends BaseActivity {
    SellerHomeFragment sellerHomeFragment;
    public static final String SELLER_ID="SELLER_ID";
    public static void launch(Context context, Activity activity,String id){
        Intent intent = new Intent(context, SellerHomeActivity.class);
        intent.putExtra(SELLER_ID,id);
        context.startActivity(intent);
        if(activity!=null){
            activity.overridePendingTransition(R.anim.act_anim_in, R.anim.act_anim_out);
        }else {
            CbbLogUtils.debugLog("launch***SellerHomeActivity***isnull");
        }
    }
    @Override
    protected int getContentViewId() {
        return R.layout.module_cbb_all_activity;
    }

    @Override
    protected void onCreateInit() {
        sellerHomeFragment=new SellerHomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,sellerHomeFragment).commit();


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
