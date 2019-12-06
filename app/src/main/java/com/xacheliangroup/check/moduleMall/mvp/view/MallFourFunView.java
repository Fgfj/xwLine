package com.xacheliangroup.check.moduleMall.mvp.view;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;
import com.xacheliangroup.check.common.glide.GlideUtils;
import com.xacheliangroup.check.moduleMall.mvp.bean.MallHeadBean;

import butterknife.BindView;

/**
 * author:yz
 * data: 2019/2/22,16:04
 */
public class MallFourFunView extends ViewSugar {
    @BindView(R.id.module_cbb_mall_home_four_fun_img)
    ImageView mImg;
    @BindView(R.id.module_cbb_mall_home_four_fun_tx)
    TextView mTx;
    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new MallFourFunView(context,parent);
    }
    public MallFourFunView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_cbb_recy_item_four_fun;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
        if(obj!=null&&obj instanceof MallHeadBean.FuncationalBean){
            MallHeadBean.FuncationalBean funcationalBean= (MallHeadBean.FuncationalBean) obj;
            GlideUtils.loadImage(context,funcationalBean.getFunPic(),mImg);
            mTx.setText(funcationalBean.getFunTitle());
        }
    }
}
