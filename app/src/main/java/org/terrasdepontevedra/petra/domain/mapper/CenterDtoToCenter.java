package org.terrasdepontevedra.petra.domain.mapper;


import org.terrasdepontevedra.petra.data.model.center.CenterDto;
import org.terrasdepontevedra.petra.domain.model.Center;
import org.terrasdepontevedra.petra.domain.model.CenterCollection;
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

import java.util.List;

public class CenterDtoToCenter {
    public static CenterCollection map(List<CenterDto> centerDto) {
        CenterCollection centerCollection = new CenterCollection();
        for (CenterDto center : centerDto) {
            centerCollection.add(map(center));
        }
        return centerCollection;
    }

    private static Center map(CenterDto centerDto) {
        return new Center(
                Title.from(centerDto.getTitle().getRendered()),
                Description.from(centerDto.getExcerptRendered()),
                Description.from(centerDto.getContent().getRendered()),
                Image.from(centerDto.getBetterFeaturedImageSourceUrl()),
                Phone.from(centerDto.getWpcfTelefonoDeContacto()),
                Email.from(centerDto.getWpcfEmailDeContactoCentroParque()),
                Web.from(centerDto.getWpcfPaginaWebDelCentroOParque()),
                Schedule.from(centerDto.getWpcfHorarioDeAtencionCentroParque()),
                Locale.from(centerDto.getWpcfDireccionPostalDelCentroOParque(), centerDto.getLatitude(), centerDto.getLongitude()),
                ImageCollection.from(centerDto.getWpcfImagenesCentroParque()),
                Audio.from(centerDto.getWpcfAudioguiaDelCentroOParque()),
                Link.from(centerDto.getLink()));
    }
}
