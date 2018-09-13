package org.terrasdepontevedra.petra.ui.center.detail;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.text.Html;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.data.local.PreferencesCollector;
import org.terrasdepontevedra.petra.domain.model.Center;
import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.domain.model.base.Link;
import org.terrasdepontevedra.petra.domain.model.base.Locale;
import org.terrasdepontevedra.petra.domain.model.base.Title;
import org.terrasdepontevedra.petra.domain.pojo.HideSeeMoreOption;
import org.terrasdepontevedra.petra.domain.pojo.OnMapReady;
import org.terrasdepontevedra.petra.domain.pojo.PetraHelpClose;
import org.terrasdepontevedra.petra.ui.base.BaseActivity;
import org.terrasdepontevedra.petra.ui.gallery.GalleryFragment;
import org.terrasdepontevedra.petra.ui.map.place.MapFragment;
import org.terrasdepontevedra.petra.ui.views.audioPlayer.AudioPlayerView;
import org.terrasdepontevedra.petra.util.IntroConstants;
import org.terrasdepontevedra.petra.util.services.FragmentService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class CenterDetailActivity extends BaseActivity implements CenterDetailMvpView {

    private static final String ARG_CENTER = "ARG_CENTER";
    @Inject
    CenterDetailPresenter mCenterDetailPresenter;
    @Inject
    FragmentService mFragmentService;
    @Inject
    PreferencesCollector mPreferencesCollector;
    @Inject
    EventBus mEventBus;

    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout mAppBarLayout;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.tv_schedule)
    TextView mTvSchedule;
    @BindView(R.id.tv_email)
    TextView mTvEmail;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;
    @BindView(R.id.tv_web)
    TextView mTvWeb;
    @BindView(R.id.tv_locale)
    TextView mTvLocale;
    @BindView(R.id.menu_horizontal)
    AppCompatImageButton mBtnMenuHorizontal;
    @BindView(R.id.linear_description)
    LinearLayout mLinearDescription;
    @BindView(R.id.linear_schedule)
    LinearLayout mLinearSchedule;
    @BindView(R.id.linear_email)
    LinearLayout mLinearEmail;
    @BindView(R.id.linear_phone)
    LinearLayout mLinearPhone;
    @BindView(R.id.linear_web)
    LinearLayout mLinearWeb;
    @BindView(R.id.linear_locale)
    LinearLayout mLinearLocale;
    @BindView(R.id.linear_audio)
    LinearLayout mLinearAudio;
    @BindView(R.id.stub_audioplayer)
    ViewStub mStubPlayer;

    private AudioPlayerView mAudioPlayer;

    public static Intent getCallingIntent(Context context, Center center) {
        Intent intent = new Intent(context, CenterDetailActivity.class);
        intent.putExtra(ARG_CENTER, center);
        return intent;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_center_detail;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Center center = getIntent().getParcelableExtra(ARG_CENTER);

        mCenterDetailPresenter.attachView(this);
        mCenterDetailPresenter.init(center);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        CenterDetailActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);

    }



    @Override
    public void onStart() {
        super.onStart();
        if (!mEventBus.isRegistered(this))
            mEventBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mEventBus.isRegistered(this))
            mEventBus.unregister(this);
    }

    @Override
    public void showTitle(String title) {
        mCollapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white));
        mCollapsingToolbarLayout.setCollapsedTitleGravity(Gravity.CENTER);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    mCollapsingToolbarLayout.setTitle(title);
                    isShow = true;
                } else if (isShow) {
                    mCollapsingToolbarLayout.setTitle(" ");
                    isShow = false;
                }
            }
        });

        if (mPreferencesCollector.isPetraActive())
            new MaterialIntroView.Builder(this)
                    .enableDotAnimation(true)
                    .setFocusGravity(FocusGravity.CENTER)
                    .setFocusType(Focus.MINIMUM)
                    .setDelayMillis(200)
                    .enableFadeAnimation(true)
                    .performClick(true)
                    .dismissOnTouch(true)
                    .setListener(materialIntroViewId -> mEventBus.post(new PetraHelpClose()))
                    .setInfoText(getString(R.string.petra_message_menu_horizontal))
                    .setTarget(mBtnMenuHorizontal)
                    .setShape(ShapeType.CIRCLE)
                    .setUsageId(IntroConstants.MENU_HORIZONTAL) //THIS SHOULD BE UNIQUE ID
                    .show();
    }

    @Override
    public void showDescription(String description) {
        mTvDescription.setText(Html.fromHtml(description));
    }

    @Override
    public void showSchedule(String schedule) {
        mTvSchedule.setText(Html.fromHtml(schedule));
    }

    @Override
    public void showEmail(String email) {
        mTvEmail.setText(Html.fromHtml(email));
    }

    @Override
    public void showPhone(String phone) {
        mTvPhone.setText(Html.fromHtml(phone));
    }

    @Override
    public void showLocale(String locale) {
        mTvLocale.setText(Html.fromHtml(locale));
    }

    @Override
    public void showMap(Place place) {
        mFragmentService.replace(MapFragment.newInstance(place, true), R.id.fragment_map);
    }

    @Override
    public void showGallery(List<String> imagesUrl) {
        mFragmentService.add(GalleryFragment.newInstance(new ArrayList<>(imagesUrl),false), R.id.layout_gallery);
    }

    @Override
    public void openGoogleMaps(Locale locale) {
        Intent navigation = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?daddr=" + locale.getLatitude() + "," + locale.getLongitude()));
        startActivity(navigation);
    }

    @Override
    public void showWebOnView(String web) {
        mTvWeb.setText(web);
    }

    @Override
    public void hideDescriptionView() {
        mLinearDescription.setVisibility(View.GONE);
    }

    @Override
    public void hideScheduleView() {
        mLinearSchedule.setVisibility(View.GONE);
    }

    @Override
    public void hideEmailView() {
        mLinearEmail.setVisibility(View.GONE);
    }

    @Override
    public void hidePhoneView() {
        mLinearPhone.setVisibility(View.GONE);
    }

    @Override
    public void hideLocaleView() {
        mLinearLocale.setVisibility(View.GONE);
    }

    @Override
    public void hideWebView() {
        mLinearWeb.setVisibility(View.GONE);
    }

    @Override
    public void showAudioOnView(String audio) {
        if (audio.startsWith("http"))
            initAudio(audio);
        else
            CenterDetailActivityPermissionsDispatcher.initAudioWithPermissionCheck(this, audio);
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    public void initAudio(String audio) {
        View layoutAudioPlayer = mStubPlayer.inflate();
        mAudioPlayer = layoutAudioPlayer.findViewById(R.id.audio_player_view);
        if (mAudioPlayer != null) {
            mAudioPlayer.init(audio);
        }
    }

    @Override
    public void hideAudioView() {
        mLinearAudio.setVisibility(View.GONE);
    }


    @OnClick(R.id.image_back_toolbar)
    public void onClickBack() {
        if (mAudioPlayer != null) {
            mAudioPlayer.destroy();
        }
        finish();
    }

    @OnClick(R.id.btn_go_to_place)
    void onClickGoTo() {
        mCenterDetailPresenter.goToPlace();
    }

    @OnClick(R.id.menu_horizontal)
    void onClickMenuHorizontal() {
        mCenterDetailPresenter.onClickButtonShared();
    }

    @OnClick(R.id.linear_web)
    void onClickWeb() {
        mCenterDetailPresenter.onWebItemClick();
    }

    @OnClick(R.id.linear_phone)
    void onClickPhone() {
        mCenterDetailPresenter.onPhoneItemClick();
    }

    @OnClick(R.id.linear_email)
    void onClickEmail() {
        mCenterDetailPresenter.onEmailItemClick();
    }

    @SuppressLint("InflateParams")
    @Override
    public void actionsToShare(Link link, Title title) {
        PopupWindow popupWindow = new PopupWindow(this);
        View inflate = LayoutInflater.from(this).inflate(R.layout.popup_window_share, null);
        inflate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        popupWindow.setContentView(inflate);
        popupWindow.setBackgroundDrawable(null);
        popupWindow.setOutsideTouchable(true);
        View view = popupWindow.getContentView();
        Button btnShare = view.findViewById(R.id.btn_share);
        btnShare.setOnClickListener(v -> {
            popupWindow.dismiss();
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/html");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, String.format(getString(R.string.can_see), title.asString(), link.asString()));
            startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)));

        });
        Button btnSeeOnWeb = view.findViewById(R.id.btn_see_on_web);
        btnSeeOnWeb.setOnClickListener(v -> {
            popupWindow.dismiss();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link.asString())));
        });
        popupWindow.showAtLocation(findViewById(R.id.menu_horizontal), Gravity.END | Gravity.TOP, 0, 0);
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

    @OnPermissionDenied(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showDenied() {
        Toast.makeText(this, R.string.must_accept_storage_permission_to_listen_audio, Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void showNeverAsk() {
        Toast.makeText(this, R.string.must_accept_storage_permission_to_listen_audio_in_settings, Toast.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onEvent(@SuppressWarnings("unused") OnMapReady onMapReady) {
        mEventBus.post(new HideSeeMoreOption());
    }
}
