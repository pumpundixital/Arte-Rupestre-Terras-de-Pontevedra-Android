package org.terrasdepontevedra.petra.ui.adventure.result;

import org.terrasdepontevedra.petra.domain.model.quiz.Quiz;
import org.terrasdepontevedra.petra.ui.base.Presenter;


public interface ResultPresenter extends Presenter<ResultMvpView> {
    void init(Quiz quiz);
}
