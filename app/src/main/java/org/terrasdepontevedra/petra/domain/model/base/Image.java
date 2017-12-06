package org.terrasdepontevedra.petra.domain.model.base;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {
    private final Url mUrl;

    private Image(Url url) {
        mUrl = url;
    }

    protected Image(Parcel in) {
        mUrl = in.readParcelable(Url.class.getClassLoader());
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    public static Image from(Url url) {
        return new Image(url);
    }

    public static Image from(String value) {
        return new Image(Url.from(value));
    }

    public Url getUrl() {
        return mUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Image)) return false;

        Image image = (Image) o;

        return mUrl != null ? mUrl.equals(image.mUrl) : image.mUrl == null;

    }

    @Override
    public int hashCode() {
        return mUrl != null ? mUrl.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Image{" +
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
