package org.terrasdepontevedra.petra.data.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.support.v4.app.NotificationCompat;

import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.offline.OfflineManager;
import com.mapbox.mapboxsdk.offline.OfflineRegion;
import com.mapbox.mapboxsdk.offline.OfflineRegionError;
import com.mapbox.mapboxsdk.offline.OfflineRegionStatus;
import com.mapbox.mapboxsdk.offline.OfflineTilePyramidRegionDefinition;

import org.json.JSONObject;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.util.NotificationService;

import timber.log.Timber;

import static org.terrasdepontevedra.petra.util.Constants.ARG_RECEIVER;
import static org.terrasdepontevedra.petra.util.Constants.SERVICE_DOWNLOAD_MAP_STATUS_FINISHED;
import static org.terrasdepontevedra.petra.util.Constants.SERVICE_DOWNLOAD_MAP_STATUS_RUNNING;
import static org.terrasdepontevedra.petra.util.NotificationService.DOWNLOAD_SERVICE_CHANNEL_ID;

public class DownloadMapService extends IntentService {

    // JSON encoding/decoding
    private static final String JSON_CHARSET = "UTF-8";
    public static final String JSON_FIELD_REGION_NAME = "FIELD_REGION_NAME";

    private OfflineRegion offlineRegion;
    private OfflineManager offlineManager;
    private NotificationManager mNotifyManager;
    private ResultReceiver receiver;
    private NotificationCompat.Builder mBuilder;

    public DownloadMapService() {
        super("DownloadMapService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        receiver = intent.getParcelableExtra(ARG_RECEIVER);
        receiver.send(SERVICE_DOWNLOAD_MAP_STATUS_RUNNING, Bundle.EMPTY);
        offlineManager = OfflineManager.getInstance(this);
        downloadRegion();
    }

    private void downloadRegion() {

        String styleUrl = "mapbox://styles/mapbox/outdoors-v10";
        LatLng noroeste = new LatLng(42.852472, -7.872764);
        LatLng sureste = new LatLng(41.8678954, -8.8743376);

        LatLngBounds latLngBounds = new LatLngBounds.Builder()
                .include(noroeste) // Northeast
                .include(sureste)
                .build();

        double minZoom = 11;
        double maxZoom = 14;
        OfflineTilePyramidRegionDefinition definition = new OfflineTilePyramidRegionDefinition(
                styleUrl, latLngBounds, minZoom, maxZoom, 1.0f);

        byte[] metadata;
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put(JSON_FIELD_REGION_NAME, org.terrasdepontevedra.petra.util.Constants.MAPBOX_REGION_NAME);
            String json = jsonObject.toString();
            metadata = json.getBytes(JSON_CHARSET);
        } catch (Exception exception) {
            Timber.e("Failed to encode metadata: " + exception.getMessage());
            metadata = new byte[0];
        }

        // Create the offline region and launch the download
        offlineManager.createOfflineRegion(definition, metadata, new OfflineManager.CreateOfflineRegionCallback() {
            @Override
            public void onCreate(OfflineRegion offlineRegion) {
                Timber.i("Offline region created: " + org.terrasdepontevedra.petra.util.Constants.MAPBOX_REGION_NAME);
                DownloadMapService.this.offlineRegion = offlineRegion;
                initNotification();
                launchDownload();
            }

            @Override
            public void onError(String error) {
                Timber.e("Error: " + error);
            }
        });
    }

    private void launchDownload() {
        // Set up an observer to handle download progress and
        // notify the user when the region is finished downloading
        offlineRegion.setObserver(new OfflineRegion.OfflineRegionObserver() {
            @Override
            public void onStatusChanged(OfflineRegionStatus status) {
                // Compute a percentage
                double percentage = status.getRequiredResourceCount() >= 0
                        ? (100.0 * status.getCompletedResourceCount() / status.getRequiredResourceCount()) :
                        0.0;

                if (status.isComplete()) {
                    // Download complete
                    receiver.send(SERVICE_DOWNLOAD_MAP_STATUS_FINISHED, Bundle.EMPTY);

                    // When the loop is finished, updates the notification
                    mBuilder.setContentText("Download complete")
                            // Removes the progress bar
                            .setProgress(0, 0, false);
                    mNotifyManager.notify(0, mBuilder.build());

                    return;
                } else if (status.isRequiredResourceCountPrecise()) {
                    mBuilder.setProgress(100, (int) Math.round(percentage), false);
                    mNotifyManager.notify(0, mBuilder.build());

                }

                Timber.i(String.format("%s/%s resources; %s bytes downloaded.",
                        String.valueOf(status.getCompletedResourceCount()),
                        String.valueOf(status.getRequiredResourceCount()),
                        String.valueOf(status.getCompletedResourceSize())));
            }

            @Override
            public void onError(OfflineRegionError error) {
                Timber.e("onError reason: " + error.getReason());
                Timber.e("onError message: " + error.getMessage());

            }

            @Override
            public void mapboxTileCountLimitExceeded(long limit) {
                Timber.e("Mapbox tile count limit exceeded: " + limit);
            }
        });

        // Change the region state
        offlineRegion.setDownloadState(OfflineRegion.STATE_ACTIVE);
    }


    private void initNotification() {
        NotificationService mNotificationService = new NotificationService(this);
        mNotificationService.createChannels();
        mNotifyManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this, DOWNLOAD_SERVICE_CHANNEL_ID);
        mBuilder.setContentTitle(getString(R.string.notification_download_map_title))
                .setContentText(getString(R.string.notification_download_map_description))
                .setSmallIcon(R.drawable.ic_map);
    }


}
