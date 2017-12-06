package org.terrasdepontevedra.petra.ui.map.itinerary;

import org.terrasdepontevedra.petra.domain.model.base.Locale;
import org.terrasdepontevedra.petra.ui.base.MvpView;
import org.terrasdepontevedra.petra.ui.base.StateView;

interface ItineraryMapMvpView extends MvpView, StateView {

    void loadOnMap(Locale locale);

    void disableItineraryClick();
}
