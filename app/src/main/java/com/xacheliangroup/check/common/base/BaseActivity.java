package com.xacheliangroup.check.common.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;


import com.xacheliangroup.check.common.http.okhttp.RxPresenter;
import com.xacheliangroup.check.common.listener.OnMessageEventListener;
import com.xacheliangroup.check.common.log.LogUtils;
import com.xacheliangroup.check.common.receiver.KeyboardReceiver;
import com.xacheliangroup.check.utils.KeyBoardUtils;
import com.xacheliangroup.check.utils.ProgressHelp;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by funnyzhao on 2017/8/15.
 *
 */

    public abstract class BaseActivity extends AppCompatActivity {

    private List<RxPresenter> rxPresenterList = new ArrayList<>();
    private Unbinder unbinder;

    /**
     * 加载布局
     * @return
     */
    protected abstract int getContentViewId();

    /**
     * 子类可以在此方法中处理生命周期onCreate,推荐在此只做视图的创建，绘制，事件绑定的操作
     */
    protected abstract void onCreateInit();

    /**
     * 子类可以在此方法中初始化一些网络请求的操作
     * 应用场景：首次进入页面初始化数据时需要网络请求
     */
    protected abstract void onCreateRequestConnected();
    /**
     * 子类可以在此方法中处理无网络的情况，比如显示无网络布局，提示信息等
     */
    protected abstract void onCreateRequestNoConnected();

    private TextView mTitleTv;

//    protected ImmersionBar mImmersionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        fontChangeSize(savedInstanceState);
        startAnim();
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setFullSreen();
        setContentView(getContentViewId());
        unbinder = ButterKnife.bind(this);
        initBaseView();
        mapViewOnCreat(savedInstanceState);
        if (isImmersionBarEnabled()) {

            initImmersionBar();
            initNormalScreenStyle();
        }

        onCreateInit();
        toInitSaveInstanceState(savedInstanceState);
        onCreateRequestConnected();
//        if (NetWorkUtils.isNetworkConnected(this)){
//
//        }else {
//            onCreateRequestNoConnected();
//        }
    }

    public void toInitSaveInstanceState(Bundle savedInstanceState){};
    public void startAnim(){};

    public void mapViewOnCreat( Bundle savedInstanceState){
    }
    public void fontChangeSize(Bundle savedInstanceState){};

    protected boolean isImmersionBarEnabled() {
        return true;
    }
    protected void initImmersionBar() {
//        mImmersionBar = ImmersionBar.with(this);
    }

    protected void initNormalScreenStyle() {
//        if (mImmersionBar != null) {
//            mImmersionBar.keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
//                    | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN).fitsSystemWindows(true).statusBarColor(R.color.colorPrimary).init();
//        }
    }

    protected void initFullScreenStyle() {
//        if (mImmersionBar != null) {
//            mImmersionBar.keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
//                    | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN).fitsSystemWindows(false).transparentStatusBar().init();
//        }
    }

    private void initBaseView() {
//        ImageView backImg = (ImageView) findViewById(R.id.toolbar_back);
//        mTitleTv = (TextView) findViewById(R.id.toolbar_title);
//        if (backImg != null) {
//            backImg.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    KeyBoardUtils.showKeyboard(BaseActivity.this, false);
//                    finish();
//                }
//            });
//        }
    }

    protected void setTitle(String title) {
        if (mTitleTv != null) {
            mTitleTv.setText(title);
        }
    }

    /**
     * 设置全屏相关
     */
    protected abstract void setFullSreen();

    /**
     * 需要注册的时候再注册
     */
    protected void setEventBusAction() {
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {

        super.onStop();
        KeyBoardUtils.showKeyboard(this, false);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        if (!rxPresenterList.isEmpty()) {
            for (RxPresenter rxPresenter : rxPresenterList) {
                rxPresenter.removeView();
            }
        }
//        if (mImmersionBar != null) {
//            mImmersionBar.destroy();
//        }
        super.onDestroy();
    }

    protected void addPresenter(RxPresenter rxPresenter) {
        if (!rxPresenterList.contains(rxPresenter)) {
            rxPresenterList.add(rxPresenter);
        }
    }

    protected void showProgress(boolean need) {
        if (need) {
            ProgressHelp.showProgress(this);
        } else {
            ProgressHelp.dismissProgress();
        }
    }

    protected Context getContext() {
        return this;
    }

    @Override
    public void finish() {

        try {
            if (KeyBoardUtils.showKeyboard(this, false, new KeyboardReceiver(new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    forceFinished(true);
                    return false;
                }
            })))) {
                // 键盘正准备隐藏，此时不做操作
                LogUtils.i("隐藏键盘操作");
            } else {
                forceFinished(true);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            super.finish();
        }

    }

    public void forceBackPressed(boolean force) {
        if (force) {
            super.onBackPressed();
        } else {
            onBackPressed();
        }
    }

    public void forceFinished(boolean force) {
        if (force) {
            super.finish();
        } else {
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        try {
            if (KeyBoardUtils.showKeyboard(this, false, new KeyboardReceiver(new Handler(new Handler.Callback() {
                @Override
                public boolean handleMessage(Message msg) {
                    forceFinished(true);
                    return false;
                }
            })))) {
                // 键盘正准备隐藏，此时不做操作
                LogUtils.i("隐藏键盘操作");
            } else {
                forceBackPressed(true);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            super.onBackPressed();
        }
    }

    protected OnMessageEventListener onMessageEventListener;

    public void setMessageEventListener(OnMessageEventListener listener) {
        this.onMessageEventListener = listener;
    }

    public void switchFragment(Fragment from, Fragment to, @IdRes int viewId) {
        switchFragment(from, to, viewId, true);
    }

    private List<Fragment> shownFragmentList;

    public void switchFragment(Fragment from, Fragment to, @IdRes int viewId, boolean needHideFrom) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (from == null || !from.isAdded()) {
            if (!to.isAdded()) {
                transaction.add(viewId, to, to.getClass().getSimpleName()).commit();
            } else {
                transaction.show(to).commit();
            }
        } else {
            if (needHideFrom) {
                transaction.hide(from);
                if (shownFragmentList != null && !shownFragmentList.isEmpty()) {
                    for (Fragment fragment : shownFragmentList) {
                        transaction.hide(fragment);
                    }
                }
            } else {
                if (shownFragmentList == null) {
                    shownFragmentList = new ArrayList<>();
                }
                shownFragmentList.add(from);
            }
            if (!to.isAdded()) {
                transaction.add(viewId, to, to.getClass().getSimpleName()).commit();
            } else {
                transaction.show(to).commit();
            }
        }
    }
}
