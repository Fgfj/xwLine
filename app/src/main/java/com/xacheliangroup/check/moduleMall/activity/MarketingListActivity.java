package com.xacheliangroup.check.moduleMall.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.common.type.MarketingEnum;
import com.xacheliangroup.check.moduleMall.fragment.MarketingListFragment;

/**
 * author:yz
 * data: 2019/2/20,10:25
 */
public class MarketingListActivity extends BaseActivity {
    MarketingListFragment marketingListFragment;
    public static final String MARKETING_TYPE="MARKETING_TYPE";
    public static final String MARKETING_DATA="MARKETING_DATA";
    public static void launch(Context context, Activity activity, MarketingEnum marketingEnum, BaseBean baseBean){
        Intent intent = new Intent(context, MarketingListActivity.class);
        intent.putExtra(MARKETING_TYPE,marketingEnum);
        intent.putExtra(MARKETING_DATA,baseBean);
        context.startActivity(intent);
        if(activity!=null){
            activity.overridePendingTransition(R.anim.act_anim_in, R.anim.act_anim_out);
        }else {
            CbbLogUtils.debugLog("launch***MarketingListActivity***isnull");
        }
    }
    @Override
    protected int getContentViewId() {
        return R.layout.module_cbb_all_activity;
    }

    @Override
    protected void onCreateInit() {
        marketingListFragment=new MarketingListFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,marketingListFragment).commit();
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
