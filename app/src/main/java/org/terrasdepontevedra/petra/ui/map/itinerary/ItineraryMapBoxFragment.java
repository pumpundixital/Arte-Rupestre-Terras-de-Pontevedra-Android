package org.terrasdepontevedra.petra.ui.map.itinerary;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.model.base.Locale;
import org.terrasdepontevedra.petra.domain.pojo.OnMapReady;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.ui.itinerary.ItineraryActivity;
import org.terrasdepontevedra.petra.util.MapUtils;

import javax.inject.Inject;


public class ItineraryMapBoxFragment extends BaseFragment implements OnMapReadyCallback, ItineraryMapMvpView {

    @Inject
    ItineraryMapPresenter mItineraryMapPresenter;
    @Inject
    EventBus mEventBus;

    private MapboxMap mMapboxMap;
    private boolean mIsDisabledClickPlace;
    private Itinerary mItinerary;
    private Locale mLocale;

    public static ItineraryMapBoxFragment newInstance(Itinerary itinerary) {
        ItineraryMapBoxFragment itineraryMapBoxFragment = new ItineraryMapBoxFragment();
        itineraryMapBoxFragment.setItinerary(itinerary);
        return itineraryMapBoxFragment;
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
        mItineraryMapPresenter.attachView(this);
        mItineraryMapPresenter.init(mItinerary);
        initMap();
    }

    private void initMap() {
        MapboxMapOptions mapboxMapOptions = new MapboxMapOptions()
                .styleUrl(Style.OUTDOORS)
                .camera(new CameraPosition.Builder()
                        .target(new LatLng(42.4338555, -8.6743651))
                        .zoom(8)
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
    public void onDestroyView() {
        super.onDestroyView();
        mItineraryMapPresenter.detachView();
    }


    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        mMapboxMap = mapboxMap;
        mMapboxMap.animateCamera(CameraUpdateFactory.zoomIn());

        mapboxMap.setOnCameraIdleListener(() -> {
        });
        mapboxMap.setOnInfoWindowClickListener(marker -> {
            if (!mIsDisabledClickPlace)
                startActivity(ItineraryActivity.getCallingIntent(getContext(), mItinerary));
            return false;
        });
        mapboxMap.setInfoWindowAdapter(marker -> {
            @SuppressLint("InflateParams") View view = LayoutInflater.from(getContext()).inflate(R.layout.info_window_map, null);
            TextView tvTitle = view.findViewById(R.id.title);
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = view.findViewById(R.id.snippet);
            tvSnippet.setText(marker.getSnippet());
            return view;
        });
        mapboxMap.setOnMarkerClickListener(marker -> false);
        mapboxMap.getUiSettings().setZoomControlsEnabled(true);
        mEventBus.post(new OnMapReady());
        if (mLocale != null) {
            addMarker();
        }
    }

    private void addMarker() {
        mMapboxMap.addMarker(new MarkerOptions()
                .position(new LatLng(mLocale.getLatitude(), mLocale.getLongitude()))
                .title(mItinerary.getTitle().asString())
                .snippet(mItinerary.getDescription().asString())
                .icon(IconFactory.getInstance(getContext()).fromBitmap(MapUtils.drawableToBitmap(getContext(), R.drawable.ic_place))));
    }

    @Override
    public void loadOnMap(Locale locale) {
        mLocale = locale;
        if (mMapboxMap != null)
            addMarker();
    }

    @Override
    public void disableItineraryClick() {
        mIsDisabledClickPlace = true;
    }


    private void setItinerary(Itinerary itinerary) {
        mItinerary = itinerary;
    }
}
