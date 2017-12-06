package org.terrasdepontevedra.petra.ui.moreInfo;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.terrasdepontevedra.petra.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MoreInfoFragment extends Fragment {

    public MoreInfoFragment() {
        // Required empty public constructor
    }

    public static MoreInfoFragment newInstance() {
        return new MoreInfoFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_more_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.btn_accommodations)
    void onClickAccommodations() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_accommodations))));
    }

    @OnClick(R.id.btn_what_to_see)
    void onClickWhatToSee() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_what_to_see))));
    }

    @OnClick(R.id.btn_food)
    void onClickFood() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_food))));
    }

    @OnClick(R.id.btn_how_to_travel)
    void onClickHowToTravel() {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.url_how_to_travel))));
    }
}
