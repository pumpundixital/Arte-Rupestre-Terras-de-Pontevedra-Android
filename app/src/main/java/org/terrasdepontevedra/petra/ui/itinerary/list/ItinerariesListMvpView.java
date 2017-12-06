package org.terrasdepontevedra.petra.ui.itinerary.list;

import org.terrasdepontevedra.petra.domain.model.ItineraryCollection;
import org.terrasdepontevedra.petra.ui.base.MvpView;
import org.terrasdepontevedra.petra.ui.base.StateView;

interface ItinerariesListMvpView extends MvpView, StateView {
    void loadItineraryCollectionOnView(ItineraryCollection itineraryCollection);
}
