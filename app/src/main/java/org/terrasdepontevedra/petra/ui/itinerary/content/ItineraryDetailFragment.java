package org.terrasdepontevedra.petra.ui.itinerary.content;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.Html;
import android.view.View;
import android.view.ViewStub;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.model.base.Locale;
import org.terrasdepontevedra.petra.domain.pojo.FragmentCreated;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.ui.gallery.horizontal.GalleryHorizontalFragment;
import org.terrasdepontevedra.petra.ui.map.itinerary.ItineraryMapFragment;
import org.terrasdepontevedra.petra.ui.views.audioPlayer.AudioPlayerView;
import org.terrasdepontevedra.petra.util.services.FragmentService;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ItineraryDetailFragment extends BaseFragment implements ItineraryDetailMvpView {

    @Inject
    ItineraryDetailPresenter mItineraryDetailPresenter;
    @Inject
    EventBus mEventBus;
    @Inject
    FragmentService mFragmentService;

    @BindView(R.id.scroll_itinerary_detail)
    View mView;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.tv_locale)
    TextView mTvLocale;
    @BindView(R.id.tv_schedule)
    TextView mTvSchedule;
    @BindView(R.id.tv_email)
    TextView mTvEmail;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_web)
    TextView mTvWeb;
    @BindView(R.id.stub_audioplayer)
    ViewStub mStubPlayer;
    @BindView(R.id.linear_email)
    LinearLayout mLinearEmail;
    @BindView(R.id.linear_phone)
    LinearLayout mLinearPhone;
    @BindView(R.id.linear_schedule)
    LinearLayout mLinearSchedule;
    @BindView(R.id.linear_web)
    LinearLayout mLinearWeb;
    @BindView(R.id.linear_gallery)
    LinearLayout mLinearGallery;
    @BindView(R.id.linear_audio)
    LinearLayout mLinearAudio;
    @BindView(R.id.linear_description)
    LinearLayout mLinearDescription;

    private AudioPlayerView mAudioPlayer;
    private View mRootView;
    private Itinerary mItinerary;

    public ItineraryDetailFragment() {
    }

    public static ItineraryDetailFragment newInstance(Itinerary itinerary) {
        ItineraryDetailFragment itineraryDetailFragment = new ItineraryDetailFragment();
        itineraryDetailFragment.setItinerary(itinerary);
        return itineraryDetailFragment;
    }

    private void setItinerary(Itinerary itinerary) {
        mItinerary = itinerary;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRootView = view;
        startAnimation();
        mEventBus.post(new FragmentCreated());
        mItineraryDetailPresenter.attachView(this);
        mItineraryDetailPresenter.init(mItinerary);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mItineraryDetailPresenter.detachView();
        if (mAudioPlayer != null) {
            mAudioPlayer.destroy();
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_itinerary_detail;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }


    private void startAnimation() {
        View view = getView();
        if (view != null) {
            final float positionY = view.getY();
            view.setY(view.getMeasuredHeight());

            mRootView.post(() -> {
                int height = view.getMeasuredHeight();
                view.setY(height);
                ViewCompat.animate(view)
                        .y(positionY)
                        .setStartDelay(300)
                        .setDuration(500)
                        .start();
            });
        }
    }


    @Override
    public void loadTitleOnView(String titleAsString) {
        mTvTitle.setText(Html.fromHtml(titleAsString));
    }

    @Override
    public void loadDescriptionOnView(String descriptionAsString) {
        mTvDescription.setText(Html.fromHtml(descriptionAsString));
    }

    @Override
    public void loadLocaleOnView(String localeAsString) {
        mTvLocale.setText(localeAsString);
    }

    @Override
    public void loadAudioPlayer(String audioUrl) {
        View layoutAudioPlayer = mStubPlayer.inflate();
        mAudioPlayer = layoutAudioPlayer.findViewById(R.id.audio_player_view);
        if (mAudioPlayer != null) {
            mAudioPlayer.init(audioUrl);
        }
    }

    @Override
    public void loadPhoneOnView(String phone) {
        mTvPhone.setText(Html.fromHtml(phone));
    }

    @Override
    public void loadEmailOnView(String email) {
        mTvEmail.setText(Html.fromHtml(email));
    }

    @Override
    public void loadScheduleOnView(String schedule) {
        mTvSchedule.setText(Html.fromHtml(schedule));
    }

    @Override
    public void loadWebOnView(String web) {
        mTvWeb.setText(Html.fromHtml(web));
    }

    @Override
    public void hidePhoneView() {
        mLinearPhone.setVisibility(View.GONE);
    }

    @Override
    public void hideEmailView() {
        mLinearEmail.setVisibility(View.GONE);
    }

    @Override
    public void hideScheduleView() {
        mLinearSchedule.setVisibility(View.GONE);
    }

    @Override
    public void hideWebView() {
        mLinearWeb.setVisibility(View.GONE);
    }

    @Override
    public void showGalleryOnView(ArrayList<String> strings) {
        mFragmentService.add(GalleryHorizontalFragment.newInstance(strings), R.id.fragment_gallery);
    }

    @Override
    public void hideGalleryView() {
        mLinearGallery.setVisibility(View.GONE);
    }

    @Override
    public void hideAudioView() {
        mLinearAudio.setVisibility(View.GONE);
    }

    @Override
    public void hideDescriptionView() {
        mLinearDescription.setVisibility(View.GONE);
    }

    @Override
    public void showMapOnView() {
        ItineraryMapFragment fragment = ItineraryMapFragment.newInstance(mItinerary);
        fragment.disableItineraryClick();
        mFragmentService.replace(fragment, R.id.fragment_map);
    }

    @Override
    public void openGoogleMaps(Locale locale) {
        Intent navigation = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + locale.getLatitude() + "," + locale.getLongitude()));
        startActivity(navigation);
    }

    @OnClick(R.id.btn_go_to_place)
    void onClickGoTo() {
        mItineraryDetailPresenter.goToPlace();
    }

    @OnClick(R.id.linear_web)
    void onClickWeb() {
        mItineraryDetailPresenter.onWebItemClick();
    }

    @OnClick(R.id.linear_phone)
    void onClickPhone() {
        mItineraryDetailPresenter.onPhoneItemClick();
    }

    @OnClick(R.id.linear_email)
    void onClickEmail() {
        mItineraryDetailPresenter.onEmailItemClick();
    }

    @Override
    public void openNavigatorWith(String web) {
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(web)));
    }

    @Override
    public void openPhoneWith(String phone) {
        startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone)));
    }

    @Override
    public void openSendMailWith(String email) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        startActivity(Intent.createChooser(intent, getString(R.string.send_mail)));
    }

}
