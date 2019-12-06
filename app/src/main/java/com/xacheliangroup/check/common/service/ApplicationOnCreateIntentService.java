package com.xacheliangroup.check.common.service;

import android.app.IntentService;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by admin on 2018/3/7.
 */

public class ApplicationOnCreateIntentService extends IntentService {
    private static final String ACTION_INIT_WHEN_APP_CREAT=" com.yz.base.service.action.INIT";
    private static final String Tag="AppIntentService";
    public static ApplicationOnCreateListener applicationOnCreateListener;
    public ApplicationOnCreateIntentService() {
        super("ApplicationOnCreateIntentService");

    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(Integer.MAX_VALUE,new Notification()); //这个id不要和应用内的其他同志id一样，不行就写 int.maxValue()        //context.startForeground(SERVICE_ID, builder.getNotification());
        }

    }

    public static void start(Context context, ApplicationOnCreateListener Listener){
        applicationOnCreateListener=Listener;
        Intent intent=new Intent(context,ApplicationOnCreateIntentService.class);
        intent.setAction(ACTION_INIT_WHEN_APP_CREAT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent);
        } else {
            context.startService(intent);
        }
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent!=null){
            final String action=intent.getAction();
            if(TextUtils.equals(action,ACTION_INIT_WHEN_APP_CREAT)){
                performInit();
            }
        }
    }
    private void performInit() {
        if(applicationOnCreateListener!=null){
            applicationOnCreateListener.toDoThing();
            Log.d(Tag,"toDoThing");
        }else {
            Log.d(Tag,"Error:applicationOnCreateListener==null");
        }
    }
}
