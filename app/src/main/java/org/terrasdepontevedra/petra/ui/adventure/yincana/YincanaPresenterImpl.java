package org.terrasdepontevedra.petra.ui.adventure.yincana;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.data.local.LanguageCollector;
import org.terrasdepontevedra.petra.data.local.ScoreCollector;
import org.terrasdepontevedra.petra.domain.model.PlaceIdentity;
import org.terrasdepontevedra.petra.domain.model.Score;
import org.terrasdepontevedra.petra.domain.model.ScoreCollection;
import org.terrasdepontevedra.petra.domain.usecase.GetItinerariesWithPlaceUseCase;
import org.terrasdepontevedra.petra.ui.map.place.MapPresenterImpl;
import org.terrasdepontevedra.petra.util.services.TranslatorService;

public class YincanaPresenterImpl extends MapPresenterImpl implements YincanaPresenter {


    private final ScoreCollector mScoreCollector;

    public YincanaPresenterImpl(GetItinerariesWithPlaceUseCase getItinerariesWithPlaceUseCase,
                                EventBus eventBus, LanguageCollector languageCollector,
                                ScoreCollector scoreCollector, TranslatorService translatorService) {
        super(getItinerariesWithPlaceUseCase, eventBus, languageCollector, translatorService);
        mScoreCollector = scoreCollector;

    }


    @Override
    public boolean checkForAchievement(int id) {
        if (mScoreCollector.hasScoreSaved()) {
            ScoreCollection scoreCollection = mScoreCollector.getScoreCollection();
            for (Score score : scoreCollection.asList()) {
                if (score.getPlaceIdentity() != null && score.getPlaceIdentity().asInt() == id) {
                    Score geoScore = new Score(score.getTitle(), true, PlaceIdentity.from(id));
                    scoreCollection.set(scoreCollection.indexOf(score), geoScore);
                    mScoreCollector.setScore(scoreCollection);
                    return true;
                }
            }
        }
        return false;
    }


}
