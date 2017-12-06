package org.terrasdepontevedra.petra.domain.usecase;

import org.terrasdepontevedra.petra.data.PetraRepository;
import org.terrasdepontevedra.petra.data.model.place.PlaceDto;
import org.terrasdepontevedra.petra.domain.mapper.PlacesDtoToPlace;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.domain.params.GetPlacesByItineraryParam;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseBaseImpl;
import org.terrasdepontevedra.petra.util.interfaces.Action1;

import java.util.List;

public class GetPlacesByItineraryUseCaseImpl
        extends UseCaseBaseImpl<GetPlacesByItineraryUseCase, PetraRepository, GetPlacesByItineraryParam, List<PlaceDto>, PlaceCollection>
        implements GetPlacesByItineraryUseCase {

    private static final int LIMIT = 100;

    public GetPlacesByItineraryUseCaseImpl(PetraRepository petraRepository) {
        super(petraRepository);
    }

    @Override
    public void execute(Action1<PlaceCollection> tResultMapSubscriber) {
        super
                .map(PlacesDtoToPlace::map)
                .execute(
                        mRepository.getPlacesByItinerary(
                                mParam.getLanguage(),
                                mParam.getItineraryIdentity().asInt(),
                                LIMIT
                        ),
                        tResultMapSubscriber
                );
    }


}
