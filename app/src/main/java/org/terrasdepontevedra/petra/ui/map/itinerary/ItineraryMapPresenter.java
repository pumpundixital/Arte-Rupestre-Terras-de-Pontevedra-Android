package org.terrasdepontevedra.petra.ui.map.itinerary;

import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.ui.base.Presenter;

public interface ItineraryMapPresenter extends Presenter<ItineraryMapMvpView> {
    void init(Itinerary itinerary);
}
