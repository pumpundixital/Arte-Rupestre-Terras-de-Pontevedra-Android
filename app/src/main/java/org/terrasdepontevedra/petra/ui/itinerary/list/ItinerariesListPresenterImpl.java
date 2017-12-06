package org.terrasdepontevedra.petra.ui.itinerary.list;

import org.terrasdepontevedra.petra.data.local.LanguageCollector;
import org.terrasdepontevedra.petra.domain.usecase.GetItinerariesUseCase;
import org.terrasdepontevedra.petra.ui.base.BasePresenter;

public class ItinerariesListPresenterImpl
        extends BasePresenter<ItinerariesListMvpView>
        implements ItinerariesListPresenter {

    private final GetItinerariesUseCase mGetItinerariesUseCase;
    private final LanguageCollector mLanguageCollector;

    public ItinerariesListPresenterImpl(GetItinerariesUseCase getItinerariesUseCase, LanguageCollector languageCollector) {
        mGetItinerariesUseCase = getItinerariesUseCase;
        mLanguageCollector = languageCollector;
    }

    @Override
    public void init() {
        mGetItinerariesUseCase
                .setPreAndPostActions(() -> mMvpView.showLoading(), () -> {
                    if (mMvpView != null)
                        mMvpView.showMain();
                })
                .setErrorAction(throwable -> mMvpView.showError(throwable))
                .setParams(mLanguageCollector.getLanguage())
                .execute(itineraryCollection -> {
                            if (mMvpView != null)
                                mMvpView.loadItineraryCollectionOnView(itineraryCollection);
                        }
                );

    }
}
