package org.terrasdepontevedra.petra.ui.adventure.yincana;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.john.waveview.WaveView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.domain.pojo.OnLoadGeofences;
import org.terrasdepontevedra.petra.domain.pojo.OnLocationChange;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.ui.map.place.MapMvpView;
import org.terrasdepontevedra.petra.util.CounterHandler;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


public class YincanaMapFragment extends BaseFragment implements
        OnMapReadyCallback, MapMvpView, CounterHandler.CounterListener {

    @Inject
    YincanaPresenter mPresenter;

    @Inject
    EventBus mEventBus;

    @BindView(R.id.button_check_yincana)
    View mButtonCheck;

    @BindView(R.id.wave_view)
    WaveView waveView;


    private final LatLng gvia = new LatLng(42.2208127, -8.7261041);

    private GoogleMap map;
    private PlaceCollection mPlaceCollection;

    private Location mCurrentPosition;

    public static YincanaMapFragment newInstance() {
        return new YincanaMapFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_yincana_map, container, false);
        ButterKnife.bind(getContext(), view);
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!mEventBus.isRegistered(this))
            mEventBus.register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mEventBus.isRegistered(this))
            mEventBus.unregister(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter.attachView(this);
        mPresenter.init();


        new CounterHandler.Builder()
                .incrementalView(mButtonCheck)
                .minRange(0) // cant go any less than -50
                .maxRange(100) // cant go any further than 50
                .startNumber(1)
                .isCycle(false) // 49,50,-50,-49 and so on
                .counterDelay(50) // speed of counter
                .counterStep(1)  // steps e.g. 0,2,4,6...
                .listener(this) // to listen counter results and show them in app
                .build();

        waveView.setProgress(50);

        mButtonCheck.performClick();
        waveView.setProgress(4);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_yincana_map;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.animateCamera(CameraUpdateFactory.zoomIn());
        map.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style));


        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(gvia, 10);
        map.animateCamera(cameraUpdate);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        map.setMyLocationEnabled(true);
        drawGeofences();


        //TODO: event -> startGeofence(places)

    }


    private static final float GEOFENCE_RADIUS = 250.0f; // in meters

    // Draw Geofence circle on GoogleMap
    private void drawGeofences() {
        for (Place place : mPlaceCollection.asList()) {
            if (place.getPosition() != null) {
                CircleOptions circleOptions = new CircleOptions()
                        .center(place.getPosition())
                        .strokeWidth(2.5f)
                        .strokeColor(ContextCompat.getColor(getContext(), R.color.blue_dark_alpha))
                        .fillColor(ContextCompat.getColor(getContext(), R.color.blue_dark_alpha))
                        .radius(GEOFENCE_RADIUS);
                map.addCircle(circleOptions);
            }
        }
    }


    @Override
    public void loadPlaceOnMap(PlaceCollection placeCollection) {
        mPlaceCollection = placeCollection;
        mEventBus.post(new OnLoadGeofences(mPlaceCollection));
        initMap();
    }

    @Override
    public void disablePlaceClick() {

    }


    @Subscribe
    public void onLocationChange(OnLocationChange event) {
        mCurrentPosition = event.location;

    }


    @Override
    public void onIncrement(View view, long number) {
        if (waveView != null) {
            waveView.setProgress((int) number);
        }
        if (number == 100) {
            if(mCurrentPosition==null){
                //Toast.makeText(getContext(),getString(R.string.error_check_location),Toast.LENGTH_SHORT).show();
            }
            else {
                checkPosition();
            }
        }
    }

    @Override
    public void onDecrement(View view, long number) {
        if (waveView != null) {
            waveView.setProgress((int) number);
        }
    }

    private void checkPosition() {
        if(mCurrentPosition!=null) {
            for (Place place : mPlaceCollection.asList()) {
                Location dest = new Location(place.getTitle());
                dest.setLatitude(place.getPosition().latitude);
                dest.setLongitude(place.getPosition().longitude);
                float distance = mCurrentPosition.distanceTo(dest);

                if (distance < 40) {
                    boolean locked = mPresenter.checkForAchievement(place.getPlaceIdentity().asInt());
                    if (locked) {
                        showDialogUnlockAchievement(place);
                        return;
                    }
                }
            }
        }
        else{
        }
    }

    private void showDialogUnlockAchievement(Place place) {
        YincanaAchievementDialog dialog = YincanaAchievementDialog.newInstance();
        dialog.setPlace(place);
        dialog.show(getChildFragmentManager(), YincanaAchievementDialog.class.getName());

    }

}

