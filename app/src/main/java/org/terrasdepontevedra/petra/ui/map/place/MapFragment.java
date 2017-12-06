package org.terrasdepontevedra.petra.ui.map.place;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.domain.pojo.OpenPlace;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.ui.itinerary.ItineraryActivity;
import org.terrasdepontevedra.petra.util.MapUtils;
import org.terrasdepontevedra.petra.util.services.FragmentService;

import javax.inject.Inject;


public class MapFragment extends BaseFragment {

    private static final String ARG_PLACE_COLLECTION = "ARG_PLACE_COLLECTION";
    private static final String ARG_SHOW_ALL_PLACE = "ARG_SHOW_ALL_PLACE";

    @Inject
    FragmentService mFragmentService;
    @Inject
    EventBus mEventBus;

    private PlaceCollection mPlaceCollection;
    private boolean mShowAllPlace;

    public MapFragment() {
    }

    public static MapFragment newInstance(PlaceCollection placeCollection, boolean showAllPlaces) {
        MapFragment mapFragment = new MapFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_PLACE_COLLECTION, placeCollection);
        bundle.putBoolean(ARG_SHOW_ALL_PLACE, showAllPlaces);
        mapFragment.setArguments(bundle);
        return mapFragment;
    }

    public static MapFragment newInstance(Place place, boolean showAllPlaces) {
        PlaceCollection placeCollection = new PlaceCollection();
        placeCollection.add(place);
        return newInstance(placeCollection, showAllPlaces);
    }

    public static MapFragment newInstance() {
        return new MapFragment();
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_map;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mPlaceCollection = getArguments().getParcelable(ARG_PLACE_COLLECTION);
            mShowAllPlace = getArguments().getBoolean(ARG_SHOW_ALL_PLACE);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initMap();
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


    @Subscribe
    public void onEvent(OpenPlace openPlace) {
        startActivity(ItineraryActivity.getCallingIntent(getActivity(), openPlace.getCurrentItinerary(), openPlace.getPlacePosition()));
    }


    private void initMap() {
        MapUtils.canUseMapBox(getContext(), this::initMapDepends);
    }

    private void initMapDepends(boolean canUseMapBox) {
        if (canUseMapBox)
            initMapBoxFragment();
        else
            initGoogleMapFragment();
    }

    private void initGoogleMapFragment() {
        initMap(MapGoogleFragment.newInstance(mPlaceCollection, mShowAllPlace));
    }

    private void initMapBoxFragment() {
        initMap(MapBoxFragment.newInstance(mPlaceCollection));
    }

    private void initMap(Fragment fragment) {
        mFragmentService.add(fragment, R.id.container_map);
    }
}
