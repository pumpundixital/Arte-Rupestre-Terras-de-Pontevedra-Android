package org.terrasdepontevedra.petra.di.module;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.data.local.LanguageCollector;
import org.terrasdepontevedra.petra.data.local.ScoreCollector;
import org.terrasdepontevedra.petra.di.scopes.ActivityScope;
import org.terrasdepontevedra.petra.di.scopes.FragmentScope;
import org.terrasdepontevedra.petra.domain.usecase.GetCentersUseCase;
import org.terrasdepontevedra.petra.domain.usecase.GetItinerariesUseCase;
import org.terrasdepontevedra.petra.domain.usecase.GetItinerariesWithPlaceUseCase;
import org.terrasdepontevedra.petra.domain.usecase.GetPlacesByItineraryUseCase;
import org.terrasdepontevedra.petra.domain.usecase.GetQuizUseCase;
import org.terrasdepontevedra.petra.domain.usecase.GetScoreUseCase;
import org.terrasdepontevedra.petra.ui.adventure.quiz.QuizPresenter;
import org.terrasdepontevedra.petra.ui.adventure.quiz.QuizPresenterImpl;
import org.terrasdepontevedra.petra.ui.adventure.quiz.pager.QuizPagerPresenter;
import org.terrasdepontevedra.petra.ui.adventure.quiz.pager.QuizPagerPresenterImpl;
import org.terrasdepontevedra.petra.ui.adventure.result.ResultPresenter;
import org.terrasdepontevedra.petra.ui.adventure.result.ResultPresenterImpl;
import org.terrasdepontevedra.petra.ui.adventure.score.ScorePresenter;
import org.terrasdepontevedra.petra.ui.adventure.score.ScorePresenterImpl;
import org.terrasdepontevedra.petra.ui.adventure.yincana.YincanaPresenter;
import org.terrasdepontevedra.petra.ui.adventure.yincana.YincanaPresenterImpl;
import org.terrasdepontevedra.petra.ui.center.detail.CenterDetailPresenter;
import org.terrasdepontevedra.petra.ui.center.detail.CenterDetailPresenterImpl;
import org.terrasdepontevedra.petra.ui.center.list.CenterListPresenter;
import org.terrasdepontevedra.petra.ui.center.list.CenterListPresenterImpl;
import org.terrasdepontevedra.petra.ui.itinerary.ItineraryPresenter;
import org.terrasdepontevedra.petra.ui.itinerary.ItineraryPresenterImpl;
import org.terrasdepontevedra.petra.ui.itinerary.content.ItineraryDetailPresenter;
import org.terrasdepontevedra.petra.ui.itinerary.content.ItineraryDetailPresenterImpl;
import org.terrasdepontevedra.petra.ui.itinerary.list.ItinerariesListPresenter;
import org.terrasdepontevedra.petra.ui.itinerary.list.ItinerariesListPresenterImpl;
import org.terrasdepontevedra.petra.ui.map.itinerary.ItineraryMapPresenter;
import org.terrasdepontevedra.petra.ui.map.itinerary.ItineraryMapPresenterImpl;
import org.terrasdepontevedra.petra.ui.map.place.MapPresenter;
import org.terrasdepontevedra.petra.ui.map.place.MapPresenterImpl;
import org.terrasdepontevedra.petra.ui.place.content.PlaceContentPresenter;
import org.terrasdepontevedra.petra.ui.place.content.PlaceContentPresenterImpl;
import org.terrasdepontevedra.petra.ui.place.detail.PlaceDetailPresenter;
import org.terrasdepontevedra.petra.ui.place.detail.PlaceDetailPresenterImpl;
import org.terrasdepontevedra.petra.util.services.TranslatorService;

import dagger.Module;
import dagger.Provides;

@Module(
        includes = {
                UseCaseModule.class
        }
)
public class PresenterModule {

    @Provides
    @FragmentScope
    ItinerariesListPresenter provideItinerariesListPresenter(GetItinerariesUseCase getItinerariesUseCase, LanguageCollector languageCollector) {
        return new ItinerariesListPresenterImpl(getItinerariesUseCase, languageCollector);
    }

    @Provides
    @ActivityScope
    ItineraryPresenter provideItineraryPresenter(GetPlacesByItineraryUseCase getPlacesByItineraryUseCase, LanguageCollector languageCollector) {
        return new ItineraryPresenterImpl(getPlacesByItineraryUseCase, languageCollector);
    }

    @Provides
    @FragmentScope
    ItineraryDetailPresenter provideItineraryDetailPresenter() {
        return new ItineraryDetailPresenterImpl();
    }

    @Provides
    @FragmentScope
    MapPresenter provideMapPresenter(GetItinerariesWithPlaceUseCase getItinerariesWithPlaceUseCase,
                                     EventBus eventBus, LanguageCollector languageCollector, TranslatorService translatorService) {
        return new MapPresenterImpl(getItinerariesWithPlaceUseCase, eventBus, languageCollector, translatorService);
    }

    @Provides
    @FragmentScope
    PlaceDetailPresenter providePlaceDetailPresenter(EventBus eventBus) {
        return new PlaceDetailPresenterImpl(eventBus);
    }

    @Provides
    @FragmentScope
    PlaceContentPresenter providePlaceContentPresenter() {
        return new PlaceContentPresenterImpl();
    }

    @Provides
    @FragmentScope
    CenterListPresenter provideCenterListPresenter(GetCentersUseCase getCentersUseCase, LanguageCollector languageCollector) {
        return new CenterListPresenterImpl(getCentersUseCase, languageCollector);
    }

    @Provides
    @ActivityScope
    CenterDetailPresenter provideCenterDetailPresenter() {
        return new CenterDetailPresenterImpl();
    }

    @Provides
    @FragmentScope
    QuizPresenter provideQuizPresenter() {
        return new QuizPresenterImpl();
    }

    @Provides
    @FragmentScope
    QuizPagerPresenter provideQuizPagerPresenter(GetQuizUseCase getQuizUseCase) {
        return new QuizPagerPresenterImpl(getQuizUseCase);
    }

    @Provides
    @FragmentScope
    ResultPresenter provideResultPresenter(ScoreCollector scoreCollector) {
        return new ResultPresenterImpl(scoreCollector);
    }

    @Provides
    @ActivityScope
    ScorePresenter provideScorePresenter(ScoreCollector scoreCollector,
                                         LanguageCollector languageCollector,
                                         GetScoreUseCase getScoreUseCase) {
        return new ScorePresenterImpl(scoreCollector, languageCollector, getScoreUseCase);
    }

    @Provides
    @FragmentScope
    YincanaPresenter provideYincanaPresenter(GetItinerariesWithPlaceUseCase getItinerariesWithPlaceUseCase,
                                             EventBus eventBus, ScoreCollector scoreCollector,
                                             LanguageCollector languageCollector, TranslatorService translatorService) {
        return new YincanaPresenterImpl(
                getItinerariesWithPlaceUseCase, eventBus, languageCollector, scoreCollector, translatorService
        );
    }

    @Provides
    @FragmentScope
    ItineraryMapPresenter provideItineraryMapPresenter() {
        return new ItineraryMapPresenterImpl();
    }
}
