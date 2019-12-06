package com.xacheliangroup.check.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.lang.reflect.Field;

/**
 * author:yz
 * data: 2018/12/14,11:18
 */
public class Measure_Utils {

    /**
     * 获取标题栏高度
     * @param context
     * @return
     */
    public static int getActionBarHigh(Context context){
        TypedArray actionbarSizeTypedArray = context.obtainStyledAttributes(new int[] { android.R.attr.actionBarSize });
        float h = actionbarSizeTypedArray.getDimension(0, 0);
        int top = (int) h;
        return top;
    }

    /**
     * 获取状态栏高度
     * @param context
     * @return
     */
    public static int getStatusBarHeight(Context  context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            return context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 75;
        }
    }

    /**
     * 改变状态栏颜色电量什么的
     * @param activity
     * @param dark
     */
    public static void setAndroidNativeLightStatusBar(Activity activity, boolean dark) {
        View decor = activity.getWindow().getDecorView();
        if (dark) {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else {
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        }
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void getmetricwith(Activity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;//屏幕宽度 像素
        int height = metrics.heightPixels;//屏幕高度 像素
        float density = metrics.density;//屏幕密度
        int densityDpi = metrics.densityDpi;//屏幕密度 dpi
    }


    //设置listview的高度
    public static void fixListViewHeight(ListView listView) {
        // 如果没有设置数据适配器，则ListView没有子项，返回。
        BaseAdapter listAdapter = (BaseAdapter) listView.getAdapter();
        int totalHeight = 0;
        if (listAdapter == null) {
            return;
        }
        for (int index = 0, len = listAdapter.getCount(); index < len; index++) {
            View listViewItem = listAdapter.getView(index, null, listView);
            // 计算子项View 的宽高
            listViewItem.measure(0, 0);
            // 计算所有子项的高度和
            totalHeight += listViewItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // listView.getDividerHeight()获取子项间分隔符的高度
        // params.height设置ListView完全显示需要的高度
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    //得到view的高度
    public static int getheight(View view) {
        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return view.getMeasuredHeight();
    }

    //设置view的高
    public static void setheight(View view, int hieght) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = layoutParams.height + hieght;
        view.setLayoutParams(layoutParams);
    }

    //得到view的宽度
    public static int getWidth(View view) {
        view.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        return view.getMeasuredWidth();
    }

    //设置listview和头部的高
    public static void fixListViewHeight(ListView listView, int headheight) {
        // 如果没有设置数据适配器，则ListView没有子项，返回。
        BaseAdapter listAdapter = (BaseAdapter) listView.getAdapter();
        int totalHeight = 0;
        if (listAdapter == null) {
            return;
        }
        for (int index = 0, len = listAdapter.getCount(); index < len; index++) {
            View listViewItem = listAdapter.getView(index, null, listView);
            // 计算子项View 的宽高
            listViewItem.measure(0, 0);
            // 计算所有子项的高度和
            totalHeight += listViewItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        // listView.getDividerHeight()获取子项间分隔符的高度
        // params.height设置ListView完全显示需要的高度
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + headheight;
        listView.setLayoutParams(params);
    }
}
