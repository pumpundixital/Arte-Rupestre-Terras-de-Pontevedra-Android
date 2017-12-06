package org.terrasdepontevedra.petra.domain.model;

import android.os.Parcel;

import org.terrasdepontevedra.petra.util.CollectionBase;

import java.util.ArrayList;

public class PlaceCollection extends CollectionBase<Place> {

    public PlaceCollection() {
        super();
    }

    private PlaceCollection(Parcel in) {
        mList = new ArrayList<>();
        in.readList(mList, Place.class.getClassLoader());
    }

    public static final Creator<PlaceCollection> CREATOR = new Creator<PlaceCollection>() {
        @Override
        public PlaceCollection createFromParcel(Parcel in) {
            return new PlaceCollection(in);
        }

        @Override
        public PlaceCollection[] newArray(int size) {
            return new PlaceCollection[size];
        }
    };

    public boolean hasOnlyOne() {
        return asList().size() == 1;
    }


    public ArrayList<String> getFeaturedImagesUrl() {
        ArrayList<String> imagesUrl = new ArrayList<>();
        for (Place place : asList()) {
            if (place.getImage() != null) {
                imagesUrl.add(place.getImage().getUrl().asString());
            } else {
                imagesUrl.add("https://blog.sqlauthority.com/wp-content/uploads/2007/06/null.png");
            }
        }
        return imagesUrl;
    }
}
