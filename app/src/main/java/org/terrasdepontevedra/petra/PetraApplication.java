package org.terrasdepontevedra.petra;

import android.app.Application;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.gms.common.api.GoogleApiClient;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.mapbox.mapboxsdk.Mapbox;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.data.service.DownloadResultReceiver;
import org.terrasdepontevedra.petra.data.walk.WalkApi;
import org.terrasdepontevedra.petra.di.component.ApplicationComponent;
import org.terrasdepontevedra.petra.di.component.DaggerApplicationComponent;
import org.terrasdepontevedra.petra.di.module.ApplicationModule;
import org.terrasdepontevedra.petra.di.module.LocaleModule;
import org.terrasdepontevedra.petra.di.module.NetworkModule;
import org.terrasdepontevedra.petra.domain.pojo.DownloadedMap;
import org.terrasdepontevedra.petra.util.Constants;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class PetraApplication extends Application implements DownloadResultReceiver.Receiver {

    @Inject
    EventBus mEventBus;

    private DownloadResultReceiver mReceiver;
    private GoogleApiClient mGoogleApiClient;
    private  WalkApi api;
    private static PetraApplication instance;

    private static ApplicationComponent sApplicationComponent;

    public static synchronized PetraApplication getInstance() {
        return (PetraApplication) sApplicationComponent;
    }


    public static ApplicationComponent getApplicationComponent() {
        return sApplicationComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Mapbox.getInstance(getApplicationContext(), "pk.eyJ1IjoicGFibG9wdW1wdW4iLCJhIjoiY2o4YzJoYWpsMDNwMjJxbnZtNXFsNjRvZSJ9.pJmsX3i7h4aBg2SB77Koag");

        instance = this;
        sApplicationComponent =
                DaggerApplicationComponent.builder()
                        .applicationModule(new ApplicationModule(this))
                        .localeModule(new LocaleModule(this))
                        .networkModule(new NetworkModule(this))
                        .build();

        sApplicationComponent.inject(this);


        initReceiver();
        initTimber();
        initCalligraphy();
        initRetrofitWalk();
    }

    public static PetraApplication getApp() {
        return instance;
    }

    public WalkApi getApi(){
        return api;
    }

    @Override
    public void onReceiveResult(int resultCode, Bundle resultData) {
        switch (resultCode) {
            case Constants.SERVICE_DOWNLOAD_MAP_STATUS_RUNNING:
                Timber.i("status running");
                break;
            case Constants.SERVICE_DOWNLOAD_MAP_STATUS_FINISHED:
                Timber.i("status finished");
                mEventBus.post(new DownloadedMap());
                break;
            case Constants.SERVICE_DOWNLOAD_MAP_STATUS_ERROR:
                Timber.i("status error");
                break;
        }
    }

    public DownloadResultReceiver getReceiver() {
        return mReceiver;
    }

    private void initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }

    private void initReceiver() {
        mReceiver = new DownloadResultReceiver(new Handler());
        mReceiver.setReceiver(this);
    }

    private void initCalligraphy(){
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    public GoogleApiClient getGoogleApiClient() {
        return mGoogleApiClient;
    }

    public void setGoogleApiClient(GoogleApiClient googleApiClient) {
        this.mGoogleApiClient = googleApiClient;
    }

    private void initRetrofitWalk() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WalkApi.API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).build();
        api = retrofit.create(WalkApi.class);
    }
}
