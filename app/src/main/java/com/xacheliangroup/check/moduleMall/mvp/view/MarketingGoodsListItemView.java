package com.xacheliangroup.check.moduleMall.mvp.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;
import com.xacheliangroup.check.common.glide.GlideUtils;
import com.xacheliangroup.check.moduleMall.mvp.bean.GoodsItemBean;

import butterknife.BindView;

/**
 * author:yz
 * data: 2019/2/20,15:33
 */
public class MarketingGoodsListItemView extends ViewSugar {
    @BindView(R.id.module_cbb_marketing_goods_list_item_img)
    ImageView mImg;
    @BindView(R.id.module_cbb_marketing_goods_list_item_title_tx)
    TextView mTitleTx;
    @BindView(R.id.module_cbb_marketing_goods_list_item_price_tx)
    TextView mPriceTx;
    @BindView(R.id.module_cbb_marketing_goods_list_item_content_tx)
    TextView mContentTx;
    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new MarketingGoodsListItemView(context,parent);
    }
    public MarketingGoodsListItemView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_cbb_recy_item_marketing_goods_list;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
        if(obj!=null&&obj instanceof GoodsItemBean){
            GoodsItemBean mGoodsItemBean= (GoodsItemBean) obj;
            GlideUtils.loadImage(context,mGoodsItemBean.getMdsePic(),mImg);
            mTitleTx.setText(mGoodsItemBean.getTitle());
            mPriceTx.setText("¥  "+mGoodsItemBean.getPrice());
            mContentTx.setText(mGoodsItemBean.getContent());
        }
    }
}
