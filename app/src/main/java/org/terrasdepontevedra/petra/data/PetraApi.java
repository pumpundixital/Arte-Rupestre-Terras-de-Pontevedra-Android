package org.terrasdepontevedra.petra.data;


import org.terrasdepontevedra.petra.data.model.center.CenterDto;
import org.terrasdepontevedra.petra.data.model.itinerary.ItineraryDto;
import org.terrasdepontevedra.petra.data.model.place.PlaceDto;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PetraApi {
    String API_URL = "http://app.terrasdepontevedra.org";

    @GET("/{language}/wp-json/wp/v2/petroglifos")
    Observable<List<PlaceDto>> getPlacesByItinerary(@Path("language") String language, @Query("job_listing_category") Integer itineraryId, @Query("per_page") int limit);

    @GET("/{language}/wp-json/wp/v2/petroglifos")
    Observable<List<PlaceDto>> getPlaces(@Path("language") String language, @Query("per_page") int limit);

    @GET("/{language}/wp-json/wp/v2/job_listing_category")
    Observable<List<ItineraryDto>> getItineraries(@Path("language") String language, @Query("per_page") int limit);

    @GET("/{language}/wp-json/wp/v2/centro")
    Observable<List<CenterDto>> getCenters(@Path("language") String language, @Query("per_page") int limit);
}
