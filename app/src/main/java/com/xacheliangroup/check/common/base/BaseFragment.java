package com.xacheliangroup.check.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.xacheliangroup.check.common.http.okhttp.RxPresenter;
import com.xacheliangroup.check.common.listener.OnMessageEventListener;
import com.xacheliangroup.check.utils.KeyBoardUtils;
import com.xacheliangroup.check.utils.NetworkUtil;
import com.xacheliangroup.check.utils.ProgressHelp;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 *
 * Created by funnyzhao on 2017/8/16.
 */

public abstract class BaseFragment extends Fragment {

    private Unbinder mUnbinder;
    private List<RxPresenter> rxPresenterList = new ArrayList<>();
//    protected ImmersionBar mImmersionBar;
    /**
     * 懒加载过
     */
    private boolean isLazyLoaded;

    private boolean isPrepared;

    protected FragmentActivity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = (FragmentActivity) activity;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(),container,false);
        getFragmentView(view);
        mUnbinder= ButterKnife.bind(this, view);
        initPresenter();
        onCreateViewInit(view);
        onCreateView(savedInstanceState);
        PresenterGetData();
        return view;
    }

    public void getFragmentView(View view){}
    public void onCreateView(Bundle savedInstanceState){}
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        isPrepared = true;
        //只有Fragment onCreateView好了，
        //另外这里调用一次lazyLoad(）
        lazyLoad();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isImmersionBarEnabled())
            initImmersionBar();
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

    protected boolean isImmersionBarEnabled() {
        return false;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
//        if (!hidden && mImmersionBar != null)
//            mImmersionBar.init();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        lazyLoad();
    }

    public int getHeight() {
        return 0;
    }

    /**
     * 调用懒加载
     */

    private void lazyLoad() {
        if (getUserVisibleHint() && isPrepared && !isLazyLoaded) {
            onLazyLoad();
            isLazyLoaded = true;
        }
    }

    protected void onLazyLoad() {
        if (NetworkUtil.isNetAvailable(getActivity())) {
            onRequestConnected();
        } else {
            onRequestNoConnected();
        }
    }


    protected void addPresenter(RxPresenter rxPresenter) {
        if (!rxPresenterList.contains(rxPresenter)) {
            rxPresenterList.add(rxPresenter);
        }
    }

    /**
     * 需要注册的时候再注册
     */
    protected void setEventBusAction() {
        EventBus.getDefault().register(this);
    }


    /**
     * 在此处理有网络连接的情况  in onActivityCreated
     */
    protected abstract void onRequestConnected();

    public void wantRefresh() {
        onRequestConnected();
    }

    /**
     * 在此处理无网络连接的情况  in onActivityCreated
     */
    protected abstract void onRequestNoConnected();

    @Override
    public void onDestroy() {

        mUnbinder.unbind();
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
    /**
     * 获取fragment布局id
     * @return
     */
    protected abstract int getLayoutId();
    /**
     * onCreateView中初始化
     * @param parentView
     */
    protected abstract void onCreateViewInit(View parentView);
    protected abstract void initPresenter();
    protected abstract void PresenterGetData();

    protected void showProgress(boolean need) {
        if (need) {
            ProgressHelp.showProgress(getContext());
        } else {
            ProgressHelp.dismissProgress();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        KeyBoardUtils.showKeyboard(getActivity(), false);
    }

    protected OnMessageEventListener onMessageEventListener;

    public void setMessageEventListener(OnMessageEventListener listener) {
        this.onMessageEventListener = listener;
    }
}
