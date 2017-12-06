package org.terrasdepontevedra.petra.domain.usecase;

import org.terrasdepontevedra.petra.data.model.itinerary.ItineraryDto;
import org.terrasdepontevedra.petra.domain.model.ItineraryCollection;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseBase;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseExecute;

import java.util.List;

public interface GetItinerariesUseCase
        extends
        UseCaseBase<GetItinerariesUseCase, String, List<ItineraryDto>, ItineraryCollection>,
        UseCaseExecute<ItineraryCollection> {
}
