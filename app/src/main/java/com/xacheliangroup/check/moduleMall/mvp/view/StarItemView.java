package com.xacheliangroup.check.moduleMall.mvp.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;
import com.xacheliangroup.check.moduleMall.mvp.bean.StarItemBean;

import butterknife.BindDrawable;
import butterknife.BindView;

/**
 * author:yz
 * data: 2019/2/19,11:11
 */
public class StarItemView extends ViewSugar{
    @BindDrawable(R.drawable.module_cbb_home_mall_star_icon)
    Drawable mFocusIcon;
    @BindDrawable(R.drawable.module_cbb_home_mall_star_defult_icon)
    Drawable mDefaultIcon;
    @BindView(R.id.module_cbb_rv_star_img)
    ImageView mStarImg;
    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new StarItemView(context,parent);
    }
    public StarItemView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_cbb_recy_item_star;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
        if(obj!=null&&obj instanceof StarItemBean){
            StarItemBean starItemBean= (StarItemBean) obj;
            if(starItemBean.isLight){
                mStarImg.setBackground(mFocusIcon);
            }else {
                mStarImg.setBackground(mDefaultIcon);
            }
        }
    }
}
