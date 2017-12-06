package org.terrasdepontevedra.petra.ui.adventure.quiz.pager;

import org.terrasdepontevedra.petra.domain.model.quiz.Quiz;
import org.terrasdepontevedra.petra.ui.base.MvpView;
import org.terrasdepontevedra.petra.ui.base.StateView;

interface QuizPagerMvpView extends MvpView, StateView {
    void loadQuizOnView(Quiz quiz);

    void next();

    void showFinish(Quiz quiz);

    void setFinishTextOnButton();

    void setNextTextOnButton();
}
