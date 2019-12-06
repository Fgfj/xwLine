package com.xacheliangroup.check.checkTickets.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.checkTickets.fragment.CTCarDetailFragment;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;

/**
 * author:yz
 * data: 2019/5/15,16:09
 */
public class CTCarDetailActivity extends BaseActivity {
    CTCarDetailFragment ctCarDetailFragment;
    public static final String ID_CARD_NO="ID_CARD_NO";
    public static void launch(Context context, Activity activity,String idCard){
        Intent intent = new Intent(context, CTCarDetailActivity.class);
        intent.putExtra(ID_CARD_NO,idCard);
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
        ctCarDetailFragment=new CTCarDetailFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,ctCarDetailFragment).commit();

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
