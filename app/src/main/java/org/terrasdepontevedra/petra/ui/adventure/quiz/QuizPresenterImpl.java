package org.terrasdepontevedra.petra.ui.adventure.quiz;

import org.terrasdepontevedra.petra.domain.model.quiz.Question;
import org.terrasdepontevedra.petra.ui.base.BasePresenter;

public class QuizPresenterImpl extends BasePresenter<QuizMvpView> implements QuizPresenter {
    public QuizPresenterImpl() {
    }

    @Override
    public void init(Question question) {
        mMvpView.setQuestion(question.getQuestion());
        mMvpView.setAnswers(question.getPossiblesAnswers());
    }
}
