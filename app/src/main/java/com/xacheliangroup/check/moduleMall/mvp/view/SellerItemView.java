package com.xacheliangroup.check.moduleMall.mvp.view;

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
import com.xacheliangroup.check.moduleMall.mvp.bean.SellerItemBean;
import com.xacheliangroup.check.utils.Measure_Utils;

import butterknife.BindView;

/**
 * author:yz
 * data: 2018/12/21,11:04
 */
public class SellerItemView extends ViewSugar {
    @BindView(R.id.module_cbb_seller_name_tx)
    TextView mTitleTx;
    @BindView(R.id.module_cbb_seller_img)
    ImageView mSellerImg;
    @BindView(R.id.module_cbb_mall_seller_item_star_number_tx)
    TextView mStarNumberTx;
    @BindView(R.id.module_cbb_mall_seller_item_sell_number_tx)
    TextView mSellNumberTx;
    @BindView(R.id.module_cbb_mall_seller_item_address_tx)
    TextView mAddressTx;
    @BindView(R.id.module_cbb_mall_seller_item_distance_tx)
    TextView mDistanceTx;
    @BindView(R.id.module_cbb_mall_seller_item_label1_rl)
    RelativeLayout mLabelOneRl;
    @BindView(R.id.module_cbb_mall_seller_item_label2_rl)
    RelativeLayout mLabelTwoRl;
    @BindView(R.id.module_cbb_mall_seller_item_label3_rl)
    RelativeLayout mLabelThreeRl;
    @BindView(R.id.module_cbb_mall_seller_item_label1_tx)
    TextView mLabelOneTx;
    @BindView(R.id.module_cbb_mall_seller_item_label2_tx)
    TextView mLabelTwoTx;
    @BindView(R.id.module_cbb_mall_seller_item_label3_tx)
    TextView mLabelThreeTx;
    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new SellerItemView(context,parent);
    }
    public SellerItemView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_cbb_recy_item_seller;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
        if(obj!=null && obj instanceof SellerItemBean){
            SellerItemBean sellerItemBea= (SellerItemBean) obj;
            mTitleTx.setText(sellerItemBea.getStorefrontname());
            GlideUtils.toLoadDefaultSquareImg(Measure_Utils.dip2px(context, 2)
            ,140,140,mSellerImg,context,sellerItemBea.getStorephoto());
            mStarNumberTx.setText(sellerItemBea.getStarnumber());
            mSellNumberTx.setText(sellerItemBea.getMonthnumber());
            mAddressTx.setText(sellerItemBea.getAddress());
            mDistanceTx.setText(sellerItemBea.getMinli()+"KM");
            if(TextUtils.isEmpty(sellerItemBea.getPushlabel1())){
                mLabelOneRl.setVisibility(View.INVISIBLE);
            }else {
                mLabelOneTx.setText(sellerItemBea.getPushlabel1());
                mLabelOneRl.setVisibility(View.VISIBLE);
            }
            if(TextUtils.isEmpty(sellerItemBea.getPushlabel2())){
                mLabelTwoRl.setVisibility(View.INVISIBLE);
            }else {
                mLabelTwoTx.setText(sellerItemBea.getPushlabel2());
                mLabelTwoRl.setVisibility(View.VISIBLE);
            }
            if(TextUtils.isEmpty(sellerItemBea.getPushlabel3())){
                mLabelThreeRl.setVisibility(View.INVISIBLE);
            }else {
                mLabelThreeTx.setText(sellerItemBea.getPushlabel3());
                mLabelThreeRl.setVisibility(View.VISIBLE);
            }
        }
    }
}
