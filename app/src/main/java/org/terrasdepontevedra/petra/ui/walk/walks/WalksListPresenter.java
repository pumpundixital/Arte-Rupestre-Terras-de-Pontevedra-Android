package org.terrasdepontevedra.petra.ui.walk.walks;

import android.annotation.SuppressLint;
import android.content.Context;

import org.terrasdepontevedra.petra.PetraApplication;
import org.terrasdepontevedra.petra.data.local.LanguageCollectorImpl;
import org.terrasdepontevedra.petra.data.walk.WalkApi;
import org.terrasdepontevedra.petra.domain.model.walk.Itinerary;
import org.terrasdepontevedra.petra.domain.model.walk.ItineraryDto;
import org.terrasdepontevedra.petra.ui.walk.pmp.BasePresenter;
import org.terrasdepontevedra.petra.util.CommonSubscriber;
import org.terrasdepontevedra.petra.util.RxUtil;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.functions.Consumer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class WalksListPresenter extends BasePresenter<WalksListMvpView> {


    WalkApi walkApi;
    LanguageCollectorImpl mLang;
    private int page;


    public WalksListPresenter(Context context) {
        walkApi = PetraApplication.getApp().getApi();
        mLang = new LanguageCollectorImpl(context);
        page = 0;
    }

    @SuppressLint("CheckResult")
    public void loadWalks() {
        page++;

        if (mLang.getLanguage().equals("/")) {
            walkApi.loadWalksEs()
                    .compose(RxUtil.rxSchedulerHelper())
                    .subscribe(itineraryDtos -> {
                        List<Itinerary> itineraries = new ArrayList<>();
                        for (ItineraryDto itineraryDto : itineraryDtos) {
                            Itinerary itinerary = Itinerary.mapper(itineraryDto);
                            itineraries.add(itinerary);
                        }
                        if (getMvpView() != null) {
                            getMvpView().onWalksLoaded(itineraries);
                        }
                    });
        } else {
            walkApi.loadWalks(mLang.getLanguage(), page)
                    .compose(RxUtil.rxSchedulerHelper())
                    .subscribe(itineraryDtos -> {
                        List<Itinerary> itineraries = new ArrayList<>();
                        for (ItineraryDto itineraryDto : itineraryDtos) {
                            Itinerary itinerary = Itinerary.mapper(itineraryDto);
                            itineraries.add(itinerary);
                        }
                        if (getMvpView() != null) {
                            getMvpView().onWalksLoaded(itineraries);
                        }
                    });
        }

    }
}
