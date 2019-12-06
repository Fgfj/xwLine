package com.xacheliangroup.check.checkTickets.activity;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.checkTickets.fragment.CTChoiceStationFragment;
import com.xacheliangroup.check.common.base.BaseActivity;

/**
 * author:yz
 * data: 2019/6/18,11:39
 */
public class CTChoiceStationActivity extends BaseActivity {
    CTChoiceStationFragment mCtChoiceStationFragment;

    @Override
    protected int getContentViewId() {
        return R.layout.module_cbb_all_activity;
    }

    @Override
    protected void onCreateInit() {
        mCtChoiceStationFragment=new CTChoiceStationFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container,mCtChoiceStationFragment).commit();

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
