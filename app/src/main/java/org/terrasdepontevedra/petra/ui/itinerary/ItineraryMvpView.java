package org.terrasdepontevedra.petra.ui.itinerary;

import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.domain.model.base.Link;
import org.terrasdepontevedra.petra.domain.model.base.Title;
import org.terrasdepontevedra.petra.ui.base.MvpView;
import org.terrasdepontevedra.petra.ui.base.StateView;

interface ItineraryMvpView extends MvpView, StateView {

    void actionsToShare(Link link, Title title);

    void loadImageOnView(String imageUrlAsString);

    void loadDetailsOnView(Itinerary itinerary);

    void openPlaceDetail(PlaceCollection placeCollection, int position);

}
