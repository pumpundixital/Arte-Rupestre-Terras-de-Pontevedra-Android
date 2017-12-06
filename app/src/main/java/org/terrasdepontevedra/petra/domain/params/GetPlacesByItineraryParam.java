package org.terrasdepontevedra.petra.domain.params;

import org.terrasdepontevedra.petra.domain.model.ItineraryIdentity;

public class GetPlacesByItineraryParam {
    private final String mLanguage;
    private final ItineraryIdentity mItineraryIdentity;


    public GetPlacesByItineraryParam(String language, ItineraryIdentity itineraryIdentity) {
        mLanguage = language;
        mItineraryIdentity = itineraryIdentity;
    }

    public String getLanguage() {
        return mLanguage;
    }

    public ItineraryIdentity getItineraryIdentity() {
        return mItineraryIdentity;
    }
}
