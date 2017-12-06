package org.terrasdepontevedra.petra.domain.mapper;

import org.terrasdepontevedra.petra.data.model.itinerary.ItineraryDto;
import org.terrasdepontevedra.petra.data.model.place.PlaceDto;
import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.model.ItineraryCollection;
import org.terrasdepontevedra.petra.domain.model.ItineraryIdentity;
import org.terrasdepontevedra.petra.domain.model.base.Audio;
import org.terrasdepontevedra.petra.domain.model.base.Description;
import org.terrasdepontevedra.petra.domain.model.base.Email;
import org.terrasdepontevedra.petra.domain.model.base.Image;
import org.terrasdepontevedra.petra.domain.model.base.ImageCollection;
import org.terrasdepontevedra.petra.domain.model.base.Link;
import org.terrasdepontevedra.petra.domain.model.base.Locale;
import org.terrasdepontevedra.petra.domain.model.base.Phone;
import org.terrasdepontevedra.petra.domain.model.base.Schedule;
import org.terrasdepontevedra.petra.domain.model.base.Title;
import org.terrasdepontevedra.petra.domain.model.base.Web;

import java.util.ArrayList;
import java.util.List;

public class ItineraryDtoToItinerary {
    public static ItineraryCollection map(List<ItineraryDto> itineraryDtos) {
        ItineraryCollection itineraryCollection = new ItineraryCollection();
        for (ItineraryDto itineraryDto : itineraryDtos) {
            itineraryCollection.add(map(itineraryDto));
        }
        return itineraryCollection;
    }

    private static Itinerary map(ItineraryDto itineraryDto) {
        return map(itineraryDto, new ArrayList<>());
    }


    public static Itinerary map(ItineraryDto itineraryDto, List<PlaceDto> placeDtos) {
        return new Itinerary(
                ItineraryIdentity.from(itineraryDto.getId()),
                Title.from(itineraryDto.getName()),
                Description.from(itineraryDto.getDescription()),
                Description.from(itineraryDto.getWpcfDescripcionLargaDelCentroOArea()),
                Image.from(itineraryDto.getWpcfImagenCentro()),
                ImageCollection.from(itineraryDto.getWpcfGaleriaFotosCentro()),
                PlacesDtoToPlace.map(placeDtos),
                Locale.from(
                        itineraryDto.getWpcfLocalizacionDelCentroOArea(),
                        Double.parseDouble(itineraryDto.getLatitude()),
                        Double.parseDouble(itineraryDto.getLongitude())
                ),
                Audio.from(itineraryDto.getWpcfAudioguiaDelCentroOArea()),
                Phone.from(itineraryDto.getWpcfTelefonoDeContactoCentro()),
                Email.from(itineraryDto.getWpcfEmailDeContactoCentro()),
                Web.from(itineraryDto.getWpcfPaginaWebDelCentroOArea()),
                Schedule.from(itineraryDto.getWpcfHorarioDeAtencionCentro()),
                Link.from(itineraryDto.getLink()));
    }
}
