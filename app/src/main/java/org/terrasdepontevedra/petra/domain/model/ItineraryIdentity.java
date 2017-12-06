package org.terrasdepontevedra.petra.domain.model;


import android.os.Parcel;
import android.os.Parcelable;

import org.terrasdepontevedra.petra.util.interfaces.AsInt;
import org.terrasdepontevedra.petra.util.interfaces.AsString;

public class ItineraryIdentity implements AsInt, AsString, Parcelable {
    static final ItineraryIdentity Empty = ItineraryIdentity.from(0);
    private final Integer mValue;

    private ItineraryIdentity(Integer value) {
        mValue = value;
    }

    private ItineraryIdentity(Parcel in) {
        if (in.readByte() == 0) {
            mValue = null;
        } else {
            mValue = in.readInt();
        }
    }

    public static final Creator<ItineraryIdentity> CREATOR = new Creator<ItineraryIdentity>() {
        @Override
        public ItineraryIdentity createFromParcel(Parcel in) {
            return new ItineraryIdentity(in);
        }

        @Override
        public ItineraryIdentity[] newArray(int size) {
            return new ItineraryIdentity[size];
        }
    };

    public static ItineraryIdentity from(Integer value) {
        return new ItineraryIdentity(value);
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
        if (!(o instanceof ItineraryIdentity)) return false;

        ItineraryIdentity that = (ItineraryIdentity) o;

        return mValue != null ? mValue.equals(that.mValue) : that.mValue == null;

    }

    @Override
    public int hashCode() {
        return mValue != null ? mValue.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ItineraryIdentity{" +
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
