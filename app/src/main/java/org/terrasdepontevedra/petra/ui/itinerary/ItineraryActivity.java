package org.terrasdepontevedra.petra.ui.itinerary;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.AppCompatImageButton;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.PopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.data.local.PreferencesCollector;
import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.domain.model.base.Link;
import org.terrasdepontevedra.petra.domain.model.base.Title;
import org.terrasdepontevedra.petra.domain.pojo.FragmentCreated;
import org.terrasdepontevedra.petra.domain.pojo.PetraHelpClose;
import org.terrasdepontevedra.petra.ui.base.BaseActivity;
import org.terrasdepontevedra.petra.ui.gallery.GalleryFragment;
import org.terrasdepontevedra.petra.ui.itinerary.content.ItineraryDetailFragment;
import org.terrasdepontevedra.petra.ui.itinerary.header.ItineraryImageFragment;
import org.terrasdepontevedra.petra.ui.place.detail.PlaceDetailFragment;
import org.terrasdepontevedra.petra.util.IntroConstants;
import org.terrasdepontevedra.petra.util.ScreenUtils;
import org.terrasdepontevedra.petra.util.services.FragmentService;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Optional;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;


public class ItineraryActivity
        extends BaseActivity
        implements ItineraryMvpView {

    private static final String ARGS_PLACE_POSITION = "ARGS_PLACE_POSITION";
    private static final String ARGS_ITINERARY = "ARGS_ITINERARY";

    @Inject
    ItineraryPresenter mItineraryPresenter;
    @Inject
    EventBus mEventBus;
    @Inject
    FragmentService mFragmentService;
    @Inject
    PreferencesCollector mPreferencesCollector;

    @BindView(R.id.container)
    View mView;
    @BindView(R.id.layout_btn_start_itinerary)
    View mBtnStart;
    @BindView(R.id.text_hint_btn_start)
    View mTvHindBtnStart;
    @BindView(R.id.menu_horizontal)
    AppCompatImageButton mBtnMenuHorizontal;

    public static Intent getCallingIntent(Context context, Itinerary itinerary) {
        Intent intent = new Intent(context, ItineraryActivity.class);
        intent.putExtra(ARGS_ITINERARY, itinerary);
        return intent;
    }

    public static Intent getCallingIntent(Context context, Itinerary itinerary, int placePosition) {
        Intent intent = getCallingIntent(context, itinerary);
        intent.putExtra(ARGS_PLACE_POSITION, placePosition);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int position = getIntent().getIntExtra(ARGS_PLACE_POSITION, -1);
        Itinerary itinerary = getIntent().getParcelableExtra(ARGS_ITINERARY);

        mItineraryPresenter.attachView(this);
        mItineraryPresenter.init(itinerary, position);
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
    protected void onDestroy() {
        super.onDestroy();
        mItineraryPresenter.detachView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_itinerary;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    private void animateFloatButton() {

        final int STARTUP_DELAY = 700;
        final int ANIM_ITEM_DURATION = 600;

        initialPositionFAB();

        ViewCompat
                .animate(mBtnStart)
                .translationX(-ScreenUtils.pxToDp(this, mBtnStart.getWidth()))
                .setStartDelay(STARTUP_DELAY)
                .setDuration(ANIM_ITEM_DURATION)
                .setInterpolator(new DecelerateInterpolator(1.2f))
                .withEndAction(() ->
                        ViewCompat
                                .animate(mTvHindBtnStart)
                                .alpha(1)
                                .setDuration(300)
                                .start()
                )
                .start();
    }

    private void initialPositionFAB() {
        mBtnStart.setX(mView.getRight() + mBtnStart.getWidth());
        if (mTvHindBtnStart != null)
            mTvHindBtnStart.setAlpha(0);
    }


    @Optional
    @OnClick({R.id.layout_btn_start_itinerary, R.id.text_hint_btn_start})
    void onClickStart() {
        mItineraryPresenter.onClickStart();
    }

    @OnClick(R.id.image_back_toolbar)
    void onClickBack() {
        finish();
    }

    @OnClick(R.id.menu_horizontal)
    void onClickMenuHorizontal() {
        mItineraryPresenter.onClickButtonShared();
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
            sharingIntent.putExtra(Intent.EXTRA_TEXT, String.format(getString(R.string.visit_the), title.asString(), link.asString()));
            startActivity(Intent.createChooser(sharingIntent, getString(R.string.share)));

        });
        Button btnSeeOnWeb = view.findViewById(R.id.btn_see_on_web);
        btnSeeOnWeb.setOnClickListener(v -> {
            popupWindow.dismiss();
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(link.asString())));
        });
        popupWindow.showAtLocation(findViewById(R.id.menu_horizontal), Gravity.END | Gravity.TOP, 0, 0);
    }

    @Subscribe
    public void onEvent(FragmentCreated fragmentCreated) {
        animateFloatButton();
    }

    @Override
    public void loadImageOnView(String imageUrlAsString) {
        mFragmentService.add(ItineraryImageFragment.newInstance(imageUrlAsString), R.id.container_top);
    }

    @Override
    public void loadDetailsOnView(Itinerary itinerary) {
        if (mPreferencesCollector.isPetraActive())
            new MaterialIntroView.Builder(this)
                    .enableDotAnimation(true)
                    .setFocusGravity(FocusGravity.CENTER)
                    .setFocusType(Focus.MINIMUM)
                    .setDelayMillis(400)
                    .enableFadeAnimation(true)
                    .performClick(true)
                    .dismissOnTouch(true)
                    .setListener(materialIntroViewId -> mEventBus.post(new PetraHelpClose()))
                    .setInfoText(getString(R.string.petra_message_menu_horizontal))
                    .setTarget(mBtnMenuHorizontal)
                    .setShape(ShapeType.CIRCLE)
                    .setUsageId(IntroConstants.MENU_HORIZONTAL) //THIS SHOULD BE UNIQUE ID
                    .show();

        mFragmentService.replace(ItineraryDetailFragment.newInstance(itinerary), R.id.container_bot);
        animateFloatButton();
    }

    @Override
    public void openPlaceDetail(PlaceCollection placeCollection, int position) {
        mBtnStart.setVisibility(View.GONE);
        mTvHindBtnStart.setVisibility(View.GONE);

        mFragmentService.replaceAndAddToBackStack(GalleryFragment.newInstance(placeCollection.getFeaturedImagesUrl(),true), R.id.container_top);
        mFragmentService.replaceAndAddToBackStack(PlaceDetailFragment.newInstance(placeCollection, position), R.id.container_bot);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mFragmentService.getCount() > 0) {
            mBtnStart.setVisibility(View.VISIBLE);
            mTvHindBtnStart.setVisibility(View.VISIBLE);
            mFragmentService.popStack();
        }
    }
}
