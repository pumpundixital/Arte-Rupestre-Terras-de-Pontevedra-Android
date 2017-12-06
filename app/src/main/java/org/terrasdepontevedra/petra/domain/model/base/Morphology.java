package org.terrasdepontevedra.petra.domain.model.base;


import android.os.Parcel;
import android.os.Parcelable;

import org.terrasdepontevedra.petra.util.interfaces.AsString;

public class Morphology implements AsString, Parcelable {
    public static final Morphology Empty = Morphology.from("");
    private final String mValue;

    private Morphology(String value) {
        mValue = value;
    }

    private Morphology(Parcel in) {
        mValue = in.readString();
    }

    public static final Creator<Morphology> CREATOR = new Creator<Morphology>() {
        @Override
        public Morphology createFromParcel(Parcel in) {
            return new Morphology(in);
        }

        @Override
        public Morphology[] newArray(int size) {
            return new Morphology[size];
        }
    };

    public static Morphology from(String value) {
        return new Morphology(value);
    }

    @Override
    public String asString() {
        return mValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Morphology)) return false;

        Morphology morphology = (Morphology) o;

        return mValue != null ? mValue.equals(morphology.mValue) : morphology.mValue == null;

    }

    @Override
    public int hashCode() {
        return mValue != null ? mValue.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Morphology{" +
                "mValue='" + mValue + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mValue);
    }
}
