package org.terrasdepontevedra.petra.domain.model.base;

import android.os.Parcel;
import android.os.Parcelable;

import org.terrasdepontevedra.petra.util.interfaces.AsString;

public class Description implements AsString, Parcelable {
    public static final Description Empty = Description.from("");
    private final String mValue;

    private Description(String value) {
        mValue = value;
    }

    protected Description(Parcel in) {
        mValue = in.readString();
    }

    public static final Creator<Description> CREATOR = new Creator<Description>() {
        @Override
        public Description createFromParcel(Parcel in) {
            return new Description(in);
        }

        @Override
        public Description[] newArray(int size) {
            return new Description[size];
        }
    };

    public static Description from(String value) {
        return new Description(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Description)) return false;

        Description that = (Description) o;

        return mValue != null ? mValue.equals(that.mValue) : that.mValue == null;

    }

    @Override
    public int hashCode() {
        return mValue != null ? mValue.hashCode() : 0;
    }

    @Override
    public String asString() {
        return mValue;
    }

    @Override
    public String toString() {
        return "Description{" +
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
