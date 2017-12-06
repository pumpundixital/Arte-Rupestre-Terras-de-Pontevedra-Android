package org.terrasdepontevedra.petra.ui.map.itinerary;

import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.ui.base.BasePresenter;

public class ItineraryMapPresenterImpl extends BasePresenter<ItineraryMapMvpView> implements ItineraryMapPresenter {

    public ItineraryMapPresenterImpl() {
    }


    @Override
    public void init(Itinerary itinerary) {
        mMvpView.loadOnMap(itinerary.getLocale());
    }
}
