package org.terrasdepontevedra.petra.domain.pojo;

import org.terrasdepontevedra.petra.domain.model.Place;

public class OpenViewPager {
    private final Place mPlace;

    public OpenViewPager(Place place) {
        mPlace = place;
    }

    public Place getPlace() {
        return mPlace;
    }
}
