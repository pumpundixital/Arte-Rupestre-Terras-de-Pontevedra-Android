package org.terrasdepontevedra.petra.domain.model.quiz;

import android.os.Parcel;
import android.os.Parcelable;

public class Question implements Parcelable {
    private final String mQuestion;
    private final Answer mCorrectAnswer;
    private final AnswerCollection mPossiblesAnswers;

    public Question(String question, Answer correctAnswer, AnswerCollection possibleAnswer) {
        mQuestion = question;
        mCorrectAnswer = correctAnswer;
        mPossiblesAnswers = possibleAnswer;
    }

    private Question(Parcel in) {
        mQuestion = in.readString();
        mCorrectAnswer = in.readParcelable(Answer.class.getClassLoader());
        mPossiblesAnswers = in.readParcelable(AnswerCollection.class.getClassLoader());
    }

    public static final Creator<Question> CREATOR = new Creator<Question>() {
        @Override
        public Question createFromParcel(Parcel in) {
            return new Question(in);
        }

        @Override
        public Question[] newArray(int size) {
            return new Question[size];
        }
    };

    public String getQuestion() {
        return mQuestion;
    }

    public AnswerCollection getPossiblesAnswers() {
        return mPossiblesAnswers;
    }

    public boolean isCorrect() {
        return mPossiblesAnswers.getAnswerChecked() != null &&
                mPossiblesAnswers.getAnswerChecked().equals(mCorrectAnswer);
    }

    public boolean hasSomeAnswerSelected() {
        return mPossiblesAnswers.hasSomeAnswerSelected();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mQuestion);
        dest.writeParcelable(mCorrectAnswer, flags);
        dest.writeParcelable(mPossiblesAnswers, flags);
    }
}
