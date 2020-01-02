package com.xacheliangroup.check.moduleNetLine.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

//import com.amap.api.maps2d.AMap;
//import com.amap.api.maps2d.MapView;
import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.moduleNetLine.activity.NetLineMapActivity;
import com.xacheliangroup.check.moduleNetLine.mvp.NetLinePresenter;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.NetLinePointListBean;

import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/9/2,09:13
 */
public class NetLineMapFragment extends BaseFragment implements IActionListener.ViewAction {

//    AMap mAMap;
//    @BindView(R.id.module_nl_maps2d)
//    MapView mMapView;
    private Intent intent;
    private String mRid;
    NetLinePresenter mNetLinePresenter;
    private NetLinePointListBean mNetLinePointListBean;

    @Override
    public void showInfoView(String type, Object obj) {
        switch (type){
            case NetLinePresenter.TO_GET_POINT_LIST:
                if(obj!=null&&obj instanceof NetLinePointListBean){
                    mNetLinePointListBean = (NetLinePointListBean) obj;
                }
                break;
        }
    }
    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_nl_fragment_map;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        initMap();
        getData();
    }

    private void getData() {
        intent = getActivity().getIntent();
        if(intent!=null){
            mRid = intent.getStringExtra(NetLineMapActivity.POINT_RID);
            mNetLinePresenter.toGetPointList(mRid);
        }
    }

    private void initMap() {
//        if(mAMap==null){
//            mAMap=mMapView.getMap();
//        }
    }
    @Override
    protected void initPresenter() {
        mNetLinePresenter=new NetLinePresenter(getContext(),this);
        addPresenter(mNetLinePresenter);
    }

    @Override
    protected void PresenterGetData() {

    }
    @OnClick(R.id.module_nl_map_back_img)
    public void toBack(){
        getActivity().finish();
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
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        if(mMapView!=null){
//            mMapView.onDestroy();
//        }
    }


}
