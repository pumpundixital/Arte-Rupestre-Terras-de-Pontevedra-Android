package org.terrasdepontevedra.petra.ui.map.itinerary;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.model.base.Locale;
import org.terrasdepontevedra.petra.domain.pojo.OnMapReady;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.ui.itinerary.ItineraryActivity;
import org.terrasdepontevedra.petra.util.MapUtils;

import javax.inject.Inject;


public class ItineraryMapGoogleFragment
        extends BaseFragment
        implements OnMapReadyCallback, ItineraryMapMvpView, GoogleMap.OnMarkerClickListener {

    @Inject
    ItineraryMapPresenter mItineraryMapPresenter;
    @Inject
    EventBus mEventBus;

    private Handler mHandler;
    private Runnable mAnimation;
    private GoogleMap mGoogleMap;
    private Itinerary mItinerary;
    private Locale mLocale;
    private boolean mIsDisabledClickPlace;

    public static ItineraryMapGoogleFragment newInstance(Itinerary itinerary) {
        ItineraryMapGoogleFragment itineraryMapGoogleFragment = new ItineraryMapGoogleFragment();
        itineraryMapGoogleFragment.setItinerary(itinerary);
        return itineraryMapGoogleFragment;
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_map_google;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mItineraryMapPresenter.detachView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mItineraryMapPresenter.attachView(this);
        mItineraryMapPresenter.init(mItinerary);
        initMap();
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void loadOnMap(Locale locale) {
        mLocale = locale;
        if (mGoogleMap != null)
            addMarker();
    }

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomIn());
        googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(getContext(), R.raw.map_style));
        googleMap.setOnInfoWindowClickListener(marker -> {
            if (!mIsDisabledClickPlace)
                startActivity(ItineraryActivity.getCallingIntent(getContext(), mItinerary));
        });
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @SuppressLint("InflateParams")
            @Override
            public View getInfoWindow(Marker marker) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.info_window_map, null);
                TextView tvTitle = view.findViewById(R.id.title);
                tvTitle.setText(marker.getTitle());
                TextView tvSnippet = view.findViewById(R.id.snippet);
                tvSnippet.setText(marker.getSnippet());
                return view;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
        googleMap.setOnMarkerClickListener(this);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        if (mLocale != null) {
            addMarker();
        }
        mEventBus.post(new OnMapReady());
    }

    @Override
    public void disableItineraryClick() {
        mIsDisabledClickPlace = true;
    }

    @SuppressLint("InflateParams")
    private void addMarker() {
        LatLng latLng = new LatLng(mLocale.getLatitude(), mLocale.getLongitude());
        mGoogleMap.addMarker(new MarkerOptions().position(latLng)
                .title(mItinerary.getTitle().asString())
                .snippet(mItinerary.getDescription().asString())
                .icon(MapUtils.getBitmapDescriptor(getContext(), R.drawable.ic_place)));
        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(10).bearing(0).tilt(0).build();
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        final long start = SystemClock.uptimeMillis();
        final long duration = 1500L;
        mHandler.removeCallbacks(mAnimation);
        mAnimation = new BounceAnimation(start, duration, marker, mHandler);
        mHandler.post(mAnimation);
        return false;
    }

    private void setItinerary(Itinerary itinerary) {
        mItinerary = itinerary;
    }

    private static class BounceAnimation implements Runnable {

        private final long mStart, mDuration;
        private final Interpolator mInterpolator;
        private final Marker mMarker;
        private final Handler mHandler;

        BounceAnimation(long start, long duration, Marker marker, Handler handler) {
            mStart = start;
            mDuration = duration;
            mMarker = marker;
            mHandler = handler;
            mInterpolator = new BounceInterpolator();
        }

        @Override
        public void run() {
            long elapsed = SystemClock.uptimeMillis() - mStart;
            float t = Math.max(1 - mInterpolator.getInterpolation((float) elapsed / mDuration), 0f);
            mMarker.setAnchor(0.5f, 1.0f + 1.2f * t);

            if (t > 0.0) {
                mHandler.postDelayed(this, 16L);
            }
        }
    }
}
