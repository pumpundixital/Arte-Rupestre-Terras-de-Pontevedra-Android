package org.terrasdepontevedra.petra.domain.model.base;

import android.os.Parcel;
import android.os.Parcelable;

import org.terrasdepontevedra.petra.util.interfaces.AsString;


public class Synopsis implements AsString, Parcelable {
    public static final Synopsis Empty = Synopsis.from("");
    private final String mValue;

    private Synopsis(String value) {
        mValue = value;
    }

    private Synopsis(Parcel in) {
        mValue = in.readString();
    }

    public static final Creator<Synopsis> CREATOR = new Creator<Synopsis>() {
        @Override
        public Synopsis createFromParcel(Parcel in) {
            return new Synopsis(in);
        }

        @Override
        public Synopsis[] newArray(int size) {
            return new Synopsis[size];
        }
    };

    public static Synopsis from(String value) {
        return new Synopsis(value);
    }

    @Override
    public String asString() {
        return mValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Synopsis)) return false;

        Synopsis synopsis = (Synopsis) o;

        return mValue != null ? mValue.equals(synopsis.mValue) : synopsis.mValue == null;

    }

    @Override
    public int hashCode() {
        return mValue != null ? mValue.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Synopsis{" +
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
