package com.xacheliangroup.check.common.web;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.api.APIField;
import com.xacheliangroup.check.common.api.AppCache;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.common.type.WebEnum;
import com.xacheliangroup.check.utils.ProgressHelp;
import com.xacheliangroup.check.utils.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * author:yz
 * data: 2018/12/20,09:55
 */
public class WebFragment extends BaseFragment {
    @BindView(R.id.module_cbb_web_wv)
    WebView mWebView;
    private WebEnum mWebEnum;
    private String mUrl;
    public ValueCallback<Uri[]> mUploadMessageForAndroid5;
    public ValueCallback<Uri> mUploadMessage;
    public final static int FILE_CHOOSER_RESULT_CODE_FOR_ANDROID_5 = 2;
    private final static int FILE_CHOOSER_RESULT_CODE = 1;// 表单的结果回调
    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_cbb_fragment_web;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        getData();
        initWebView();
    }

    @SuppressLint("JavascriptInterface")
    private void initWebView() {
        WebSettings webSettings = mWebView.getSettings();
        // 设置与Js交互的权限
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
//        1，LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
//        2，LOAD_DEFAULT: 根据cache-control决定是否从网络上取数据。
//        3，LOAD_CACHE_NORMAL: API level 17中已经废弃, 从API level 11开始作用同LOAD_DEFAULT模式
//        4，LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
//        5，LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        webSettings.setDomStorageEnabled(true);
        // 设置允许JS弹窗
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setDefaultTextEncodingName("UTF-8");
        mWebView.addJavascriptInterface(this,"cbb");
        if(!TextUtils.isEmpty(mUrl)){
            mWebView.loadUrl(mUrl);
            mWebView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_INSET);//滚动条风格
        }

        //如果不设置WebViewClient，请求会跳转系统浏览器
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //该方法在Build.VERSION_CODES.LOLLIPOP以前有效，从Build.VERSION_CODES.LOLLIPOP起，建议使用shouldOverrideUrlLoading(WebView, WebResourceRequest)} instead
                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
//                Map extraHeaders = new HashMap();
//                extraHeaders.put("Referer", "http://test.95505.com.cn");
//                view.loadUrl(url,extraHeaders);
//                view.loadUrl(url);
                // 如下方案可在非微信内部WebView的H5页面中调出微信支付
                CbbLogUtils.debugLog(url);
                if (url.startsWith("weixin://wap/pay?")||url.startsWith("alipay")) {
                    try {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        startActivity(intent);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (url.startsWith("weixin://wap/pay?")){
                            ToastUtils.showToast(getContext(),"请确认是否安装微信");
                        }
                        if(url.startsWith("alipay")){
                            ToastUtils.showToast(getContext(),"请确认是否安装支付宝");
                        }
                    }
                    return true;
                }else if(url.startsWith("http://47.92.84.90:21006")||
                        url.startsWith("http://192.168.0.63:8089")||
                        url.startsWith("http://jzcar.chelianjk.com:21001")){
                    view.loadUrl(url);
                } else {
                    Map<String, String> extraHeaders = new HashMap<String, String>();
                    String headString="http://test.95505.com.cn";
                    String type = APIField.getMetaData(AppCache.getContext(), "APP_ENV");
                    switch (type) {
                        case "UAT"://测试服
                            headString="http://test.95505.com.cn";
                            break;
                        case "DEVELOP"://开发服
                            headString="http://test.95505.com.cn";
                            break;
                        case "RELEASE"://正式服
                            headString="https://www.95505.com.cn";
                            break;
                        default://其他
                            headString="https://www.95505.com.cn";
                            break;
                    }
                    extraHeaders.put("Referer",headString);
                    view.loadUrl(url, extraHeaders);
                }
                return true;
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }
            //            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
