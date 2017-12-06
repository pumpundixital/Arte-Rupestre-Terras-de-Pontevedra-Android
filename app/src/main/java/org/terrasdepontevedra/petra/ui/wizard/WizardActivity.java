package org.terrasdepontevedra.petra.ui.wizard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.widget.ImageView;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.data.local.PreferencesCollector;
import org.terrasdepontevedra.petra.ui.MainActivity;
import org.terrasdepontevedra.petra.ui.adventure.yincana.YincanaActivity;
import org.terrasdepontevedra.petra.ui.base.BaseActivity;
import org.terrasdepontevedra.petra.util.views.CirclePageIndicator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

import static org.terrasdepontevedra.petra.util.Constants.ARG_WIZARD_TYPE;


public class WizardActivity extends BaseActivity {

    public enum WizardType{
        INTRO,YINCANA
    }

    @Inject
    WizardAdapter mWizardAdapter;
    @Inject
    PreferencesCollector mPreferencesCollector;

    @BindView(R.id.vp_wizard)
    ViewPager mViewPager;
    @BindView(R.id.btn_skip)
    ImageView mButton;
    @BindView(R.id.indicator)
    CirclePageIndicator mCirclePageIndicator;

    WizardType mType;

    public static Intent getCallingIntent(Context context, WizardType wizardType) {
        Intent intent = new Intent(context, WizardActivity.class);
        intent.putExtra(ARG_WIZARD_TYPE,wizardType);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onNewIntent(getIntent());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        setupSlides();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mType = (WizardType) intent.getSerializableExtra(ARG_WIZARD_TYPE);

    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_wizard;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }


    private void setupSlides() {
        mButton.setVisibility(View.INVISIBLE);
        mWizardAdapter.loadFragments(mType);
        mViewPager.setAdapter(mWizardAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mButton.setVisibility(position == mWizardAdapter.getLastPosition() ? View.VISIBLE : View.INVISIBLE);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mCirclePageIndicator.setViewPager(mViewPager);
    }

    private void startActivity() {
        if(mType==WizardType.INTRO) {
            startActivity(MainActivity.getCallingIntent(this));
        }
        else if(mType==WizardType.YINCANA){
            startActivity(YincanaActivity.getCallingIntent(this));
        }
        this.finish();
    }

    @OnClick(R.id.btn_skip)
    public void onClickDone() {
        startActivity();
        mPreferencesCollector.setFirstTime(false);
    }
}
