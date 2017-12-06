package org.terrasdepontevedra.petra.di.module;

import org.terrasdepontevedra.petra.data.PetraRepository;
import org.terrasdepontevedra.petra.data.local.ScoreCollector;
import org.terrasdepontevedra.petra.domain.usecase.GetCentersUseCase;
import org.terrasdepontevedra.petra.domain.usecase.GetCentersUseCaseImpl;
import org.terrasdepontevedra.petra.domain.usecase.GetItinerariesUseCase;
import org.terrasdepontevedra.petra.domain.usecase.GetItinerariesUseCaseImpl;
import org.terrasdepontevedra.petra.domain.usecase.GetItinerariesWithPlaceUseCase;
import org.terrasdepontevedra.petra.domain.usecase.GetItinerariesWithPlaceUseCaseImpl;
import org.terrasdepontevedra.petra.domain.usecase.GetPlacesByItineraryUseCase;
import org.terrasdepontevedra.petra.domain.usecase.GetPlacesByItineraryUseCaseImpl;
import org.terrasdepontevedra.petra.domain.usecase.GetQuizUseCase;
import org.terrasdepontevedra.petra.domain.usecase.GetQuizUseCaseImpl;
import org.terrasdepontevedra.petra.domain.usecase.GetScoreUseCase;
import org.terrasdepontevedra.petra.domain.usecase.GetScoreUseCaseImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class UseCaseModule {

    @Provides
    GetItinerariesUseCase provideGetItinerariesUseCase(PetraRepository petraRepository) {
        return new GetItinerariesUseCaseImpl(petraRepository);
    }

    @Provides
    GetPlacesByItineraryUseCase provideGetPlacesByItineraryUseCase(PetraRepository petraRepository) {
        return new GetPlacesByItineraryUseCaseImpl(petraRepository);
    }

    @Provides
    GetItinerariesWithPlaceUseCase provideGetItinerariesWithPlaceUseCase(PetraRepository petraRepository) {
        return new GetItinerariesWithPlaceUseCaseImpl(petraRepository);
    }

    @Provides
    GetCentersUseCase provideGetCentersUseCase(PetraRepository petraRepository) {
        return new GetCentersUseCaseImpl(petraRepository);
    }

    @Provides
    GetQuizUseCase provideGetQuizUseCase(PetraRepository petraRepository) {
        return new GetQuizUseCaseImpl(petraRepository);
    }

    @Provides
    GetScoreUseCase provideGetScoreUseCase(PetraRepository petraRepository, ScoreCollector scoreCollector) {
        return new GetScoreUseCaseImpl(petraRepository, scoreCollector);
    }
}
