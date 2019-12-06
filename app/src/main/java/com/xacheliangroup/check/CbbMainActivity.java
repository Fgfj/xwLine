package com.xacheliangroup.check;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleCarCircle.fragment.CarCircleFragment;
import com.xacheliangroup.check.moduleCarComment.fragment.CarCommentFragment;
import com.xacheliangroup.check.moduleMall.fragment.MallCbbFragment;
import com.xacheliangroup.check.moduleMine.fragment.MineFragment;
import com.xacheliangroup.check.utils.ExitBackCallBack;
import com.xacheliangroup.check.utils.ExitBackUtils;
import com.xacheliangroup.check.utils.Measure_Utils;

import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.OnClick;

public class CbbMainActivity extends BaseActivity {


    @BindView(R.id.module_cbb_home_mall_ly_tx)
    TextView mMallTx;
    @BindView(R.id.module_cbb_home_car_circle_ly_tx)
    TextView mCarCircleTx;
    @BindView(R.id.module_cbb_home_car_comment_ly_tx)
    TextView mCarCommentTx;
    @BindView(R.id.module_cbb_home_mine_ly_tx)
    TextView mMineTx;

    @BindView(R.id.module_cbb_home_mall_ly_img)
    ImageView mMallImg;
    @BindView(R.id.module_cbb_home_car_circle_ly_img)
    ImageView mCarCircleImg;
    @BindView(R.id.module_cbb_home_car_comment_ly_img)
    ImageView mCarCommentImg;
    @BindView(R.id.module_cbb_home_mine_ly_img)
    ImageView mMineImg;

    @BindDrawable(R.drawable.module_cbb_home_mall_nomarl_icon)
    Drawable mMallDefaultIcon;
    @BindDrawable(R.drawable.module_cbb_home_mall_focus_icon)
    Drawable mMallFocusIcon;

    @BindDrawable(R.drawable.module_cbb_home_car_circle_nomarl_icon)
    Drawable mCarCircleDefaultIcon;
    @BindDrawable(R.drawable.module_cbb_home_car_circle_focus_icon)
    Drawable mCarCircleFocusIcon;

    @BindDrawable(R.drawable.module_cbb_home_car_comment_nomarl_icon)
    Drawable mCarCommentDefaultIcon;
    @BindDrawable(R.drawable.module_cbb_home_car_comment_focus_icon)
    Drawable mCarCommentFocusIcon;

    @BindDrawable(R.drawable.module_cbb_home_mine_nomarl_icon)
    Drawable mMineDefaultIcon;
    @BindDrawable(R.drawable.module_cbb_home_mine_focus_icon)
    Drawable mMineFocusIcon;

    int textColor=R.color.secondaryTextPrimary;//未点击颜色
    int textPressColor=R.color.Primary;//点击颜色


    MallCbbFragment mMallCbbFragment;
    CarCircleFragment mCarCircleFragment;
    CarCommentFragment mCarCommentFragment;
    MineFragment mMineFragment;
    private Fragment mSelectFragment;

    public static void launch(Context context, Activity activity){
        Intent intent = new Intent(context, CbbMainActivity.class);
        context.startActivity(intent);
        if(activity!=null){
            activity.overridePendingTransition(R.anim.act_anim_in, R.anim.act_anim_out);
        }else {
            CbbLogUtils.debugLog("launch***CbbMainActivity***isnull");
        }
    }
    @Override
    protected int getContentViewId() {
        return R.layout.module_cbb_activity_main;
    }

    @Override
    protected void onCreateInit() {
        showFragment(0);
    }

    @Override
    protected void onCreateRequestConnected() {

    }

    @Override
    protected void onCreateRequestNoConnected() {

    }


    @Override
    protected void setFullSreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //全屏 但是影响键盘
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().setStatusBarColor(Color.argb(0,0,0,0));
        }
    }

    private void restoreDefaultEffect() {
        mMallImg.setImageDrawable(mMallDefaultIcon);
        mCarCircleImg.setImageDrawable(mCarCircleDefaultIcon);
        mCarCommentImg.setImageDrawable(mCarCommentDefaultIcon);
        mMineImg.setImageDrawable(mMineDefaultIcon);

        mMallTx.setTextColor(getResources().getColor(textColor));
        mCarCircleTx.setTextColor(getResources().getColor(textColor));
        mCarCommentTx.setTextColor(getResources().getColor(textColor));
        mMineTx.setTextColor(getResources().getColor(textColor));
    }
    /**
     * 变为点击效果
     */
    private void changePressedEffect(int i) {
        restoreDefaultEffect();
        if (i == 0) {
            mMallImg.setImageDrawable(mMallFocusIcon);
            mMallTx.setTextColor(getResources().getColor(textPressColor));
        }
        if (i == 1) {
            mCarCircleImg.setImageDrawable(mCarCircleFocusIcon);
            mCarCircleTx.setTextColor(getResources().getColor(textPressColor));
        }
        if (i == 2) {
            mCarCommentImg.setImageDrawable(mCarCommentFocusIcon);
            mCarCommentTx.setTextColor(getResources().getColor(textPressColor));
        }
        if (i == 3) {
            mMineImg.setImageDrawable(mMineFocusIcon);
            mMineTx.setTextColor(getResources().getColor(textPressColor));
        }
    }
    private void showFragment(int i) {
        if(i==1||i==2){
            Measure_Utils.setAndroidNativeLightStatusBar(this,true);
        }else {
            Measure_Utils.setAndroidNativeLightStatusBar(this,false);
        }
        changePressedEffect(i);
        switch (i){
            case 0:
                if(mMallCbbFragment==null){
                    mMallCbbFragment=new MallCbbFragment();
                }
                switchFragment(mSelectFragment, mMallCbbFragment);
                mSelectFragment=mMallCbbFragment;
                break;
            case 1:
                if(mCarCircleFragment==null){
                    mCarCircleFragment=new CarCircleFragment();
                }
                switchFragment(mSelectFragment, mCarCircleFragment);
                mSelectFragment=mCarCircleFragment;
                break;
            case 2:
                if(mCarCommentFragment ==null){
                    mCarCommentFragment =new CarCommentFragment();
                }
                switchFragment(mSelectFragment, mCarCommentFragment);
                mSelectFragment= mCarCommentFragment;
                break;
            case 3:
                if(mMineFragment==null){
                    mMineFragment=new MineFragment();
                }
                switchFragment(mSelectFragment, mMineFragment);
                mSelectFragment=mMineFragment;
                break;
        }
    }
    private void switchFragment(Fragment from, Fragment to) {
        switchFragment(from, to, true);
    }

    private void switchFragment(Fragment from, Fragment to, boolean needHideFrom) {
        switchFragment(from, to, R.id.module_cbb_home_fl, needHideFrom);
    }
    @OnClick(R.id.module_cbb_home_mall_ly)
    public void toMall(){
        showFragment(0);
    }
    @OnClick(R.id.module_cbb_home_car_circle_ly)
    public void toCarCircle(){
        showFragment(1);
    }
    @OnClick(R.id.module_cbb_home_car_comment_ly)
    public void toCarComment(){
        showFragment(2);
    }
    @OnClick(R.id.module_cbb_home_mine_ly)
    public void toMine(){
        showFragment(3);
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        if(outState!=null){
            FragmentManager manager = getSupportFragmentManager();
            manager.popBackStackImmediate(null, 1);
        }
        System.exit(0);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return ExitBackUtils.exitBy2Click(keyCode, event, getContext(), new ExitBackCallBack() {
            @Override
            public void toBack() {

            }
        });
    }
}
