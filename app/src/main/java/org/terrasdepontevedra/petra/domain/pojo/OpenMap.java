package org.terrasdepontevedra.petra.domain.pojo;

import org.terrasdepontevedra.petra.domain.model.Itinerary;

public class OpenMap {
    private final Itinerary mItinerary;

    public OpenMap(Itinerary itinerary) {
        mItinerary = itinerary;
    }

    public Itinerary getItinerary() {
        return mItinerary;
    }
}
