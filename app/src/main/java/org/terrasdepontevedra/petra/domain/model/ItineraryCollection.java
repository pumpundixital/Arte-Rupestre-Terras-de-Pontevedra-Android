package org.terrasdepontevedra.petra.domain.model;

import android.os.Parcel;

import org.terrasdepontevedra.petra.util.CollectionBase;

import java.util.ArrayList;

public class ItineraryCollection extends CollectionBase<Itinerary> {
    public ItineraryCollection() {
        super();
    }

    private ItineraryCollection(Parcel in) {
        mList = new ArrayList<>();
        in.readList(mList, Itinerary.class.getClassLoader());
    }

    public static final Creator<ItineraryCollection> CREATOR = new Creator<ItineraryCollection>() {
        @Override
        public ItineraryCollection createFromParcel(Parcel in) {
            return new ItineraryCollection(in);
        }

        @Override
        public ItineraryCollection[] newArray(int size) {
            return new ItineraryCollection[size];
        }
    };

    public PlaceCollection getAllPlaces() {
        PlaceCollection placeCollection = new PlaceCollection();
        for (Itinerary itinerary : asList()) {
            placeCollection.addAll(itinerary.getPlaceCollection().asList());
        }
        return placeCollection;
    }

    public void removeSelect() {
        for (Itinerary itinerary : asList()) {
            itinerary.setChecked(false);
        }
    }

    public void select(Itinerary itinerary) {
        get(indexOf(itinerary)).setChecked(true);
    }

    public boolean hasSomeSelected() {
        for (Itinerary itinerary : asList()) {
            if (itinerary.isChecked())
                return true;
        }
        return false;
    }

    public Itinerary getFirst() {
        return get(0);
    }
}
