package com.xacheliangroup.check.checkTickets.download;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.xacheliangroup.check.common.eventbus.MessageEvent;
import com.xacheliangroup.check.common.flag.Flag;
import com.xacheliangroup.check.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * author:yz
 * data: 2019/6/10,15:15
 */
public class CommonIntentService extends IntentService {
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.heybuluo.overseastribe.common.update.action.FOO";
    private static final String ACTION_BAZ = "com.heybuluo.overseastribe.common.update.action.BAZ";
    private static final String ACTION_UPDATE = "com.heybuluo.overseastribe.common.update.action.UPDATE";

    private static final String EXTRA_PARAM1 = "com.heybuluo.overseastribe.common.update.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.heybuluo.overseastribe.common.update.extra.PARAM2";
    private static final String EXTRA_DOWNLOAD_PATH = "com.heybuluo.overseastribe.common.update.extra.DOWNLOAD.PATH";

    public CommonIntentService() {
        super("CommonIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, CommonIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, CommonIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * 更新服务
     */
    public static void startActionUpdate(Context context, String fullPath) {
        Intent intent = new Intent(context, CommonIntentService.class);
        intent.setAction(ACTION_UPDATE);
        intent.putExtra(EXTRA_DOWNLOAD_PATH, fullPath);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FOO.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionFoo(param1, param2);
            } else if (ACTION_BAZ.equals(action)) {
                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);
                handleActionBaz(param1, param2);
            } else if (ACTION_UPDATE.equals(action)) {
                final String downloadPath = intent.getStringExtra(EXTRA_DOWNLOAD_PATH);

                handleActionDownload(downloadPath);
            }
        }
    }

    private static List<String> isDownloadingList = new ArrayList<>();

    private void handleActionDownload(final String downloadPath) {
        if (TextUtils.isEmpty(downloadPath)
                || isDownloadingList.contains(downloadPath)
                || DownloadUtil.getInstance().isDownloading(downloadPath)
                || DownloadUtil.getInstance().isDownloaded(downloadPath)
        ) {
            return;
        }
        String name = DownloadUtil.getAPKNameByPath(downloadPath);


        DownloadUtil.getInstance().setListener(new DownloadUtil.DownloadListener() {
            @Override
            public void onStart() {
                isDownloadingList.add(downloadPath);
                ToastUtils.showToast(getApplicationContext(), "开始下载");
                CommonMessageNotification.notifyUpdate(getApplicationContext(), 0, "");
            }

            @Override
            public void onFailure(int errorCode, String errorMsg) {
                isDownloadingList.remove(downloadPath);
                ToastUtils.showToast(getApplicationContext(), "下载失败，请稍后重试");
                CommonMessageNotification.cancel(getApplicationContext(), CommonMessageNotification.ID_UPDATE);
            }

            @Override
            public void onSuccess(String filePath) {
                isDownloadingList.remove(downloadPath);
                CommonMessageNotification.cancel(getApplicationContext(), CommonMessageNotification.ID_UPDATE);
                try {
                    InstallUtil.installApk(getApplicationContext(), filePath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onDownloading(long fileTotalSize, long fileDownloadSize, boolean isDown) {
                int downSizeFor100 = (int) (1.0 * fileDownloadSize / fileTotalSize * 100);
//                ToastUtils.showToast(getApplicationContext(), "下载中"+downSizeFor100);
                Log.d("yz",downSizeFor100+"====");
                CommonMessageNotification.notifyUpdate(getApplicationContext(), downSizeFor100, downSizeFor100 + "%");
                EventBus.getDefault().post(new MessageEvent(Flag.NETLINE.UPDATE_PROGRESS,downSizeFor100));
//                if (downSizeFor100 % 5 == 0) {
//
//                    Message message = new Message();
//                    message.what = HANDLER_UPDATE_PROGRESS;
//                    message.obj = downSizeFor100;
//                    downloadHandler.sendMessage(message);
//                }
            }
        }).download(downloadPath, name);
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
    }


}
