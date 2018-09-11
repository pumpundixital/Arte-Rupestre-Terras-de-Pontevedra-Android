package org.terrasdepontevedra.petra.ui.walk.walks;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.walk.Itinerary;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class WalksListFragment extends Fragment implements WalksListMvpView {


    private Unbinder unbinder;

    @BindView(R.id.loading)
    View mLoading;

    @BindView(R.id.recycler)
    RecyclerView mRecycler;

    WalksListPresenter walksListPresenter;
    WalksAdapter walksAdapter;

    public static WalksListFragment newInstance() {
        return new WalksListFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_walks_list, container, false);
        unbinder = ButterKnife.bind(this,v);
        walksListPresenter = new WalksListPresenter();
        walksListPresenter.attachView(this);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showLoading(true);
        walksListPresenter.loadWalks();


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void showLoading(boolean loading) {
        mLoading.setVisibility(loading?View.VISIBLE:View.GONE);
    }

    @Override
    public void onWalksLoaded(List<Itinerary> itineraryList) {
        showLoading(false);
        walksAdapter = new WalksAdapter(itineraryList);
        mRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecycler.setAdapter(walksAdapter);
    }
}
