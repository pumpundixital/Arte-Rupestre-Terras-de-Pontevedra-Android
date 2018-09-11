package org.terrasdepontevedra.petra.ui.walk.single;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.walk.Itinerary;
import org.terrasdepontevedra.petra.domain.model.walk.Place;
import org.terrasdepontevedra.petra.ui.walk.base.SimpleFragment;
import org.terrasdepontevedra.petra.ui.walk.list.ItineraryListFragment;
import org.terrasdepontevedra.petra.ui.walk.views.BottomBar;
import org.terrasdepontevedra.petra.ui.walk.views.BottomBarTab;
import org.terrasdepontevedra.petra.util.Constants;

import java.util.List;

import butterknife.ButterKnife;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by pablopumpun on 22/9/17.
 */

public class ItineraryTabFragment extends SimpleFragment implements ItineraryContract.View {

    public static final int FIRST = 0;
    public static final int SECOND = 1;

    private SupportFragment[] mFragments = new SupportFragment[3];

    private BottomBar mBottomBar;
    private String mItinerarySlug;
    private int mItineraryId;

    private Itinerary mItinerary;
    private List<Place> mPlacesList;

    ItineraryPresenter mPresenter;

    public static ItineraryTabFragment newInstance(int id) {
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_ITINERARY_ID,id);
        ItineraryTabFragment fragment = new ItineraryTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    public static ItineraryTabFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString(Constants.ARG_ITINERARY_SLUG,url);
        ItineraryTabFragment fragment = new ItineraryTabFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_walk_tab, container, false);
        ButterKnife.bind(this,view);
        initView(view);
        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_walk_tab;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ItineraryPresenter();
        mPresenter.attachView(this);
        mItinerarySlug = getArguments().getString(Constants.ARG_ITINERARY_SLUG);
        mItineraryId = getArguments().getInt(Constants.ARG_ITINERARY_ID);
    }

    @Override
    protected void initEventAndData() {
        if(mItinerarySlug!=null) {
            mPresenter.getData(mItinerarySlug);
        }
        else if(mItineraryId!=-1){
            mPresenter.getData(mItineraryId);
        }
    }


    private void initView(View view) {
        mBottomBar = view.findViewById(R.id.bottomBar);
        mBottomBar
                .addItem(new BottomBarTab(_mActivity,"tab 1"))
                .addItem(new BottomBarTab(_mActivity, "tab 2"));
        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                showHideFragment(mFragments[position], mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {
//                EventBus.getDefault().post(new TabSelectedEvent(position));
            }
        });
    }

    @Override
    public void stateError() {

    }

    @Override
    public void stateEmpty() {

    }

    @Override
    public void stateLoading() {

    }

    public void stateMain() {
        SupportFragment firstFragment = findChildFragment(ItineraryDetailFragment.class);
        if (firstFragment == null) {
            mFragments[FIRST] = ItineraryDetailFragment.newInstance(mItinerary,mPlacesList);
            mFragments[SECOND] = ItineraryListFragment.newInstance(mPlacesList);

            loadMultipleRootFragment(R.id.fl_tab_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND]);
        } else {
            mFragments[FIRST] = firstFragment;
            mFragments[SECOND] = findChildFragment(ItineraryListFragment.class);
        }
    }

    @Override
    public void stateErrorNetWork() {

    }


    public void onDataLoaded(Itinerary currentItinerary, List<Place> placesList) {
        mItinerary = currentItinerary;
        mPlacesList = placesList;
        stateMain();
    }

    @Override
    public void showContent(Itinerary itinerary) {

    }
}
