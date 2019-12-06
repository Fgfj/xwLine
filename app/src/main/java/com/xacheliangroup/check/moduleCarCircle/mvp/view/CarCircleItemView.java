package com.xacheliangroup.check.moduleCarCircle.mvp.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;
import com.xacheliangroup.check.common.glide.GlideUtils;
import com.xacheliangroup.check.moduleCarCircle.mvp.bean.CarCircleItemBean;
import com.xacheliangroup.check.utils.Measure_Utils;

import butterknife.BindDrawable;
import butterknife.BindView;

/**
 * author:yz
 * data: 2018/12/24,13:51
 */
public class CarCircleItemView extends ViewSugar {
    @BindView(R.id.module_cbb_car_circle_item_title_tx)
    TextView mTitleTx;
    @BindView(R.id.module_cbb_car_circle_item_img)
    ImageView mImg;
    @BindView(R.id.module_cbb_car_circle_user_img)
    ImageView mUserImg;
    @BindView(R.id.module_cbb_car_circle_user_name_tx)
    TextView mUserNameTx;
    @BindView(R.id.module_cbb_car_circle_add_thumb_tx)
    TextView mAddThumbTx;
    @BindView(R.id.module_cbb_car_circle_add_thumb_img)
    ImageView mThumbStatusImg;

    @BindDrawable(R.drawable.module_cbb_thumb_white_icon)
    Drawable mDefaultIcon;
    @BindDrawable(R.drawable.module_cbb_thumb_red_icon)
    Drawable mFocusIcon;
    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new CarCircleItemView(context,parent);
    }
    public CarCircleItemView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_cbb_recy_item_car_circle_home;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
        if(obj!=null&&obj instanceof CarCircleItemBean){
            CarCircleItemBean carCircleItemBean= (CarCircleItemBean) obj;
            mTitleTx.setText(carCircleItemBean.getTitle());
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) mImg.getLayoutParams();
            if(TextUtils.isEmpty(carCircleItemBean.getPichight())||TextUtils.equals(carCircleItemBean.getPichight(),"0")){
                layoutParams.height= Measure_Utils.dip2px(context,200);
            }else {
                layoutParams.height=Integer.valueOf(carCircleItemBean.getPichight())/2;
            }
            mImg.setLayoutParams(layoutParams);
            GlideUtils.loadImage(context,carCircleItemBean.getPicurl(),mImg,layoutParams.width,layoutParams.height);
            GlideUtils.loadAvatarPicture(context,carCircleItemBean.getUserpic(),mUserImg);
            mUserNameTx.setText(carCircleItemBean.getUsername());
            mAddThumbTx.setText(carCircleItemBean.getPerfect());
            if(TextUtils.equals(carCircleItemBean.getIsperfect(),"0")){//是否点赞 1未点赞 0 已点赞
                mThumbStatusImg.setBackground(mFocusIcon);
            }else {
                mThumbStatusImg.setBackground(mDefaultIcon);
            }
        }
    }
}
