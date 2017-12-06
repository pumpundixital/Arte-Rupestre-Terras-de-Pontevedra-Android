package org.terrasdepontevedra.petra.domain.model.base;

import android.os.Parcel;
import android.os.Parcelable;

import org.terrasdepontevedra.petra.util.interfaces.AsString;

public class Locale implements AsString, Parcelable {
    private final String mValue;
    private final double mLatitude;
    private final double mLongitude;

    private Locale(String value, double latitude, double longitude) {
        mValue = value;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    protected Locale(Parcel in) {
        mValue = in.readString();
        mLatitude = in.readDouble();
        mLongitude = in.readDouble();
    }

    public static final Creator<Locale> CREATOR = new Creator<Locale>() {
        @Override
        public Locale createFromParcel(Parcel in) {
            return new Locale(in);
        }

        @Override
        public Locale[] newArray(int size) {
            return new Locale[size];
        }
    };

    public static Locale from(String value, double latitude, double longitude) {
        return new Locale(value, latitude, longitude);
    }

    @Override
    public String asString() {
        if (mValue == null)
            return "";
        return mValue;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Locale)) return false;

        Locale locale = (Locale) o;

        return Double.compare(locale.getLatitude(), getLatitude()) == 0
                && Double.compare(locale.getLongitude(), getLongitude()) == 0
                && mValue.equals(locale.mValue);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = mValue.hashCode();
        temp = Double.doubleToLongBits(getLatitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getLongitude());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Locale{" +
                "mValue='" + mValue + '\'' +
                ", mLatitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mValue);
        dest.writeDouble(mLatitude);
        dest.writeDouble(mLongitude);
    }
}
