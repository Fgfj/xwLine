package com.xacheliangroup.check.moduleNetLine.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.base.BaseRecycler.BaseRecyclerAdapter;
import com.xacheliangroup.check.common.base.BaseRecycler.OnItemClickListener;
import com.xacheliangroup.check.common.eventbus.MessageEvent;
import com.xacheliangroup.check.common.flag.Flag;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.moduleNetLine.activity.NetLineHomeActivity;
import com.xacheliangroup.check.moduleNetLine.activity.NetLineListActivity;
import com.xacheliangroup.check.moduleNetLine.activity.NetLineMapActivity;
import com.xacheliangroup.check.moduleNetLine.activity.NetLineQrCodeActivity;
import com.xacheliangroup.check.moduleNetLine.mvp.NetLinePresenter;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.CarStatusBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.ChangeCarTypeBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.CheckTicketBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.LoginBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.NetLineCarListBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.NetLineUserListBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.PostCarBean;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.TicketBean;
import com.xacheliangroup.check.utils.DialogUtils;
import com.xacheliangroup.check.utils.ProgressHelp;
import com.xacheliangroup.check.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/9/2,09:12
 */
public class NetLineHomeFragment extends BaseFragment implements IActionListener.ViewAction {
    NetLinePresenter mNetLinePresenter;
    @BindView(R.id.module_nl_driver_name_tx)
    TextView mDiverNameTx;
    @BindView(R.id.module_nl_driver_number_tx)
    TextView mDiverPhoneNumberTx;
    @BindView(R.id.module_nl_top_car_num_tx)
    TextView mCarNumberTx;
    @BindView(R.id.module_nl_top_car_human_num_tx)
    TextView mCarHumanNumberTx;
    @BindView(R.id.module_nl_top_car_type_num_tx)
    TextView mCarTypeTx;
    @BindView(R.id.module_nl_bind_ly)
    LinearLayout mBindLy;
    @BindView(R.id.module_nl_bind_over_ly)
    LinearLayout mBindOverLy;
    @BindView(R.id.module_nl_nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.module_nl_post_car_tx)
    TextView mUpCarTx;
    BaseRecyclerAdapter<NetLineUserListBean.PalxSlorderListBean> mBaseRecyclerAdapter;
    @BindView(R.id.module_nl_user_list_rcv)
    RecyclerView mRecyclerView;
    private LoginBean mLoginBean;

    private int mRId = -1;//运输记录Id

