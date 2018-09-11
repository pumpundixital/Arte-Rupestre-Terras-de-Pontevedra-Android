package org.terrasdepontevedra.petra.data.walk;



import org.terrasdepontevedra.petra.domain.model.walk.ItineraryDto;
import org.terrasdepontevedra.petra.domain.model.walk.PlaceDto;
import org.terrasdepontevedra.petra.domain.model.walk.RelationDto;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WalkApi {

    public static String API_URL = "http://app.terrasdepontevedra.org";

    @GET("/{language}/wp-json/wp/v2/itinerario")
    Flowable<List<ItineraryDto>> loadItineraryBySlug(@Path("language") String language, @Query("slug") String link);

    @GET("/{language}/wp-json/wp/v2/itinerario/{id}/")
    Flowable<ItineraryDto> loadItinerary(@Path("language") String language, @Path("id") int itineraryId);

    @GET("/wp-json/wp/v2/itinerario/{id}/")
    Flowable<ItineraryDto> loadItineraryEs(@Path("id") int itineraryId);

    @GET("/{language}/wp-json/wp/v2/lugar")
    Flowable<List<PlaceDto>> loadPlacesFromIds(@Path("language") String language, @Query("include") String placesIds);

    @GET("/wp-json/wp/v2/lugar")
    Flowable<List<PlaceDto>> loadPlacesFromIdsEs( @Query("include") String placesIds);

    @GET("/{language}/wp-json/pumpun/v1/relacion/{id}")
    Flowable<List<RelationDto>> loadPlacesRelationsByCategoryItineraryId(@Path("language") String language, @Path("id") int categoryItineraryId, @Query("per_page") int limit);

    @GET("/wp-json/pumpun/v1/relacion/{id}")
    Flowable<List<RelationDto>> loadPlacesRelationsByCategoryItineraryIdEs(@Path("id") int categoryItineraryId, @Query("per_page") int limit);

    @GET("/{language}/wp-json/wp/v2/itinerario")
    Flowable<List<ItineraryDto>> loadWalks(@Path("language") String language, @Query("page") int page);

    @GET("/wp-json/wp/v2/itinerario")
    Flowable<List<ItineraryDto>> loadWalksEs();


}
