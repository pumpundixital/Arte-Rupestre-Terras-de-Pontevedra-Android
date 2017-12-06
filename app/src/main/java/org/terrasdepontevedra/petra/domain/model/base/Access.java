package org.terrasdepontevedra.petra.domain.model.base;

import android.os.Parcel;
import android.os.Parcelable;

import org.terrasdepontevedra.petra.util.interfaces.AsString;


public class Access implements AsString, Parcelable {
    public static final Access Empty = Access.from("");
    private final String mValue;

    private Access(String value) {
        mValue = value;
    }

    private Access(Parcel in) {
        mValue = in.readString();
    }

    public static final Creator<Access> CREATOR = new Creator<Access>() {
        @Override
        public Access createFromParcel(Parcel in) {
            return new Access(in);
        }

        @Override
        public Access[] newArray(int size) {
            return new Access[size];
        }
    };

    public static Access from(String value) {
        return new Access(value);
    }

    @Override
    public String asString() {
        return mValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Access)) return false;

        Access email = (Access) o;

        return mValue != null ? mValue.equals(email.mValue) : email.mValue == null;

    }

    @Override
    public int hashCode() {
        return mValue != null ? mValue.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Access{" +
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
