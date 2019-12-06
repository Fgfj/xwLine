package com.xacheliangroup.check.moduleMine.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.base.BaseRecycler.BaseRecyclerAdapter;
import com.xacheliangroup.check.common.glide.GlideUtils;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.type.ChangeOrderTypeOperateEnum;
import com.xacheliangroup.check.common.type.OrderDetailBottomType;
import com.xacheliangroup.check.common.type.OrderTypeEnum;
import com.xacheliangroup.check.moduleMine.activity.OrderDetailActivity;
import com.xacheliangroup.check.moduleMine.mvp.MinePresenter;
import com.xacheliangroup.check.moduleMine.mvp.bean.OrderDetailBean;
import com.xacheliangroup.check.moduleMine.mvp.bean.OrderDetailMoneyBean;
import com.xacheliangroup.check.moduleMine.mvp.bean.OrderDetailTimeBean;
import com.xacheliangroup.check.moduleMine.mvp.bean.OrderListItemBean;
import com.xacheliangroup.check.utils.ProgressHelp;
import com.xacheliangroup.check.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/2/28,11:47
 */
public class OrderDetailFragment extends BaseFragment implements IActionListener.ViewAction {
    MinePresenter mMinePresenter;
    @BindView(R.id.module_cbb_order_detail_time_rv)
    RecyclerView mTimeRecyclerView;
    @BindView(R.id.module_cbb_order_detail_money_rv)
    RecyclerView mMoneyRecyclerView;
    BaseRecyclerAdapter<OrderDetailTimeBean> mTimeBaseRecyclerAdapter;
    BaseRecyclerAdapter<OrderDetailMoneyBean> mMoneyBeanBaseRecyclerAdapter;
    private OrderListItemBean mOrderListItemBean;
    @BindView(R.id.module_cbb_mine_order_detail_get_user_name_tx)
    TextView mGetGoodsNameTx;
    @BindView(R.id.module_cbb_mine_order_detail_get_user_address_tx)
    TextView mGetGoodsAddressTx;
    @BindView(R.id.module_cbb_mine_order_detail_goods_name_tx)
    TextView mGoodsNameTx;
    @BindView(R.id.module_cbb_mine_order_detail_goods_content_tx)
    TextView mGoodsContentTx;
    @BindView(R.id.module_cbb_mine_order_detail_goods_img)
    ImageView mGoodsImg;
    @BindView(R.id.module_cbb_mine_order_detail_goods_number_tx)
    TextView mGoodsNumberTx;
    @BindView(R.id.module_cbb_mine_order_detail_goods_money_tx)
    TextView mGoodsMoneyTx;
    @BindView(R.id.module_cbb_mine_order_detail_mail_tx)
    TextView mMailTx;
    @BindView(R.id.module_cbb_mine_order_detail_mail_rl)
    LinearLayout mMailLy;
    @BindView(R.id.module_cbb_mine_order_detail_mail_top_tx)
    TextView mMailTopTx;
    @BindView(R.id.module_cbb_mine_order_detail_top_title_tx)
    TextView mTopTitleTx;
    @BindView(R.id.module_cbb_order_detail_submit_all_rl)
    RelativeLayout mBottomAllRl;
    @BindView(R.id.module_cbb_order_detail_submit_two_rl)
    LinearLayout mTwoBottomLy;
    @BindView(R.id.module_cbb_order_detail_submit_one_rl)
    RelativeLayout mOneBottomRl;//一个按钮
    @BindView(R.id.module_cbb_order_detail_submit_two_left_rl)
    RelativeLayout mTwoLeftBottomRl;//左边按钮牛
    @BindView(R.id.module_cbb_order_detail_submit_two_right_rl)
    RelativeLayout mTwoRightBottomRl;//右边按钮
    @BindView(R.id.module_cbb_order_detail_submit_one_tx)
    TextView mOneTx;
    @BindView(R.id.module_cbb_order_detail_submit_two_left_tx)
    TextView mTwoTx;
    @BindView(R.id.module_cbb_order_detail_submit_two_right_tx)
    TextView mThreeTx;
    private OrderDetailBean mOrderDetailBean;

    @Override
    public void showInfoView(String type, Object obj) {
        ProgressHelp.dismissProgress();
        switch (type) {
            case MinePresenter.TO_GET_ORDER_DETAIL_DATA:
                if (obj != null && obj instanceof OrderDetailBean) {
                    mOrderDetailBean = (OrderDetailBean) obj;
                    changeView(mOrderDetailBean);
                }
                break;
        }
    }

