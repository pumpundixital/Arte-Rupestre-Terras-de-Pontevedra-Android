package org.terrasdepontevedra.petra.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.data.local.PreferencesCollector;
import org.terrasdepontevedra.petra.data.service.LocationService;
import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.model.ItineraryCollection;
import org.terrasdepontevedra.petra.domain.pojo.ActivateFilter;
import org.terrasdepontevedra.petra.domain.pojo.DeactivateFilter;
import org.terrasdepontevedra.petra.domain.pojo.OpenMap;
import org.terrasdepontevedra.petra.ui.adventure.AdventureFragment;
import org.terrasdepontevedra.petra.ui.adventure.quiz.QuizDialog;
import org.terrasdepontevedra.petra.ui.adventure.score.dialog.ScoreDialog;
import org.terrasdepontevedra.petra.ui.base.BaseActivity;
import org.terrasdepontevedra.petra.ui.center.list.CenterListFragment;
import org.terrasdepontevedra.petra.ui.drawer.DrawerAdapter;
import org.terrasdepontevedra.petra.ui.drawer.DrawerItem;
import org.terrasdepontevedra.petra.ui.drawer.SimpleItem;
import org.terrasdepontevedra.petra.ui.help.HelpFragment;
import org.terrasdepontevedra.petra.ui.itinerary.list.ItinerariesListFragment;
import org.terrasdepontevedra.petra.ui.map.filter.ItineraryFilterMenu;
import org.terrasdepontevedra.petra.ui.map.itinerary.ItineraryMapFragment;
import org.terrasdepontevedra.petra.ui.map.place.MapFragment;
import org.terrasdepontevedra.petra.ui.moreInfo.MoreInfoFragment;
import org.terrasdepontevedra.petra.ui.settings.SettingsFragment;
import org.terrasdepontevedra.petra.ui.wizard.WizardActivity;
import org.terrasdepontevedra.petra.util.Constants;
import org.terrasdepontevedra.petra.util.IntroConstants;
import org.terrasdepontevedra.petra.util.interfaces.Action;
import org.terrasdepontevedra.petra.util.services.FragmentService;

import java.util.Arrays;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;

import static org.terrasdepontevedra.petra.util.AnimationUtils.rotate;

