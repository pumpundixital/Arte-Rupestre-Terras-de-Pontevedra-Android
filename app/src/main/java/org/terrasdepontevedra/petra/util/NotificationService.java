package org.terrasdepontevedra.petra.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;
import android.os.Build;

public class NotificationService extends ContextWrapper {

    private NotificationManager mManager;
    public static final String DOWNLOAD_SERVICE_CHANNEL_ID = "pumpun.com.petra.util.DOWNLOAD_SERVICE";
    private static final String DOWNLOAD_SERVICE_CHANNEL_NAME = "DOWNLOAD SERVICE";
    public static final String GEOFENCE_CHANNEL_ID = "pumpun.com.petra.util.GEOFENCE_SERVICE";
    private static final String GEOFENCE_CHANNEL_NAME = "GEOFENCE SERVICE";

    public NotificationService(Context base) {
        super(base);
        createChannels();
    }

    public void createChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel downloadServiceChannel = new NotificationChannel(DOWNLOAD_SERVICE_CHANNEL_ID,
                    DOWNLOAD_SERVICE_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            downloadServiceChannel.enableLights(true);
            downloadServiceChannel.enableVibration(true);
            downloadServiceChannel.setLightColor(Color.GREEN);
            downloadServiceChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannel(downloadServiceChannel);


            NotificationChannel geofenceServiceChannel = new NotificationChannel(GEOFENCE_CHANNEL_ID,
                    GEOFENCE_CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            geofenceServiceChannel.enableLights(true);
            geofenceServiceChannel.enableVibration(true);
            geofenceServiceChannel.setLightColor(Color.GREEN);
            geofenceServiceChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            getManager().createNotificationChannel(geofenceServiceChannel);

        }
    }

    private NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return mManager;
    }
}
