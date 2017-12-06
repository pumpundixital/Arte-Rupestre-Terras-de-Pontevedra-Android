package org.terrasdepontevedra.petra.data.service;

import android.Manifest;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.PetraApplication;
import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.domain.pojo.OnGeofenceServiceCreated;
import org.terrasdepontevedra.petra.domain.pojo.OnGooogleApiConnected;
import org.terrasdepontevedra.petra.domain.pojo.OnLoadGeofences;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

import static com.google.android.gms.common.GooglePlayServicesUtil.getErrorString;
import static com.google.android.gms.location.Geofence.NEVER_EXPIRE;

public class GeofenceTrasitionService extends IntentService implements ResultCallback<Status> {

    @Inject
    EventBus mEventBus;

    private GoogleApiClient mGoogleApiClient;
    private PendingIntent mGeofencePendingIntent;
    private PlaceCollection mPlaceCollection;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, GeofenceTrasitionService.class);
    }

    public GeofenceTrasitionService() {
        super("GeofenceTrasitionService");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.i("on create geofence");
        PetraApplication.getApplicationComponent().inject(this);
        if (!mEventBus.isRegistered(this))
            mEventBus.register(this);

        mEventBus.post(new OnGeofenceServiceCreated());

        new Thread(() -> {
            while (mPlaceCollection == null) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            start();

        }).start();
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
    }

    @Subscribe
    public void setGoogleClient(OnGooogleApiConnected event) {
        mGoogleApiClient = event.googleApiClient;
    }

    @Subscribe
    public void setGeofences(OnLoadGeofences event) {
        mPlaceCollection = event.placeCollection;
    }

    private void start() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.GeofencingApi.addGeofences(
                mGoogleApiClient,
                getGeofencingRequest(),
                getGeofencePendingIntent()
        ).setResultCallback(this);
    }

    private GeofencingRequest getGeofencingRequest() {
        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER | GeofencingRequest.INITIAL_TRIGGER_DWELL);
        builder.addGeofences(getGeofecne());
        return builder.build();
    }


    private List<Geofence> getGeofecne() {
        List<Geofence> mGeofenceList = new ArrayList<>();

        for (Place place : mPlaceCollection.asList()) {

            if (place.getPosition() != null) {
                Timber.i("adding new geofence on the service");
                mGeofenceList.add(new Geofence.Builder()
                        .setRequestId(place.getPlaceIdentity().asString())
                        .setCircularRegion(
                                place.getPosition().latitude,
                                place.getPosition().longitude,
                                250) // radios
                        .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_DWELL)
                        .setLoiteringDelay(3000)
                        .setExpirationDuration(NEVER_EXPIRE)
                        .build());
            }

        }


        return mGeofenceList;

    }

    private PendingIntent getGeofencePendingIntent() {
        // Reuse the PendingIntent if we already have it.
        if (mGeofencePendingIntent != null) {
            return mGeofencePendingIntent;
        }
        Timber.i("ojo pendint intent");
        Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
        return PendingIntent.getService(this, 0, intent, PendingIntent.
                FLAG_UPDATE_CURRENT);
    }


    @Override
    public void onResult(@NonNull Status status) {
        if (status.isSuccess()) {
            Log.i(getClass().getSimpleName(), "Success");
        } else {
            // Get the status code for the error and log it using a user-friendly message.
            Log.i(getClass().getSimpleName(), getErrorString(status.getStatusCode()));
        }
    }
}