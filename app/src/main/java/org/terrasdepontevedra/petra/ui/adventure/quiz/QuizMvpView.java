package org.terrasdepontevedra.petra.ui.adventure.quiz;

import org.terrasdepontevedra.petra.domain.model.quiz.AnswerCollection;
import org.terrasdepontevedra.petra.ui.base.MvpView;

public interface QuizMvpView extends MvpView {
    void setQuestion(String question);

    void setAnswers(AnswerCollection possibleAnswer);
}
