package org.terrasdepontevedra.petra.di.component;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.PetraApplication;
import org.terrasdepontevedra.petra.data.PetraRepository;
import org.terrasdepontevedra.petra.data.local.LanguageCollector;
import org.terrasdepontevedra.petra.data.local.PreferencesCollector;
import org.terrasdepontevedra.petra.data.local.ScoreCollector;
import org.terrasdepontevedra.petra.data.service.GeofenceTrasitionService;
import org.terrasdepontevedra.petra.data.service.LocationService;
import org.terrasdepontevedra.petra.di.module.ApplicationModule;
import org.terrasdepontevedra.petra.di.scopes.ApplicationScope;
import org.terrasdepontevedra.petra.util.services.TranslatorService;

import dagger.Component;

@ApplicationScope
@Component(
        modules = {
                ApplicationModule.class
        }
)
public interface ApplicationComponent {
    PetraRepository getPetraRepository();

    ScoreCollector getScoreCollector();

    LanguageCollector getLanguageCollector();

    PreferencesCollector getPreferencesCollector();

    EventBus getEventBus();

    TranslatorService getTranslatorService();

    void inject(PetraApplication petraApplication);

    void inject(LocationService locationService);

    void inject(GeofenceTrasitionService geofenceTrasitionService);
}