public class MainActivity extends BaseActivity
        implements DrawerAdapter.OnItemSelectedListener {

    private static final long WAIT_TIME = 2000L;
    private static long TOUCH_TIME;
    private final int mContainerFragment = R.id.container_main;

    private static final int POS_INTRO = 0;
    private static final int POS_CENTERS = 1;
    private static final int POS_ITINERARIES = 2;
    private static final int POS_MAP = 3;
    private static final int POS_ADVENTURE = 4;
    private static final int POS_HELP = 5;
    private static final int POS_MORE_INFO = 6;
    private static final int POS_SETTINGS = 7;

    @Inject
    FragmentService mFragmentService;
    @Inject
    EventBus mEventBus;
    @Inject
    PreferencesCollector mPreferencesCollector;

    @BindView(R.id.img_filter)
    ImageButton mImgFilter;
    @BindView(R.id.tv_title_petra)
    TextView mTvTitlePetra;
    @BindView(R.id.image_toolbar_menu)
    ImageView mImgMenu;

    private SlidingRootNav slidingRootNav;
    private String[] screenTitles;
    private Drawable[] screenIcons;

    private ItineraryCollection mItineraryCollection;
    private DrawerAdapter mDrawerAdapter;
    private Itinerary mItinerary;
    private SparseArray<Action> mActionSparseArray;
    private final Integer[] mFragments = new Integer[2];

    private boolean showDialog = false;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialize();

        mImgFilter.setVisibility(View.GONE);
        mTvTitlePetra.setVisibility(View.VISIBLE);

        initDrawer(savedInstanceState);

        if (mPreferencesCollector.isPetraActive())
            new MaterialIntroView.Builder(this)
                    .enableDotAnimation(true)
                    .setFocusGravity(FocusGravity.CENTER)
                    .setFocusType(Focus.MINIMUM)
                    .setDelayMillis(200)
                    .enableFadeAnimation(true)
                    .performClick(true)
                    .dismissOnTouch(true)
                    .setInfoText(getString(R.string.petra_message_main))
                    .setTarget(findViewById(R.id.image_toolbar_menu))
                    .setShape(ShapeType.CIRCLE)
                    .setUsageId(IntroConstants.MAIN) //THIS SHOULD BE UNIQUE ID
                    .show();

        ActivityCompat.requestPermissions(
                this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                1337
        );

        Intent intent = new Intent(this, LocationService.class);
        startService(intent);

    }

    private void initialize() {
        mActionSparseArray = new SparseArray<>();
        mActionSparseArray.put(POS_INTRO, this::showIntro);
        mActionSparseArray.put(POS_CENTERS, this::showCenters);
        mActionSparseArray.put(POS_ITINERARIES, this::showItineraries);
        mActionSparseArray.put(POS_MAP, this::showMap);
        mActionSparseArray.put(POS_ADVENTURE, this::showAdventure);
        mActionSparseArray.put(POS_HELP, this::showHelp);
        mActionSparseArray.put(POS_MORE_INFO, this::showMoreInfo);
        mActionSparseArray.put(POS_SETTINGS, this::showSettings);
    }

    private void showMoreInfo() {
        mFragments[0] = POS_MORE_INFO;
        restoreMenuIcon();
        mTvTitlePetra.setText(screenTitles[POS_MORE_INFO]);
        mFragmentService.replaceWithVerticalAnimation(MoreInfoFragment.newInstance(), mContainerFragment);
    }

    private void showHelp() {
        mFragments[0] = POS_HELP;
        restoreMenuIcon();
        mTvTitlePetra.setText(screenTitles[POS_HELP]);
        mFragmentService.replaceWithVerticalAnimation(HelpFragment.newInstance(), mContainerFragment);
    }

    private void showAdventure() {
        mFragments[0] = POS_ADVENTURE;
        restoreMenuIcon();
        mTvTitlePetra.setText(screenTitles[POS_ADVENTURE]);
        mFragmentService.replaceWithVerticalAnimation(AdventureFragment.newInstance(), mContainerFragment);
    }

    private void showCenters() {
        mFragments[0] = POS_CENTERS;
        restoreMenuIcon();
        mTvTitlePetra.setText(screenTitles[POS_CENTERS]);
        mFragmentService.replaceWithVerticalAnimation(CenterListFragment.newInstance(), mContainerFragment);
    }

    private void showIntro() {
        restoreMenuIcon();
        startActivity(WizardActivity.getCallingIntent(this, WizardActivity.WizardType.INTRO));
    }

    private void showSettings() {
        restoreMenuIcon();
        mTvTitlePetra.setText(screenTitles[POS_SETTINGS]);
        mItinerary = null;
        mFragments[0] = POS_SETTINGS;
        mFragmentService.replaceWithVerticalAnimation(SettingsFragment.newInstance(), mContainerFragment);
    }

    private void showMap() {
        mFragments[1] = POS_MAP;
        mTvTitlePetra.setText(screenTitles[POS_MAP]);
        if (mItinerary != null) {
            mFragmentService.replaceWithVerticalAnimation(ItineraryMapFragment.newInstance(mItinerary), mContainerFragment);
            mItinerary = null;
        } else {
            mFragmentService.replaceWithVerticalAnimation(MapFragment.newInstance(), mContainerFragment);
        }
        rotate(mImgMenu, ContextCompat.getDrawable(this, R.drawable.ic_back));
    }

    private void showItineraries() {
        mFragments[0] = POS_ITINERARIES;
        restoreMenuIcon();
        mTvTitlePetra.setText(screenTitles[POS_ITINERARIES]);
        mFragmentService.replaceWithVerticalAnimation(ItinerariesListFragment.newInstance(), mContainerFragment);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //todo check request code
        if(resultCode == Activity.RESULT_OK){
            showDialog = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(showDialog){
            ScoreDialog.newInstance().show(getSupportFragmentManager(), ScoreDialog.class.getName());
            showDialog=false;
        }
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
    public void onBackPressed() {
        if (mFragments[1] != null) {
            mFragments[1] = null;
            mItinerary = null;
            onEvent(new DeactivateFilter());
            mDrawerAdapter.setSelected(mFragments[0]);
            rotate(mImgMenu, ContextCompat.getDrawable(this, R.drawable.ic_menu));
        } else {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                this.finish();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                Toast.makeText(this, getString(R.string.press_again), Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Subscribe
    public void onEvent(ActivateFilter activateFilter) {
        mItineraryCollection = activateFilter.getItineraryCollection();
        mImgFilter.setVisibility(View.VISIBLE);
        mTvTitlePetra.setVisibility(View.GONE);

        if (mPreferencesCollector.isPetraActive())
            new MaterialIntroView.Builder(this)
                    .enableDotAnimation(true)
                    .setFocusGravity(FocusGravity.CENTER)
                    .setFocusType(Focus.MINIMUM)
                    .setDelayMillis(200)
                    .enableFadeAnimation(true)
                    .performClick(true)
                    .dismissOnTouch(true)
                    .setInfoText(getString(R.string.petra_message_main_filter))
                    .setTarget(mImgFilter)
                    .setShape(ShapeType.CIRCLE)
                    .setUsageId(IntroConstants.FILTER) //THIS SHOULD BE UNIQUE ID
                    .show();
    }


    @Subscribe
    public void onEvent(@SuppressWarnings("unused") DeactivateFilter deactivateFilter) {
        mImgFilter.setVisibility(View.GONE);
        mTvTitlePetra.setVisibility(View.VISIBLE);
    }

    @Subscribe
    public void onEvent(OpenMap openMap) {
        mItinerary = openMap.getItinerary();
        mDrawerAdapter.setSelected(POS_MAP);
    }

    private void initDrawer(Bundle savedInstanceState) {
        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        slidingRootNav = new SlidingRootNavBuilder(this)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(false)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.drawer)
                .inject();


        mDrawerAdapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_INTRO),
                createItemFor(POS_CENTERS).setChecked(true),
                createItemFor(POS_ITINERARIES),
                createItemFor(POS_MAP),
                createItemFor(POS_ADVENTURE),
                createItemFor(POS_HELP),
                createItemFor(POS_MORE_INFO),
                createItemFor(POS_SETTINGS)
        ));
        mDrawerAdapter.setListener(this);


        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(mDrawerAdapter);

        mDrawerAdapter.setSelected(POS_CENTERS);

    }

    private DrawerItem createItemFor(int position) {
        return new SimpleItem(screenIcons[position], screenTitles[position])
                .withIconTint(color(R.color.black))
                .withTextTint(color(R.color.black))
                .withSelectedIconTint(color(R.color.blue_dark))
                .withSelectedTextTint(color(R.color.blue_dark));
    }



    private Drawable[] loadScreenIcons() {
        TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
        Drawable[] icons = new Drawable[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            int id = ta.getResourceId(i, 0);
            if (id != 0) {
                icons[i] = ContextCompat.getDrawable(this, id);
            }
        }
        ta.recycle();
        return icons;
    }

    private String[] loadScreenTitles() {
        return getResources().getStringArray(R.array.ld_activityScreenTitles);
    }

    @Override
    public void onItemSelected(int position) {
        mActionSparseArray.get(position).execute();
        slidingRootNav.closeMenu(true);

    }

    @ColorInt
    private int color(@ColorRes int res) {
        return ContextCompat.getColor(this, res);
    }

    @OnClick(R.id.image_toolbar_menu)
    void onClickDrawerMenu() {
        if (mFragments[1] != null) {
            mFragments[1] = null;
            mItinerary = null;
            mDrawerAdapter.setSelected(mFragments[0]);
            onEvent(new DeactivateFilter());
            rotate(mImgMenu, ContextCompat.getDrawable(this, R.drawable.ic_menu));
            return;
        }
        if (slidingRootNav.isMenuClosed()) {
            slidingRootNav.openMenu(true);
        } else {
            slidingRootNav.closeMenu(true);
        }
    }

    @OnClick(R.id.img_filter)
    void onClickFilter() {
        rotate(mImgFilter, ContextCompat.getDrawable(this, R.drawable.ic_close));

        ItineraryFilterMenu itineraryFilterMenu = new ItineraryFilterMenu(this, mItineraryCollection, mEventBus);
        itineraryFilterMenu.setOnDismissListener(() ->
                rotate(mImgFilter, ContextCompat.getDrawable(this, R.drawable.ic_filter))
        );
        itineraryFilterMenu.showAsDropDown(mImgFilter);
    }


    private void restoreMenuIcon() {
        if (mFragments[1] != null) {
            mFragments[1] = null;
            mItinerary = null;
            onEvent(new DeactivateFilter());
            rotate(mImgMenu, ContextCompat.getDrawable(this, R.drawable.ic_menu));
        }
    }
}
