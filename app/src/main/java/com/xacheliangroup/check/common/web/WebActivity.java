package com.xacheliangroup.check.common.web;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseActivity;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.common.type.WebEnum;

/**
 * author:yz
 * data: 2018/12/20,09:49
 */
public class WebActivity extends BaseActivity {
    WebFragment webFragment;
    public static final String WEB_ENUM="WEB_ENUM";
    public static final String WEB_URL="WEB_URL";
    public static void launch(Context context, Activity activity,  String webUrl,WebEnum webEnum){
        Intent intent = new Intent(context, WebActivity.class);
        intent.putExtra(WEB_ENUM,webEnum);
        intent.putExtra(WEB_URL,webUrl);
        context.startActivity(intent);
        if(activity!=null){
            activity.overridePendingTransition(R.anim.act_anim_in,R.anim.act_anim_out);
        }else {
            CbbLogUtils.debugLog("launch***WebActivity***isnull");
        }
    }
    @Override
    protected int getContentViewId() {
        return R.layout.module_cbb_all_activity;

    }

    @Override
    protected void onCreateInit() {
        webFragment=new WebFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.cbb_concern_report_container, webFragment).commit();

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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //这是一个监听用的按键的方法，keyCode 监听用户的动作，如果是按了返回键，同时Webview要返回的话，WebView执行回退操作，因为mWebView.canGoBack()返回的是一个Boolean类型，所以我们把它返回为true
        if(keyCode==KeyEvent.KEYCODE_BACK&&webFragment.getWebViewCanBack()){
            webFragment.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
