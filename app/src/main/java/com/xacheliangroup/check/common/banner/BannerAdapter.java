package com.xacheliangroup.check.common.banner;

import android.app.Activity;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.type.BannerEnum;
import com.xacheliangroup.check.common.type.WebEnum;
import com.xacheliangroup.check.common.web.WebActivity;
import com.xacheliangroup.check.moduleMall.mvp.bean.MallHeadBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author:yz
 * data: 2018/12/18,16:30
 */
public class BannerAdapter extends PagerAdapter {

    private final ArrayList<View> contentArray;
    public List<String> imglist;
    public Context context;
    private List<MallHeadBean.SuperrecommendBean> mSuperrecommendBeansList;//超级推荐
    private List<MallHeadBean.ShufflingBean> mShufflingBeanList;//商城首页轮播
    private BannerEnum mBannerEnum;

    public BannerAdapter(ArrayList<View> contentArray, Context context, List data) {
        this.contentArray = contentArray;
        this.context = context;
        if(data.get(0) instanceof MallHeadBean.ShufflingBean){
            this.mShufflingBeanList=data;
            this.mBannerEnum=BannerEnum.MALL_TOP_BANNER;
        }else if(data.get(0) instanceof MallHeadBean.SuperrecommendBean){
            this.mSuperrecommendBeansList=data;
            this.mBannerEnum=BannerEnum.MALL_GROOM_BANNER;
        }else if(data.get(0) instanceof String){
            this.imglist=data;
            this.mBannerEnum=BannerEnum.IMG_BANNER;
        }
    }
    @Override
    public int getCount() {
        return contentArray.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = contentArray.get(position);
        container.addView(view);
        switch (mBannerEnum){
            case IMG_BANNER:
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        // if(position==0){
                        //      ShowImgActivity.start(context,(ArrayList<String>) list,0);
                        //  }else {
                        //      ShowImgActivity.start(context,(ArrayList<String>) list,position-1);
                        //  }
                        }
                    });
                break;
            case MALL_TOP_BANNER:
                    view.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(position==0){
                                WebActivity.launch(context,(Activity) context,
                                        mShufflingBeanList.get(0).getWebUrl(),
                                        WebEnum.MALL_HOME_BANNER);

                            }else {
                                WebActivity.launch(context,(Activity) context,
                                        mShufflingBeanList.get(position-1).getWebUrl(),
                                        WebEnum.MALL_HOME_BANNER);
                            }

                        }
                    });
                break;
            case MALL_GROOM_BANNER:
                    ImageView one=view.findViewById(R.id.module_cbb_view_mall_top_item_one_img);
                    ImageView two=view.findViewById(R.id.module_cbb_view_mall_top_item_two_img);
                    one.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebActivity.launch(context,(Activity) context,
                                    mSuperrecommendBeansList.get(position*2-2).getWebUrl(),
                                    WebEnum.MALL_HOME_SUPER);
                        }
                    });
                    two.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            WebActivity.launch(context,(Activity) context,
                                    mSuperrecommendBeansList.get(position*2-1).getWebUrl(),
                                    WebEnum.MALL_HOME_SUPER);
                        }
                    });
                break;
            case OTHER_BANNER:

                break;
        }


        return contentArray.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        viewPager.removeView((View) object);
    }
}
