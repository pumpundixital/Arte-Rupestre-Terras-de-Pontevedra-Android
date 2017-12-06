package org.terrasdepontevedra.petra.domain.usecase;

import org.terrasdepontevedra.petra.data.PetraRepository;
import org.terrasdepontevedra.petra.data.model.itinerary.ItineraryDto;
import org.terrasdepontevedra.petra.data.model.place.PlaceDto;
import org.terrasdepontevedra.petra.domain.mapper.ItineraryDtoToItinerary;
import org.terrasdepontevedra.petra.domain.model.ItineraryCollection;
import org.terrasdepontevedra.petra.domain.usecase.base.UseCaseZipBaseImpl;
import org.terrasdepontevedra.petra.util.interfaces.Action1;

import java.util.ArrayList;
import java.util.List;


public class GetItinerariesWithPlaceUseCaseImpl
        extends UseCaseZipBaseImpl<GetItinerariesWithPlaceUseCase, PetraRepository, String, List<ItineraryDto>, List<PlaceDto>, ItineraryCollection>
        implements GetItinerariesWithPlaceUseCase {

    public GetItinerariesWithPlaceUseCaseImpl(PetraRepository petraRepository) {
        super(petraRepository);
    }

    @Override
    public void execute(Action1<ItineraryCollection> tResultMapSubscriber) {
        super
                .map((itineraryDtos, placeDtos) ->
                {
                    ItineraryCollection itineraryCollection = new ItineraryCollection();

                    for (ItineraryDto itineraryDto : itineraryDtos) {
                        List<PlaceDto> placeDtoToItinerary = new ArrayList<>();
                        for (PlaceDto placeDto : placeDtos) {
                            if (placeDto.getJobListingCategory().contains(itineraryDto.getId()))
                                placeDtoToItinerary.add(placeDto);
                        }
                        itineraryCollection.add(ItineraryDtoToItinerary.map(itineraryDto, placeDtoToItinerary));
                    }
                    return itineraryCollection;
                })
                .execute(mRepository.getItineraries(mParams, 100), mRepository.getPlaces(mParams, 100), tResultMapSubscriber);
    }
}
