package org.terrasdepontevedra.petra.domain.pojo;

import org.terrasdepontevedra.petra.domain.model.Itinerary;


public class OpenPlace {
    private final Itinerary mCurrentItinerary;
    private final int mPlacePosition;

    public OpenPlace(Itinerary currentItinerary, int placePosition) {
        mCurrentItinerary = currentItinerary;
        mPlacePosition = placePosition;
    }

    public Itinerary getCurrentItinerary() {
        return mCurrentItinerary;
    }

    public int getPlacePosition() {
        return mPlacePosition;
    }
}
