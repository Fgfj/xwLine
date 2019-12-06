package com.xacheliangroup.check.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import java.util.Timer;
import java.util.TimerTask;

/**
 * author:yz
 * data: 2018/12/25,17:00
 */
public class ExitBackUtils {
    /**
     双击退出
     */
    private static Boolean isExit = false;
    public static boolean exitBy2Click(int keyCode, KeyEvent event, Context context, ExitBackCallBack exitBackCallBack) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            Timer tExit = null;
            if (isExit == false) {
                isExit = true; // 准备退出
                ToastUtils.showToast(context, "再按一次退出程序");
                tExit = new Timer();
                tExit.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false; // 取消退出
                    }
                }, 2000); // 如果2秒钟内没有按下返回键，则启动定时器取消掉刚才执行的任务

            } else {
                exitBackCallBack.toBack();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.addCategory(Intent.CATEGORY_HOME);
                context.startActivity(intent);
//                System.exit(0);//退出应用
            }
        }
        return false;
    }
    /**
     * 点击banck建 finsh当前activity
     * @param keyCode
     * @param activity
     * @return
     */
    public static boolean exitfinshClick(int keyCode,Activity activity) {
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            activity.finish();
        }
        return false;
    }
}
