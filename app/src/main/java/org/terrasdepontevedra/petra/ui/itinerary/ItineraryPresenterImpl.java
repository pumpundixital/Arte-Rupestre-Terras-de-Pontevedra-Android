package org.terrasdepontevedra.petra.ui.itinerary;

import org.terrasdepontevedra.petra.data.local.LanguageCollector;
import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.domain.params.GetPlacesByItineraryParam;
import org.terrasdepontevedra.petra.domain.usecase.GetPlacesByItineraryUseCase;
import org.terrasdepontevedra.petra.ui.base.BasePresenter;


public class ItineraryPresenterImpl extends BasePresenter<ItineraryMvpView>
        implements ItineraryPresenter {


    private Itinerary mItinerary;
    private final GetPlacesByItineraryUseCase mGetPlacesByItineraryUseCase;
    private final LanguageCollector mLanguageCollector;

    public ItineraryPresenterImpl(GetPlacesByItineraryUseCase getPlacesByItineraryUseCase, LanguageCollector languageCollector) {
        mGetPlacesByItineraryUseCase = getPlacesByItineraryUseCase;
        mLanguageCollector = languageCollector;
    }


    @Override
    public void init(Itinerary itinerary, int position) {
        mItinerary = itinerary;
        mGetPlacesByItineraryUseCase
                .setParams(new GetPlacesByItineraryParam(mLanguageCollector.getLanguage(), itinerary.getItineraryIdentity()))
                .setPreAndPostActions(() -> {
                            if (mMvpView != null)
                                mMvpView.showLoading();
                        },
                        () -> {
                            if (mMvpView != null)
                                mMvpView.showMain();
                        })
                .setErrorAction(throwable -> {
                    if (mMvpView != null)
                        mMvpView.showError(throwable);
                })
                .execute(placeCollection -> actionWhenGetedPlaces(itinerary, position, placeCollection));
    }

    @Override
    public void onClickStart() {
        mMvpView.openPlaceDetail(mItinerary.getPlaceCollection(), -1);
    }

    @Override
    public void onClickButtonShared() {
        mMvpView.actionsToShare(mItinerary.getLink(), mItinerary.getTitle());
    }

    private void actionWhenGetedPlaces(Itinerary itinerary, int position, PlaceCollection placeCollection) {
        mItinerary.setPlaceCollection(placeCollection);

        mMvpView.loadDetailsOnView(itinerary);
        mMvpView.loadImageOnView(itinerary.getImage().getUrl().asString());

        if (position >= 0)
            mMvpView.openPlaceDetail(mItinerary.getPlaceCollection(), position);
    }
}
