package com.xacheliangroup.check.checkTickets.mvp.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.checkTickets.mvp.bean.CTUserListBean;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;
import com.xacheliangroup.check.common.eventbus.MessageEvent;
import com.xacheliangroup.check.common.flag.Flag;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;

/**
 * author:yz
 * data: 2019/5/15,23:59
 */
public class CheckTicketsUserListView extends ViewSugar {
//    @BindView(R.id.module_ct_item_user_img)
//    ImageView mImg;
    @BindView(R.id.item_checked_user_list_back_rl)
    RelativeLayout mBackTicketsRl;

    @BindView(R.id.item_ticket_detail_bill_tx)
    TextView mTicketBillTx;
    @BindView(R.id.item_ticket_detail_money_tx)
    TextView mTicketMoneyTx;
    @BindView(R.id.item_ticket_detail_time_tx)
    TextView mTicketTimeTx;
//    @BindView(R.id.item_ticket_detail_start_station_tx)
//    TextView mStartStationTx;
    @BindView(R.id.item_ticket_detail_end_station_tx)
    TextView mEndStationTx;
    @BindView(R.id.item_ticket_detail_user_name_tx)
    TextView mUserNameTx;
    @BindView(R.id.item_ticket_detail_user_id_tx)
    TextView mUserIdCardTx;
    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new CheckTicketsUserListView(context,parent);
    }
    public CheckTicketsUserListView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_ct_rv_item_check_tickets;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
        if(obj!=null&& obj instanceof CTUserListBean){
            final CTUserListBean ctUserListBean= (CTUserListBean) obj;
            mTicketBillTx.setText("票号："+ctUserListBean.getTicketBill()+"");
            mTicketMoneyTx.setText(ctUserListBean.getPrice()+"");
            mTicketTimeTx.setText(ctUserListBean.getClasDateTime()+"");
//            mStartStationTx.setText(ctUserListBean.getOwnerStationName());
            mEndStationTx.setText(ctUserListBean.getArriveName());
            mUserNameTx.setText(ctUserListBean.getInsurancedName());
            if(!TextUtils.isEmpty(ctUserListBean.getInsuranceIdCard())&&ctUserListBean.getInsuranceIdCard().length()>=18){
                String id=ctUserListBean.getInsuranceIdCard();
                mUserIdCardTx.setText("身份证号："+id.substring(0, 3) + "********" + id.substring(11));
            }else {
                mUserIdCardTx.setText(ctUserListBean.getInsuranceIdCard());
            }

//            checktype  0可退，1不可退
            if(TextUtils.equals(ctUserListBean.getCheckType(),"0")){
                mBackTicketsRl.setVisibility(View.VISIBLE);
            }else {
                mBackTicketsRl.setVisibility(View.GONE);
            }
            mBackTicketsRl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EventBus.getDefault().post(new MessageEvent(Flag.CHECK.BACK_TICKETS,ctUserListBean));
                }
            });
            if(ctUserListBean.isShowRl()){
                mBackTicketsRl.setVisibility(View.GONE);
            }else {
                mBackTicketsRl.setVisibility(View.VISIBLE);
            }
//            GlideUtils.loadAvatarPicture(context,"http://img4.duitang.com/uploads/item/201406/30/20140630212346_Lnmxr.jpeg",mImg);
        }
    }
}
