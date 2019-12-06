package com.xacheliangroup.check.moduleNetLine.mvp.view;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;
import com.xacheliangroup.check.common.eventbus.MessageEvent;
import com.xacheliangroup.check.common.flag.Flag;
import com.xacheliangroup.check.moduleNetLine.mvp.bean.NetLineUserListBean;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author:yz
 * data: 2019/9/2,15:51
 */
public class NetLineUserListView extends ViewSugar {
    @BindView(R.id.module_nl_user_item_type_tx)
    TextView mTypeTx;
    @BindView(R.id.module_nl_user_item_number_tx)
    TextView mPersonNumberTx;
    @BindView(R.id.module_nl_user_item_start_tx)
    TextView mStartStationTx;
    @BindView(R.id.module_nl_user_item_end_tx)
    TextView mEndStationTx;
    @BindView(R.id.module_nl_user_item_name_tx)
    TextView mUserNameTx;
    @BindView(R.id.module_nl_user_item_station_tx)
    TextView mStationTx;
    private NetLineUserListBean.PalxSlorderListBean mPalxSlorderListBean;

    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new NetLineUserListView(context,parent);
    }

    public NetLineUserListView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_nl_user_list_recy_item;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
        if(obj!=null&&obj instanceof NetLineUserListBean.PalxSlorderListBean){
            mPalxSlorderListBean = (NetLineUserListBean.PalxSlorderListBean) obj;
            mUserNameTx.setText(mPalxSlorderListBean.getUsernames()+"");
            mStartStationTx.setText(mPalxSlorderListBean.getStaradress());
            mEndStationTx.setText(mPalxSlorderListBean.getEndaddress());
            mPersonNumberTx.setText(mPalxSlorderListBean.getPeoplenum()+"");

            if(mPalxSlorderListBean.getStatus()==4|| mPalxSlorderListBean.getStatus()==5){
//1待支付，2已支付 3已派单 4已核销 5已评价 6已退款 7已删除 8，退款中
                mTypeTx.setText("已上车");
            }else {
                mTypeTx.setText("未上车");
            }
            if(TextUtils.isEmpty(mPalxSlorderListBean.getArrivetime())){
                mStationTx.setTextColor(Color.parseColor("#3F3F3F"));
            }else {
                mStationTx.setTextColor(Color.parseColor("#999999"));
            }
            mStationTx.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(mPalxSlorderListBean.getArrivetime())){
                        EventBus.getDefault().post(new MessageEvent(Flag.NETLINE.UP_CAR,mPalxSlorderListBean));
                    }
                }
            });
        }
    }
    @OnClick(R.id.module_nl_user_item_phone_click_ly)
    public void  toCallPhone(){
        EventBus.getDefault().post(new MessageEvent(Flag.NETLINE.CALL_PHONE,mPalxSlorderListBean.getOrdermobile()));
    }
}
