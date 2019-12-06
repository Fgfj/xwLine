package com.xacheliangroup.check.moduleNetLine.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.moduleNetLine.activity.NetLineCheckActivity;
import com.xacheliangroup.check.moduleNetLine.activity.NetLineQrCodeActivity;
import com.xacheliangroup.check.moduleNetLine.mvp.NetLinePresenter;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.CheckTicketBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.TicketBean;
import com.xacheliangroup.check.utils.ProgressHelp;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/9/3,21:43
 */
public class NetLineCheckFragment extends BaseFragment implements IActionListener.ViewAction {

    NetLinePresenter mNetLinePresenter;
    @BindView(R.id.module_nl_check_name_tx)
    TextView mNameTx;
    @BindView(R.id.module_nl_check_phone_tx)
    TextView mNumberTx;
    @BindView(R.id.module_nl_check_id_tx)
    TextView mIdTx;
    @BindView(R.id.module_nl_check_time_tx)
    TextView mTimeTx;
    @BindView(R.id.module_nl_check_order_tx)
    TextView mOrderTx;
    @BindView(R.id.module_nl_check_start_tx)
    TextView mStartTx;
    @BindView(R.id.module_nl_check_end_tx)
    TextView mEndTx;
    @BindView(R.id.module_nl_check_start_time_tx)
    TextView mStartTimeTx;
    private String mCode;
    private TicketBean mTicketBean;


    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type) {
            case NetLinePresenter.TO_GET_TICKET_INFO:
                if (obj != null && obj instanceof TicketBean) {
                    mTicketBean = (TicketBean) obj;
                    setView(mTicketBean);
                }
                break;
            case NetLinePresenter.TO_CHECK:
                if (obj != null && obj instanceof CheckTicketBean) {
                    CheckTicketBean checkTicketBean = (CheckTicketBean) obj;
                    toQrCodeAct();
                }
                break;
        }
    }

    private void setView(TicketBean mTicketBean) {
        mNameTx.setText("");
        mNumberTx.setText("");
        mIdTx.setText("");
        mTimeTx.setText(mTicketBean.getCreatetime());
        mOrderTx.setText(mTicketBean.getTicketno());
        mStartTx.setText("");
        mEndTx.setText("");
        mStartTimeTx.setText("");
    }

    private void toQrCodeAct() {
        NetLineQrCodeActivity.launch(getContext(), getActivity());
        getActivity().finish();
    }

    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_nl_fragment_check;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        getData();
    }

    private void getData() {
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            mCode = intent.getStringExtra(NetLineCheckActivity.QR_CODE);
            toGetTicketInfo(mCode);
        }
    }

    private void toGetTicketInfo(String mCode) {
        ProgressHelp.showProgress(getContext());
        mNetLinePresenter.toTicketInfo(mCode);
    }

    @Override
    protected void initPresenter() {
        mNetLinePresenter = new NetLinePresenter(getContext(), this);
        addPresenter(mNetLinePresenter);
    }

    @Override
    protected void PresenterGetData() {

    }

    @OnClick(R.id.module_nl_check_back_img)
    public void toBack() {
        getActivity().finish();
    }

    @OnClick(R.id.module_nl_check_rl)
    public void toCheck() {
        ProgressHelp.showProgress(getContext());
        mNetLinePresenter.toCheck(mTicketBean.getTicketid() + "");
    }

}
