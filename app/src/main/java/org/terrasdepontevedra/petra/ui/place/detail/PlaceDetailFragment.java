package org.terrasdepontevedra.petra.ui.place.detail;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.domain.pojo.OnGalleryImageChange;
import org.terrasdepontevedra.petra.domain.pojo.OnMapReady;
import org.terrasdepontevedra.petra.domain.pojo.OnPlaceChangePage;
import org.terrasdepontevedra.petra.domain.pojo.OpenViewPager;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.ui.map.place.MapFragment;
import org.terrasdepontevedra.petra.ui.place.PlacePagerAdapter;
import org.terrasdepontevedra.petra.util.services.FragmentService;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class PlaceDetailFragment extends BaseFragment implements PlaceDetailMvpView {

    @Inject
    EventBus mEventBus;
    @Inject
    PlacePagerAdapter mPlacePagerAdapter;
    @Inject
    PlaceDetailPresenter mPlaceDetailPresenter;
    @Inject
    FragmentService mFragmentService;

    @BindView(R.id.pager_places)
    ViewPager mViewPager;
    @BindView(R.id.sliding_layout)
    SlidingUpPanelLayout mPanel;
    @BindView(R.id.image_pager_next)
    ImageView mImgPagerNext;
    @BindView(R.id.image_pager_back)
    ImageView mImgPagerBack;
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_subtitle)
    TextView mTvSubTitle;

    private final AnimatorSet mAnimationSet = new AnimatorSet();
    private PlaceCollection mPlaceCollection;
    private int mPosition;

    public static PlaceDetailFragment newInstance(PlaceCollection placeCollection, int position) {
        PlaceDetailFragment placeDetailFragment = new PlaceDetailFragment();
        placeDetailFragment.setPlaceCollection(placeCollection);
        placeDetailFragment.setPosition(position);
        return placeDetailFragment;
    }

    private void setPlaceCollection(PlaceCollection placeCollection) {
        mPlaceCollection = placeCollection;
    }

    private void setPosition(int position) {
        mPosition = position;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPanel.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

        mFragmentService.add(MapFragment.newInstance(mPlaceCollection, false), R.id.fragment_map);

        mPlaceDetailPresenter.attachView(this);
        mPlaceDetailPresenter.init(mPlaceCollection, mPosition);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!mEventBus.isRegistered(this))
            mEventBus.register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mEventBus.isRegistered(this))
            mEventBus.unregister(this);
    }

    @Subscribe
    public void onEvent(@SuppressWarnings("unused") OnMapReady onMapReady) {
        if (mPosition == -1)
            Observable.timer(1, TimeUnit.SECONDS)
                    .subscribe(new Observer<Long>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Long aLong) {

                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                            getActivity().runOnUiThread(() -> {
                                if (mPanel != null)
                                    mPanel.setPanelState(SlidingUpPanelLayout.PanelState.COLLAPSED);
                                mEventBus.post(new OnPlaceChangePage(mPlaceCollection.get(0), 0));
                            });
                        }
                    });
    }

    @Subscribe
    public void onEvent(OpenViewPager openViewPager) {
        if (mPanel != null)
            mPanel.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);

        int position = mPlaceCollection.indexOf(openViewPager.getPlace());
        mViewPager.setCurrentItem(position);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_place_detail;
    }


    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void loadPlacesOnView(PlaceCollection placeCollection) {
        initPager(placeCollection);
    }

    @Override
    public void loadTitleOnView(String title) {
        mTvTitle.setText(Html.fromHtml(title));
    }

    @Override
    public void loadSubtitleOnView(String subtitle) {
        mTvSubTitle.setText(Html.fromHtml(subtitle));
    }

    @Override
    public void hideArrowViewPager() {
        mImgPagerBack.setVisibility(View.INVISIBLE);
        mImgPagerNext.setVisibility(View.INVISIBLE);
    }

    @Override
    public void loadPlaceOnView(int position) {
        if (mViewPager != null)
            mViewPager.setCurrentItem(position);
    }

    @OnClick(R.id.image_pager_next)
    void onClickNext() {
        if (mViewPager.getCurrentItem() < mViewPager.getAdapter().getCount())
            animFade(mImgPagerNext);

        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
    }

    @OnClick(R.id.image_pager_back)
    void onClickBack() {
        if (mViewPager.getCurrentItem() > 0)
            animFade(mImgPagerBack);

        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
    }

    @Subscribe
    public void onEvent(OnGalleryImageChange onGalleryImageChange) {
        if (mViewPager != null) {
            mViewPager.setCurrentItem(onGalleryImageChange.getPosition());
        }
    }

    private void initPager(PlaceCollection placeCollection) {
        mPlacePagerAdapter.setPlaceCollection(placeCollection);
        mViewPager.setAdapter(mPlacePagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mPlaceDetailPresenter.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void animFade(View v) {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(v, "alpha", 1f, .3f);
        fadeOut.setDuration(200);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(v, "alpha", .3f, 1f);
        fadeIn.setDuration(200);
        mAnimationSet.play(fadeIn).after(fadeOut);
        mAnimationSet.start();
    }
}
