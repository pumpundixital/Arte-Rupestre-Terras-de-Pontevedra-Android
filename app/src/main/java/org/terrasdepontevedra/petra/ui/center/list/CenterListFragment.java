package org.terrasdepontevedra.petra.ui.center.list;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Center;
import org.terrasdepontevedra.petra.domain.model.CenterCollection;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.ui.center.detail.CenterDetailActivity;
import org.terrasdepontevedra.petra.util.ActivityUtils;

import javax.inject.Inject;

import butterknife.BindView;

public class CenterListFragment extends BaseFragment implements CenterListMvpView, OnCenterClick {

    @Inject
    CenterListPresenter mCenterListPresenter;
    @Inject
    CenterCollectionAdapter mCenterAdapter;

    @BindView(R.id.rc_centers)
    RecyclerView mRecyclerView;

    public CenterListFragment() {
        // Required empty public constructor
    }

    public static CenterListFragment newInstance() {
        return new CenterListFragment();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mCenterAdapter.setListener(this);
        mRecyclerView.setAdapter(mCenterAdapter);

        mCenterListPresenter.attachView(this);
        mCenterListPresenter.init();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_center_list;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void loadCenterCollectionOnView(CenterCollection centerCollection) {
        mCenterAdapter.setCenterCollection(centerCollection);
    }

    @Override
    public void onCenterClicked(Center center, View transitionView) {
        ActivityUtils.openActivityWith(
                getActivity(), CenterDetailActivity.getCallingIntent(getContext(), center),
                transitionView, getString(R.string.transition_item_itinerary)
        );
    }
}
