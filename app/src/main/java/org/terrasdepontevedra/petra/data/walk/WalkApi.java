package org.terrasdepontevedra.petra.data.walk;



import org.terrasdepontevedra.petra.domain.model.walk.ItineraryDto;
import org.terrasdepontevedra.petra.domain.model.walk.PlaceDto;
import org.terrasdepontevedra.petra.domain.model.walk.RelationDto;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WalkApi {

    public static String API_URL = "http://app.terrasdepontevedra.org";

    @GET("/{language}/wp-json/wp/v2/itinerario")
    Flowable<List<ItineraryDto>> loadItineraryBySlug(@Path("language") String language, @Query("slug") String link);


    @GET("/{language}/wp-json/wp/v2/itinerario/{id}/")
    Flowable<ItineraryDto> loadItinerary(@Path("id") int itineraryId);

    @GET("relacion")
    Flowable<List<RelationDto>> loadPlacesRelationsByCategoryItineraryId(@Query("filter[meta_query][0][key]") String belongs, @Query("filter[meta_query][0][value]") int categoryItineraryId, @Query("per_page") int limit);

    @GET("lugar")
    Flowable<List<PlaceDto>> loadPlacesFromIds(@Query("include") String placesIds);

}
