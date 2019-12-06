package com.xacheliangroup.check.checkTickets.download;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;

import com.xacheliangroup.check.R;

/**
 * author:yz
 * data: 2019/6/10,15:14
 */
public class CommonMessageNotification {
    /**
     * The unique identifier for this type of notification.
     */
    private static final String NOTIFICATION_TAG = "CommonMessage";
    public static final int ID_UPDATE = 0;

    /**
     * Shows the notification, or updates a previously shown notification of
     * this type, with the given parameters.
     * <p>
     * the notification.
     * <p>
     * presentation of common message notifications. Make
     * sure to follow the
     * <a href="https://developer.android.com/design/patterns/notifications.html">
     * Notification design guidelines</a> when doing so.
     *
     * @see #cancel(Context, int)
     */
    public static NotificationCompat.Builder notifyUpdate(final Context context, int currentProgressFor100, String contentText) {

        String title = context.getResources().getString(R.string.app_name);

        String content;
        if (!TextUtils.isEmpty(contentText)) {
            content = contentText;
        } else {
//            content = context.getString(R.string.is_downloading_newest_version);
            content = "正在下载最新版本";
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)

//                .setDefaults(Notification.DEFAULT_ALL)
                .setSmallIcon(R.drawable.app_logo_launch)
                .setContentTitle(title)
                .setContentText(content)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setProgress(100, currentProgressFor100, true)
                .setAutoCancel(false);
        notify(context, builder.build(), ID_UPDATE
        );
        return builder;
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
    private static void notify(final Context context, final Notification notification, int id) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm != null) {
            nm.notify(NOTIFICATION_TAG, id, notification);
        }
    }

    /**
     * Cancels any notifications of this type previously shown using
     */
    @TargetApi(Build.VERSION_CODES.ECLAIR)
    public static void cancel(final Context context, int id) {
        final NotificationManager nm = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        if (nm != null) {
            nm.cancel(NOTIFICATION_TAG, id);
        }
    }
}
