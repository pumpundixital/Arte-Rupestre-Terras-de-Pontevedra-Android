package org.terrasdepontevedra.petra.di.module;

import android.support.v4.app.FragmentManager;

import org.terrasdepontevedra.petra.di.scopes.ActivityScope;
import org.terrasdepontevedra.petra.di.scopes.FragmentScope;
import org.terrasdepontevedra.petra.ui.adventure.score.ScoreAdapter;
import org.terrasdepontevedra.petra.ui.center.list.CenterCollectionAdapter;
import org.terrasdepontevedra.petra.ui.itinerary.list.ItinerariesAdapter;
import org.terrasdepontevedra.petra.ui.place.PlacePagerAdapter;
import org.terrasdepontevedra.petra.ui.wizard.WizardAdapter;

import dagger.Module;
import dagger.Provides;


@Module
public class AdapterModule {

    private final FragmentManager mFragmentManager;

    public AdapterModule(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    @Provides
    @FragmentScope
    ItinerariesAdapter provideItinerariesAdapter() {
        return new ItinerariesAdapter();
    }

    @Provides
    @FragmentScope
    PlacePagerAdapter providePlacePagerAdapter() {
        return new PlacePagerAdapter(mFragmentManager);
    }

    @Provides
    @FragmentScope
    CenterCollectionAdapter provideCenterCollectionAdapter() {
        return new CenterCollectionAdapter();
    }

    @Provides
    @ActivityScope
    ScoreAdapter provideScoreAdapter() {
        return new ScoreAdapter();
    }

    @Provides
    @ActivityScope
    WizardAdapter provideWizardAdapter() {
        return new WizardAdapter(mFragmentManager);
    }
}
