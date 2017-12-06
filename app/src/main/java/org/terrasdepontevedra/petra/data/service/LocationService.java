package org.terrasdepontevedra.petra.data.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.PetraApplication;
import org.terrasdepontevedra.petra.domain.pojo.OnGooogleApiConnected;
import org.terrasdepontevedra.petra.domain.pojo.OnLocationChange;

import javax.inject.Inject;

import timber.log.Timber;


public class LocationService extends Service implements LocationListener{

    private GoogleApiClient mGoogleApiClient;

    @Inject
    EventBus mBus;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, LocationService.class);
    }

    @Subscribe
    public void setGoogleClient(OnGooogleApiConnected event) {
        Timber.i("google api client setted");
        mGoogleApiClient = event.googleApiClient;
        startSpyMode();
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        PetraApplication.getApplicationComponent().inject(this);
        if (!mBus.isRegistered(this))
            mBus.register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBus.isRegistered(this))
            mBus.unregister(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        mBus.post(new OnLocationChange(location));
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_NOT_STICKY;
    }


    private void startSpyMode(){
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(60 * 1000);
        locationRequest.setFastestInterval(2 * 1000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        if (ContextCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, this);
        }
    }

}