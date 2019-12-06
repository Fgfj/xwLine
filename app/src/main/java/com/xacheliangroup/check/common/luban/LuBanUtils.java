package com.xacheliangroup.check.common.luban;

import android.content.Context;


import com.xacheliangroup.check.common.log.CbbLogUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

/**
 * Created by admin on 2018/4/16.
 */

public class LuBanUtils {
    public static void toGetImgList(Context context,final List<String> list,final LuBanListener luBanListener){
        final List<String> mImageUrlList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            File file = new File(list.get(i));
            //压缩
            Luban.with(context)
                    .load(file)
                    .setCompressListener(new OnCompressListener() {

                        @Override
                        public void onStart() {
                            CbbLogUtils.debugLog("开始压缩");
                        }
                        @Override
                        public void onSuccess(File file) {
                            mImageUrlList.add(file.getAbsolutePath());
                            if (mImageUrlList.size() == list.size()) {
                                CbbLogUtils.debugLog(mImageUrlList);
                                luBanListener.success(mImageUrlList);
                            }
                        }
                        @Override
                        public void onError(Throwable e) {
                            CbbLogUtils.debugLog(e.toString()+"压缩失败");
                            luBanListener.fail();
                        }
                    }).launch();
        }
    }
}
