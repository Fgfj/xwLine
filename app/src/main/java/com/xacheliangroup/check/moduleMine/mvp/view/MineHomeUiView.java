package com.xacheliangroup.check.moduleMine.mvp.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;
import com.xacheliangroup.check.common.glide.GlideUtils;
import com.xacheliangroup.check.moduleMine.mvp.bean.MineHomeUiBean;

import butterknife.BindView;

/**
 * author:yz
 * data: 2018/12/19,11:38
 */
public class MineHomeUiView extends ViewSugar {
    @BindView(R.id.module_cbb_mine_ui_item_img)
    ImageView mImg;
    @BindView(R.id.module_cbb_mine_ui_item_top_tx)
    TextView mTopTx;
    @BindView(R.id.module_cbb_mine_ui_item_title_tx)
    TextView mTitleTx;
    @BindView(R.id.module_cbb_mine_ui_item_line_tx)
    TextView mLineTx;
    @BindView(R.id.module_cbb_mine_ui_item_red_img)
    ImageView mRedImg;
    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new MineHomeUiView(context,parent);
    }
    public MineHomeUiView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_cbb_recy_item_mine_home_ui;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
        if(obj!=null&&obj instanceof MineHomeUiBean){
            MineHomeUiBean mineHomeUiBean= (MineHomeUiBean) obj;
            mTitleTx.setText(mineHomeUiBean.getTitle());
            GlideUtils.loadIntImage(context,mineHomeUiBean.getImgId(),mImg);
            if(mineHomeUiBean.isShowGray()){
                mTopTx.setVisibility(View.VISIBLE);
            }else {
                mTopTx.setVisibility(View.INVISIBLE);
            }
            if(mineHomeUiBean.isShowLine()){
                mLineTx.setVisibility(View.VISIBLE);
            }else {
                mLineTx.setVisibility(View.INVISIBLE);
            }
            if(mineHomeUiBean.isShowRedPoint()){
                mRedImg.setVisibility(View.VISIBLE);
            }else {
                mRedImg.setVisibility(View.INVISIBLE);
            }
        }
    }
}
