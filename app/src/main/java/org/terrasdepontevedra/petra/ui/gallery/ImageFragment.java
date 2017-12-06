package org.terrasdepontevedra.petra.ui.gallery;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.daimajia.slider.library.GlideApp;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.ui.views.PinchImageView;

import butterknife.BindView;


public class ImageFragment extends BaseFragment {
    private static final String ARGS_URL = "ARGS_URL";

    @BindView(R.id.piv_image)
    PinchImageView mPinchImageView;
    private String mUrl;

    public ImageFragment() {
        // Required empty public constructor
    }


    public static ImageFragment newInstance(String url) {
        ImageFragment imageFragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARGS_URL, url);
        imageFragment.setArguments(bundle);
        return imageFragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_image;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUrl = getArguments().getString(ARGS_URL);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        GlideApp
                .with(getContext())
                .load(mUrl)
                .into(mPinchImageView);
    }
}
