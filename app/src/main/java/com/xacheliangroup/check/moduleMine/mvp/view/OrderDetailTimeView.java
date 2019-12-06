package com.xacheliangroup.check.moduleMine.mvp.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;
import com.xacheliangroup.check.moduleMine.mvp.bean.OrderDetailTimeBean;

import butterknife.BindView;

/**
 * author:yz
 * data: 2019/2/28,16:19
 */
public class OrderDetailTimeView extends ViewSugar {
    @BindView(R.id.module_cbb_order_detail_time_title_tx)
    TextView mTitleTx;
    @BindView(R.id.module_cbb_order_detail_time_line_tx)
    TextView mLineTx;
    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new OrderDetailTimeView(context,parent);
    }
    public OrderDetailTimeView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_cbb_recy_item_order_detail_time;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
         if(obj!=null&&obj instanceof OrderDetailTimeBean){
             OrderDetailTimeBean orderDetailTimeBean= (OrderDetailTimeBean) obj;
             mTitleTx.setText(orderDetailTimeBean.getTitle());
              if(orderDetailTimeBean.isShowLine()){
                  mLineTx.setVisibility(View.VISIBLE);
              }else {
                  mLineTx.setVisibility(View.GONE);
              }
         }
    }
}
