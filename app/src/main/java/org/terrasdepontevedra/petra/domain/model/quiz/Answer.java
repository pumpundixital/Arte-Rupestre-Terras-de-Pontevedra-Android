package org.terrasdepontevedra.petra.domain.model.quiz;

import android.os.Parcel;
import android.os.Parcelable;

import org.terrasdepontevedra.petra.util.interfaces.AsString;

public class Answer implements AsString, Parcelable {
    private final String mValue;
    private boolean isChecked;

    private Answer(String value) {
        mValue = value;
    }

    private Answer(Parcel in) {
        mValue = in.readString();
        isChecked = in.readByte() != 0;
    }

    public static final Creator<Answer> CREATOR = new Creator<Answer>() {
        @Override
        public Answer createFromParcel(Parcel in) {
            return new Answer(in);
        }

        @Override
        public Answer[] newArray(int size) {
            return new Answer[size];
        }
    };

    public static Answer from(String value) {
        return new Answer(value);
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String asString() {
        if (mValue == null)
            return "";
        return mValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Answer)) return false;

        Answer answer = (Answer) o;

        return mValue != null ? mValue.equals(answer.mValue) : answer.mValue == null;

    }

    @Override
    public int hashCode() {
        return mValue != null ? mValue.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "AnswerDto{" +
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
        dest.writeByte((byte) (isChecked ? 1 : 0));
    }
}
