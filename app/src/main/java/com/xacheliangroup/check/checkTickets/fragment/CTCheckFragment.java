package com.xacheliangroup.check.checkTickets.fragment;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.device.ScanDevice;
import android.media.MediaPlayer;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.checkTickets.activity.CTCheckActivity;
import com.xacheliangroup.check.checkTickets.mvp.CTPresenter;
import com.xacheliangroup.check.checkTickets.mvp.Utils;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTBackLocationBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTCarDetailBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTCarListBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTIdOrQrcodeCheckBean;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTUserListBean;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.base.BaseRecycler.BaseRecyclerAdapter;
import com.xacheliangroup.check.common.eventbus.MessageEvent;
import com.xacheliangroup.check.common.flag.Flag;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.log.CbbLogUtils;
import com.xacheliangroup.check.utils.DialogUtils;
import com.xacheliangroup.check.utils.ProgressHelp;
import com.xacheliangroup.check.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/5/15,17:47
 */
public class CTCheckFragment extends BaseFragment implements IActionListener.ViewAction {


    /**
     * 获取身份证号码 或者条形码  进行检票  若果不能直接检票成功
     * 则弹窗显示票务信息 手工进行检票
     * 检票成功以后  将数据添加到乘客列表里面
     */
    private MediaPlayer mMediaPlayer;

    private String mInputType = "0";//0身份证  1二维码
    CTPresenter mCtPresenter;
    private CTCarListBean.DataBean mCtCarListBean;//班次列表bean
    @BindView(R.id.module_ct_check_sell_number_tx)
    TextView mSellNumberTx;
    @BindView(R.id.module_ct_check_all_number_tx)
    TextView mAllNumberTx;
    @BindView(R.id.module_ct_check_all_money_tx)
    TextView mMoneyTx;
    @BindView(R.id.module_ct_check_car_no_tx)
    TextView mCarNoTx;
    @BindView(R.id.module_ct_check_station_tx)
    TextView mStationTx;
    @BindView(R.id.module_ct_back_location_rl)
    RelativeLayout mBackLocationRl;//离位按钮
    @BindView(R.id.module_ct_cancel_back_version_rl)
    RelativeLayout mCancelBackLocationRl;//撤销离位
    @BindView(R.id.module_ct_check_start_rl)
    RelativeLayout mStartReadIdCardRl;//开始读卡
    @BindView(R.id.module_ct_check_keyboard_rl)
    LinearLayout mInputImgRl;//手动输入图标
    private String mType;//0 上车  1 离位
    private CTCarDetailBean mCtCarDetailBean;//班次详情bean
    private Dialog mCheckDialog;
    private Dialog mInputChoiceDialog;
    private Dialog mInputNumberDialog;
    private Dialog mTicketsDetilDialog;
    private TextView mTicketNameTx;
    private TextView mTicketIdNumberTx;
    private TextView mTicketPNumTx;
    private TextView mTicketTimeTx;
    private TextView mTicketStartStaionTx;
    private TextView mTicketEndStaionTx;
    private TextView mTicketTypeTx;
    private TextView mTicketMoneyTx;
    private TextView mTicketSetTx;
    private RelativeLayout mTicketCheckRl;
    private Dialog mDialogShowToast;
    private TextView mDialogShowToastTx;
    private Dialog mDialogBack;
    private TextView mDialogBackTitletx;
    private static final String SN = Utils.getDeviceSN();

