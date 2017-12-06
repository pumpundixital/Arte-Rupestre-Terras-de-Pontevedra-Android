package org.terrasdepontevedra.petra.data;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.terrasdepontevedra.petra.data.cache.CacheProvider;
import org.terrasdepontevedra.petra.data.model.center.CenterDto;
import org.terrasdepontevedra.petra.data.model.itinerary.ItineraryDto;
import org.terrasdepontevedra.petra.data.model.place.PlaceDto;
import org.terrasdepontevedra.petra.data.model.quiz.QuestionDto;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;

public class PetraRepositoryImpl implements PetraRepository {
    private final PetraApi mPetraApi;
    private final CacheProvider mCacheProvider;
    private final Gson mGson;
    private final Context mContext;

    public PetraRepositoryImpl(PetraApi petraApi, CacheProvider cacheProvider, Gson gson, Context context) {
        mPetraApi = petraApi;
        mCacheProvider = cacheProvider;
        mGson = gson;
        mContext = context;
    }

    @Override
    public Observable<List<ItineraryDto>> getItineraries(String language, int limit) {
        return mCacheProvider.getItineraries(mPetraApi.getItineraries(language, limit), new DynamicKey("getItineraries-" + language));
    }

    @Override
    public Observable<List<PlaceDto>> getPlacesByItinerary(String language, Integer itineraryId, int limit) {
        return mCacheProvider.getPlacesByItinerary(mPetraApi.getPlacesByItinerary(language, itineraryId, limit), new DynamicKey(String.format(Locale.getDefault(), "getPlacesByItinerary-%s-%d", language, itineraryId)));
    }

    @Override
    public Observable<List<PlaceDto>> getPlaces(String language, int limit) {
        return mCacheProvider.getPlaces(mPetraApi.getPlaces(language, limit), new DynamicKey("getPlaces-" + language));
    }

    @Override
    public Observable<List<CenterDto>> getCenter(String language, int limit) {
        return mCacheProvider.getCenters(mPetraApi.getCenters(language, limit), new DynamicKey("getCenter-" + language));
    }

    @Override
    public Observable<List<QuestionDto>> getQuiz(int param) {
        Type listType = new TypeToken<ArrayList<QuestionDto>>() {
        }.getType();
        InputStream inputStream = mContext.getResources().openRawResource(param);
        Reader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (reader != null)
            return Observable.just(mGson.fromJson(reader, listType));
        else
            return Observable.just(new ArrayList<>());
    }
}
