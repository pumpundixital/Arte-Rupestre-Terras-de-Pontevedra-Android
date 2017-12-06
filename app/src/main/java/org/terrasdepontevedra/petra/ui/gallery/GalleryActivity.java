package org.terrasdepontevedra.petra.ui.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.ui.base.BaseActivity;
import org.terrasdepontevedra.petra.util.views.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GalleryActivity extends BaseActivity {
    private static final String ARGS_POSITION = "ARGS_POSITION";
    private static final String ARGS_IMAGE_URLS = "ARGS_IMAGE_URLS";

    @BindView(R.id.vp_gallery_full_screen)
    ViewPager mViewPager;
    @BindView(R.id.indicator_image)
    CirclePageIndicator mCirclePageIndicator;

    private int mPosition;
    private List<String> mUrls;

    public static Intent getCallingIntent(Context context, int currentPosition, ArrayList<String> urls) {
        Intent intent = new Intent(context, GalleryActivity.class);
        intent.putExtra(ARGS_POSITION, currentPosition);
        intent.putExtra(ARGS_IMAGE_URLS, urls);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPosition = getIntent().getIntExtra(ARGS_POSITION, 0);
        mUrls = getIntent().getStringArrayListExtra(ARGS_IMAGE_URLS);

        loadPager();
    }

    private void loadPager() {
        mViewPager.setAdapter(new ImagePageAdapter(getSupportFragmentManager(), mUrls));
        mViewPager.setCurrentItem(mPosition);
        mCirclePageIndicator.setViewPager(mViewPager);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_gallery;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @OnClick(R.id.btn_close)
    public void onClickClose() {
        finish();
    }
}
