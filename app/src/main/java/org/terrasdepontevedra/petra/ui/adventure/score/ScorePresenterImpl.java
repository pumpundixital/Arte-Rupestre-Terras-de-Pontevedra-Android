package org.terrasdepontevedra.petra.ui.adventure.score;


import org.terrasdepontevedra.petra.data.local.LanguageCollector;
import org.terrasdepontevedra.petra.data.local.ScoreCollector;
import org.terrasdepontevedra.petra.domain.usecase.GetScoreUseCase;
import org.terrasdepontevedra.petra.ui.base.BasePresenter;

public class ScorePresenterImpl extends BasePresenter<ScoreMvpView> implements ScorePresenter {

    private final ScoreCollector mScoreCollector;
    private final LanguageCollector mLanguageCollector;
    private final GetScoreUseCase mGetScoreUseCase;

    public ScorePresenterImpl(ScoreCollector scoreCollector, LanguageCollector languageCollector,
                              GetScoreUseCase getScoreUseCase) {
        mScoreCollector = scoreCollector;
        mLanguageCollector = languageCollector;
        mGetScoreUseCase = getScoreUseCase;
    }

    @Override
    public void init() {
        mMvpView.showTitle();
        if (!mScoreCollector.hasScoreSaved()) {
            generateScore();
        } else {
            mMvpView.loadScoreOnView(mScoreCollector.getScoreCollection());
        }
    }

    private void generateScore() {
        mGetScoreUseCase
                .setParams(mLanguageCollector.getLanguage())
                .setPreAndPostActions(() -> mMvpView.showLoading(), () -> mMvpView.showMain())
                .setErrorAction(throwable -> mMvpView.showError(throwable))
                .execute(scoreCollection -> mMvpView.loadScoreOnView(scoreCollection));
    }
}
