package com.xacheliangroup.check.moduleNetLine.fragment;

import android.view.View;
import android.widget.EditText;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.moduleNetLine.activity.NetLineHomeActivity;
import com.xacheliangroup.check.moduleNetLine.mvp.NetLinePresenter;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.LoginBean;
import com.xacheliangroup.check.utils.ProgressHelp;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/9/2,09:11
 */
public class NetLineLoginFragment extends BaseFragment implements IActionListener.ViewAction {
    NetLinePresenter mNetLinePresenter;
    @BindView(R.id.module_nl_phone_et)
    EditText mPhoneEt;
    @BindView(R.id.module_nl_ps_et)
    EditText mPasswrod;
    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type){
            case NetLinePresenter.TO_GET_PHONE_NUMBER:
                if(obj!=null&&obj instanceof LoginBean){
                    LoginBean loginBean= (LoginBean) obj;
                    toHome(loginBean);
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
        return R.layout.module_nl_fragment_login;
    }

    @Override
    protected void onCreateViewInit(View parentView) {

    }

    @Override
    protected void initPresenter() {
        mNetLinePresenter=new NetLinePresenter(getContext(),this);
        addPresenter(mNetLinePresenter);
    }

    @Override
    protected void PresenterGetData() {

    }
    @OnClick(R.id.module_nl_login)
    public void toLogin(){
        ProgressHelp.showProgress(getContext());
        mNetLinePresenter.toLogin(mPhoneEt.getText().toString(),mPasswrod.getText().toString());
    }
    public void toHome(LoginBean loginBean){
        NetLineHomeActivity.launch(getContext(),getActivity(),loginBean);
    }

}
