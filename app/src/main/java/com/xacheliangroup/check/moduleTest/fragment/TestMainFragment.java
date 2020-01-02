package com.xacheliangroup.check.moduleTest.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

//import com.amap.api.maps2d.AMap;
//import com.amap.api.maps2d.MapView;
import com.xacheliangroup.check.R;
import com.xacheliangroup.check.checkTickets.mvp.KeyboardUtil;
import com.xacheliangroup.check.common.CbbApplication;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.greenDao.DaoSession;
import com.xacheliangroup.check.moduleTest.greenDao.User;
import com.xacheliangroup.check.moduleTest.intf.PassOnInterface;
import com.xacheliangroup.check.utils.ToastUtils;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.qrcode.core.BarcodeType;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

/**
 * author:yz
 * data: 2019/8/8,16:45
 */
public class TestMainFragment extends BaseFragment {

//    @BindView(R.id.main_test_socket_btn)
//    Button mSocketBtn;
//    AMap mAMap;
//    @BindView(R.id.maps2d)
//    MapView mMapView;
    @BindView(R.id.zxingview)
    ZXingView mZXingView;
    private PassOnInterface mPassOnInterface;
    private DaoSession daoSession;
    @BindView(R.id.tx_test_ed)
    EditText ed;

    boolean isBooleOpenFlashlight=false;
    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    public void onCreateView(Bundle savedInstanceState) {
        super.onCreateView(savedInstanceState);
//        mMapView.onCreate(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_cbb_fragment_test_mian;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        initDaoData();
        initQrcode();
        initMap();
        initEditTx();
    }
    KeyboardUtil keyboardUtil;
    private void initEditTx() {
        ed.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (keyboardUtil == null) {
                    keyboardUtil = new KeyboardUtil(getActivity(), ed);
                    keyboardUtil.hideSoftInputMethod();
                    keyboardUtil.showKeyboard();
                } else {
                    keyboardUtil.showKeyboard();
                }
                return false;
            }
        });
    }

    @OnClick(R.id.main_test_socket_btn)
    public void toConnect(){
        initSocketConnect();
    }
    private void initSocketConnect() {
        //连接参数设置(IP,端口号),这也是一个连接的唯一标识,不同连接,该参数中的两个值至少有其一不一样
        ConnectionInfo info = new ConnectionInfo("192.168.0.253", 9999);
//调用OkSocket,开启这次连接的通道,拿到通道Manager
        IConnectionManager manager = OkSocket.open(info);
//注册Socket行为监听器,SocketActionAdapter是回调的Simple类,其他回调方法请参阅类文档
        manager.registerReceiver(new SocketActionAdapter(){
            @Override
            public void onSocketConnectionSuccess(ConnectionInfo info, String action) {
                ToastUtils.showToast(getContext(),"链接成功");
            }
        });
//调用通道进行连接
        manager.connect();
    }

    private void initMap() {
//        if(mAMap==null){
//            mAMap=mMapView.getMap();
//        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mZXingView.changeToScanBarcodeStyle(); // 切换成扫描条码样式
        mZXingView.setType(BarcodeType.ONE_DIMENSION, null); // 只识别一维条码

        mZXingView.startCamera();//打开相机


        mZXingView.startSpotAndShowRect(); // 显示扫描框，并开始识别

//        mZXingView.showScanRect();//显示扫描框
//        mZXingView.startSpot();//开始识别二维码


    }

    @Override
    public void onResume() {
        super.onResume();
//        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
//        mMapView.onPause();
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
//        if(mMapView!=null){
//             mMapView.onDestroy();
//        }
    }

    private void initQrcode() {
        mZXingView.setDelegate(new QRCodeView.Delegate() {
            @Override
            public void onScanQRCodeSuccess(String result) {
                ToastUtils.showToast(getContext(),result);
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

    private void initDaoData() {
        User user=new User(null,"name","23");
        daoSession = ((CbbApplication)getActivity().getApplication()).getDaoSession();
        daoSession.insertOrReplace(user);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void PresenterGetData() {

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            if(activity instanceof PassOnInterface){
                mPassOnInterface= (PassOnInterface) activity;
                CbbLogUtils.debugLog("mPassOnInterface activity");
            }else {
                throw new RuntimeException(activity.toString()+ " must implement PassonInterface");
            }
        }
    }

//    SDK API<23时，onAttach(Context)不执行，需要使用onAttach(Activity)。Fragment自身的Bug，v4的没有此问题
    @Override
    public void onAttach(Context context) {
        if(context instanceof PassOnInterface){
            mPassOnInterface = (PassOnInterface) context;
            CbbLogUtils.debugLog("mPassOnInterface context");
        }else {
            throw new RuntimeException(context.toString()+ " must implement PassonInterface");
        }
        super.onAttach(context);
    }
    @OnClick(R.id.main_test_tv)
    public void toBtnOnclick(){
        mPassOnInterface.passonVaule("你好");
        daoSession.insertOrReplace(new User(null,"yy","1111"));
        List<User> userLists= daoSession.loadAll(User.class);
        for (int i=0;i<userLists.size();i++){
            User user=userLists.get(i);
            CbbLogUtils.debugLog("user"+user.getId()+user.getName()+user.getAge());
        }
    }
}
