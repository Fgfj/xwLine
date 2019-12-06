package com.xacheliangroup.check.moduleMine.fragment;

import android.app.Dialog;
import android.view.Gravity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.api.APIField;
import com.xacheliangroup.check.common.api.AppCache;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.eventbus.MessageEvent;
import com.xacheliangroup.check.common.flag.Flag;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.sharepreference.UserSaveUtils;
import com.xacheliangroup.check.common.type.WebEnum;
import com.xacheliangroup.check.common.web.WebActivity;
import com.xacheliangroup.check.moduleOther.mvp.OtherPresenter;
import com.xacheliangroup.check.utils.DataCleanManager;
import com.xacheliangroup.check.utils.DialogUtils;
import com.xacheliangroup.check.utils.InstallUtil;
import com.xacheliangroup.check.utils.ProgressHelp;
import com.xacheliangroup.check.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2018/12/25,17:15
 */
public class AppSettingFragment extends BaseFragment implements IActionListener.ViewAction {
    OtherPresenter mOtherPresenter;
    @BindView(R.id.module_cbb_mine_setting_clear_size_tx)
    TextView mSizeTx;
    @BindView(R.id.module_cbb_mine_setting_version_tx)
    TextView mVersionTx;
    private Dialog mLogoutDialog;
    private RelativeLayout mSubmitRl;
    private RelativeLayout mCancelRl;

    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type) {
            case OtherPresenter.TO_LOGOUT_DATA:
                if (obj != null) {
                    UserSaveUtils.clearUserInfoLogoutToSp();
                    getActivity().finish();
                    EventBus.getDefault().post(new MessageEvent(Flag.EVENT.LOGIN_OUT, null));
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
        return R.layout.module_cbb_fragment_app_setting;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        setView();
        initLogoutDialog();
    }

    private void setView() {
        mVersionTx.setText(InstallUtil.getVersionName(getContext()));
        try {
            mSizeTx.setText(DataCleanManager.getTotalCacheSize(getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLogoutDialog() {
        View logoutView = View.inflate(getContext(), R.layout.module_cbb_dialog_choice, null);
        mLogoutDialog = DialogUtils.getDiyDialog(getActivity(), getContext(), logoutView, Gravity.CENTER, 0.7f);
        mSubmitRl = logoutView.findViewById(R.id.module_cbb_dialog_submit_rl);
        mCancelRl = logoutView.findViewById(R.id.module_cbb_dialog_cancel_rl);
        mSubmitRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressHelp.dismissProgress();
                mOtherPresenter.toLogout();
            }
        });
        mCancelRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLogoutDialog.dismiss();
            }
        });
    }

    @Override
    protected void initPresenter() {
        mOtherPresenter = new OtherPresenter(getContext(), this);
        addPresenter(mOtherPresenter);

    }

    @Override
    protected void PresenterGetData() {

    }

    @OnClick(R.id.module_cbb_mine_setting_list_back_fl)
    public void toBack() {
        getActivity().finish();
    }

    @OnClick(R.id.module_cbb_mine_setting_logout_ry)
    public void toLogout() {
        if(AppCache.hasLogged()){
            if (mLogoutDialog == null) {
                initLogoutDialog();
            }
            mLogoutDialog.show();
        }else {
            ToastUtils.showToast(getContext(),"请去登录");
        }


    }

    @OnClick(R.id.module_cbb_mine_setting_clear_size_ly)
    public void toClearSize() {
        showProgress(true);
        try {
            DataCleanManager.clearAllCache(getContext());
            mSizeTx.setText(DataCleanManager.getTotalCacheSize(getContext()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        showProgress(false);
    }

    @OnClick(R.id.module_cbb_mine_setting_about_us_ly)
    public void toGoAboutUsWeb() {
        WebActivity.launch(getContext(), getActivity(), APIField.getAppServiceUrl() + "/upload/myfours/cbb_about.html", WebEnum.OTHER_WEB);
    }

    @OnClick(R.id.module_cbb_mine_setting_service_ly)
    public void toGoServiceWeb() {
        WebActivity.launch(getContext(), getActivity(), APIField.getAppServiceUrl() + "/upload/userservice/cbb_service.html", WebEnum.OTHER_WEB);
    }

}
