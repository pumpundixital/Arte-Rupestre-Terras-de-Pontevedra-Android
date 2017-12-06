package org.terrasdepontevedra.petra.di.module;

import android.content.Context;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.di.scopes.ApplicationScope;
import org.terrasdepontevedra.petra.util.services.TranslatorService;
import org.terrasdepontevedra.petra.util.services.TranslatorServiceImpl;

import dagger.Module;
import dagger.Provides;

@Module(includes = {
        NetworkModule.class,
        LocaleModule.class
})
public class ApplicationModule {

    private final Context mContext;

    public ApplicationModule(Context context) {
        mContext = context;
    }

    @ApplicationScope
    @Provides
    EventBus provideEventBus() {
        return EventBus.getDefault();
    }

    @ApplicationScope
    @Provides
    TranslatorService provideTranslatorService() {
        return new TranslatorServiceImpl(mContext);
    }
}
