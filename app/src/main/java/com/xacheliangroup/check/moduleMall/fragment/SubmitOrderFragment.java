package com.xacheliangroup.check.moduleMall.fragment;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseFragment;
import com.xacheliangroup.check.common.eventbus.MessageEvent;
import com.xacheliangroup.check.common.flag.Flag;
import com.xacheliangroup.check.common.glide.GlideUtils;
import com.xacheliangroup.check.common.http.okhttp.IActionListener;
import com.xacheliangroup.check.common.type.AddressEnum;
import com.xacheliangroup.check.moduleMall.activity.PayOrderActivity;
import com.xacheliangroup.check.moduleMall.activity.SubmitOrderActivity;
import com.xacheliangroup.check.moduleMall.mvp.bean.GoodsDetailBean;
import com.xacheliangroup.check.moduleMine.activity.SettingAddressActivity;
import com.xacheliangroup.check.moduleMine.mvp.MinePresenter;
import com.xacheliangroup.check.moduleMine.mvp.bean.AddressItemBean;
import com.xacheliangroup.check.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/2/22,10:30
 */
public class SubmitOrderFragment extends BaseFragment implements IActionListener.ViewAction {
    MinePresenter mMinePresenter;
    int mPayGoodsNumber =1;
    AddressItemBean mPayOrderAddressItemBean;//收货地址bean
    @BindView(R.id.module_cbb_submit_goods_number_tx)
    TextView mGoodsPayNumberTx;
    @BindView(R.id.module_cbb_submit_goods_img)
    ImageView mGoodsImg;
    @BindView(R.id.module_cbb_submit_goods_title_img)
    TextView mGoodsTitleTx;
    @BindView(R.id.module_cbb_submit_goods_content_tx)
    TextView mGoodsContentTx;
    @BindView(R.id.module_cbb_submit_goods_price_tx)
    TextView mGoodsPriceTx;
    @BindView(R.id.module_cbb_submit_goods_postage_tx)
    TextView mGoodPostageTx;
    @BindView(R.id.module_cbb_submit_goods_pay_money_tx)
    TextView mGoodsPayAllMoneyTx;
    private GoodsDetailBean mGoodsDeatilBean;
    @BindView(R.id.module_cbb_submit_order_name_tx)
    TextView mAddressNameTx;
    @BindView(R.id.module_cbb_submit_order_phone_tx)
    TextView mAddressPhoneTx;
    @BindView(R.id.module_cbb_submit_order_address_tx)
    TextView mAddressContentTx;
    @BindView(R.id.module_cbb_submit_order_have_address_ly)
    LinearLayout mHaveAddressLy;
    @BindView(R.id.module_cbb_submit_order_no_have_address_ly)
    LinearLayout mNoHaceAddressLy;


    @Override
    public void showInfoView(String type, Object obj) {
        switch (type){
            case MinePresenter.TO_GET_ADDRESS_LIST_DATA:
                 if(obj!=null&&obj instanceof List){
                    List<AddressItemBean> list= (List<AddressItemBean>) obj;
                    if(list.isEmpty()){
                        mHaveAddressLy.setVisibility(View.GONE);
                        mNoHaceAddressLy.setVisibility(View.VISIBLE);
                    }else {
                        for(AddressItemBean addressItemBean:list){
                            if(TextUtils.equals(addressItemBean.getIfDefault(),"0")){
                                changeAddressDefaultView(addressItemBean);
                            }
                        }
                        mHaveAddressLy.setVisibility(View.VISIBLE);
                        mNoHaceAddressLy.setVisibility(View.GONE);
                    }
                 }
                break;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        switch (event.getType()) {
            case Flag.EVENT.PAY_GOODS_CHOICE_ADDRESS:
                 if(event.getObject()!=null&&event.getObject() instanceof AddressItemBean){
                     AddressItemBean addressItemBean= (AddressItemBean) event.getObject();
                     changeAddressDefaultView(addressItemBean);
                 }
                break;
        }
    }

    private void changeAddressDefaultView(AddressItemBean addressItemBean) {
        mPayOrderAddressItemBean=addressItemBean;
        mAddressNameTx.setText(addressItemBean.getName());
        mAddressPhoneTx.setText(addressItemBean.getPhoneNumber());
        mAddressContentTx.setText(addressItemBean.getAddress());
    }


    @Override
    protected void onRequestConnected() {

    }

    @Override
    protected void onRequestNoConnected() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.module_cbb_fragment_submit_order;
    }

