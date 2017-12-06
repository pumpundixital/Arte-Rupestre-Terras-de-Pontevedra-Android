package org.terrasdepontevedra.petra.ui.map.place;

import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.ui.base.Presenter;

public interface MapPresenter extends Presenter<MapMvpView> {
    void init();

    void init(PlaceCollection placeCollection);

    void filterMapBy(Itinerary itinerary);

    void tryOpenPlace(Place place);

    void onResume();
}
