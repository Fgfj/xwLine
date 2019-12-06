package com.xacheliangroup.check;


import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.module.AppGlideModule;

/**
 * 自定义GlideModule,用来改变Glide的一些默认行为：图片质量|缓存地址
 * Created by funnyzhao on 2017/9/2.
 */

@GlideModule
public final class OvAppGlideModule extends AppGlideModule {
//    @Override
//    public void registerComponents(Context context, Glide glide, Registry registry) {
//        super.registerComponents(context, glide, registry);
//    }
//
//    @Override
//    public void applyOptions(Context context, GlideBuilder builder) {
////        customCacheOptions(context,builder);
//    }

    /**
     * 设置缓存设置
     */
    private void customCacheOptions(Context context, GlideBuilder builder) {
        //设置内存缓存大小和bitmap池大小
        MemorySizeCalculator calculator = new MemorySizeCalculator.Builder(context)
                .setMemoryCacheScreens(2)
                .build();
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapCacheSize = calculator.getBitmapPoolSize();
        //比默认的内存分配大20%
        int customMemoryCacheSize = (int) (4 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (4 * defaultBitmapCacheSize);
        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));

        //设置磁盘缓存
        int cacheSize100MegaBytes =  1024 * 1024 * 100;//200M
        //外部磁盘缓存
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));

    }
    @Override
    public boolean isManifestParsingEnabled() {
        //不使用清单配置的方式,减少初始化时间
        return false;
    }
}