    private boolean isBind;//是否绑定车辆
    private boolean isUpCar;//是否报班
    private LoginBean.CarsBean mNowCarsBean;
    private NetLineUserListBean mNetLineUserListBean;
    private Dialog mLineDialog;
    private RecyclerView mLineRecyclerView;
    private BaseRecyclerAdapter<NetLineCarListBean> mLineBaseRecyclerAdapter;


    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type) {
            case NetLinePresenter.TO_BIND_CAR:
                if (obj != null && obj instanceof LoginBean.CarsBean) {
                    LoginBean.CarsBean carsBean = (LoginBean.CarsBean) obj;
                    initView(carsBean);
                    setTopView(carsBean);
                }
                break;
            case NetLinePresenter.TO_GET_USER_LIST:
                if (obj != null && obj instanceof NetLineUserListBean) {
                    mNetLineUserListBean = (NetLineUserListBean) obj;
                    mBaseRecyclerAdapter.init(mNetLineUserListBean.getPalxSlorderList());
                    mBaseRecyclerAdapter.notifyDataSetChanged();
                }
                break;
            case NetLinePresenter.TO_GET_TICKET_INFO:
                if (obj != null && obj instanceof TicketBean) {
                    TicketBean ticketBean = (TicketBean) obj;
                    ProgressHelp.showProgress(getContext());
                    mNetLinePresenter.toCheck(ticketBean.getTicketid() + "");
                }
                break;
            case NetLinePresenter.TO_CHECK:
                if (obj != null && obj instanceof CheckTicketBean) {
                    CheckTicketBean checkTicketBean = (CheckTicketBean) obj;
                }
                break;
            case NetLinePresenter.TO_GET_CAR_LINE_LIST:
                if (obj != null && obj instanceof List) {
                    List<NetLineCarListBean> lineCarListBeans = (List<NetLineCarListBean>) obj;
                    mLineBaseRecyclerAdapter.init(lineCarListBeans);
                    mLineBaseRecyclerAdapter.notifyDataSetChanged();
                    mLineDialog.show();
                    mLineBaseRecyclerAdapter.setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClicked(int position, Object obj) {
                            if(obj!=null&&obj instanceof NetLineCarListBean){
                                NetLineCarListBean netLineCarListBean= (NetLineCarListBean) obj;
                                ProgressHelp.showProgress(getContext());
                                mNetLinePresenter.toPostCar(mNowCarsBean.getCarid(), mLoginBean.getDriver().getDriverid(), netLineCarListBean.getLineid());
                                mLineDialog.dismiss();
                            }
                        }

                        @Override
                        public boolean onItemLongClicked(int position, Object obj) {
                            return false;
                        }
                    });


                }
                break;
            case NetLinePresenter.TO_POST_CAR:
                if (obj != null && obj instanceof PostCarBean) {
                    PostCarBean mPostCarBean = (PostCarBean) obj;
                    mRId = mPostCarBean.getRecordid();
                    isUpCar = true;
                    mUpCarTx.setText("已报班");
                    ToastUtils.showToast(getContext(), "报班成功");
                    toGetUserList();
                } else {
                    ToastUtils.showToast(getContext(), "报班失败");
                }
                break;
            case NetLinePresenter.TO_GO_ADN_SEND_OVER:
                if (obj != null && obj instanceof CarStatusBean) {
                    CarStatusBean carStatusBean = (CarStatusBean) obj;
//                    1,接单中2，已出发 3全部到达已完成，必填参数
                    switch (carStatusBean.getStatus()) {
                        case 2:
                            ToastUtils.showToast(getContext(), "出发成功");
                            break;
                        case 3:
                            ToastUtils.showToast(getContext(), "全部到达成功");
                            break;
                    }
                } else {
                    ToastUtils.showToast(getContext(), "操作失败");
                }
                break;
            case NetLinePresenter.TO_CHANGE_CAR_TYPE:
                if (obj != null && obj instanceof ChangeCarTypeBean) {
                    ChangeCarTypeBean changeCarTypeBean = (ChangeCarTypeBean) obj;
                    switch (changeCarTypeBean.getTypes()) {
                        case 0:
                            ToastUtils.showToast(getContext(), "车辆报修中");
                            break;
                        case 5:
                            ToastUtils.showToast(getContext(), "车辆休息中");
                            break;
                        case 6:
                            toLogOut();
                            break;
                    }
                }
                break;
            case NetLinePresenter.TO_UP_CAR:
                ToastUtils.showToast(getContext(), "上车成功");
                toGetUserList();
                break;
        }

    }

    private void toLogOut() {
        ToastUtils.showToast(getContext(), "退出登录");
        getActivity().finish();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case Flag.NETLINE.CALL_PHONE:
                if (event.getObject() != null && event.getObject() instanceof String) {
                    String phoneNumber = (String) event.getObject();
                    callPhone(phoneNumber);
                }
                break;
            case Flag.NETLINE.QR_CODE:
                if (event.getObject() != null && event.getObject() instanceof String) {
                    String code = (String) event.getObject();
                    toCheckQrCode(code);
                }
                break;
            case Flag.NETLINE.UP_CAR:
                if (event.getObject() != null && event.getObject() instanceof NetLineUserListBean.PalxSlorderListBean) {
                    NetLineUserListBean.PalxSlorderListBean palxSlorderListBean = (NetLineUserListBean.PalxSlorderListBean) event.getObject();
                    toUpCar(palxSlorderListBean);
                }
                break;
        }
    }

    private void toUpCar(NetLineUserListBean.PalxSlorderListBean palxSlorderListBean) {
        ProgressHelp.showProgress(getContext());
        mNetLinePresenter.toUpCar(palxSlorderListBean.getOrderid() + "");

    }

    private void toCheckQrCode(String code) {
        ProgressHelp.showProgress(getContext());
        mNetLinePresenter.toTicketInfo(code);
    }

    private void callPhone(String phoneNumber) {
        Intent intent = new Intent();
        //设置拨打电话的动作
        intent.setAction(Intent.ACTION_CALL);
        //设置拨打电话的号码
        intent.setData(Uri.parse("tel:" + phoneNumber));
        //开启打电话的意图
        startActivity(intent);

    }

    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_nl_fragment_home;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        setEventBusAction();
        initRecyclerView();
        getData();
        initDialog();