    private List<CTUserListBean> mHistoryUserListBean;
    private EditText mInputEt;

    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type) {
            case CTPresenter.TO_GET_CAR_DETAIL_DATA://获取班次信息
                if (obj != null && obj instanceof List) {
                    List<CTCarDetailBean> list = (List<CTCarDetailBean>) obj;
                    if (list.size() != 0) {
                        mCtCarDetailBean = list.get(0);
                        int nub=mCtCarDetailBean.getSaleNum()+mCtCarDetailBean.getDPNum();
                        mSellNumberTx.setText(nub+ "");
                        mAllNumberTx.setText(mCtCarDetailBean.getCheckCount() + "");
                        mMoneyTx.setText(mCtCarDetailBean.getAmount() + "");
                        mCarNoTx.setText(mCtCarDetailBean.getBusCode() + "");
                        mStationTx.setText(mCtCarListBean.getLineName() + "");

                        toGetCheckedListData();
                    }
                }
                break;
            case CTPresenter.TO_GET_CHECKED_USER_LIST://获取检票列表
                if (obj instanceof List) {
                    List<CTUserListBean> listBeans = (List<CTUserListBean>) obj;
                    mHistoryUserListBean = listBeans;
                    for (int i = 0; i < listBeans.size(); i++) {
                        CTUserListBean ctUserListBean = listBeans.get(i);
                        if (TextUtils.equals(mType, "0")) {////0 上车  1 离位
                            ctUserListBean.setShowRl(false);
                        } else {
                            ctUserListBean.setShowRl(true);
                        }
                    }

                    mBaseRecyclerAdapter.init(listBeans);
                    mBaseRecyclerAdapter.notifyDataSetChanged();
                    if (listBeans.isEmpty()) {
                        mRecyclerView.setVisibility(View.GONE);
                    } else {
                        mRecyclerView.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case CTPresenter.TO_BACK_CHECk://退检
                if (obj == null) {
                    ToastUtils.showToast(getContext(), "退检成功");
                    toGetCheckedListData();
                    toGetCarDetail();
                } else {
                    ToastUtils.showToast(getContext(), (String) obj);
                }
                break;
            case CTPresenter.TO_CHECK_FOR_IDCARD_QRCODE://进行第一次检票  身份证或者条形码
                if (obj != null && obj instanceof CTIdOrQrcodeCheckBean) {
                    CTIdOrQrcodeCheckBean ctIdOrQrcodeCheckBean = (CTIdOrQrcodeCheckBean) obj;
                    switch (ctIdOrQrcodeCheckBean.getType()) {
                        case 1://直接检票成功
                            ToastUtils.showToast(getContext(), "检票成功");
                            toGetCheckedListData();
                            toGetCarDetail();
                            //添加检票成功以后  二维码持续掉
                            if (TextUtils.equals(mInputType, "1")) {
                                toCheckScan();
                            }
                            break;
                        case 2://混剪  需要确认
                            showTicketsDetail("2", ctIdOrQrcodeCheckBean);
                            break;
                        case 3://不可检票
                            ToastUtils.showToast(getContext(), "不可检");
                            showTicketsDetail("3", ctIdOrQrcodeCheckBean);
                            mMediaPlayer.start();
                            break;
                        case 4://未查到票
                            ToastUtils.showToast(getContext(), "没有查到票");
                            mMediaPlayer.start();
                            break;

                    }
                } else if (obj instanceof String) {
//                    ToastUtils.showToast(getContext(),(String) obj);
                    mDialogShowToastTx.setText((String) obj);
                    mDialogShowToast.show();
                    toGetCheckedListData();//会排序需要重新刷新
                    mMediaPlayer.start();
                }

                break;
            case CTPresenter.TO_CHECK_FOR_PERSON://人工检票
                if (mTicketsDetilDialog.isShowing()) {
                    mTicketsDetilDialog.dismiss();
                }
                if (obj != null) {
                    ToastUtils.showToast(getContext(), (String) obj);
                    toGetCheckedListData();//会排序需要重新刷新
                } else {
                    ToastUtils.showToast(getContext(), "检票成功");
                    toGetCheckedListData();
                    toGetCarDetail();

                }
                break;
            case CTPresenter.TO_GET_BACK_LOCATION://离位  撤销离位//0 上车  1 离位
                if (obj != null && obj instanceof CTBackLocationBean) {
                    CTBackLocationBean ctBackLocationBean = (CTBackLocationBean) obj;
                    if (TextUtils.equals(ctBackLocationBean.getSuccess(), "true")) {//成功
                        EventBus.getDefault().post(new MessageEvent(Flag.CHECK.UPDATE_CAR_LIST, null));
                        if (TextUtils.equals(ctBackLocationBean.getOpertype(), "1")) {//离位
                            ToastUtils.showToast(getContext(), "离位成功");
                            mType = "1";
                            changUiForType(mType);
                            toPrint();
                        } else {
                            ToastUtils.showToast(getContext(), "撤销离位成功");
                            mType = "0";
                            changUiForType(mType);
                        }
                    } else {//失败
                        if (TextUtils.equals(ctBackLocationBean.getOpertype(), "1")) {//离位
                            ToastUtils.showToast(getContext(), "离位失败");
                        } else {
                            ToastUtils.showToast(getContext(), "撤销离位失败");
                        }
                    }
                }
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case Flag.CHECK.BACK_TICKETS:
                if (event.getObject() != null && event.getObject() instanceof CTUserListBean) {
                    CTUserListBean ctUserListBean = (CTUserListBean) event.getObject();
                    toShowBackCheckDialog(ctUserListBean);
                }

                break;

        }
    }

    private CTUserListBean mCheckOnclickCTUserListBean;

    public void toShowBackCheckDialog(CTUserListBean ctUserListBean) {
        mDialogBackTitletx.setText("是否退检？姓名：" + ctUserListBean.getInsurancedName() + "   票号：" + ctUserListBean.getTicketBill());
        mCheckOnclickCTUserListBean = ctUserListBean;
        mDialogBack.show();

    }

    @BindView(R.id.module_ct_check_rv)
    RecyclerView mRecyclerView;

    BaseRecyclerAdapter<CTUserListBean> mBaseRecyclerAdapter;

    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_ct_check_fragment;
    }

    ScanDevice sm;
    private final static String SCAN_ACTION = "scan.rcv.message";
    private String barcodeStr;

    @Override
    protected void onCreateViewInit(View parentView) {
        setEventBusAction();
        initRecyclerView();
        getCarDetailData();
        initDialog();
        initQrCode();
        mMediaPlayer=MediaPlayer.create(getContext(),R.raw.checkfile);
    }

    private BroadcastReceiver mScanReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {

            byte[] barocode = intent.getByteArrayExtra("barocode");
            int barocodelen = intent.getIntExtra("length", 0);
            byte temp = intent.getByteExtra("barcodeType", (byte) 0);
            byte[] aimid = intent.getByteArrayExtra("aimid");
            barcodeStr = new String(barocode, 0, barocodelen);
            toCheck(barcodeStr);
            sm.stopScan();
        }
    };

    private void initQrCode() {
        sm = new ScanDevice();
        sm.setOutScanMode(0);// 模式-值:0 广播模式，1 个编辑框模式，2 个键盘模 式
    }

    private void initDialog() {
        /**
         * 选择检票方式
         */
        View logoutView = View.inflate(getContext(), R.layout.module_ct_dialog_choice_check_method, null);
        mCheckDialog = DialogUtils.getDiyDialog(getActivity(), getContext(), logoutView, Gravity.CENTER, 0.7f);
        TextView mIDCardTx = logoutView.findViewById(R.id.dialog_ck_choice_id_card);
        TextView mQrCodeTx = logoutView.findViewById(R.id.dialog_ck_choice_ch_photo);
        TextView mCancelTx = logoutView.findViewById(R.id.dialog_ck_choice_cancel);
        mIDCardTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputType = "0";
                toCheckIdCard();
                mCheckDialog.dismiss();
            }
        });
        mQrCodeTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputType = "1";
                toCheckScan();
                mCheckDialog.dismiss();
            }
        });
        mCancelTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCheckDialog.dismiss();
            }
        });
        /**
         * 票信息
         */
        View mTicketsDetailView = View.inflate(getContext(), R.layout.module_ct_dialog_tickets_detail, null);
        mTicketsDetilDialog = DialogUtils.getDiyDialog(getActivity(), getContext(), mTicketsDetailView, Gravity.CENTER, 0.8f);

        mTicketNameTx = mTicketsDetailView.findViewById(R.id.dialog_ticket_detail_name_tx);
        mTicketIdNumberTx = mTicketsDetailView.findViewById(R.id.dialog_ticket_detail_id_card_tx);
        mTicketPNumTx = mTicketsDetailView.findViewById(R.id.dialog_ticket_detail_order_number_tx);
        mTicketTimeTx = mTicketsDetailView.findViewById(R.id.dialog_ticket_detail_time_tx);
        mTicketStartStaionTx = mTicketsDetailView.findViewById(R.id.dialog_ticket_detail_start_station_tx);
        mTicketEndStaionTx = mTicketsDetailView.findViewById(R.id.dialog_ticket_detail_end_station_tx);
        mTicketTypeTx = mTicketsDetailView.findViewById(R.id.dialog_ticket_detail_type_tx);
        mTicketMoneyTx = mTicketsDetailView.findViewById(R.id.dialog_ticket_detail_money_tx);
        mTicketSetTx = mTicketsDetailView.findViewById(R.id.dialog_ticket_detail_seat_tx);
        mTicketCheckRl = mTicketsDetailView.findViewById(R.id.dialog_ticket_detail_check_rl);


        /**
         * 输入身份证号二维码弹窗
         */
        View mInputNumberView = View.inflate(getContext(), R.layout.module_ct_dialog_input_number, null);
        mInputNumberDialog = DialogUtils.getDiyDialog(getActivity(), getContext(), mInputNumberView, Gravity.CENTER, 0.7f);
        final TextView mInputTitleTx = mInputNumberView.findViewById(R.id.dialog_ct_input_title_tx);
        mInputEt = mInputNumberView.findViewById(R.id.dialog_ct_input_et);
        TextView mInputCancelTx = mInputNumberView.findViewById(R.id.dialog_ct_input_cancel_tx);
        TextView mInputSubmitTx = mInputNumberView.findViewById(R.id.dialog_ct_input_submit_tx);
        mInputCancelTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputNumberDialog.dismiss();
            }
        });
        mInputSubmitTx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputNumberDialog.dismiss();
                toCheck(mInputEt.getText().toString());
            }
        });

        /**
         * 输入选择框
         */
        View mInputChoiceView = View.inflate(getContext(), R.layout.module_ct_dialog_input_choice, null);
        mInputChoiceDialog = DialogUtils.getDiyDialog(getActivity(), getContext(), mInputChoiceView, Gravity.CENTER, 0.7f);
        TextView mInputIdCardText = mInputChoiceView.findViewById(R.id.dialog_ck_input_id_card);
        TextView mInputQrCodeText = mInputChoiceView.findViewById(R.id.dialog_ck_input_ch_photo);
        TextView mInputCancelText = mInputChoiceView.findViewById(R.id.dialog_ck_input_cancel);

        mInputIdCardText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputType = "0";
                mInputChoiceDialog.dismiss();
                mInputNumberDialog.show();
                mInputTitleTx.setText("请输入身份证号码");
                mInputEt.setText("");
            }
        });
        mInputQrCodeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputType = "1";
                mInputChoiceDialog.dismiss();
                mInputNumberDialog.show();
                mInputTitleTx.setText("请输入条形码编码");
                mInputEt.setText("");
            }
        });
        mInputCancelText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mInputChoiceDialog.dismiss();
            }
        });


        /**
         *检票提示框
         */

        View mDialogTxView = View.inflate(getContext(), R.layout.module_ct_dialog_tx, null);
        mDialogShowToast = DialogUtils.getDiyDialog(getActivity(), getContext(), mDialogTxView, Gravity.CENTER, 0.7f);
        mDialogShowToastTx = mDialogTxView.findViewById(R.id.module_ct_dialog_tx);

        /**
         * 退检确认框
         */

        View mDialogBackView = View.inflate(getContext(), R.layout.module_ct_dialog_back_check_sure, null);
        mDialogBack = DialogUtils.getDiyDialog(getActivity(), getContext(), mDialogBackView, Gravity.CENTER, 0.7f);
        mDialogBackTitletx = mDialogBackView.findViewById(R.id.ct_dialog_back_check_title_tx);

        RelativeLayout mCancelRl = mDialogBackView.findViewById(R.id.ct_dialog_back_check_cancel);
        mCancelRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogBack.dismiss();
            }
        });
        RelativeLayout mSubmit = mDialogBackView.findViewById(R.id.ct_dialog_back_check_setup_app_submit);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogBack.dismiss();
                ProgressHelp.showProgress(getContext());
                mCtPresenter.toBackCheck(SN, mCheckOnclickCTUserListBean.getSumid() + "",
                        mCheckOnclickCTUserListBean.getClassdate() + "", mCheckOnclickCTUserListBean.getTicketBill() + "",
                        mCheckOnclickCTUserListBean.getCheckid() + "", mCheckOnclickCTUserListBean.getSeat(), mCheckOnclickCTUserListBean.getClassid(), mCheckOnclickCTUserListBean.getCheckType(),
                        mCheckOnclickCTUserListBean.getCheckBill());
            }
        });
    }

    private void showTicketsDetail(String type, final CTIdOrQrcodeCheckBean ctIdOrQrcodeCheckBean) {


        mTicketNameTx.setText(ctIdOrQrcodeCheckBean.getData().getInsurancedName());
        mTicketIdNumberTx.setText(ctIdOrQrcodeCheckBean.getData().getInsurancedIDCard());
        mTicketPNumTx.setText("票号：" + ctIdOrQrcodeCheckBean.getData().getTicketBill() + "");
        mTicketTimeTx.setText(ctIdOrQrcodeCheckBean.getData().getClasdatetime());
        mTicketStartStaionTx.setText(ctIdOrQrcodeCheckBean.getData().getOwnerStationName());
        mTicketEndStaionTx.setText(ctIdOrQrcodeCheckBean.getData().getArriveName());
        mTicketTypeTx.setText(ctIdOrQrcodeCheckBean.getData().getTicketTypeName());
        mTicketSetTx.setText("座位号：" + ctIdOrQrcodeCheckBean.getData().getSeat());
        mTicketMoneyTx.setText(ctIdOrQrcodeCheckBean.getData().getPrice() + "");

        if (TextUtils.equals(type, "2")) {//混剪  需要确认
            mTicketCheckRl.setVisibility(View.VISIBLE);
        } else {//不可见
            mTicketCheckRl.setVisibility(View.GONE);
        }
        mTicketCheckRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressHelp.showProgress(getContext());
                mCtPresenter.toPersonCheck(SN, mCtCarDetailBean.getID() + "",
                        ctIdOrQrcodeCheckBean.getData().getID() + "", ctIdOrQrcodeCheckBean.getData().getClasdatetime(), mCtCarDetailBean.getClasID() + "",
                        ctIdOrQrcodeCheckBean.getData().getCheckBill(), ctIdOrQrcodeCheckBean.getData().getIsFree() + "", mCtCarDetailBean.getBusCode());
            }
        });
        mTicketsDetilDialog.show();

    }

    private void getCarDetailData() {
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(CTCheckActivity.CAR_DETAIL_BEAN)) {
            mCtCarListBean = (CTCarListBean.DataBean) intent.getSerializableExtra(CTCheckActivity.CAR_DETAIL_BEAN);
            CbbLogUtils.debugLog(mCtCarListBean);

        }
        if (intent != null && intent.hasExtra(CTCheckActivity.CAR_DETAIL_TYPE)) {
            mType = intent.getStringExtra(CTCheckActivity.CAR_DETAIL_TYPE);

            changUiForType(mType);

        }
    }

    private void changUiForType(String mType) {
        if (TextUtils.equals(mType, "1")) {//离位
            mBackLocationRl.setVisibility(View.GONE);
            mInputImgRl.setVisibility(View.GONE);
            mStartReadIdCardRl.setVisibility(View.GONE);
            mCancelBackLocationRl.setVisibility(View.VISIBLE);
            List<CTUserListBean> data = mBaseRecyclerAdapter.getData();
            List<CTUserListBean> tt = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                CTUserListBean ctUserListBean = data.get(i);
                ctUserListBean.setShowRl(true);
                tt.add(ctUserListBean);
            }
            mBaseRecyclerAdapter.init(tt);
            mBaseRecyclerAdapter.notifyDataSetChanged();
        } else {
//            mBackLocationRl.setVisibility(View.VISIBLE);
            mInputImgRl.setVisibility(View.VISIBLE);
            mCancelBackLocationRl.setVisibility(View.GONE);
            mStartReadIdCardRl.setVisibility(View.VISIBLE);
            List<CTUserListBean> data = mBaseRecyclerAdapter.getData();
            List<CTUserListBean> tt = new ArrayList<>();
            for (int i = 0; i < data.size(); i++) {
                CTUserListBean ctUserListBean = data.get(i);
                ctUserListBean.setShowRl(false);
                tt.add(ctUserListBean);
            }
            mBaseRecyclerAdapter.init(tt);
            mBaseRecyclerAdapter.notifyDataSetChanged();
        }
    }


    private void initRecyclerView() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mBaseRecyclerAdapter = new BaseRecyclerAdapter<>(getContext());
        mRecyclerView.setAdapter(mBaseRecyclerAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);

    }

    @Override
    public void onDestroy() {
        onPosDestroy();
        super.onDestroy();
        if (sm != null) {
            sm.stopScan();
            sm.setScanLaserMode(8);
            sm.closeScan();
        }
    }


    @Override
    protected void initPresenter() {
        mCtPresenter = new CTPresenter(getContext(), this);
        addPresenter(mCtPresenter);
    }

    @Override
    protected void PresenterGetData() {
        toGetCarDetail();

    }

    private void toGetCarDetail() {
        mCtPresenter.toGetCarDetail(SN, mType, mCtCarListBean.getID() + "", mCtCarListBean.getClasID() + "", mCtCarListBean.getClasDate());

    }

    @OnClick(R.id.module_ct_check_back_img)
    public void toBack() {
        getActivity().finish();
    }


    @OnClick(R.id.module_ct_check_start_rl)
    public void toCheckStart() {

//        mCheckDialog.show();


    }

    /**
     * 调取pos机刷去身份证
     */
    public void toCheckIdCard() {
    }

    public void toCheckScan() {

    }

    @OnClick(R.id.module_ct_check_keyboard_img)
    public void toScannerCode() {
        ToastUtils.showToast(getContext(), "选择手动输入的身份证或者条形码编号");
        mInputChoiceDialog.show();
    }


    /**
     * 检票
     */
    public void toCheck(String number) {
        if (TextUtils.isEmpty(number)) {
            ToastUtils.showToast(getContext(), "请输入正确的证件号码");
            return;
        }
        ProgressHelp.showProgress(getContext());
        if (isIDNumber(number)) {
            ToastUtils.showToast(getContext(), "身份证检票");

            mCtPresenter.toCheckForIdOrQrCode(SN, mCtCarDetailBean.getClasID() + "",
                    mCtCarDetailBean.getID() + "", number, mCtCarDetailBean.getClasDate(), mCtCarDetailBean.getLineID() + "", mCtCarDetailBean.isFree() ? "1" : "0", mCtCarDetailBean.getBusCode());

        } else {
            ToastUtils.showToast(getContext(), "条形码检票");
            mCtPresenter.toCheckForIdOrQrCode(SN, mCtCarDetailBean.getClasID() + "",
                    mCtCarDetailBean.getID() + "", number, mCtCarDetailBean.getClasDate(), mCtCarDetailBean.getLineID() + "", mCtCarDetailBean.isFree() ? "1" : "0", mCtCarDetailBean.getBusCode());

        }
    }

    @OnClick(R.id.module_ct_back_location_rl)
    public void toBackLocation() {

        ProgressHelp.showProgress(getContext());
        mCtPresenter.toBackLocation(mCtCarDetailBean.getID() + "", mCtCarDetailBean.getClasID() + ""
                , SN, mCtCarDetailBean.getClasDate(), "1");
    }

    @OnClick({R.id.module_ct_cancel_back_version_rl})
    public void toCancelBackLocation() {
        ProgressHelp.showProgress(getContext());
        mCtPresenter.toBackLocation(mCtCarDetailBean.getID() + "", mCtCarDetailBean.getClasID() + ""
                , SN, mCtCarDetailBean.getClasDate(), "2");
    }

    /**
     * 获取已检乘客列表
     */
    public void toGetCheckedListData() {
        mCtPresenter.toGetCheckedUserList(SN, mCtCarDetailBean.getClasID() + "", mCtCarDetailBean.getID() + "");
    }

    private boolean isIDNumber(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
                "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        boolean matches = IDNumber.matches(regularExpression);
        //判断第18位校验值
        if (matches) {

            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        return false;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

        }
        return matches;
    }

    public void toPrint() {
    }


    public void onPosDestroy() {
    }

    @Override
    public void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter();
        filter.addAction(SCAN_ACTION);
        if (!sm.isScanOpened()) {
            sm.openScan();
            ToastUtils.showToast(getContext(), "打开扫描");
        }
        if (!sm.getScanBeepState()) {
            sm.setScanBeep();
        }
        getActivity().registerReceiver(mScanReceiver, filter);
    }


    @Override
    public void onPause() {
        super.onPause();
        getActivity().unregisterReceiver(mScanReceiver);
    }


}
