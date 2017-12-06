package org.terrasdepontevedra.petra.di.module;

import android.content.Context;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import org.terrasdepontevedra.petra.data.PetraApi;
import org.terrasdepontevedra.petra.data.PetraRepository;
import org.terrasdepontevedra.petra.data.PetraRepositoryImpl;
import org.terrasdepontevedra.petra.data.cache.CacheProvider;
import org.terrasdepontevedra.petra.di.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(
        includes = LocaleModule.class
)
public class NetworkModule {

    private final Context mContext;

    public NetworkModule(Context context) {
        mContext = context;
    }

    @ApplicationScope
    @Provides
    PetraRepository providePetraRepository(PetraApi petraApi, CacheProvider cacheProvider, Gson gson) {
        return new PetraRepositoryImpl(petraApi, cacheProvider, gson, mContext);
    }

    @ApplicationScope
    @Provides
    PetraApi providePetraApi(Retrofit retrofit) {
        return retrofit.create(PetraApi.class);
    }

    @ApplicationScope
    @Provides
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    String url = chain.request().url().toString().replace("%2F", "");
                    Request request = chain.request().newBuilder().url(url).build();
                    return chain.proceed(request);
                })
                .build();
    }

    @ApplicationScope
    @Provides
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(PetraApi.API_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
