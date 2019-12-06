package com.xacheliangroup.check.moduleMall.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleMall.fragment.GoodsDetailFragment;

/**
 * author:yz
 * data: 2019/2/21,10:19
 */
public class GoodsDetailActivity extends BaseActivity {
    GoodsDetailFragment mGoodsDetailFragment;
    public static final String GOODS_ID="GOODS_ID";
    public static void launch(Context context, Activity activity,String goodId){
        Intent intent = new Intent(context, GoodsDetailActivity.class);
        intent.putExtra(GOODS_ID,goodId);
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
        mGoodsDetailFragment=new GoodsDetailFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,mGoodsDetailFragment).commit();
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
