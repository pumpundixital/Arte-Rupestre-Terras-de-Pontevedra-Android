package org.terrasdepontevedra.petra.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Transformers.BaseTransformer;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.data.local.PreferencesCollector;
import org.terrasdepontevedra.petra.domain.pojo.OnGalleryImageChange;
import org.terrasdepontevedra.petra.domain.pojo.OnPlaceChangePage;
import org.terrasdepontevedra.petra.domain.pojo.PetraHelpClose;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.util.Constants;
import org.terrasdepontevedra.petra.util.IntroConstants;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;

import static org.terrasdepontevedra.petra.ui.gallery.GalleryActivity.getCallingIntent;


public class GalleryFragment extends BaseFragment {
    @Inject
    EventBus mEventBus;
    @Inject
    PreferencesCollector mPreferencesCollector;

    SliderLayout mSlider;
    @BindView(R.id.btn_full_screen)
    ImageView mBtnFullScreen;

    @BindView(R.id.slider)
    FrameLayout frameSlider;

    private ArrayList<String> mImagesUrl;

    public static GalleryFragment newInstance(ArrayList<String> imagesUrls) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(Constants.ARG_IMAGES_URLS, imagesUrls);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImagesUrl = getArguments().getStringArrayList(Constants.ARG_IMAGES_URLS);

        mSlider = new SliderLayout(getContext(),mImagesUrl.size());
        frameSlider.addView(mSlider);
        mSlider.stopAutoCycle();

        if (mImagesUrl.size() == 1) {
            mSlider.setPagerTransformer(false, new BaseTransformer() {
                @Override
                protected void onTransform(View view, float position) {

                }
            });
            mSlider.setIndicatorVisibility(PagerIndicator.IndicatorVisibility.Invisible);
        } else {

            mSlider.addOnPageChangeListener(new ViewPagerEx.OnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }

                @Override
                public void onPageSelected(int position) {
                    mEventBus.post(new OnGalleryImageChange(position));
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }

            });
        }
        setSliderItems();

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

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_gallery;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    private void setSliderItems() {
        for (String url : mImagesUrl) {
            TextSliderView textSliderView = new TextSliderView(getContext());
            textSliderView
                    .image(url)
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop);
            mSlider.addSlider(textSliderView);
        }

    }

    @Subscribe
    public void onEvent(OnPlaceChangePage onPlaceChangePage) {
        if (mSlider != null) {
            mSlider.setCurrentPosition(onPlaceChangePage.getPosition());
        }
    }

    @Subscribe
    public void onEvent(@SuppressWarnings("unused") PetraHelpClose petraHelpClose) {
        if (mPreferencesCollector.isPetraActive())
            new MaterialIntroView.Builder(getActivity())
                    .enableDotAnimation(true)
                    .setFocusGravity(FocusGravity.CENTER)
                    .setFocusType(Focus.MINIMUM)
                    .setDelayMillis(100)
                    .enableFadeAnimation(true)
                    .performClick(true)
                    .dismissOnTouch(true)
                    .setInfoText(getString(R.string.petra_message_gallery_full_screen))
                    .setTarget(mBtnFullScreen)
                    .setShape(ShapeType.CIRCLE)
                    .setUsageId(IntroConstants.FULL_SCREEN) //THIS SHOULD BE UNIQUE ID
                    .show();
    }


    @OnClick(R.id.btn_full_screen)
    public void onClickFullScreen() {
        startActivity(getCallingIntent(getContext(), mSlider.getCurrentPosition(), mImagesUrl));
    }
}
