package org.terrasdepontevedra.petra.domain.model.base;

import android.os.Parcel;
import android.os.Parcelable;

public class Audio implements Parcelable {
    private final Url mUrl;

    private Audio(Url url) {
        mUrl = url;
    }

    private Audio(Parcel in) {
        mUrl = in.readParcelable(Url.class.getClassLoader());
    }

    public static final Creator<Audio> CREATOR = new Creator<Audio>() {
        @Override
        public Audio createFromParcel(Parcel in) {
            return new Audio(in);
        }

        @Override
        public Audio[] newArray(int size) {
            return new Audio[size];
        }
    };

    private static Audio from(Url url) {
        return new Audio(url);
    }

    public static Audio from(String value) {
        return from(Url.from(value));
    }

    public Url getUrl() {
        return mUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Audio)) return false;

        Audio audio = (Audio) o;

        return mUrl != null ? mUrl.equals(audio.mUrl) : audio.mUrl == null;

    }

    @Override
    public int hashCode() {
        return mUrl != null ? mUrl.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "mUrl=" + mUrl.asString() +
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
