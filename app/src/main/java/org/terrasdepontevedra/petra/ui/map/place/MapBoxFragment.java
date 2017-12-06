package org.terrasdepontevedra.petra.ui.map.place;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.SupportMapFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.domain.model.base.Locale;
import org.terrasdepontevedra.petra.domain.pojo.FilterMap;
import org.terrasdepontevedra.petra.domain.pojo.HideSeeMoreOption;
import org.terrasdepontevedra.petra.domain.pojo.OnMapReady;
import org.terrasdepontevedra.petra.domain.pojo.OnPlaceChangePage;
import org.terrasdepontevedra.petra.domain.pojo.OpenViewPager;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.util.MapUtils;

import javax.inject.Inject;


public class MapBoxFragment extends BaseFragment implements OnMapReadyCallback, MapMvpView {

    @Inject
    MapPresenter mMapPresenter;
    @Inject
    EventBus mEventBus;
    private PlaceCollection mPlaceCollection;
    private MapboxMap mMapboxMap;
    private final LongSparseArray<Place> mPlaceLongSparseArray = new LongSparseArray<>();
    private boolean mIsDisabledClickPlace;
    private boolean mHideSeeMoreButton = false;

    public static MapBoxFragment newInstance(PlaceCollection placeCollection) {
        MapBoxFragment mapBoxFragment = new MapBoxFragment();
        mapBoxFragment.setPlaceCollection(placeCollection);
        return mapBoxFragment;
    }

    private void setPlaceCollection(PlaceCollection placeCollection) {
        mPlaceCollection = placeCollection;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_map_box;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMapPresenter.attachView(this);
        mMapPresenter.init(mPlaceCollection);
        initMap();
    }

    private void initMap() {
        MapboxMapOptions mapboxMapOptions = new MapboxMapOptions()
                .styleUrl(Style.OUTDOORS)
                .camera(new CameraPosition.Builder()
                        .target(new LatLng(42.4338555, -8.6743651))
                        .zoom(11)
                        .bearing(0)
                        .tilt(0)
                        .build());


        SupportMapFragment mapFragment = SupportMapFragment.newInstance(mapboxMapOptions);

        // Add map fragment to parent container
        getChildFragmentManager()
                .beginTransaction()
                .add(R.id.container, mapFragment, "com.mapbox.map")
                .commit();
        mapFragment.getMapAsync(this);
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

    @Subscribe
    public void onEvent(FilterMap filterMap) {
        mMapPresenter.filterMapBy(filterMap.getItinerary());
    }

    @Subscribe
    public void onEvent(@SuppressWarnings("unused") HideSeeMoreOption hideSeeMoreOption) {
        mHideSeeMoreButton = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapPresenter.onResume();
    }

    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        mMapboxMap = mapboxMap;
        mMapboxMap.animateCamera(CameraUpdateFactory.zoomIn());

        mapboxMap.setOnCameraIdleListener(() -> {
        });
        mapboxMap.setOnMarkerClickListener(marker -> false);
        mapboxMap.setOnInfoWindowClickListener(marker -> {
            if (mIsDisabledClickPlace)
                mEventBus.post(new OpenViewPager(mPlaceLongSparseArray.get(marker.getId())));
            else
                mMapPresenter.tryOpenPlace(mPlaceLongSparseArray.get(marker.getId()));
            return false;
        });
        mapboxMap.setInfoWindowAdapter(marker -> {
            @SuppressLint("InflateParams") View view = LayoutInflater.from(getContext()).inflate(R.layout.info_window_map, null);
            TextView tvTitle = view.findViewById(R.id.title);
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = view.findViewById(R.id.snippet);
            tvSnippet.setText(marker.getSnippet());
            TextView tvSeeMore = view.findViewById(R.id.tv_see_more);
            tvSeeMore.setVisibility(mHideSeeMoreButton ? View.INVISIBLE : View.VISIBLE);
            return view;
        });
        mapboxMap.getUiSettings().setZoomControlsEnabled(true);
        mEventBus.post(new OnMapReady());
        if (mPlaceCollection != null)
            for (Place place : mPlaceCollection.asList()) {
                addMarket(place);
                mPlaceLongSparseArray.put(mMapboxMap.getMarkers().get(mMapboxMap.getMarkers().size() - 1).getId(), place);
            }
    }

    private void addMarket(Place place) {
        Locale locale = place.getLocale();
        mMapboxMap.addMarker(new MarkerOptions()
                .position(new LatLng(locale.getLatitude(), locale.getLongitude()))
                .title(place.getTitle())
                .snippet(place.getSnippet())
                .icon(IconFactory.getInstance(getContext()).fromBitmap(MapUtils.drawableToBitmap(getContext(), R.drawable.ic_place))));
    }

    @Override
    public void loadPlaceOnMap(PlaceCollection placeCollection) {
        mPlaceCollection = placeCollection;
        if (mMapboxMap != null)
            for (Place place : placeCollection.asList()) {
                addMarket(place);
            }
    }

    @Override
    public void disablePlaceClick() {
        mIsDisabledClickPlace = true;
    }

    @Subscribe
    public void onEvent(OnPlaceChangePage onPlaceChangePage) {
        Locale locale = onPlaceChangePage.getPlace().getLocale();
        mMapboxMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(locale.getLatitude(), locale.getLongitude()), 14));
    }

}
