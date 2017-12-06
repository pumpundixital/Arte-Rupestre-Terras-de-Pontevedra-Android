package org.terrasdepontevedra.petra.domain.usecase;


import org.terrasdepontevedra.petra.data.model.itinerary.ItineraryDto;
import org.terrasdepontevedra.petra.data.model.place.PlaceDto;
import org.terrasdepontevedra.petra.domain.model.ItineraryCollection;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseBaseZip;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseExecute;

import java.util.List;

public interface GetItinerariesWithPlaceUseCase
        extends
        UseCaseBaseZip<GetItinerariesWithPlaceUseCase, String, List<ItineraryDto>, List<PlaceDto>, ItineraryCollection>,
        UseCaseExecute<ItineraryCollection> {
}
