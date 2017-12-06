package org.terrasdepontevedra.petra.domain.pojo;

import org.terrasdepontevedra.petra.domain.model.Itinerary;

public class FilterMap {
    private final Itinerary mItinerary;

    public FilterMap(Itinerary itinerary) {
        mItinerary = itinerary;
    }

    public Itinerary getItinerary() {
        return mItinerary;
    }
}
