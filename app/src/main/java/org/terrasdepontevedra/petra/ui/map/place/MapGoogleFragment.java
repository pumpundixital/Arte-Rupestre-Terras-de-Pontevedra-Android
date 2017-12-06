package org.terrasdepontevedra.petra.ui.map.place;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
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
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.domain.pojo.FilterMap;
import org.terrasdepontevedra.petra.domain.pojo.HideSeeMoreOption;
import org.terrasdepontevedra.petra.domain.pojo.OnMapReady;
import org.terrasdepontevedra.petra.domain.pojo.OnPlaceChangePage;
import org.terrasdepontevedra.petra.domain.pojo.OpenViewPager;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.util.MapUtils;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;


public class MapGoogleFragment
        extends BaseFragment
        implements OnMapReadyCallback, MapMvpView, GoogleMap.OnMarkerClickListener {
    private static final String ARG_PLACE_COLLECTION = "ARG_PLACE_COLLECTION";
    private static final String ARG_SHOW_ALL_PLACE = "ARG_SHOW_ALL_PLACE";

    @Inject
    MapPresenter mMapPresenter;
    @Inject
    EventBus mEventBus;

    private ClusterManager<Place> mClusterManager;

    private PlaceCollection mPlaceCollection;
    private final Map<String, Place> mPlaceHashMap = new HashMap<>();
    private Handler mHandler;
    private Runnable mAnimation;
    private GoogleMap mGoogleMap;
    private boolean mIsDisabledClickPlace = false;
    private boolean mHideSeeMoreButton = false;
    private boolean mShowAllMarkers = false;

    public static MapGoogleFragment newInstance(PlaceCollection placeCollection, boolean showAllPlaces) {
        MapGoogleFragment mapGoogleFragment = new MapGoogleFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_PLACE_COLLECTION, placeCollection);
        bundle.putBoolean(ARG_SHOW_ALL_PLACE, showAllPlaces);
        mapGoogleFragment.setArguments(bundle);
        return mapGoogleFragment;
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_map_google;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHandler = new Handler();

        if (getArguments() != null) {
            mPlaceCollection = getArguments().getParcelable(ARG_PLACE_COLLECTION);
            mShowAllMarkers = getArguments().getBoolean(ARG_SHOW_ALL_PLACE);
        }
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
    public void onDestroyView() {
        super.onDestroyView();
        mMapPresenter.detachView();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapPresenter.attachView(this);
        mMapPresenter.init(mPlaceCollection);
        initMap();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapPresenter.onResume();
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void loadPlaceOnMap(PlaceCollection placeCollection) {
        mPlaceCollection = placeCollection;
        loadClusterManager(placeCollection);
    }

    @Override
    public void disablePlaceClick() {
        mIsDisabledClickPlace = true;
    }

    @Subscribe
    public void onEvent(FilterMap filterMap) {
        mMapPresenter.filterMapBy(filterMap.getItinerary());
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

        mClusterManager = new ClusterManager<>(getContext(), googleMap);
        PlaceMarkerRenderer placeMarkerRenderer = new PlaceMarkerRenderer(getContext(), googleMap, mClusterManager);
        mClusterManager.setRenderer(placeMarkerRenderer);
        googleMap.setOnCameraIdleListener(mClusterManager);
        googleMap.setOnMarkerClickListener(this);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.setOnInfoWindowClickListener(marker -> {
            if (mIsDisabledClickPlace)
                mEventBus.post(new OpenViewPager(mPlaceHashMap.get(marker.getId())));
            else
                mMapPresenter.tryOpenPlace(mPlaceHashMap.get(marker.getId()));
        });
        googleMap.setOnMapLoadedCallback(this::showAllMarkers);
        googleMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @SuppressLint("InflateParams")
            @Override
            public View getInfoWindow(Marker marker) {
                View view = LayoutInflater.from(getContext()).inflate(R.layout.info_window_map, null);
                TextView tvTitle = view.findViewById(R.id.title);
                tvTitle.setText(marker.getTitle());
                TextView tvSnippet = view.findViewById(R.id.snippet);
                tvSnippet.setText(marker.getSnippet());
                TextView tvSeeMore = view.findViewById(R.id.tv_see_more);
                tvSeeMore.setVisibility(mHideSeeMoreButton ? View.INVISIBLE : View.VISIBLE);
                return view;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
        loadClusterManager(mPlaceCollection);
        mEventBus.post(new OnMapReady());
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (TextUtils.isEmpty(marker.getTitle()))
            return true;
        final long start = SystemClock.uptimeMillis();
        final long duration = 1500L;
        mHandler.removeCallbacks(mAnimation);
        mAnimation = new BounceAnimation(start, duration, marker, mHandler);
        mHandler.post(mAnimation);
        return false;
    }

    @Subscribe
    public void onEvent(OnPlaceChangePage onPlaceChangePage) {
        getActivity().runOnUiThread(() -> {
            if (mGoogleMap != null) {
                mGoogleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(onPlaceChangePage.getPlace().getPosition(), 15));
                Marker marker = null;
                for (Map.Entry<String, Place> stringPlaceEntry : mPlaceHashMap.entrySet()) {
                    if (stringPlaceEntry.getValue().equals(onPlaceChangePage.getPlace())) {
                        for (Marker marker1 : mClusterManager.getMarkerCollection().getMarkers()) {
                            if (marker1.getId().equals(stringPlaceEntry.getKey())) {
                                marker = marker1;
                            }
                        }
                        break;
                    }
                }

                final long start = SystemClock.uptimeMillis();
                final long duration = 1500L;
                mHandler.removeCallbacks(mAnimation);
                mAnimation = new BounceAnimation(start, duration, marker, mHandler);
                mHandler.post(mAnimation);
            }
        });
    }

    @Subscribe
    public void onEvent(@SuppressWarnings("unused") HideSeeMoreOption hideSeeMoreOption) {
        mHideSeeMoreButton = true;
    }


    private void loadClusterManager(PlaceCollection placeCollection) {
        if (mClusterManager != null && placeCollection != null) {
            mClusterManager.clearItems();
            mClusterManager.addItems(placeCollection.asList());
            mClusterManager.cluster();
        }
    }

    private void showAllMarkers() {
        if (mIsDisabledClickPlace && !mShowAllMarkers)
            return;
        LatLng center = MapUtils.getCenterFrom(mPlaceCollection).getCenter();
        CameraPosition cameraPosition = new CameraPosition.Builder().target(center).zoom(10).bearing(0).tilt(0).build();
        mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        mShowAllMarkers = false;
    }

    private class PlaceMarkerRenderer extends DefaultClusterRenderer<Place> {


        PlaceMarkerRenderer(Context context, GoogleMap map, ClusterManager<Place> clusterManager) {
            super(context, map, clusterManager);
        }

        @Override
        protected void onBeforeClusterItemRendered(Place place, MarkerOptions markerOptions) {
            markerOptions.icon(MapUtils.getBitmapDescriptor(getContext(), R.drawable.ic_place));
        }

        @Override
        protected void onClusterItemRendered(Place item, Marker marker) {
            mPlaceHashMap.put(marker.getId(), item);
            super.onClusterItemRendered(item, marker);
        }

        @Override
        protected int getColor(int clusterSize) {
            return ContextCompat.getColor(getContext(), R.color.colorPrimaryDark);
        }
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
            if (mMarker == null)
                return;
            long elapsed = SystemClock.uptimeMillis() - mStart;
            float t = Math.max(1 - mInterpolator.getInterpolation((float) elapsed / mDuration), 0f);
            mMarker.setAnchor(0.5f, 1.0f + 1.2f * t);

            if (t > 0.0) {
                mHandler.postDelayed(this, 16L);
            }
        }
    }
}
