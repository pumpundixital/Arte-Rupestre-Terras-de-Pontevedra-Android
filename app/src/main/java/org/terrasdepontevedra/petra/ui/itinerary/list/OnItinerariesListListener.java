package org.terrasdepontevedra.petra.ui.itinerary.list;

import android.view.View;

import org.terrasdepontevedra.petra.domain.model.Itinerary;


interface OnItinerariesListListener {
    void onClickItinerary(Itinerary itinerary, View transitionView);

    void onClickSeeMap(Itinerary item);
}