//            {
//                //返回false，意味着请求过程里，不管有多少次的跳转请求（即新的请求地址），均交给webView自己处理，这也是此方法的默认处理
//                //返回true，说明你自己想根据url，做新的跳转，比如在判断url符合条件的情况下，我想让webView加载http://ask.csdn.net/questions/178242
//                return true;
//            }
        });
        mWebView.setWebChromeClient(new WebChromeClient(){
            //Android < 5.0
            public void openFileChooser(ValueCallback<Uri> uploadMsg, String acceptType, String capture) {
                openFileChooserImpl(uploadMsg);
            }
            //Android => 5.0
            public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> uploadMsg, WebChromeClient.FileChooserParams fileChooserParams) {
                onenFileChooseImpleForAndroid(uploadMsg);
                return true;
            }

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress == 100) {
                    ProgressHelp.dismissProgress();
                }
            }
        });
    }
    /**
     * android 5.0 以下开启图片选择（原生）
     * 可以自己改图片选择框架。
     */
    private void openFileChooserImpl(ValueCallback<Uri> uploadMsg) {
        mUploadMessage = uploadMsg;
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(Intent.createChooser(i, "File Chooser"),
                FILE_CHOOSER_RESULT_CODE);
    }
    /**
     * android 5.0(含) 以上开启图片选择（原生）
     * 可以自己改图片选择框架。
     */
    private void onenFileChooseImpleForAndroid(ValueCallback<Uri[]> filePathCallback) {
        mUploadMessageForAndroid5 = filePathCallback;
        Intent contentSelectionIntent = new Intent(Intent.ACTION_GET_CONTENT);
        contentSelectionIntent.addCategory(Intent.CATEGORY_OPENABLE);
        contentSelectionIntent.setType("image/*");
        Intent chooserIntent = new Intent(Intent.ACTION_CHOOSER);
        chooserIntent.putExtra(Intent.EXTRA_INTENT, contentSelectionIntent);
        chooserIntent.putExtra(Intent.EXTRA_TITLE, "Image Chooser");
        startActivityForResult(chooserIntent, FILE_CHOOSER_RESULT_CODE_FOR_ANDROID_5);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri result = (data == null || resultCode != Activity.RESULT_OK) ? null: data.getData();
        switch (requestCode){
            case FILE_CHOOSER_RESULT_CODE:  //android 5.0以下 选择图片回调
                if (null == mUploadMessage)
                    return;
                mUploadMessage.onReceiveValue(result);
                mUploadMessage = null;
                break;
            case FILE_CHOOSER_RESULT_CODE_FOR_ANDROID_5:  //android 5.0(含) 以上 选择图片回调
                if (null == mUploadMessageForAndroid5)
                    return;
                if (result != null) {
                    mUploadMessageForAndroid5.onReceiveValue(new Uri[]{result});
                } else {
                    mUploadMessageForAndroid5.onReceiveValue(new Uri[]{});
                }
                mUploadMessageForAndroid5 = null;
                break;
        }
    }
    private void getData() {
        ProgressHelp.showProgress(getContext());
        Intent intent = getActivity().getIntent();
        if(intent!=null&&intent.hasExtra(WebActivity.WEB_ENUM)&&intent.hasExtra(WebActivity.WEB_URL)){
            mWebEnum = (WebEnum) intent.getSerializableExtra(WebActivity.WEB_ENUM);
            mUrl = intent.getStringExtra(WebActivity.WEB_URL);
        }else if(getActivity().getIntent().getData()!=null){
            mWebEnum=WebEnum.OTHER_WEB;
        }else {
            ToastUtils.showToast(getContext(),"加载失败");
        }
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void PresenterGetData() {

    }
    @JavascriptInterface
    public void toCallPhone(String phoneNumber){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ phoneNumber));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    public boolean getWebViewCanBack(){
        return mWebView.canGoBack();
    }
    public void goBack(){
        mWebView.goBack();
    }
    @JavascriptInterface
    public void toFinsh(){
        getActivity().finish();
    }
}
