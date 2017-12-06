package org.terrasdepontevedra.petra.domain.model;

import android.os.Parcel;

import org.terrasdepontevedra.petra.util.CollectionBase;

import java.util.ArrayList;


public class ScoreCollection extends CollectionBase<Score> {
    public ScoreCollection() {
        super();
    }

    private ScoreCollection(Parcel in) {
        mList = new ArrayList<>();
        in.readList(mList, Score.class.getClassLoader());
    }

    public static final Creator<ScoreCollection> CREATOR = new Creator<ScoreCollection>() {
        @Override
        public ScoreCollection createFromParcel(Parcel in) {
            return new ScoreCollection(in);
        }

        @Override
        public ScoreCollection[] newArray(int size) {
            return new ScoreCollection[size];
        }
    };

}
