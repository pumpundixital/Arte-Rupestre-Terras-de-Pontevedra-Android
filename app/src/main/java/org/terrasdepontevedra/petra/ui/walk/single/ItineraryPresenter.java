package org.terrasdepontevedra.petra.ui.walk.single;



import org.terrasdepontevedra.petra.PetraApplication;
import org.terrasdepontevedra.petra.data.walk.WalkApi;
import org.terrasdepontevedra.petra.domain.model.walk.Itinerary;
import org.terrasdepontevedra.petra.domain.model.walk.ItineraryDto;
import org.terrasdepontevedra.petra.domain.model.walk.Place;
import org.terrasdepontevedra.petra.domain.model.walk.PlaceDto;
import org.terrasdepontevedra.petra.domain.model.walk.Relation;
import org.terrasdepontevedra.petra.domain.model.walk.RelationDto;
import org.terrasdepontevedra.petra.ui.walk.base.RxPresenter;
import org.terrasdepontevedra.petra.util.CommonSubscriber;
import org.terrasdepontevedra.petra.util.DeepLinkUtils;
import org.terrasdepontevedra.petra.util.RxUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import timber.log.Timber;

/**
 * Created by pablopumpun on 22/9/17.
 */

public class ItineraryPresenter extends RxPresenter<ItineraryContract.View> implements ItineraryContract.Presenter {

    private Itinerary mItinerary;
    private WalkApi walkApi;

    public ItineraryPresenter() {
        walkApi = PetraApplication.getApp().getApi();
    }

    public void getData(String slug) {
        getItineraryFromSlug(slug);
    }

    public void getData(int id) {
        getItineraryFromId(id);
    }

    @Override
    public void getItineraryFromSlug(String slug) {
        mView.stateLoading();
        addSubscribe(walkApi.loadItineraryBySlug("es", DeepLinkUtils.getSlugFromLink(slug))
                .compose(RxUtil.rxSchedulerHelper())
                .map(itineraryDtos -> {
                    List<Itinerary> itineraries = new ArrayList<>();
                    for (ItineraryDto itineraryDto : itineraryDtos) {
                        itineraries.add(Itinerary.mapper(itineraryDto));
                    }
                    return itineraries;
                })
                .subscribeWith(new CommonSubscriber<List<Itinerary>>(mView) {
                    @Override
                    public void onNext(List<Itinerary> itineraries) {
                        if (itineraries.isEmpty()) {
                            mView.stateEmpty();
                        } else {
                            Itinerary itinerary = itineraries.get(0);
                            mItinerary = itinerary;
                            getPlacesFromItineraryId(itinerary.getId());
                        }
                    }
                })
        );
    }

    public void getItineraryFromId(int id) {
        mView.stateLoading();
        addSubscribe(walkApi.loadItinerary(id)
                .compose(RxUtil.rxSchedulerHelper())
                .map(Itinerary::mapper)
                .subscribeWith(new CommonSubscriber<Itinerary>(mView) {
                    @Override
                    public void onNext(Itinerary itinerary) {
                        mItinerary = itinerary;
                        getPlacesFromItineraryId(itinerary.getId());
                    }
                })
        );
    }

    private void getPlacesFromItineraryId(int itineraryId) {
        addSubscribe(walkApi.loadPlacesRelationsByCategoryItineraryId("_wpcf_belongs_itinerario_id",itineraryId, 100)
                .compose(RxUtil.rxSchedulerHelper())
                .map(relationDtos -> {
                    List<Relation> relations = new ArrayList<>();
                    for (RelationDto relationDto : relationDtos) {
                        relations.add(Relation.mapper(relationDto));
                    }
                    return relations;
                })
                .subscribeWith(new CommonSubscriber<List<Relation>>(mView) {
                    @Override
                    public void onNext(List<Relation> relations) {
                        if (!relations.isEmpty()) {
                            List<Integer> ids = new ArrayList<>();

                            Arrays.sort(
                                    relations.toArray(),
                                    (o1, o2) -> ((Relation) o1).getOrder() > ((Relation) o2).getOrder() ? 1 : -1
                            );

                            for (Relation relation : relations) {
                                ids.add(relation.getCategoryPlaceId());
                            }


                            getPlacesFromIds(ids);
                        } else {
                            mView.stateEmpty();
                        }
                    }
                })
        );
    }


    private void getPlacesFromIds(List<Integer> placesIds) {
        addSubscribe(walkApi.loadPlacesFromIds( generateUrlFrom(placesIds))
                .compose(RxUtil.rxSchedulerHelper())
                .map(placeDtos -> {
                    List<Place> places = new ArrayList<>();
                    for (PlaceDto placeDto : placeDtos) {
                        places.add(Place.mapper(placeDto));
                    }
                    return places;
                })
                .subscribeWith(new CommonSubscriber<List<Place>>(mView) {
                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Place> places) {
                        if (!places.isEmpty()) {
                            Timber.i("on data loaded");
                            //mView.onDataLoaded(mItinerary, places);
                        }
                    }
                })
        );
    }

    private String generateUrlFrom(List<Integer> integerList) {
        String url = "";
        for (int id : integerList) {
            url += id + ",";
        }
        if (url.length() > 0) {
            url = url.substring(0, url.length() - 1);
        }
        return url;
    }

}