    @Override
    protected void onCreateViewInit(View parentView) {
        setEventBusAction();
        getData();
    }

    private void getData() {
        Intent intent = getActivity().getIntent();
        if(intent!=null&&intent.hasExtra(SubmitOrderActivity.GOODS_DETAIL_BEAN)){
            mGoodsDeatilBean = (GoodsDetailBean) intent.getSerializableExtra(SubmitOrderActivity.GOODS_DETAIL_BEAN);
            changeView(mGoodsDeatilBean);
        }
    }

    private void changeView(GoodsDetailBean mGoodsDeatilBean) {
        GlideUtils.loadImage(getContext(),mGoodsDeatilBean.getMdseBannerList().get(0),mGoodsImg);
        mGoodsTitleTx.setText(mGoodsDeatilBean.getMdseTitle());
        mGoodsContentTx.setText(mGoodsDeatilBean.getMdseContent());
        mGoodsPriceTx.setText("¥ "+mGoodsDeatilBean.getMdsePrice());//单价
        mGoodPostageTx.setText("¥ "+mGoodsDeatilBean.getMdsePostage());//邮费
        mGoodsPayAllMoneyTx.setText("¥ "+mPayGoodsNumber*(Double.valueOf(mGoodsDeatilBean.getMdsePostage())+Double.valueOf(mGoodsDeatilBean.getMdsePrice())));//总价
        mGoodsPayNumberTx.setText("1");//购买数量
    }

    @Override
    protected void initPresenter() {
        mMinePresenter=new MinePresenter(getContext(),this);
        addPresenter(mMinePresenter);
    }

    @Override
    protected void PresenterGetData() {

        mMinePresenter.toGetAddressListData();
    }
    @OnClick(R.id.module_cbb_submit_order_back_img)
    public void toBack(){
        getActivity().finish();
    }
    @OnClick(R.id.module_cbb_submit_order_submit_rl)
    public void toPayOrder(){
        ToastUtils.showToast(getContext(),""+mPayOrderAddressItemBean.getAddressId()+mPayOrderAddressItemBean.getName());
        PayOrderActivity.launch(getContext(),getActivity());
    }
    @OnClick(R.id.module_cbb_submit_goods_add_number_img)
    public void toAddPayNumber(){
        mPayGoodsNumber++;
        mGoodsPayNumberTx.setText(""+ mPayGoodsNumber);
        mGoodsPayAllMoneyTx.setText("¥ "+mPayGoodsNumber*(Double.valueOf(mGoodsDeatilBean.getMdsePostage())+Double.valueOf(mGoodsDeatilBean.getMdsePrice())));//总价
    }
    @OnClick(R.id.module_cbb_submit_goods_remove_number_img)
    public void toRemoPayNumber(){
        if(mPayGoodsNumber>1){
            mPayGoodsNumber--;
            mGoodsPayNumberTx.setText(""+ mPayGoodsNumber);
            mGoodsPayAllMoneyTx.setText("¥ "+mPayGoodsNumber*(Double.valueOf(mGoodsDeatilBean.getMdsePostage())+Double.valueOf(mGoodsDeatilBean.getMdsePrice())));//总价
        }
    }
    @OnClick(R.id.module_cbb_submit_order_address_ly)
    public void toUpdateAddressLy(){
        SettingAddressActivity.launch(getContext(),getActivity(), AddressEnum.PAY_ORDER_IN);
    }

}
