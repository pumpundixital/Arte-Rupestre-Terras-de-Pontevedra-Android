package org.terrasdepontevedra.petra.domain.usecase;

import org.terrasdepontevedra.petra.data.model.place.PlaceDto;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.domain.params.GetPlacesByItineraryParam;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseBase;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseExecute;

import java.util.List;

public interface GetPlacesByItineraryUseCase
        extends
        UseCaseBase<GetPlacesByItineraryUseCase, GetPlacesByItineraryParam, List<PlaceDto>, PlaceCollection>,
        UseCaseExecute<PlaceCollection> {
}
