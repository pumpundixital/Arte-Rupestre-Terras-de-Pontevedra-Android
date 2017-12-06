package org.terrasdepontevedra.petra.domain.usecase;


import org.terrasdepontevedra.petra.data.PetraRepository;
import org.terrasdepontevedra.petra.data.model.itinerary.ItineraryDto;
import org.terrasdepontevedra.petra.domain.mapper.ItineraryDtoToItinerary;
import org.terrasdepontevedra.petra.domain.model.ItineraryCollection;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseBaseImpl;
import org.terrasdepontevedra.petra.util.interfaces.Action1;

import java.util.List;

public class GetItinerariesUseCaseImpl
        extends UseCaseBaseImpl<GetItinerariesUseCase, PetraRepository, String, List<ItineraryDto>, ItineraryCollection>
        implements GetItinerariesUseCase {

    public GetItinerariesUseCaseImpl(PetraRepository petraRepository) {
        super(petraRepository);
    }


    @Override
    public void execute(Action1<ItineraryCollection> itineraryCollectionAction) {
        super
                .map(ItineraryDtoToItinerary::map)
                .execute(mRepository.getItineraries(mParam, 100), itineraryCollectionAction);
    }
}
