package org.terrasdepontevedra.petra.domain.mapper;

import org.terrasdepontevedra.petra.data.model.place.PlaceDto;
import org.terrasdepontevedra.petra.domain.model.ItineraryIdentity;
import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.domain.model.PlaceIdentity;
import org.terrasdepontevedra.petra.domain.model.base.Access;
import org.terrasdepontevedra.petra.domain.model.base.Description;
import org.terrasdepontevedra.petra.domain.model.base.Image;
import org.terrasdepontevedra.petra.domain.model.base.ImageCollection;
import org.terrasdepontevedra.petra.domain.model.base.Locale;
import org.terrasdepontevedra.petra.domain.model.base.Morphology;
import org.terrasdepontevedra.petra.domain.model.base.Site;
import org.terrasdepontevedra.petra.domain.model.base.Synopsis;
import org.terrasdepontevedra.petra.domain.model.base.Title;

import java.util.List;

public class PlacesDtoToPlace {
    public static PlaceCollection map(List<PlaceDto> placeDtos) {
        PlaceCollection placeCollection = new PlaceCollection();
        for (PlaceDto placeDto : placeDtos) {
            placeCollection.add(map(placeDto));
        }
        return placeCollection;
    }

    private static Place map(PlaceDto placeDto) {
        Place place = new Place(
                PlaceIdentity.from(placeDto.getSlug().hashCode()),
                Title.from(placeDto.getTitle().getRendered()),
                ItineraryIdentity.from(placeDto.getJobListingCategory().get(0)),
                Description.from(placeDto.getContent().getRendered()),
                ImageCollection.from(placeDto.getWpcfImagenesAdicionalesPetroglifo()),
                Locale.from(
                        placeDto.getWpcfLocalizacion(),
                        Double.parseDouble(placeDto.getLatitude()),
                        Double.parseDouble(placeDto.getLongitude())
                ),
                Site.from(placeDto.getWpcfEmplazamientoPetroglifo()),
                Morphology.from(placeDto.getWpcfMorfologiaPetroglifo()),
                Access.from(placeDto.getWpcfAccesoAlPetroglifo()),
                Synopsis.from(placeDto.getWpcfDescripcionDeLaPiedraPetroglifo())
        );


        if (placeDto.getBetterFeaturedImage() != null
                && placeDto.getBetterFeaturedImage().getMediaDetails() != null
                && placeDto.getBetterFeaturedImage().getMediaDetails().getSizes() != null
                && placeDto.getBetterFeaturedImage().getMediaDetails().getSizes().getThumbnail() != null) {
            place.setImage(Image.from(placeDto.getBetterFeaturedImage().getMediaDetails().getSizes().getThumbnail().getSourceUrl()));
        }

        return place;
    }
}
