package org.terrasdepontevedra.petra.di.module;

import android.content.Context;
import android.support.v4.app.Fragment;

import org.terrasdepontevedra.petra.di.qualifier.FragmentContext;
import org.terrasdepontevedra.petra.di.scopes.FragmentScope;
import org.terrasdepontevedra.petra.ui.base.StateView;
import org.terrasdepontevedra.petra.ui.base.StateViewImpl;
import org.terrasdepontevedra.petra.util.services.FragmentService;
import org.terrasdepontevedra.petra.util.services.FragmentServiceImpl;

import dagger.Module;
import dagger.Provides;

@FragmentScope
@Module
public class FragmentModule {
    private final Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }

    @Provides
    @FragmentContext
    @FragmentScope
    Context provideContext() {
        return mFragment.getContext();
    }

    @Provides
    @FragmentScope
    StateView provideStateView(@FragmentContext Context context) {
        return new StateViewImpl(context);
    }

    @Provides
    @FragmentScope
    FragmentService provideFragmentService() {
        return new FragmentServiceImpl(mFragment.getChildFragmentManager());
    }
}
