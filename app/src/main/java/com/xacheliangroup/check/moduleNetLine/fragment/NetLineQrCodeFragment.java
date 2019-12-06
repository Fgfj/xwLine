package com.xacheliangroup.check.moduleNetLine.fragment;

import android.view.View;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleNetLine.activity.NetLineCheckActivity;
import com.xacheliangroup.check.utils.ToastUtils;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * author:yz
 * data: 2019/9/2,14:55
 */
public class NetLineQrCodeFragment extends BaseFragment {
    @BindView(R.id.module_nl_zxingview)
    ZXingView mZXingView;
    boolean isBooleOpenFlashlight=false;
    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_nl_fragment_qr_code;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        initQrcode();
    }
    @Override
    public void onStart() {
        super.onStart();
//        mZXingView.changeToScanBarcodeStyle(); // 切换成扫描条码样式
//        mZXingView.setType(BarcodeType.ONE_DIMENSION, null); // 只识别一维条码
//
//        mZXingView.startCamera();//打开相机
//
//
//        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别

        mZXingView.showScanRect();//显示扫描框
        mZXingView.startSpot();//开始识别二维码


    }
    private void initQrcode() {
        mZXingView.setDelegate(new QRCodeView.Delegate() {
            @Override
            public void onScanQRCodeSuccess(String result) {
                ToastUtils.showToast(getContext(),result);
                qrCodeResultSuccess(result);
            }

            @Override
            public void onCameraAmbientBrightnessChanged(boolean isDark) {
                CbbLogUtils.debugLog(isDark+"----");
                if(isDark){
                    if(!isBooleOpenFlashlight){
                        mZXingView.openFlashlight();
                        isBooleOpenFlashlight=true;
                    }

                }
            }

            @Override
            public void onScanQRCodeOpenCameraError() {

            }
        });
    }

    private void qrCodeResultSuccess(String result) {
//        EventBus.getDefault().post(new MessageEvent(Flag.NETLINE.QR_CODE,result));
        NetLineCheckActivity.launch(getContext(),getActivity(),result);
        getActivity().finish();

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void PresenterGetData() {

    }
    @Override
    public void onStop() {
        mZXingView.stopCamera();
        super.onStop();
    }
    @Override
    public void onDestroy() {
        mZXingView.onDestroy();
        super.onDestroy();
    }
}
