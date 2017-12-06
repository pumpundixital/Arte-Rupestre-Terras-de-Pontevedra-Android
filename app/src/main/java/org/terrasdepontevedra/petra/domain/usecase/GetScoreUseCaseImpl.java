package org.terrasdepontevedra.petra.domain.usecase;

import org.terrasdepontevedra.petra.data.PetraRepository;
import org.terrasdepontevedra.petra.data.local.ScoreCollector;
import org.terrasdepontevedra.petra.data.model.place.PlaceDto;
import org.terrasdepontevedra.petra.domain.mapper.PlaceDtoToScore;
import org.terrasdepontevedra.petra.domain.model.ScoreCollection;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseBaseImpl;
import org.terrasdepontevedra.petra.util.interfaces.Action1;

import java.util.List;


public class GetScoreUseCaseImpl
        extends UseCaseBaseImpl<GetScoreUseCase, PetraRepository, String, List<PlaceDto>, ScoreCollection>
        implements GetScoreUseCase {
    private static final int LIMIT = 100;
    private final ScoreCollector mScoreCollector;

    public GetScoreUseCaseImpl(PetraRepository petraRepository, ScoreCollector scoreCollector) {
        super(petraRepository);
        mScoreCollector = scoreCollector;
    }

    @Override
    public void execute(Action1<ScoreCollection> tResultMapSubscriber) {
        super
                .map(PlaceDtoToScore::map)
                .execute(
                        mRepository.getPlaces(mParam, LIMIT),
                        scoreCollection -> {
                            ScoreCollection collection = new ScoreCollection();
                            collection.addAll(mScoreCollector.getQuizScore().asList());
                            collection.addAll(scoreCollection.asList());
                            mScoreCollector.setScore(collection);
                            tResultMapSubscriber.execute(collection);
                        }
                );
    }
}
