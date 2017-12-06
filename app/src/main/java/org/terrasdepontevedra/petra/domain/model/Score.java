package org.terrasdepontevedra.petra.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.terrasdepontevedra.petra.domain.model.base.Title;

public class Score implements Parcelable {
    private final Title mTitle;
    private final boolean mIsComplete;
    private PlaceIdentity mPlaceIdentity;

   public Score(Title title, boolean isComplete) {
        mTitle = title;
        mIsComplete = isComplete;
    }

    public Score(Title title, boolean isComplete, PlaceIdentity placeIdentity) {
        mTitle = title;
        mIsComplete = isComplete;
        mPlaceIdentity = placeIdentity;
    }

    Score(Parcel in) {
        mTitle = in.readParcelable(Title.class.getClassLoader());
        mIsComplete = in.readByte() != 0;
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    public Title getTitle() {
        return mTitle;
    }

    public boolean isComplete() {
        return mIsComplete;
    }

    public PlaceIdentity getPlaceIdentity() {
        return mPlaceIdentity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Score)) return false;

        Score score = (Score) o;

        return getTitle().equals(score.getTitle());
    }

    @Override
    public int hashCode() {
        int result = getTitle().hashCode();
        result = 31 * result + (isComplete() ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Score{" +
                "mTitle=" + mTitle +
                ", mIsComplete=" + mIsComplete +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mTitle, flags);
        dest.writeByte((byte) (mIsComplete ? 1 : 0));
    }
}
