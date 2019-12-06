package com.xacheliangroup.check.moduleOther.fragment;

import android.graphics.Paint;
import android.os.CountDownTimer;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.eventbus.MessageEvent;
import com.xacheliangroup.check.common.flag.Flag;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.common.sharepreference.UserSaveUtils;
import com.xacheliangroup.check.common.type.WebEnum;
import com.xacheliangroup.check.common.web.WebActivity;
import com.xacheliangroup.check.moduleOther.mvp.OtherPresenter;
import com.xacheliangroup.check.moduleOther.mvp.bean.UserLoginBean;
import com.xacheliangroup.check.utils.KeyBoardUtils;
import com.xacheliangroup.check.utils.Measure_Utils;
import com.xacheliangroup.check.utils.ProgressHelp;
import com.xacheliangroup.check.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2018/12/24,16:57
 */
public class LoginFragment extends BaseFragment implements IActionListener.ViewAction {
    private CountDownTimer mCountDownTimer;
    OtherPresenter mOtherPresenter;
    @BindView(R.id.module_cbb_login_user_agreement_tx)
    TextView mAgreementTx;
    @BindView(R.id.module_cbb_login_status_bar_rl)
    TextView mStatusBarTx;
    @BindView(R.id.module_cbb_login_app_bar_rl)
    RelativeLayout mAppBarRl;
    @DrawableRes
    int mGrayBg=R.drawable.module_cbb_e3e3e3_6_bg;
    @DrawableRes
    int mRedBg=R.drawable.module_cbb_df1f01_6_bg;
    @BindView(R.id.module_cbb_login_phone_number_et)
    EditText mPhoneEt;
    @BindView(R.id.module_cbb_login_phone_code_et)
    EditText mCodeEt;
    @BindView(R.id.module_cbb_login_bottom_login_rl)
    RelativeLayout mLoginRl;
    @BindView(R.id.module_cbb_login_get_phone_code_tx)
    TextView mGetPhoneCodeTx;
    private  boolean isGetCodeOnClick;
    private  boolean isToLogin;
    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type){
            case OtherPresenter.TO_GET_PHONE_CODE_LOGIN:
                if(obj!=null){
                    isGetCodeOnClick=true;
                    mStartTime();
                }
                break;
            case OtherPresenter.TO_LOGIN_FOR_CBB:
                if(obj!=null&&obj instanceof UserLoginBean){
                    UserLoginBean userLoginBean= (UserLoginBean) obj;
                    CbbLogUtils.debugLog(userLoginBean);
                    UserSaveUtils.saveUserInfoForLoginSuccessToSp(userLoginBean);
                    EventBus.getDefault().post(new MessageEvent(Flag.EVENT.LOGIN_SUCCESS,null));
                    getActivity().finish();
                }else {
                    ToastUtils.showToast(getContext(),"输入的验证码有误，请重新输入");
                }
                break;
        }
    }

    private void mStartTime() {
        mCountDownTimer.start();
    }

    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_cbb_fragment_login;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        initView();
        addEditTextListener();
        initContentTimer();
    }

    private void initContentTimer() {
        mCountDownTimer = new CountDownTimer(60 * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                mGetPhoneCodeTx.setText(millisUntilFinished/1000+"s");
            }

            @Override
            public void onFinish() {
                mGetPhoneCodeTx.setText("获取验证码");
                isGetCodeOnClick=false;
            }
        };
    }

    private void addEditTextListener() {
        mCodeEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length()==4&&!TextUtils.isEmpty(mPhoneEt.getText().toString())){
                    mLoginRl.setBackground(ContextCompat.getDrawable(getContext(),mRedBg));
                    isToLogin=true;
                }else {
                    mLoginRl.setBackground(ContextCompat.getDrawable(getContext(),mGrayBg));
                    isToLogin=false;
                }
            }
        });
        mPhoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(TextUtils.isEmpty(mPhoneEt.getText().toString())||
                        mPhoneEt.getText().toString().length()<6){
                    mLoginRl.setBackground(ContextCompat.getDrawable(getContext(),mGrayBg));
                    isToLogin=false;
                }
                if(!TextUtils.isEmpty(mPhoneEt.getText().toString())&&
                        mPhoneEt.getText().toString().length()>=6&&mCodeEt.getText().toString().length()==4){
                    mLoginRl.setBackground(ContextCompat.getDrawable(getContext(),mRedBg));
                    isToLogin=true;
                }
            }
        });
    }
    @OnClick(R.id.module_cbb_login_get_phone_code_tx)
    public void toGetPhoneCodeTx(){
        if(TextUtils.isEmpty(mPhoneEt.getText().toString())||
                mPhoneEt.getText().toString().length()<6){
            ToastUtils.showToast(getContext(),"请输入正确的手机号");
            return;
        }
        if(isGetCodeOnClick){
            return;
        }
        ProgressHelp.showProgress(getContext());
        mOtherPresenter.toGetPhoneCodeData(mPhoneEt.getText().toString());
    }

    private void initView() {
        KeyBoardUtils.showKeyboard(getActivity(),false);
        mAgreementTx.setText("《用户注册协议》");
        mAgreementTx.getPaint().setFlags(Paint. UNDERLINE_TEXT_FLAG );
        Measure_Utils.setAndroidNativeLightStatusBar(getActivity(),true);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) mAppBarRl.getLayoutParams();
        layoutParams.height= Measure_Utils.getActionBarHigh(getContext());
        mAppBarRl.setLayoutParams(layoutParams);

        RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) mStatusBarTx.getLayoutParams();
        layoutParams1.height=Measure_Utils.getStatusBarHeight(getContext());
        mStatusBarTx.setLayoutParams(layoutParams1);
    }

    @Override
    protected void initPresenter() {
        mOtherPresenter=new OtherPresenter(getContext(),this);
        addPresenter(mOtherPresenter);
    }

    @Override
    protected void PresenterGetData() {

    }
    @OnClick(R.id.module_cbb_login_user_agreement_tx)
    public void toShowUserAgreement(){
        WebActivity.launch(getContext(),getActivity(),"http://baidu.com", WebEnum.OTHER_WEB);
    }
    @OnClick(R.id.module_cbb_login_close_img)
    public void toBack(){
        getActivity().finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        toCancelTimer();
    }

    public void toCancelTimer(){
        if(mCountDownTimer!=null){
            mCountDownTimer.cancel();
            mCountDownTimer=null;
        }
    }
    @OnClick(R.id.module_cbb_login_bottom_login_rl)
    public void toLogin(){
        if(!isToLogin){
            return;
        }
        ProgressHelp.showProgress(getContext());
        mOtherPresenter.toLoginData(mPhoneEt.getText().toString(),mCodeEt.getText().toString(),"");
    }

}
