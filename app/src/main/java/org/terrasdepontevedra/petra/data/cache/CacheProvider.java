package org.terrasdepontevedra.petra.data.cache;


import org.terrasdepontevedra.petra.data.model.center.CenterDto;
import org.terrasdepontevedra.petra.data.model.itinerary.ItineraryDto;
import org.terrasdepontevedra.petra.data.model.place.PlaceDto;

import java.util.List;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.ProviderKey;

public interface CacheProvider {

    @ProviderKey("itineraries")
    Observable<List<ItineraryDto>> getItineraries(Observable<List<ItineraryDto>> itineraries, DynamicKey dynamicKey);

    @ProviderKey("places_by_itinerary")
    Observable<List<PlaceDto>> getPlacesByItinerary(Observable<List<PlaceDto>> placesByItinerary, DynamicKey dynamicKey);

    @ProviderKey("places")
    Observable<List<PlaceDto>> getPlaces(Observable<List<PlaceDto>> places, DynamicKey dynamicKey);

    @ProviderKey("centers")
    Observable<List<CenterDto>> getCenters(Observable<List<CenterDto>> centers, DynamicKey dynamicKey);
}
