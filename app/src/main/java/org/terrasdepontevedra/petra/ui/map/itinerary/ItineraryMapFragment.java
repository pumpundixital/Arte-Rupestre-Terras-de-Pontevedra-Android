package org.terrasdepontevedra.petra.ui.map.itinerary;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.pojo.DeactivateFilter;
import org.terrasdepontevedra.petra.domain.pojo.OpenPlace;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.ui.itinerary.ItineraryActivity;
import org.terrasdepontevedra.petra.util.MapUtils;
import org.terrasdepontevedra.petra.util.services.FragmentService;

import javax.inject.Inject;


public class ItineraryMapFragment extends BaseFragment {

    @Inject
    FragmentService mFragmentService;
    @Inject
    EventBus mEventBus;


    private Itinerary mItinerary;
    private boolean mIsDisabledClickPlace;


    public ItineraryMapFragment() {
    }

    public static ItineraryMapFragment newInstance(Itinerary itinerary) {
        ItineraryMapFragment itineraryMapFragment = new ItineraryMapFragment();
        itineraryMapFragment.setItinerary(itinerary);
        return itineraryMapFragment;
    }


    private void setItinerary(Itinerary itinerary) {
        mItinerary = itinerary;
        mItinerary.setChecked(true);
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
        startActivity(ItineraryActivity.getCallingIntent(getActivity(), mItinerary));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mEventBus.post(new DeactivateFilter());
    }

    public void disableItineraryClick() {
        mIsDisabledClickPlace = true;
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
        ItineraryMapGoogleFragment fragment = ItineraryMapGoogleFragment.newInstance(mItinerary);
        if (mIsDisabledClickPlace)
            fragment.disableItineraryClick();
        initMap(fragment);
    }

    private void initMapBoxFragment() {
        ItineraryMapBoxFragment fragment = ItineraryMapBoxFragment.newInstance(mItinerary);
        if (mIsDisabledClickPlace)
            fragment.disableItineraryClick();
        initMap(fragment);
    }

    private void initMap(Fragment fragment) {
        mFragmentService.add(fragment, R.id.container_map);
    }
}
