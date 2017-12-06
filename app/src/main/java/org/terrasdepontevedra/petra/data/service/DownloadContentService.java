package org.terrasdepontevedra.petra.data.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.ResultReceiver;
import android.support.v4.app.NotificationCompat;

import com.bumptech.glide.request.target.Target;
import com.daimajia.slider.library.GlideApp;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.PetraApplication;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.data.local.LanguageCollector;
import org.terrasdepontevedra.petra.di.component.DaggerServiceComponent;
import org.terrasdepontevedra.petra.di.component.ServiceComponent;
import org.terrasdepontevedra.petra.domain.model.Center;
import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.domain.model.base.Audio;
import org.terrasdepontevedra.petra.domain.model.base.Image;
import org.terrasdepontevedra.petra.domain.model.base.ImageCollection;
import org.terrasdepontevedra.petra.domain.params.GetPlacesByItineraryParam;
import org.terrasdepontevedra.petra.domain.pojo.DownloadedContent;
import org.terrasdepontevedra.petra.domain.usecase.GetCentersUseCase;
import org.terrasdepontevedra.petra.domain.usecase.GetItinerariesWithPlaceUseCase;
import org.terrasdepontevedra.petra.domain.usecase.GetPlacesByItineraryUseCase;
import org.terrasdepontevedra.petra.util.NotificationService;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

import static org.terrasdepontevedra.petra.util.Constants.ARG_RECEIVER;
import static org.terrasdepontevedra.petra.util.Constants.SERVICE_DOWNLOAD_CONTENT_STATUS_RUNNING;
import static org.terrasdepontevedra.petra.util.NotificationService.DOWNLOAD_SERVICE_CHANNEL_ID;


public class DownloadContentService extends IntentService {

    @Inject
    GetCentersUseCase mGetCentersUseCase;
    @Inject
    GetPlacesByItineraryUseCase mGetPlacesByItineraryUseCase;
    @Inject
    GetItinerariesWithPlaceUseCase mGetItinerariesWithPlaceUseCase;
    @Inject
    LanguageCollector mLanguageCollector;
    @Inject
    EventBus mEventBus;

    private NotificationManager mNotifyManager;
    private NotificationCompat.Builder mBuilder;
    private ImageCollection mImageCollection;
    private List<Audio> mAudios;
    private int numberPlaces;
    private int counterPlaces;

    public DownloadContentService() {
        super("DownloadContentService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ServiceComponent serviceComponent =
                DaggerServiceComponent.builder()
                        .applicationComponent(PetraApplication.getApplicationComponent())
                        .build();

        serviceComponent.inject(this);

        mImageCollection = new ImageCollection();
        mAudios = new ArrayList<>();
        initNotification();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ResultReceiver receiver = intent.getParcelableExtra(ARG_RECEIVER);
        receiver.send(SERVICE_DOWNLOAD_CONTENT_STATUS_RUNNING, Bundle.EMPTY);
        mNotifyManager.notify(1, mBuilder.build());

        mGetCentersUseCase
                .setParams(mLanguageCollector.getLanguage())
                .execute(centerCollection -> {
                    for (Center center : centerCollection.asList()) {
                        mImageCollection.addAll(center.getImageCollection().asList());
                        mAudios.add(center.getAudio());
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mGetItinerariesWithPlaceUseCase
                            .setParams(mLanguageCollector.getLanguage())
                            .execute(itineraryCollection -> {
                                numberPlaces += itineraryCollection.getAllPlaces().size();
                                for (Itinerary itinerary : itineraryCollection.asList()) {
                                    mImageCollection.add(itinerary.getImage());
                                    mImageCollection.addAll(itinerary.getImageCollection().asList());
                                    mAudios.add(itinerary.getAudio());
                                    try {
                                        Thread.sleep(1000);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    mGetPlacesByItineraryUseCase
                                            .setParams(new GetPlacesByItineraryParam(mLanguageCollector.getLanguage(), itinerary.getItineraryIdentity()))
                                            .execute(placeCollection -> {
                                                counterPlaces += placeCollection.size();

                                                for (Place place : placeCollection.asList()) {
                                                    mImageCollection.add(place.getImage());
                                                    mImageCollection.addAll(place.getImageCollection().asList());
                                                }

                                                if (counterPlaces == numberPlaces) {
                                                    downloadImageAllImage();
                                                }
                                            });
                                }
                            });
                });
    }

    private void downloadImageAllImage() {
        for (Image image : mImageCollection.asList()) {
            if (image != null && image.getUrl() != null)
                downloadImage(image.getUrl().asString());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        for (Audio audio : mAudios) {
            if (!audio.getUrl().asString().trim().equals(""))
                new DownloadTask().execute(audio.getUrl().asString());
        }

        mNotifyManager.cancel(1);
        mEventBus.post(new DownloadedContent());
    }

    private void downloadImage(String imageUrl) {

        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    GlideApp
                            .with(getApplicationContext())
                            .load(imageUrl)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    private void initNotification() {
        NotificationService mNotificationService = new NotificationService(this);
        mNotificationService.createChannels();
        mNotifyManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(this, DOWNLOAD_SERVICE_CHANNEL_ID);
        mBuilder.setContentTitle(getString(R.string.notification_download_content_title))
                .setContentText(getString(R.string.notification_download_map_description))
                .setProgress(0, 0, true)
                .setSmallIcon(R.drawable.icon);
    }


    private class DownloadTask extends AsyncTask<String, Integer, String> {

        @SuppressWarnings("ResultOfMethodCallIgnored")
        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                String[] split = url.toString().split("/");
                String name = split[split.length - 1];
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream(Environment.getExternalStorageDirectory().getAbsolutePath() + "/audio/" + name);

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }
    }
}
