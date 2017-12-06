package org.terrasdepontevedra.petra.ui.itinerary.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.model.ItineraryCollection;
import org.terrasdepontevedra.petra.domain.pojo.OpenMap;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.util.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;

import static org.terrasdepontevedra.petra.ui.itinerary.ItineraryActivity.getCallingIntent;


public class ItinerariesListFragment
        extends BaseFragment
        implements ItinerariesListMvpView, OnItinerariesListListener {

    @Inject
    ItinerariesAdapter mAdapter;
    @Inject
    ItinerariesListPresenter mItinerariesListPresenter;
    @Inject
    EventBus mEventBus;

    @BindView(R.id.recycler_itinerary)
    RecyclerView mRcItinerary;

    public ItinerariesListFragment() {
    }

    public static ItinerariesListFragment newInstance() {
        return new ItinerariesListFragment();
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRcItinerary.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter.setListener(this);
        mRcItinerary.setAdapter(mAdapter);

        mItinerariesListPresenter.attachView(this);
        mItinerariesListPresenter.init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mItinerariesListPresenter.detachView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_itineraries_list;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void loadItineraryCollectionOnView(ItineraryCollection itineraryCollection) {
        mAdapter.setCollection(itineraryCollection);
    }

    @Override
    public void onClickItinerary(Itinerary itinerary, View transitionView) {
        ActivityUtils.openActivityWith(
                getActivity(), getCallingIntent(getActivity(), itinerary),
                transitionView, getString(R.string.transition_item_itinerary)
        );
    }

    @Override
    public void onClickSeeMap(Itinerary itinerary) {
        mEventBus.post(new OpenMap(itinerary));
    }
}
