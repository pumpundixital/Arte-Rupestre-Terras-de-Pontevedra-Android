package org.terrasdepontevedra.petra.di.module;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

import org.terrasdepontevedra.petra.di.qualifier.ActivityContext;
import org.terrasdepontevedra.petra.di.scopes.ActivityScope;
import org.terrasdepontevedra.petra.ui.base.StateView;
import org.terrasdepontevedra.petra.ui.base.StateViewImpl;
import org.terrasdepontevedra.petra.util.services.FragmentService;
import org.terrasdepontevedra.petra.util.services.FragmentServiceImpl;

import dagger.Module;
import dagger.Provides;

@ActivityScope
@Module
public class ActivityModule {

    private final FragmentActivity mActivity;

    public ActivityModule(FragmentActivity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityContext
    @ActivityScope
    Context provideContext() {
        return mActivity;
    }

    @Provides
    @ActivityScope
    StateView provideStateView(@ActivityContext Context context) {
        return new StateViewImpl(context);
    }

    @Provides
    @ActivityScope
    FragmentService provideFragmentService() {
        return new FragmentServiceImpl(mActivity.getSupportFragmentManager());
    }

}
