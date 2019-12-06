package com.xacheliangroup.check.common.glide;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.xacheliangroup.check.GlideApp;
import com.xacheliangroup.check.R;

import java.io.File;


/**
 * Created by Afra55 on 2017/10/10.
 * Smile is the best name card.
 */

public class GlideUtils {

    public static void loadAvatarPicture(Context context, Object url, ImageView targetView, @DrawableRes int drawRes) {
        loadAvatarPicture(context, url, targetView, drawRes, new RequestListener() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, DataSource dataSource, boolean isFirstResource) {
                return false;
            }
        });
    }

    public static void loadAvatarPicture(Context context, Object url, ImageView targetView, @DrawableRes int drawRes, RequestListener listener) {
        GlideApp.with(context)
                .load(url)
                .listener(listener)
                .circleCrop()
                .skipMemoryCache(false)
                .placeholder(drawRes)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(targetView)
        ;
    }
    public static void loadAvatarPictureForFile(Context context, File file,ImageView imageView){
        GlideApp.with(context)
                .load(file)
                .diskCacheStrategy(DiskCacheStrategy.DATA)
                .into(imageView)
        ;
    }
    //加载头像
    public static void loadAvatarPicture(Context context, String url, ImageView targetView) {
        loadAvatarPicture(context, url, targetView, R.drawable.default_user_icon);
    }
    //加载头像
    public static void loadAvatarIntPicture(Context context, int url, ImageView targetView) {
        loadAvatarPicture(context, url, targetView, R.drawable.default_user_icon);
    }
    //加载资源图片
    public static void loadIntImage(Context context, int url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .circleCrop()
                .into(imageView);
    }
    //加载资源图片NoCircle
    public static void loadIntRectImage(Context context, int url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .into(imageView);
    }
    /**
     * 加载轮播
     */
    public static void loadCenterCropImage(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.module_cbb_all_img_default)
                .into(imageView);
    }

    /**
     * 广告页加载图片专业
     */
    public static void  loadImageForWHForAdvert(Context context, String url, ImageView imageView,int width,int height){
        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(1);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(width, height);
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .apply(options)
                .into(imageView);
    }

    //加载默认为正方形的图片
    public static void toLoadDefaultSquareImg(int radiusDp,int width,
                                             int height,
                                             ImageView imageView,
                                             Context context,
                                             String url){
        toLoadImg(R.drawable.module_cbb_all_img_rect_default,0,0,radiusDp,
                width,height,imageView,context,url);
    }

    //加载默认为长方形的图片
    public static void toLoadDefaultoblongImg(int radiusDp,int width,
                                               int height,
                                               ImageView imageView,
                                               Context context,
                                               String url){
        toLoadImg(R.drawable.module_cbb_all_img_default,0,0,radiusDp,
                width,height,imageView,context,url);
    }
    //加载图片
    private static void toLoadImg(int defaultImg,
                                  int broderDp,
                                  int broderColor,
                                  int radiusDp,
                                  int width,
                                  int height,
                                  ImageView imageView,
                                  Context context,
                                  String url){
        //设置图片圆角角度
        RoundedCorners roundedCorners= new RoundedCorners(radiusDp);
        //通过RequestOptions扩展功能,override:采样率,因为ImageView就这么大,可以压缩图片,降低内存消耗
        RequestOptions options=RequestOptions.bitmapTransform(roundedCorners).override(width, height);
        GlideApp.with(context)
                .load(url)
                .centerCrop()
                .apply(options)
                .placeholder(defaultImg)
                .into(imageView);
    }

    public static void loadImage(Context context, String url, ImageView imageView,int width,int hight) {
        GlideApp.with(context)
                .load(url)
                .thumbnail(0.2f)
                .placeholder(R.drawable.module_cbb_all_img_default)
                .override(width,hight)
                .centerCrop()
                .into(imageView);
    }
    public static void loadImage(Context context, String url, ImageView imageView) {
        GlideApp.with(context)
                .load(url)
                .thumbnail(0.2f)
                .placeholder(R.drawable.module_cbb_all_img_default)
                .centerCrop()
                .into(imageView);
    }
}
