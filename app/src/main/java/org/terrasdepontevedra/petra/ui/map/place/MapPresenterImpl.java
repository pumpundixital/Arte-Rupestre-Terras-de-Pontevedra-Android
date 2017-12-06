package org.terrasdepontevedra.petra.ui.map.place;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.data.local.LanguageCollector;
import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.model.ItineraryCollection;
import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.domain.pojo.ActivateFilter;
import org.terrasdepontevedra.petra.domain.pojo.FragmentCreated;
import org.terrasdepontevedra.petra.domain.pojo.OpenPlace;
import org.terrasdepontevedra.petra.domain.usecase.GetItinerariesWithPlaceUseCase;
import org.terrasdepontevedra.petra.ui.base.BasePresenter;
import org.terrasdepontevedra.petra.util.services.TranslatorService;

public class MapPresenterImpl extends BasePresenter<MapMvpView> implements MapPresenter {

    private final GetItinerariesWithPlaceUseCase mGetItinerariesWithPlaceUseCase;
    private final EventBus mEventBus;
    private final LanguageCollector mLanguageCollector;
    private final TranslatorService mTranslatorService;

    private ItineraryCollection mItineraryCollection;
    private boolean mActivateFilter = false;

    public MapPresenterImpl(GetItinerariesWithPlaceUseCase getItinerariesWithPlaceUseCase,
                            EventBus eventBus, LanguageCollector languageCollector,
                            TranslatorService translatorService) {
        mGetItinerariesWithPlaceUseCase = getItinerariesWithPlaceUseCase;
        mEventBus = eventBus;
        mLanguageCollector = languageCollector;
        mTranslatorService = translatorService;
    }


    @Override
    public void init() {
        mGetItinerariesWithPlaceUseCase
                .setParams(mLanguageCollector.getLanguage())
                .setPreAndPostActions(() -> {
                    if (mMvpView != null)
                        mMvpView.showLoading();
                }, () -> {
                    if (mMvpView != null)
                        mMvpView.showMain();
                })
                .setErrorAction(throwable -> mMvpView.showError(throwable))
                .execute(itineraryCollection -> {
                    mItineraryCollection = itineraryCollection;
                    if (mMvpView != null)
                        mMvpView.loadPlaceOnMap(mItineraryCollection.getAllPlaces());
                    mEventBus.post(new FragmentCreated());
                    mEventBus.post(new ActivateFilter(mItineraryCollection));
                    mActivateFilter = true;
                });
    }

    @Override
    public void init(PlaceCollection placeCollection) {
        if (placeCollection == null) {
            init();
            return;
        }
        mMvpView.disablePlaceClick();
        mMvpView.loadPlaceOnMap(placeCollection);
    }

    @Override
    public void filterMapBy(Itinerary itinerary) {
        if (itinerary.equals(Itinerary.seeAll(mTranslatorService.from(R.string.see_all)))) {
            mMvpView.loadPlaceOnMap(mItineraryCollection.getAllPlaces());
            return;
        }

        int index = mItineraryCollection.indexOf(itinerary);
        Itinerary itinerary1 = mItineraryCollection.get(index);
        itinerary1.setChecked(true);
        mMvpView.loadPlaceOnMap(itinerary1.getPlaceCollection());
    }

    @Override
    public void tryOpenPlace(Place place) {
        Itinerary currentItinerary = null;
        int placePosition = 0;
        if (mItineraryCollection != null) {
            for (Itinerary itinerary : mItineraryCollection.asList()) {
                if (itinerary.getPlaceCollection().contains(place)) {
                    currentItinerary = itinerary;
                    placePosition = itinerary.getPlaceCollection().indexOf(place);
                    break;
                }
            }
            mEventBus.post(new OpenPlace(currentItinerary, placePosition));
        }
    }

    @Override
    public void onResume() {
        if (mActivateFilter)
            mEventBus.post(new ActivateFilter(mItineraryCollection));
    }
}
