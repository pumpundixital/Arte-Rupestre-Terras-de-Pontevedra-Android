package org.terrasdepontevedra.petra.domain.pojo;

import org.terrasdepontevedra.petra.domain.model.Place;


public class OnPlaceChangePage {
    private final Place mPlace;
    private final int mPosition;

    public OnPlaceChangePage(Place place, int position) {
        mPlace = place;
        mPosition = position;
    }

    public Place getPlace() {
        return mPlace;
    }

    public int getPosition() {
        return mPosition;
    }
}
