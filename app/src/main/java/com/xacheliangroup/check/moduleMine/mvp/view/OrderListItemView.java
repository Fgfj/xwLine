package com.xacheliangroup.check.moduleMine.mvp.view;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;
import com.xacheliangroup.check.common.glide.GlideUtils;
import com.xacheliangroup.check.common.type.OrderTypeEnum;
import com.xacheliangroup.check.moduleMine.mvp.bean.OrderListItemBean;

import butterknife.BindView;

/**
 * author:yz
 * data: 2019/2/28,10:57
 */
public class OrderListItemView extends ViewSugar {
    @BindView(R.id.module_cbb_order_list_item_type_tx)
    TextView mTypeTx;
    @BindView(R.id.module_cbb_order_list_item_onclick_tx)
    TextView mOnclickTx;
    @BindView(R.id.module_cbb_order_list_item_onclick_rl)
    RelativeLayout mOnclickRl;
    @BindView(R.id.module_cbb_order_list_item_change_tx)
    TextView mChangeTx;
    @BindView(R.id.module_cbb_order_list_item_py_money_tx)
    TextView mMoneyAllTx;
    @BindView(R.id.module_cbb_order_list_item_goods_content_tx)
    TextView mGoodsContentTx;
    @BindView(R.id.module_cbb_order_list_item_goods_number_tx)
    TextView mGoodsNumberTx;
    @BindView(R.id.module_cbb_order_list_item_goods_name_tx)
    TextView mGoodsNameTx;
    @BindView(R.id.module_cbb_order_list_item_goods_img)
    ImageView mGoodsImg;
    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new OrderListItemView(context,parent);
    }
    public OrderListItemView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_cbb_recy_item_order_list;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
        if(obj!=null&&obj instanceof OrderListItemBean){
            OrderListItemBean orderListItemBean= (OrderListItemBean) obj;
            GlideUtils.loadImage(context,orderListItemBean.getMdsePhoto(),mGoodsImg);
            mTypeTx.setText(OrderTypeEnum.getTypeEnumTxByType(orderListItemBean.getOrderType()));
            String orderTypeExplain=OrderTypeEnum.getOrderListOnclickTxByType(orderListItemBean.getOrderType());
             if(TextUtils.isEmpty(orderTypeExplain)){
                 mOnclickRl.setVisibility(View.GONE);
             }else {
                 mOnclickRl.setVisibility(View.VISIBLE);
                 mOnclickTx.setText(orderTypeExplain);
             }
              if(OrderTypeEnum.getChangeOpreateByType(orderListItemBean.getOrderType())!=null){
                  mChangeTx.setVisibility(View.VISIBLE);
                  mChangeTx.setText(OrderTypeEnum.getChangeOpreateByType(orderListItemBean.getOrderType()).getExplain());
              }else {
                  mChangeTx.setVisibility(View.INVISIBLE);
              }
            mMoneyAllTx.setText("¥"+orderListItemBean.getOrderMoney());
             mGoodsContentTx.setText(orderListItemBean.getMdseDes());
            mGoodsNumberTx.setText("数量："+orderListItemBean.getBuyCount());
            mGoodsNameTx.setText(orderListItemBean.getMdseName());
        }
    }
}
