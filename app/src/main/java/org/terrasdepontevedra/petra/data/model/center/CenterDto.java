package org.terrasdepontevedra.petra.data.model.center;

import com.google.gson.annotations.SerializedName;

import org.terrasdepontevedra.petra.data.model.base.image.BetterFeaturedImageDto;
import org.terrasdepontevedra.petra.data.model.place.ContentDto;
import org.terrasdepontevedra.petra.data.model.place.TitleDto;
import org.terrasdepontevedra.petra.util.extensions.CollectionExtension;
import org.terrasdepontevedra.petra.util.extensions.StringExtensions;

import java.util.List;

public class CenterDto {
    @SerializedName("id")
    private final int id;
    @SerializedName("modified")
    private final String modified;
    @SerializedName("link")
    private final String link;
    @SerializedName("title")
    private final TitleDto title;
    @SerializedName("content")
    private final ContentDto content;
    @SerializedName("excerpt")
    private final ExcerptDto excerptDto;
    @SerializedName("better_featured_image")
    private final BetterFeaturedImageDto betterFeaturedImage;
    @SerializedName("wpcf-imagenes-centro-parque")
    private final List<String> wpcfImagenesCentroParque;
    @SerializedName("wpcf-telefono-de-contacto")
    private final List<String> wpcfTelefonoDeContacto;
    @SerializedName("wpcf-email-de-contacto-centro-parque")
    private final List<String> wpcfEmailDeContactoCentroParque;
    @SerializedName("wpcf-pagina-web-del-centro-o-parque")
    private final List<String> wpcfPaginaWebDelCentroOParque;
    @SerializedName("wpcf-horario-de-atencion-centro-parque")
    private final List<String> wpcfHorarioDeAtencionCentroParque;
    @SerializedName("wpcf-direccion-postal-del-centro-o-parque")
    private final List<String> wpcfDireccionPostalDelCentroOParque;
    @SerializedName("wpcf-audioguia-del-centro-o-parque")
    private final List<String> wpcfAudioguiaDelCentroOParque;
    @SerializedName("latitude")
    private final List<String> mLatitude;
    @SerializedName("longitude")
    private final List<String> mLongitude;


    public CenterDto(int id, String modified,
                     String link, TitleDto title,
                     ContentDto content, ExcerptDto excerptDto,
                     BetterFeaturedImageDto betterFeaturedImage, List<String> wpcfImagenesCentroParque,
                     List<String> wpcfTelefonoDeContacto, List<String> wpcfEmailDeContactoCentroParque,
                     List<String> wpcfPaginaWebDelCentroOParque,
                     List<String> wpcfHorarioDeAtencionCentroParque,
                     List<String> wpcfDireccionPostalDelCentroOParque,
                     List<String> wpcfAudioguiaDelCentroOParque, List<String> latitude, List<String> longitude) {
        this.id = id;
        this.modified = modified;
        this.link = link;
        this.title = title;
        this.content = content;
        this.excerptDto = excerptDto;
        this.betterFeaturedImage = betterFeaturedImage;
        this.wpcfImagenesCentroParque = wpcfImagenesCentroParque;
        this.wpcfTelefonoDeContacto = wpcfTelefonoDeContacto;
        this.wpcfEmailDeContactoCentroParque = wpcfEmailDeContactoCentroParque;
        this.wpcfPaginaWebDelCentroOParque = wpcfPaginaWebDelCentroOParque;
        this.wpcfHorarioDeAtencionCentroParque = wpcfHorarioDeAtencionCentroParque;
        this.wpcfDireccionPostalDelCentroOParque = wpcfDireccionPostalDelCentroOParque;
        this.wpcfAudioguiaDelCentroOParque = wpcfAudioguiaDelCentroOParque;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public int getId() {
        return id;
    }

    public String getModified() {
        return modified;
    }


    public String getLink() {
        return link;
    }

    public TitleDto getTitle() {
        return title;
    }

    public ContentDto getContent() {
        return content;
    }

    private ExcerptDto getExcerptDto() {
        return excerptDto;
    }

    private BetterFeaturedImageDto getBetterFeaturedImage() {
        return betterFeaturedImage;
    }

    public List<String> getWpcfImagenesCentroParque() {
        return wpcfImagenesCentroParque;
    }

    public String getWpcfTelefonoDeContacto() {
        if (!CollectionExtension.isNullOrEmpty(wpcfTelefonoDeContacto))
            return wpcfTelefonoDeContacto.get(0);
        return "";
    }

    public String getWpcfEmailDeContactoCentroParque() {
        if (!CollectionExtension.isNullOrEmpty(wpcfEmailDeContactoCentroParque))
            return wpcfEmailDeContactoCentroParque.get(0);
        return "";
    }

    public String getWpcfPaginaWebDelCentroOParque() {
        if (!CollectionExtension.isNullOrEmpty(wpcfPaginaWebDelCentroOParque))
            return wpcfPaginaWebDelCentroOParque.get(0);
        return "";
    }

    public String getWpcfHorarioDeAtencionCentroParque() {
        if (!CollectionExtension.isNullOrEmpty(wpcfHorarioDeAtencionCentroParque))
            return wpcfHorarioDeAtencionCentroParque.get(0);
        return "";
    }

    public String getWpcfDireccionPostalDelCentroOParque() {
        if (!CollectionExtension.isNullOrEmpty(wpcfDireccionPostalDelCentroOParque))
            return wpcfDireccionPostalDelCentroOParque.get(0);
        return "";
    }

    public String getWpcfAudioguiaDelCentroOParque() {
        if (!CollectionExtension.isNullOrEmpty(wpcfAudioguiaDelCentroOParque))
            return wpcfAudioguiaDelCentroOParque.get(0);
        return "";
    }


    public String getExcerptRendered() {
        if (getExcerptDto() != null)
            return getExcerptDto().getRendered();
        return "";
    }

    public String getBetterFeaturedImageSourceUrl() {
        if (getBetterFeaturedImage() != null)
            return getBetterFeaturedImage().getSourceUrl();
        return "";
    }

    public double getLatitude() {
        if (!CollectionExtension.isNullOrEmpty(mLatitude)) {
            String latitude = mLatitude.get(0);
            if (!StringExtensions.isNullOrEmpty(latitude))
                return Double.parseDouble(latitude);
        }
        return 0;
    }

    public double getLongitude() {
        if (!CollectionExtension.isNullOrEmpty(mLongitude)) {
            String longitude = mLongitude.get(0);
            if (!StringExtensions.isNullOrEmpty(longitude))
                return Double.parseDouble(longitude);
        }
        return 0;
    }
}
