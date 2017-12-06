package org.terrasdepontevedra.petra.domain.model.quiz;

import android.os.Parcel;

import org.terrasdepontevedra.petra.util.CollectionBase;

import java.util.ArrayList;

public class AnswerCollection extends CollectionBase<Answer> {

    public AnswerCollection() {
        super();
    }

    private AnswerCollection(Parcel in) {
        mList = new ArrayList<>();
        in.readList(mList, Answer.class.getClassLoader());
    }

    public static final Creator<AnswerCollection> CREATOR = new Creator<AnswerCollection>() {
        @Override
        public AnswerCollection createFromParcel(Parcel in) {
            return new AnswerCollection(in);
        }

        @Override
        public AnswerCollection[] newArray(int size) {
            return new AnswerCollection[size];
        }
    };

    Answer getAnswerChecked() {
        for (Answer answer : asList()) {
            if (answer.isChecked())
                return answer;
        }

        return null;
    }

    public void deselect() {
        Answer answerChecked = getAnswerChecked();
        if (answerChecked != null)
            answerChecked.setChecked(false);
    }

    public void select(Answer answer) {
        get(indexOf(answer)).setChecked(true);
    }

    boolean hasSomeAnswerSelected() {
        return getAnswerChecked() != null;
    }
}
