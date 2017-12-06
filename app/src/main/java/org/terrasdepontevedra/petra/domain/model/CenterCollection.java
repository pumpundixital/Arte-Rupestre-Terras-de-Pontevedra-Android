package org.terrasdepontevedra.petra.domain.model;

import android.os.Parcel;

import org.terrasdepontevedra.petra.util.CollectionBase;

import java.util.ArrayList;

public class CenterCollection extends CollectionBase<Center> {
    public CenterCollection() {
        super();
    }

    private CenterCollection(Parcel in) {
        mList = new ArrayList<>();
        in.readList(mList, null);
    }

    public static final Creator<CenterCollection> CREATOR = new Creator<CenterCollection>() {
        @Override
        public CenterCollection createFromParcel(Parcel in) {
            return new CenterCollection(in);
        }

        @Override
        public CenterCollection[] newArray(int size) {
            return new CenterCollection[size];
        }
    };

}
