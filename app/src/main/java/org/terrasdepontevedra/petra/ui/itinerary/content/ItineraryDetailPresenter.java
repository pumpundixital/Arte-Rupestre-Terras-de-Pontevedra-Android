package org.terrasdepontevedra.petra.ui.itinerary.content;

import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.ui.base.Presenter;

public interface ItineraryDetailPresenter extends Presenter<ItineraryDetailMvpView> {
    void init(Itinerary itinerary);

    void goToPlace();

    void onWebItemClick();

    void onPhoneItemClick();

    void onEmailItemClick();
}
