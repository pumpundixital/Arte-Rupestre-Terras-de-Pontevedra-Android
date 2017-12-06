package org.terrasdepontevedra.petra.ui.itinerary;

import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.ui.base.Presenter;


public interface ItineraryPresenter extends Presenter<ItineraryMvpView> {
    void init(Itinerary itinerary, int position);

    void onClickStart();

    void onClickButtonShared();
}
