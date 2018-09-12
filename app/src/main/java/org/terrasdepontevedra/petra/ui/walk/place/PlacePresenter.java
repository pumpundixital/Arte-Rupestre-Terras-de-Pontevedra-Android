package org.terrasdepontevedra.petra.ui.walk.place;



import android.content.Context;

import org.terrasdepontevedra.petra.PetraApplication;
import org.terrasdepontevedra.petra.data.local.LanguageCollector;
import org.terrasdepontevedra.petra.data.local.LanguageCollectorImpl;
import org.terrasdepontevedra.petra.data.walk.WalkApi;
import org.terrasdepontevedra.petra.domain.model.walk.Place;
import org.terrasdepontevedra.petra.domain.model.walk.PlaceDto;
import org.terrasdepontevedra.petra.ui.walk.base.RxPresenter;
import org.terrasdepontevedra.petra.util.CommonSubscriber;
import org.terrasdepontevedra.petra.util.DeepLinkUtils;
import org.terrasdepontevedra.petra.util.RxUtil;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by pumpun3 on 13/6/17.
 */

public class PlacePresenter extends RxPresenter<PlaceContract.View> implements PlaceContract.Presenter {


    WalkApi walkApi;
    LanguageCollector mLang;

    public PlacePresenter(Context context) {
        walkApi = PetraApplication.getApp().getApi();
        mLang = new LanguageCollectorImpl(context);
    }

    @Override
    public void attachView(PlaceContract.View view) {
        super.attachView(view);
    }

    @Override
    public void getData(String link) {
        mView.stateLoading();
        if(mLang.getLanguage().equals("/")) {
            addSubscribe(walkApi.loadPlaceBySlugEs(DeepLinkUtils.getSlugFromLink(link))
                    .compose(RxUtil.rxSchedulerHelper())
                    .subscribeWith(new CommonSubscriber<List<PlaceDto>>(mView) {
                        @Override
                        public void onNext(List<PlaceDto> placeDtos) {

                            Observable.fromArray(placeDtos)
                                    .flatMapIterable(placesDtos -> placesDtos)
                                    .map(Place::mapper)
                                    .toList()
                                    .subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(places -> {

                                        if (!places.isEmpty()) {
                                            mView.showContent(places.get(0));

                                        }
                                    }, e -> {
                                        Timber.e(e.getMessage());
                                    });

                        }
                    })
            );
        }
        else{
            addSubscribe(walkApi.loadPlaceBySlug(mLang.getLanguage(),DeepLinkUtils.getSlugFromLink(link))
                    .compose(RxUtil.rxSchedulerHelper())
                    .subscribeWith(new CommonSubscriber<List<PlaceDto>>(mView) {
                        @Override
                        public void onNext(List<PlaceDto> placeDtos) {

                            Observable.fromArray(placeDtos)
                                    .flatMapIterable(placesDtos -> placesDtos)
                                    .map(Place::mapper)
                                    .toList()
                                    .subscribeOn(Schedulers.newThread())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(places -> {

                                        if (!places.isEmpty()) {
                                            mView.showContent(places.get(0));

                                        }
                                    }, e -> {
                                        Timber.e(e.getMessage());
                                    });

                        }
                    })
            );
        }

    }

    @Override
    public void getData(int placeId) {
        mView.stateLoading();
        Timber.i("get data:" + placeId);
  /*      addSubscribe(mDataManager.fetchPlaceById(placeId)
                .compose(RxUtil.rxSchedulerHelper())
                .subscribeWith(new CommonSubscriber<PlaceDto>(mView) {
                    @Override
                    public void onNext(PlaceDto placeDto) {

                        Observable.just(placeDto)
                                .map(Place::mapper)
                                .subscribeOn(Schedulers.newThread())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(place -> {
                                    mView.showContent(place);
                                }, e -> {
                                    Timber.e(e.getMessage());
                                });

                    }
                })
        );*/

    }



}
