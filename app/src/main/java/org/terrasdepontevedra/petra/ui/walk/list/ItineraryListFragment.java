package org.terrasdepontevedra.petra.ui.walk.list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.walk.Place;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

import static org.terrasdepontevedra.petra.util.Constants.ARG_PLACES_LIST;


public class ItineraryListFragment extends SupportFragment {

    @BindView(R.id.recycler_walk_list)
    RecyclerView mRecycler;

    private ItinerariesAdapter mAdapter;
    private List<Place> mPlaces;


    public ItineraryListFragment() {
    }

    public static ItineraryListFragment newInstance(List<Place> places) {
        ItineraryListFragment fragment = new ItineraryListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_PLACES_LIST, new ArrayList<>(places));
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPlaces = getArguments().getParcelableArrayList(ARG_PLACES_LIST);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_walk_list, container, false);
        ButterKnife.bind(this, view);
        mRecycler.setLayoutManager(new GridLayoutManager(view.getContext(), 1));
        mAdapter = new ItinerariesAdapter(this);
        mRecycler.setAdapter(mAdapter);
        setContent(mPlaces);
        return view;
    }


    private void setContent(List<Place> placeList) {
        mAdapter.setItems(placeList);
    }

    public void onClickPlace(String url) {
        //startActivity(PlaceActivity.getCallingIntent(getContext(), url));
    }

    /*public LatLng getLatLng() {
        SharedPreferences mPrefs = App.getInstance().getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        double lat = Double.longBitsToDouble(mPrefs.getLong(PREF_KEY_LAST_LOCATION_LAT, Double.doubleToLongBits(-1)));
        double lng = Double.longBitsToDouble(mPrefs.getLong(PREF_KEY_LAST_LOCATION_LNG, Double.doubleToLongBits(-1)));
        return new LatLng(lat,lng);
    }*/


}
