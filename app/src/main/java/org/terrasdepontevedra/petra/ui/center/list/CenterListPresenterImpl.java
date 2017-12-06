package org.terrasdepontevedra.petra.ui.center.list;

import org.terrasdepontevedra.petra.data.local.LanguageCollector;
import org.terrasdepontevedra.petra.domain.usecase.GetCentersUseCase;
import org.terrasdepontevedra.petra.ui.base.BasePresenter;

public class CenterListPresenterImpl
        extends BasePresenter<CenterListMvpView>
        implements CenterListPresenter {

    private final GetCentersUseCase mGetCentersUseCase;
    private final LanguageCollector mLanguageCollector;

    public CenterListPresenterImpl(GetCentersUseCase getCentersUseCase, LanguageCollector languageCollector) {
        mGetCentersUseCase = getCentersUseCase;
        mLanguageCollector = languageCollector;
    }

    @Override
    public void init() {
        mGetCentersUseCase
                .setParams(mLanguageCollector.getLanguage())
                .setPreAndPostActions(() -> mMvpView.showLoading(), () -> mMvpView.showMain())
                .setErrorAction(throwable -> mMvpView.showError(throwable))
                .execute(centerCollection -> mMvpView.loadCenterCollectionOnView(centerCollection));
    }
}
