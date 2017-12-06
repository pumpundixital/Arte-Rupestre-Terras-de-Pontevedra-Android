package org.terrasdepontevedra.petra.domain.model.quiz;

import android.os.Parcel;

import org.terrasdepontevedra.petra.domain.model.base.Title;
import org.terrasdepontevedra.petra.util.CollectionBase;

import java.util.ArrayList;

public class Quiz extends CollectionBase<Question> {
    private Title mTitle;

    public Quiz(Title title) {
        mTitle = title;
    }

    public Quiz() {
        super();
    }

    private Quiz(Parcel in) {
        mTitle = in.readParcelable(Title.class.getClassLoader());
        mList = new ArrayList<>();
        in.readList(mList, Question.class.getClassLoader());
    }

    public static final Creator<Quiz> CREATOR = new Creator<Quiz>() {
        @Override
        public Quiz createFromParcel(Parcel in) {
            return new Quiz(in);
        }

        @Override
        public Quiz[] newArray(int size) {
            return new Quiz[size];
        }
    };


    public int getTotalCorrectAnswer() {
        int total = 0;

        for (Question question : asList()) {
            if (question.isCorrect())
                total++;
        }

        return total;
    }

    public Title getTitle() {
        return mTitle;
    }

    public boolean isAllCorrect() {
        return getTotalCorrectAnswer() == size();
    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mTitle, flags);
        super.writeToParcel(dest, flags);
    }
}
