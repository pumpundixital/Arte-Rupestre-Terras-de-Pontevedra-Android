package org.terrasdepontevedra.petra.data;

import org.terrasdepontevedra.petra.data.model.center.CenterDto;
import org.terrasdepontevedra.petra.data.model.itinerary.ItineraryDto;
import org.terrasdepontevedra.petra.data.model.place.PlaceDto;
import org.terrasdepontevedra.petra.data.model.quiz.QuestionDto;

import java.util.List;

import io.reactivex.Observable;

public interface PetraRepository {
    Observable<List<ItineraryDto>> getItineraries(String language, int limit);

    Observable<List<PlaceDto>> getPlacesByItinerary(String language, Integer itineraryId, int limit);

    Observable<List<PlaceDto>> getPlaces(String language, int limit);

    Observable<List<CenterDto>> getCenter(String language, int limit);

    Observable<List<QuestionDto>> getQuiz(int param);
}