//        状态 1空闲 2接乘客中 3满员 4已出发 5 休息 0报修中 6 退出登录 必填
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.nav_list:

                        NetLineListActivity.launch(getContext(), getActivity(), mNowCarsBean.getCarid(), mLoginBean.getDriver().getDriverid());
                        break;
                    case R.id.push_fix:
                        if (isUpCar) {
                            toChangeCarType("0");
                        } else {
                            ToastUtils.showToast(getContext(), "需要先报班");
                        }

                        break;
                    case R.id.push_rest:
                        if (isUpCar) {
                            toChangeCarType("5");
                        } else {
                            ToastUtils.showToast(getContext(), "需要先报班");
                        }
                        break;
                    case R.id.nav_logout:
                        if (isBind) {
                            toChangeCarType("6");

                        } else {
                            getActivity().finish();
                        }
                        break;
                }
                return true;
            }
        });
    }

    private void initDialog() {
        View mLineDialogView = View.inflate(getContext(), R.layout.module_nl_dialog_line_list, null);
        mLineDialog = DialogUtils.getDiyDialog(getActivity(), getContext(), mLineDialogView, Gravity.BOTTOM, 1f);
        mLineRecyclerView = mLineDialogView.findViewById(R.id.dialog_line_list_rl);

        mLineRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mLineBaseRecyclerAdapter = new BaseRecyclerAdapter<>(getContext());
        mLineRecyclerView.setAdapter(mLineBaseRecyclerAdapter);
        mLineRecyclerView.setNestedScrollingEnabled(false);
    }

    public void toChangeCarType(String type) {
        ProgressHelp.showProgress(getContext());
        mNetLinePresenter.toChangeCarType(mNowCarsBean.getCarid(), type);
    }

    private void getData() {
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            mLoginBean = (LoginBean) intent.getSerializableExtra(NetLineHomeActivity.LOGIN_BEAN);
            mRId = mLoginBean.getRecordid();
        }
        initView(mLoginBean.getCars());
        isUpCarMethod(mLoginBean);
    }

    private void isUpCarMethod(LoginBean mLoginBean) {
        if (mLoginBean.getRecordid() == -1) {
            isUpCar = false;
            mUpCarTx.setText("未报班");
        } else {
            isUpCar = true;
            mUpCarTx.setText("已报班");
        }
    }

    /**
     * 初始化头部view
     *
     * @param carsBean
     */
    private void initView(LoginBean.CarsBean carsBean) {
        CbbLogUtils.debugLog(carsBean);
        mNowCarsBean = carsBean;
        if (carsBean.getBindtype() == 2) {//2已绑定
            mBindOverLy.setVisibility(View.VISIBLE);
            mBindLy.setVisibility(View.GONE);
            isBind = true;
        } else {//1：未绑定
            mBindLy.setVisibility(View.VISIBLE);
            mBindOverLy.setVisibility(View.GONE);
            isBind = false;
        }
        setTopView(mLoginBean.getCars());
        mDiverNameTx.setText(mLoginBean.getDriver().getRealname());
        mDiverPhoneNumberTx.setText(mLoginBean.getDriver().getMobile());
    }

    public void setTopView(LoginBean.CarsBean carsBean) {
        mCarNumberTx.setText(carsBean.getCarnumber());
        mCarHumanNumberTx.setText(carsBean.getSeatnumber() + "");
        switch (carsBean.getStatus()) {//status 状态1空闲 2接乘客中 3满员  4已出发 5 休息  0报修中
            case 0:
                mCarTypeTx.setText("报修中");
                break;
            case 1:
                mCarTypeTx.setText("空闲");

                break;
            case 2:
                mCarTypeTx.setText("接乘客中");
                toGetUserList();
                break;
            case 3:
                mCarTypeTx.setText("满员");

                break;
            case 4:
                mCarTypeTx.setText("已出发 ");

                break;
            case 5:
                mCarTypeTx.setText("休息");

                break;
        }
    }

    public void toGetUserList() {
        ProgressHelp.showProgress(getContext());
        mNetLinePresenter.toGetUserList(mNowCarsBean.getCarid(), mLoginBean.getDriver().getDriverid());
    }

    private void initRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBaseRecyclerAdapter = new BaseRecyclerAdapter<>(getContext());
        mRecyclerView.setAdapter(mBaseRecyclerAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initPresenter() {
        mNetLinePresenter = new NetLinePresenter(getContext(), this);
        addPresenter(mNetLinePresenter);
    }

    @Override
    protected void PresenterGetData() {

    }

    @OnClick(R.id.module_nl_home_back_img)
    public void toBack() {
        getActivity().finish();
    }

    @OnClick(R.id.module_nl_qr_code_ly)
    public void toQrcode() {
        if (isUpCar) {
            NetLineQrCodeActivity.launch(getContext(), getActivity());
        } else {
            ToastUtils.showToast(getContext(), "需要先报班");
        }

    }

    @OnClick(R.id.module_nl_user_map_rl)
    public void toUserMap() {
        if (isUpCar) {
            NetLineMapActivity.launch(getContext(), getActivity(), mNetLineUserListBean.getRecordid());

        } else {
            ToastUtils.showToast(getContext(), "需要先报班");
        }
    }

    @OnClick(R.id.module_nl_bind_ly)
    public void toBind() {
        ProgressHelp.showProgress(getContext());
        mNetLinePresenter.toBindCar("陕A10001", mLoginBean.getDriver().getDriverid());
    }

    @OnClick(R.id.module_nl_post_car_tx)
    public void toPostOnclick() {
        if (!isUpCar) {
            ProgressHelp.showProgress(getContext());
            mNetLinePresenter.toSelectLineList();
        } else {
            ToastUtils.showToast(getContext(), "已经报过班了");
        }

    }

    @OnClick(R.id.module_nl_go_tx)
    public void toCarGo() {
        if (isUpCar) {
            toGoAndSendOver("2");
        } else {
            ToastUtils.showToast(getContext(), "需要先报班");
        }
    }

    @OnClick(R.id.module_nl_send_over_tx)
    public void toCarSendOver() {
        if (isUpCar) {
            toGoAndSendOver("3");
        } else {
            ToastUtils.showToast(getContext(), "需要先报班");
        }

    }

    //1,接单中2，已出发 3全部到达已完成，必填参数
    public void toGoAndSendOver(String type) {
        ProgressHelp.showProgress(getContext());
        mNetLinePresenter.toGoAndSendOver(mRId, type, mNowCarsBean.getCarid());
    }


}
