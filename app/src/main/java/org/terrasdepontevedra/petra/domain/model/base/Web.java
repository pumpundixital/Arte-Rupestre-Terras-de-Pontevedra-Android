package org.terrasdepontevedra.petra.domain.model.base;

import android.os.Parcel;
import android.os.Parcelable;

import org.terrasdepontevedra.petra.util.interfaces.AsString;

public class Web implements AsString, Parcelable {
    private final Url mUrl;

    private Web(Url url) {
        mUrl = url;
    }

    private Web(Parcel in) {
        mUrl = in.readParcelable(Url.class.getClassLoader());
    }

    public static final Creator<Web> CREATOR = new Creator<Web>() {
        @Override
        public Web createFromParcel(Parcel in) {
            return new Web(in);
        }

        @Override
        public Web[] newArray(int size) {
            return new Web[size];
        }
    };

    public static Web from(String value){
        return new Web(Url.from(value));
    }

    @Override
    public String asString() {
        return mUrl.asString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Web)) return false;

        Web web = (Web) o;

        return mUrl != null ? mUrl.equals(web.mUrl) : web.mUrl == null;

    }

    @Override
    public int hashCode() {
        return mUrl != null ? mUrl.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Web{" +
                "mUrl=" + mUrl +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mUrl, flags);
    }
}
