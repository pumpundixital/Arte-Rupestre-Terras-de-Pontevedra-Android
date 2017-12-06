package org.terrasdepontevedra.petra.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.terrasdepontevedra.petra.util.interfaces.AsInt;
import org.terrasdepontevedra.petra.util.interfaces.AsString;

public class PlaceIdentity implements AsInt, AsString, Parcelable {
    static final PlaceIdentity Empty = PlaceIdentity.from(0);
    private final Integer mValue;

    private PlaceIdentity(Integer value) {
        mValue = value;
    }

    private PlaceIdentity(Parcel in) {
        if (in.readByte() == 0) {
            mValue = null;
        } else {
            mValue = in.readInt();
        }
    }

    public static final Creator<PlaceIdentity> CREATOR = new Creator<PlaceIdentity>() {
        @Override
        public PlaceIdentity createFromParcel(Parcel in) {
            return new PlaceIdentity(in);
        }

        @Override
        public PlaceIdentity[] newArray(int size) {
            return new PlaceIdentity[size];
        }
    };

    public static PlaceIdentity from(Integer value) {
        return new PlaceIdentity(value);
    }

    @Override
    public int asInt() {
        if (mValue == null)
            return 0;
        return mValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlaceIdentity)) return false;

        PlaceIdentity that = (PlaceIdentity) o;

        return mValue != null ? mValue.equals(that.mValue) : that.mValue == null;

    }

    @Override
    public int hashCode() {
        return mValue != null ? mValue.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PlaceIdentity{" +
                "mValue=" + mValue +
                '}';
    }

    @Override
    public String asString() {
        return mValue.toString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mValue == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mValue);
        }
    }
}

