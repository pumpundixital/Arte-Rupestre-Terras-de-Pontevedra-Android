package org.terrasdepontevedra.petra.ui.adventure.quiz.pager;

import org.terrasdepontevedra.petra.domain.model.quiz.Quiz;
import org.terrasdepontevedra.petra.domain.params.GetQuizParams;
import org.terrasdepontevedra.petra.domain.usecase.GetQuizUseCase;
import org.terrasdepontevedra.petra.ui.base.BasePresenter;

public class QuizPagerPresenterImpl
        extends BasePresenter<QuizPagerMvpView>
        implements QuizPagerPresenter {

    private final GetQuizUseCase mGetQuizUseCase;
    private Quiz mQuiz;

    public QuizPagerPresenterImpl(GetQuizUseCase getQuizUseCase) {
        mGetQuizUseCase = getQuizUseCase;
    }

    @Override
    public void init(Integer id, String name) {
        mGetQuizUseCase
                .setPreAndPostActions(() -> mMvpView.showLoading(), () -> mMvpView.showMain())
                .setErrorAction(throwable -> mMvpView.showError(throwable))
                .setParams(new GetQuizParams(id, name))
                .execute(quiz -> {
                    mQuiz = quiz;
                    mMvpView.loadQuizOnView(quiz);
                });
    }

    @Override
    public void onNextItem(int currentItemPosition) {
        if (currentItemPosition == mQuiz.size() - 1)
            mMvpView.showFinish(mQuiz);
        else {
            if (currentItemPosition == mQuiz.size() - 2)
                mMvpView.setFinishTextOnButton();
            else
                mMvpView.setNextTextOnButton();
            mMvpView.next();
        }
    }
}