    private void changeView(OrderDetailBean orderDetailBean) {

        GlideUtils.loadImage(getContext(), orderDetailBean.getMdsePhoto(), mGoodsImg);
        mGetGoodsAddressTx.setText(orderDetailBean.getAddress());
        mGetGoodsNameTx.setText(orderDetailBean.getUserName() + "  " + orderDetailBean.getUserPhone());
        mGoodsNameTx.setText(orderDetailBean.getMdseName());
        mGoodsContentTx.setText(orderDetailBean.getMdseDes());
        mGoodsNumberTx.setText("数量：" + orderDetailBean.getBuyCount());
        mGoodsMoneyTx.setText("¥" + orderDetailBean.getOrderMoney());
        switch (OrderTypeEnum.getTypeEnumByType(orderDetailBean.getOrderType())){//底部单号
            case ORDER_WAIT_PAY:
            case ORDER_WAIT_SEND:
            case ORDER_CANCEL:
                mMailLy.setVisibility(View.GONE);
                break;
            default:
                mMailLy.setVisibility(View.VISIBLE);
                mMailTx.setText(orderDetailBean.getCourierCompany() + ":" + orderDetailBean.getCourierNumber());
                break;
        }
        switch (OrderTypeEnum.getTypeEnumByType(orderDetailBean.getOrderType())){//头部单号
            case ORDER_WAIT_PAY:
                mMailTopTx.setVisibility(View.VISIBLE);
                mMailTopTx.setText("倒计时");
                break;
            case ORDER_WAIT_SEND:
                mMailTopTx.setVisibility(View.VISIBLE);
                mMailTopTx.setText("平台正在处理，商品将在两个工作日内发货");
                break;
            case ORDER_WAIT_GET:
            case ORDER_WAIT_COMMENT:
            case ORDER_COMPLETE:
                mMailTopTx.setVisibility(View.VISIBLE);
                mMailTopTx.setText(orderDetailBean.getCourierCompany() + ":" + orderDetailBean.getCourierNumber());
                break;
            case ORDER_CANCEL:
                mMailTopTx.setVisibility(View.GONE);
                break;


        }
        mTopTitleTx.setText(OrderTypeEnum.getOrderDetailTopTilteTxByType(orderDetailBean.getOrderType()));
        List<OrderDetailMoneyBean> mMoneyList = new ArrayList<>();
        mMoneyList.add(new OrderDetailMoneyBean("商品总额", "¥" + orderDetailBean.getOrderMoney(), true));
        switch (OrderTypeEnum.getTypeEnumByType(orderDetailBean.getOrderType())) {
            case ORDER_WAIT_PAY:
            case ORDER_CANCEL:
                mMoneyList.add(new OrderDetailMoneyBean("运费", "¥" + orderDetailBean.getPostage(), false));
                break;
            default:
                mMoneyList.add(new OrderDetailMoneyBean("运费", "¥" + orderDetailBean.getPostage(), true));
                mMoneyList.add(new OrderDetailMoneyBean("已付款", "¥" + orderDetailBean.getPayMoney(), false));
                break;
        }
        mMoneyBeanBaseRecyclerAdapter.init(mMoneyList);
        mMoneyBeanBaseRecyclerAdapter.notifyDataSetChanged();


        List<OrderDetailTimeBean> mTimeList = new ArrayList<>();
        mTimeList.add(new OrderDetailTimeBean("订单编号：" + orderDetailBean.getOrderNumb(), true));
        mTimeList.add(new OrderDetailTimeBean("下单时间：" + orderDetailBean.getOrderTime(),
                (OrderTypeEnum.getTypeEnumByType(orderDetailBean.getOrderType()) == OrderTypeEnum.ORDER_WAIT_PAY) ? false : true));
        mTimeList.add(new OrderDetailTimeBean("支付时间：" + orderDetailBean.getPaymentTime(),
                (OrderTypeEnum.getTypeEnumByType(orderDetailBean.getOrderType()) == OrderTypeEnum.ORDER_WAIT_SEND) ? false : true));
        mTimeList.add(new OrderDetailTimeBean("发货时间：" + orderDetailBean.getDeliveryTime(),
                (OrderTypeEnum.getTypeEnumByType(orderDetailBean.getOrderType()) == OrderTypeEnum.ORDER_WAIT_GET) ? false : true));
        mTimeList.add(new OrderDetailTimeBean("收货时间：" + orderDetailBean.getTakeTime(), false));
        mTimeList.add(new OrderDetailTimeBean("取消时间：" + orderDetailBean.getCancelTime(), false));
        switch (OrderTypeEnum.getTypeEnumByType(orderDetailBean.getOrderType())) {
            case ORDER_WAIT_PAY:
                mTimeBaseRecyclerAdapter.init(mTimeList.subList(0, 2));
                break;
            case ORDER_WAIT_SEND:
                mTimeBaseRecyclerAdapter.init(mTimeList.subList(0, 3));
                break;
            case ORDER_WAIT_GET:
                mTimeBaseRecyclerAdapter.init(mTimeList.subList(0, 4));
                break;
            case ORDER_WAIT_COMMENT:
                mTimeBaseRecyclerAdapter.init(mTimeList.subList(0, 5));
                break;
            case ORDER_COMPLETE:
                mTimeList.remove(5);
                mTimeBaseRecyclerAdapter.init(mTimeList);
                break;
            case ORDER_CANCEL:
                mTimeList.remove(4);
                mTimeList.remove(3);
                mTimeList.remove(2);
                mTimeBaseRecyclerAdapter.init(mTimeList);
                break;
        }
        mTimeBaseRecyclerAdapter.notifyDataSetChanged();


        switch (OrderTypeEnum.getBotttonShowType(orderDetailBean.getOrderType())){
            case ONE_BUTTON:
                mBottomAllRl.setVisibility(View.VISIBLE);
                mTwoBottomLy.setVisibility(View.GONE);
                mOneBottomRl.setVisibility(View.VISIBLE);
                mOneTx.setText(OrderTypeEnum.getOne(orderDetailBean.getOrderType()).getExplain());
                break;
            case TWO_BUTTON:
                mBottomAllRl.setVisibility(View.VISIBLE);
                mTwoBottomLy.setVisibility(View.VISIBLE);
                mOneBottomRl.setVisibility(View.GONE);
                mTwoTx.setText(OrderTypeEnum.getTwo(orderDetailBean.getOrderType()).getExplain());
                mThreeTx.setText(OrderTypeEnum.getThree(orderDetailBean.getOrderType()).getExplain()+"¥"+orderDetailBean.getPayMoney());
                break;
            case CANCEL_BUTTON:
                mBottomAllRl.setVisibility(View.GONE);
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
        return R.layout.module_cbb_order_detail;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        initRecyclerView();
        getData();
    }

    private void getData() {
        Intent intent = getActivity().getIntent();
        if (intent != null && intent.hasExtra(OrderDetailActivity.ORDER_LIST_BEAN)) {
            mOrderListItemBean = (OrderListItemBean) intent.getSerializableExtra(OrderDetailActivity.ORDER_LIST_BEAN);
        }
    }

    private void initRecyclerView() {
        mTimeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mTimeBaseRecyclerAdapter = new BaseRecyclerAdapter<>(getActivity());
        mTimeRecyclerView.setAdapter(mTimeBaseRecyclerAdapter);
        mTimeRecyclerView.setNestedScrollingEnabled(false);

        mMoneyRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mMoneyBeanBaseRecyclerAdapter = new BaseRecyclerAdapter<>(getActivity());
        mMoneyRecyclerView.setAdapter(mMoneyBeanBaseRecyclerAdapter);
        mMoneyRecyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    protected void initPresenter() {
        mMinePresenter = new MinePresenter(getContext(), this);
        addPresenter(mMinePresenter);
    }

    @Override
    protected void PresenterGetData() {
        ProgressHelp.showProgress(getContext());
        mMinePresenter.toGetOrderDetailData(mOrderListItemBean.getOrderId() + "");
    }

    @OnClick(R.id.module_cbb_mine_order_detail_back_fl)
    public void toBack() {
        getActivity().finish();
    }
    @OnClick(R.id.module_cbb_order_detail_submit_one_rl)
    public void toOneBottomOnclick(){
        if(OrderTypeEnum.getBotttonShowType(mOrderDetailBean.getOrderType())== OrderDetailBottomType.ONE_BUTTON){
            toBottomOnclick(OrderTypeEnum.getOne(mOrderDetailBean.getOrderType()));
        }
    }
    @OnClick(R.id.module_cbb_order_detail_submit_two_left_rl)
    public void toTwoBottomOnclick(){
        if(OrderTypeEnum.getBotttonShowType(mOrderDetailBean.getOrderType())== OrderDetailBottomType.TWO_BUTTON){
            toBottomOnclick(OrderTypeEnum.getTwo(mOrderDetailBean.getOrderType()));
        }
    }
    @OnClick(R.id.module_cbb_order_detail_submit_two_right_rl)
    public void toThreeBottomOnclick(){
        if(OrderTypeEnum.getBotttonShowType(mOrderDetailBean.getOrderType())== OrderDetailBottomType.TWO_BUTTON){
            toBottomOnclick(OrderTypeEnum.getThree(mOrderDetailBean.getOrderType()));
        }
    }
    public void toBottomOnclick(ChangeOrderTypeOperateEnum changeOrderTypeOperateEnum){
        switch (changeOrderTypeOperateEnum){
            case PAY_ORDER_CHANGE:
                ToastUtils.showToast(getContext(),"支付");
                break;
            case GET_ORDER_CHANGE:
                ToastUtils.showToast(getContext(),"确认收货");
                break;
            case CANCEL_ORDER_CHANGE:
                ToastUtils.showToast(getContext(),"取消");
                break;
            case NEW_ORDER_CHANGE:
                ToastUtils.showToast(getContext(),"新下单");
                break;
            case DELETE_ORDER_CHANGE:
                ToastUtils.showToast(getContext(),"删除");
                break;
            case COMMENT_ORDER_CHANGE:
                ToastUtils.showToast(getContext(),"去评价");
                break;
        }
    }


}
