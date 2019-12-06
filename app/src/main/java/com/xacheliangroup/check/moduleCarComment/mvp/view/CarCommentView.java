package com.xacheliangroup.check.moduleCarComment.mvp.view;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.base.BaseBean;
import com.xacheliangroup.check.common.base.holder.ViewSugar;
import com.xacheliangroup.check.common.glide.GlideUtils;
import com.xacheliangroup.check.moduleCarComment.mvp.bean.CarCommentBean;

import butterknife.BindView;

/**
 * author:yz
 * data: 2018/12/19,17:04
 */
public class CarCommentView extends ViewSugar {
    @BindView(R.id.item_car_comment_tx)
    TextView mTitleTx;
    @BindView(R.id.item_car_comment_top_img)
    ImageView mTopImg;
    @BindView(R.id.item_car_comment_type_im)
    ImageView mTypeImg;
    @DrawableRes
    int vedioImg=R.drawable.module_cbb_car_comment_vedio_music_icon;
    @DrawableRes
    int txImg=R.drawable.module_cbb_car_commen_pic_icon;
    @DrawableRes
    int musicImg=R.drawable.module_cbb_car_comment_music_icon;
    public static ViewSugar getInstance(Context context,ViewGroup parent){
        return new CarCommentView(context,parent);
    }
    public CarCommentView(Context context, ViewGroup parent) {
        super(context, parent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.module_cbb_recy_item_car_comment_home;
    }

    @Override
    public <T extends BaseBean> void bind(T obj) {
        if(obj!=null&&obj instanceof CarCommentBean){
            CarCommentBean carCommentBea= (CarCommentBean) obj;
            mTitleTx.setText(carCommentBea.getTitle());
            GlideUtils.toLoadDefaultoblongImg(1,1400,600,mTopImg,context,carCommentBea.getPic());
//            类型：1视频 2音频 3图文
            switch (carCommentBea.getType()){
                case "1":
                    mTypeImg.setBackgroundResource(vedioImg);
                    break;
                case  "2":
                    mTypeImg.setBackgroundResource(musicImg);
                    break;
                case "3":
                    mTypeImg.setBackgroundResource(txImg);
                    break;
                default:

                    break;
            }
        }
    }
}
