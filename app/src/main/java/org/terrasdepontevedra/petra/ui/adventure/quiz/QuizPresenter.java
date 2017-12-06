package org.terrasdepontevedra.petra.ui.adventure.quiz;

import org.terrasdepontevedra.petra.domain.model.quiz.Question;
import org.terrasdepontevedra.petra.ui.base.Presenter;


public interface QuizPresenter extends Presenter<QuizMvpView> {
    void init(Question question);
}
