package org.terrasdepontevedra.petra.ui.place.content;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.domain.model.base.Locale;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.ui.gallery.horizontal.GalleryHorizontalFragment;
import org.terrasdepontevedra.petra.util.services.FragmentService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;


public class PlaceContentFragment extends BaseFragment implements PlaceContentMvpView {

    @Inject
    PlaceContentPresenter mPlaceContentPresenter;

    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.tv_site)
    TextView mTvSite;
    @BindView(R.id.tv_morphology)
    TextView mTvMorphology;
    @BindView(R.id.tv_access)
    TextView mTvAccess;
    @BindView(R.id.tv_synopsis)
    TextView mTvSynopsis;
    @BindView(R.id.linear_description)
    LinearLayout mLinearDescription;
    @BindView(R.id.linear_site)
    LinearLayout mLinearSite;
    @BindView(R.id.linear_morphology)
    LinearLayout mLinearMorphology;
    @BindView(R.id.linear_access)
    LinearLayout mLinearAccess;
    @BindView(R.id.linear_synopsis)
    LinearLayout mLinearSynopsis;
    @BindView(R.id.linear_gallery)
    LinearLayout mLinearGallery;

    @Inject
    FragmentService mFragmentService;

    private Place mPlace;

    public static PlaceContentFragment newInstance(Place place) {
        PlaceContentFragment placeContentFragment = new PlaceContentFragment();
        placeContentFragment.setPlace(place);
        return placeContentFragment;
    }

    private void setPlace(Place place) {
        mPlace = place;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPlaceContentPresenter.attachView(this);
        mPlaceContentPresenter.init(mPlace);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_content_place;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }


    @Override
    public void loadDescriptionOnView(String descriptionAsString) {
        mTvDescription.setText(Html.fromHtml(descriptionAsString));
    }

    @OnClick(R.id.btn_go_to_place)
    void onClickGoTo() {
        mPlaceContentPresenter.goToPlace();
    }

    @Override
    public void openGoogleMaps(Locale locale) {
        Intent navigation = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + locale.getLatitude() + "," + locale.getLongitude()));
        startActivity(navigation);
    }

    @Override
    public void loadSiteOnView(String site) {
        mTvSite.setText(Html.fromHtml(site));
    }

    @Override
    public void loadMorphologyOnView(String morphology) {
        mTvMorphology.setText(Html.fromHtml(morphology));
    }

    @Override
    public void loadAccessOnView(String access) {
        mTvAccess.setText(Html.fromHtml(access));
    }

    @Override
    public void loadSynopsisOnView(String synopsis) {
        mTvSynopsis.setText(Html.fromHtml(synopsis));
    }

    @Override
    public void loadGalleryOnView(List<String> imagesUrls) {
        mFragmentService.add(GalleryHorizontalFragment.newInstance(new ArrayList<>(imagesUrls)), R.id.container_horizontal_gallery);
    }

    @Override
    public void hideDescriptionView() {
        mLinearDescription.setVisibility(View.GONE);
    }

    @Override
    public void hideSiteView() {
        mLinearSite.setVisibility(View.GONE);
    }

    @Override
    public void hideMorphologyView() {
        mLinearMorphology.setVisibility(View.GONE);
    }

    @Override
    public void hideAccessView() {
        mLinearAccess.setVisibility(View.GONE);
    }

    @Override
    public void hideSynopsisView() {
        mLinearSynopsis.setVisibility(View.GONE);
    }

    @Override
    public void hideGalleryView() {
        mLinearGallery.setVisibility(View.GONE);
    }
}
