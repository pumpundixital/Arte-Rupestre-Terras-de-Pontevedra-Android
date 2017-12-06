package org.terrasdepontevedra.petra.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.terrasdepontevedra.petra.data.cache.CacheProvider;
import org.terrasdepontevedra.petra.data.local.LanguageCollector;
import org.terrasdepontevedra.petra.data.local.LanguageCollectorImpl;
import org.terrasdepontevedra.petra.data.local.PreferencesCollector;
import org.terrasdepontevedra.petra.data.local.PreferencesCollectorImpl;
import org.terrasdepontevedra.petra.data.local.ScoreCollector;
import org.terrasdepontevedra.petra.data.local.ScoreCollectorImpl;
import org.terrasdepontevedra.petra.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

@Module
public class LocaleModule {

    private final Context mContext;

    public LocaleModule(Context context) {
        mContext = context;
    }

    @ApplicationScope
    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @ApplicationScope
    @Provides
    GsonSpeaker provideGsonSpeaker() {
        return new GsonSpeaker();
    }

    @ApplicationScope
    @Provides
    CacheProvider provideCacheProvider(GsonSpeaker gsonSpeaker) {
        return new RxCache.Builder()
                .setMaxMBPersistenceCache(300)
                .persistence(mContext.getFilesDir(), gsonSpeaker)
                .using(CacheProvider.class);
    }

    @ApplicationScope
    @Provides
    ScoreCollector provideScoreDataCollector(Gson gson) {
        return new ScoreCollectorImpl(mContext, gson);
    }

    @ApplicationScope
    @Provides
    LanguageCollector provideLanguageCollector() {
        return new LanguageCollectorImpl(mContext);
    }

    @ApplicationScope
    @Provides
    PreferencesCollector providePreferencesCollector() {
        return new PreferencesCollectorImpl(mContext);
    }
}
