package org.terrasdepontevedra.petra.ui.adventure.result;

import org.terrasdepontevedra.petra.data.local.ScoreCollector;
import org.terrasdepontevedra.petra.domain.model.quiz.Quiz;
import org.terrasdepontevedra.petra.ui.base.BasePresenter;

import timber.log.Timber;

public class ResultPresenterImpl extends BasePresenter<ResultMvpView> implements ResultPresenter {
    private final ScoreCollector mScoreCollector;

    public ResultPresenterImpl(ScoreCollector scoreCollector) {
        mScoreCollector = scoreCollector;
    }

    @Override
    public void init(Quiz quiz) {
        Timber.i("init quiz");
        mScoreCollector.setQuizScore(quiz);
        mMvpView.showResult(quiz.getTotalCorrectAnswer(), quiz.size());
    }
}
