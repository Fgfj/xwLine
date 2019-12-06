package com.xacheliangroup.check.common.banner;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.xacheliangroup.check.R;
import com.xacheliangroup.check.common.glide.GlideUtils;
import com.xacheliangroup.check.moduleMall.mvp.bean.MallHeadBean;

import java.util.ArrayList;
import java.util.List;

/**
 * author:yz
 * data: 2018/12/18,16:24
 */
public class BannerUtils {
//    商品详情  单纯字符串轮播
    public static void initListUrlBanner(Context context, final List<String> list,
                                         ViewPager viewPager, RadioGroup mRadioGroup){
        ArrayList<View> bannerViewArray = new ArrayList<>();
        ArrayList<View> startAndEndView = new ArrayList<>(); // 请务必存储 开始和最后的view
        for (int i = 0; i < list.size(); i++) {
            ImageView draweeView = new ImageView(context);
            GlideUtils.loadCenterCropImage(context, list.get(i), draweeView);
            bannerViewArray.add(draweeView);
            if (i == 0) { // 开始的view
                ImageView start = new ImageView(context);
                GlideUtils.loadCenterCropImage(context,list.get(i), start);
                startAndEndView.add(start);
            } else if (i == list.size() - 1) { // 结束的view
                ImageView end = new ImageView(context);
                GlideUtils.loadCenterCropImage(context, list.get(i), end);
                startAndEndView.add(end);
            }
        }
        BannerHelper.initViewList(bannerViewArray, startAndEndView);
        BannerAdapter bannerAdapter = new BannerAdapter(bannerViewArray,context,list);
        viewPager.setAdapter(bannerAdapter);
        if (bannerViewArray.size() > 1) {
            mRadioGroup.setVisibility(View.VISIBLE);
            BannerHelper.getInstance().start(context, true, viewPager, bannerViewArray, mRadioGroup);
        }
    }
    //头条
    public static void initBanner(Context context, final List<MallHeadBean.ShufflingBean> shufflingBeans,
                                  ViewPager viewPager, RadioGroup mRadioGroup){
        List<String> list=new ArrayList<>();
        for(MallHeadBean.ShufflingBean bean:shufflingBeans){
            list.add(bean.getPic());
        }
        ArrayList<View> bannerViewArray = new ArrayList<>();
        ArrayList<View> startAndEndView = new ArrayList<>(); // 请务必存储 开始和最后的view
        for (int i = 0; i < list.size(); i++) {
            ImageView draweeView = new ImageView(context);
            GlideUtils.loadCenterCropImage(context, list.get(i), draweeView);
            bannerViewArray.add(draweeView);
            if (i == 0) { // 开始的view
                ImageView start = new ImageView(context);
                GlideUtils.loadCenterCropImage(context,list.get(i), start);
                startAndEndView.add(start);
            } else if (i == list.size() - 1) { // 结束的view
                ImageView end = new ImageView(context);
                GlideUtils.loadCenterCropImage(context, list.get(i), end);
                startAndEndView.add(end);
            }
        }
        BannerHelper.initViewList(bannerViewArray, startAndEndView);
        BannerAdapter bannerAdapter = new BannerAdapter(bannerViewArray,context,shufflingBeans);
        viewPager.setAdapter(bannerAdapter);
        if (bannerViewArray.size() > 1) {
            mRadioGroup.setVisibility(View.VISIBLE);
            BannerHelper.getInstance().start(context, true, viewPager, bannerViewArray, mRadioGroup);
        }
    }
    //商品详情
    public static void initGoodsBanner(Context context, final List<MallHeadBean.SuperrecommendBean> list,ViewPager viewPager, RadioGroup mRadioGroup){
        ArrayList<View> bannerViewArray = new ArrayList<>();
        ArrayList<View> startAndEndView = new ArrayList<>(); // 请务必存储 开始和最后的view
        int pageSize=list.size()/2;
        if(list.size()%2!=0){
            pageSize++;
        }
        for(int i=0;i<pageSize;i++){
            View view = View.inflate(context, R.layout.module_cbb_view_goods_top_two_img, null);
            ImageView one = view.findViewById(R.id.module_cbb_view_mall_top_item_one_img);
            ImageView two = view.findViewById(R.id.module_cbb_view_mall_top_item_two_img);
            GlideUtils.toLoadDefaultoblongImg(6,340,160,one,context,list.get(i*2).getPic());
            if(i*2+1>list.size()-1){
                two.setVisibility(View.INVISIBLE);
            }else {
                two.setVisibility(View.VISIBLE);
                GlideUtils.toLoadDefaultoblongImg(6,340,160,two,context,list.get(i*2+1).getPic());
            }
            bannerViewArray.add(view);
            if(i==0){
                View startView = View.inflate(context, R.layout.module_cbb_view_goods_top_two_img, null);
                ImageView oneStartImg = startView.findViewById(R.id.module_cbb_view_mall_top_item_one_img);
                ImageView twoStartImg = view.findViewById(R.id.module_cbb_view_mall_top_item_two_img);
                GlideUtils.toLoadDefaultoblongImg(6,340,160,oneStartImg,context,list.get(0).getPic());
                GlideUtils.toLoadDefaultoblongImg(6,340,160,twoStartImg,context,list.get(1).getPic());
                startAndEndView.add(startView);

            }else if(i==pageSize-1){
                View endView = View.inflate(context, R.layout.module_cbb_view_goods_top_two_img, null);
                ImageView oneEndImg = endView.findViewById(R.id.module_cbb_view_mall_top_item_one_img);
                ImageView twoEndImg = view.findViewById(R.id.module_cbb_view_mall_top_item_two_img);
                GlideUtils.toLoadDefaultoblongImg(6,340,160,oneEndImg,context,list.get(i*2).getPic());
                if(i*2+1>list.size()-1){
                    twoEndImg.setVisibility(View.INVISIBLE);
                }else {
                    twoEndImg.setVisibility(View.VISIBLE);
                    GlideUtils.toLoadDefaultoblongImg(6,340,160,twoEndImg,context,list.get(i*2+1).getPic());
                }
                startAndEndView.add(endView);
            }
        }
        BannerHelper.initViewList(bannerViewArray, startAndEndView);
        BannerAdapter bannerAdapter = new BannerAdapter(bannerViewArray,context,list);
        viewPager.setAdapter(bannerAdapter);
        if (bannerViewArray.size() > 1) {
            BannerHelper.getInstance().start(context, true, viewPager, bannerViewArray, mRadioGroup);
        }
    }
}
