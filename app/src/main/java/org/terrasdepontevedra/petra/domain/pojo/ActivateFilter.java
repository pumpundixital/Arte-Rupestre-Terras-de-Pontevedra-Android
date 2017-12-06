package org.terrasdepontevedra.petra.domain.pojo;


import org.terrasdepontevedra.petra.domain.model.ItineraryCollection;

public class ActivateFilter {
    private final ItineraryCollection mItineraryCollection;

    public ActivateFilter(ItineraryCollection itineraryCollection) {
        mItineraryCollection = itineraryCollection;
    }

    public ItineraryCollection getItineraryCollection() {
        return mItineraryCollection;
    }
}
