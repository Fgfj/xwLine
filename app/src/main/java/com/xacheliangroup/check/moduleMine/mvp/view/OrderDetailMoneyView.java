package com.xacheliangroup.check.moduleMine.mvp.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;
import com.xacheliangroup.check.moduleMine.mvp.bean.OrderDetailMoneyBean;

import butterknife.BindView;

/**
 * author:yz
 * data: 2019/2/28,16:20
 */
public class OrderDetailMoneyView extends ViewSugar {
    @BindView(R.id.module_cbb_item_money_order_detail_title_tx)
    TextView mTitleTx;
    @BindView(R.id.module_cbb_item_money_order_detail_money_tx)
    TextView mMoneyTx;
    @BindView(R.id.module_cbb_item_money_order_detail_line_tx)
    TextView mLineTx;
    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new OrderDetailMoneyView(context,parent);
    }
    public OrderDetailMoneyView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_cbb_recy_item_order_detail_money;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
        if(obj!=null&&obj instanceof OrderDetailMoneyBean){
            OrderDetailMoneyBean orderDetailMoneyBean= (OrderDetailMoneyBean) obj;
            mTitleTx.setText(orderDetailMoneyBean.getTitle());
            mMoneyTx.setText(orderDetailMoneyBean.getMoney());
             if(orderDetailMoneyBean.isShowLine()){
                 mLineTx.setVisibility(View.VISIBLE);
             }else {
                 mLineTx.setVisibility(View.GONE);
             }
        }
    }
}
