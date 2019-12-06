package com.xacheliangroup.check.common;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.text.TextUtils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;
//import com.tencent.bugly.crashreport.CrashReport;
import com.xacheliangroup.check.common.api.AppCache;
import com.xacheliangroup.check.common.service.ApplicationOnCreateIntentService;
import com.xacheliangroup.check.common.service.ApplicationOnCreateListener;
import com.xacheliangroup.check.greenDao.DaoMaster;
import com.xacheliangroup.check.greenDao.DaoSession;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * author:yz
 * data: 2018/12/14,09:43
 */
public class CbbApplication extends Application{
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppCache.setContext(this);
        AppCache.setIsDebugMode(true);
        initGreenDao();
        ApplicationOnCreateIntentService.start(getApplicationContext(), new ApplicationOnCreateListener() {
            @Override
            public void toDoThing() {
                initLogger();

//                initBugly();
            }
        });
    }
    private void initLogger() {
        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(true)  //（可选）是否显示线程信息。 默认值为true
                .methodCount(4)         // （可选）要显示的方法行数。 默认2
                .methodOffset(7)        // （可选）隐藏内部方法调用到偏移量。 默认5
//                .logStrategy(customLog) //（可选）更改要打印的日志策略。 默认LogCat
                .tag(BuildConfig.LOG_NAME)   //（可选）每个日志的全局标记。 默认PRETTY_LOGGER
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));
    }
//    private void initBugly() {
//        Context context = getApplicationContext();
//// 获取当前包名
//        String packageName = context.getPackageName();
//// 获取当前进程名
//        String processName = getProcessName(android.os.Process.myPid());
//// 设置是否为上报进程
//        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);
//        strategy.setUploadProcess(processName == null || processName.equals(packageName));
//        CrashReport.initCrashReport(getApplicationContext(), BuildConfig.BUG_LY_APP_ID, true);
//
///BackService/        CrashReport.testJavaCrash();
//    }
    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;

    }
    /**
     * 初始化GreenDao,直接在Application中进行初始化操作
     */
    private void initGreenDao() {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "idcard.db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster daoMaster = new DaoMaster(db);
        daoSession = daoMaster.newSession();
    }

    private DaoSession daoSession;
    public DaoSession getDaoSession() {
        return daoSession;
    }


}
