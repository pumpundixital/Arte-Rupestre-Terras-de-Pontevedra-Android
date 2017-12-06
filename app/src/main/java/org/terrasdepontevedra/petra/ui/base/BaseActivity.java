package org.terrasdepontevedra.petra.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.terrasdepontevedra.petra.PetraApplication;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.data.local.LanguageCollector;
import org.terrasdepontevedra.petra.di.component.ActivityComponent;
import org.terrasdepontevedra.petra.di.component.DaggerActivityComponent;
import org.terrasdepontevedra.petra.di.module.ActivityModule;
import org.terrasdepontevedra.petra.di.module.AdapterModule;
import org.terrasdepontevedra.petra.util.LanguageUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public abstract class BaseActivity extends AppCompatActivity implements StateView {

    @Inject
    StateView mStateView;
    @Inject
    LanguageCollector mLanguageCollector;

    @Nullable
    @BindView(R.id.container)
    View mView;

    private Unbinder mUnbind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        inject();
        LanguageUtils.apply(this, mLanguageCollector.getLanguage().equals("/") ? "es" : mLanguageCollector.getLanguage());
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        inject();
        LanguageUtils.apply(this, mLanguageCollector.getLanguage());
        super.onCreate(savedInstanceState, persistentState);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnbind != null)
            mUnbind.unbind();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public void showEmptyView() {
        mStateView.showEmptyView();
    }

    @Override
    public void showError() {
        mStateView.showError();
    }

    @Override
    public void showErrorNetwork() {
        mStateView.showErrorNetwork();
    }

    @Override
    public void showLoading() {
        mStateView.showLoading();
    }

    @Override
    public void showMain() {
        mStateView.showMain();
    }

    @Override
    public void showError(Throwable throwable) {
        mStateView.showError(throwable);
    }

    @Override
    public void setMainView(View view) {
        mStateView.setMainView(view);
    }

    private void init() {
        setContentView(getLayoutRes());
        mUnbind = ButterKnife.bind(this);
        setMainView(mView);
    }

    protected ActivityComponent getActivityComponent() {
        return DaggerActivityComponent
                .builder()
                .activityModule(new ActivityModule(this))
                .adapterModule(new AdapterModule(getSupportFragmentManager()))
                .applicationComponent(PetraApplication.getApplicationComponent())
                .build();

    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void inject();
}
