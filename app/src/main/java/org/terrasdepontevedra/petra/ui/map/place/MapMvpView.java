package org.terrasdepontevedra.petra.ui.map.place;

import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.ui.base.MvpView;
import org.terrasdepontevedra.petra.ui.base.StateView;

public interface MapMvpView extends MvpView, StateView {

    void loadPlaceOnMap(PlaceCollection placeCollection);

    void disablePlaceClick();
}
