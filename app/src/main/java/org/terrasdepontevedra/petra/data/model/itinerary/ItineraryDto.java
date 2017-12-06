package org.terrasdepontevedra.petra.data.model.itinerary;

import com.google.gson.annotations.SerializedName;

import org.terrasdepontevedra.petra.util.extensions.StringExtensions;

import java.util.List;

import static org.terrasdepontevedra.petra.util.extensions.StringExtensions.isNullOrEmpty;

public class ItineraryDto {
    @SerializedName("id")
    private final int mId;

    @SerializedName("description")
    private final String mDescription;

    @SerializedName("link")
    private final String mLink;

    @SerializedName("name")
    private final String mName;

    @SerializedName("wpcf-imagen-centro")
    private final String mWpcfImagenCentro;

    @SerializedName("wpcf-audioguia-del-centro-o-area")
    private final String mWpcfAudioguiaDelCentroOArea;

    @SerializedName("wpcf-telefono-de-contacto-centro")
    private final String mWpcfTelefonoDeContactoCentro;

    @SerializedName("wpcf-email-de-contacto-centro")
    private final String mWpcfEmailDeContactoCentro;

    @SerializedName("wpcf-pagina-web-del-centro-o-area")
    private final String mWpcfPaginaWebDelCentroOArea;

    @SerializedName("wpcf-horario-de-atencion-centro")
    private final String mWpcfHorarioDeAtencionCentro;

    @SerializedName("wpcf-descripcion-larga-del-centro-o-area")
    private final String mWpcfDescripcionLargaDelCentroOArea;

    @SerializedName("wpcf-localizacion-del-centro-o-area")
    private final String mWpcfLocalizacionDelCentroOArea;

    @SerializedName("latitude")
    private final String mLatitude;

    @SerializedName("longitude")
    private final String mLongitude;

    @SerializedName("wpcf-galeria-fotos-centro")
    private final List<String> mWpcfGaleriaFotosCentro;

    public ItineraryDto(int id, String description, String link, String name,
                        String wpcfImagenCentro, String wpcfTelefonoDeContactoCentro,
                        String wpcfEmailDeContactoCentro, String wpcfPaginaWebDelCentroOArea,
                        String wpcfHorarioDeAtencionCentro, String wpcfDescripcionLargaDelCentroOArea,
                        String wpcfLocalizacionDelCentroOArea,
                        String wpcfAudioguiaDelCentroOArea, String latitude, String longitude,
                        List<String> wpcfGaleriaFotosCentro) {
        this.mId = id;
        this.mDescription = description;
        this.mLink = link;
        this.mName = name;
        this.mWpcfImagenCentro = wpcfImagenCentro;
        this.mWpcfTelefonoDeContactoCentro = wpcfTelefonoDeContactoCentro;
        this.mWpcfEmailDeContactoCentro = wpcfEmailDeContactoCentro;
        this.mWpcfPaginaWebDelCentroOArea = wpcfPaginaWebDelCentroOArea;
        this.mWpcfHorarioDeAtencionCentro = wpcfHorarioDeAtencionCentro;
        this.mWpcfDescripcionLargaDelCentroOArea = wpcfDescripcionLargaDelCentroOArea;
        this.mWpcfLocalizacionDelCentroOArea = wpcfLocalizacionDelCentroOArea;
        this.mWpcfAudioguiaDelCentroOArea = wpcfAudioguiaDelCentroOArea;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        mWpcfGaleriaFotosCentro = wpcfGaleriaFotosCentro;
    }

    public int getId() {
        return mId;
    }

    public String getDescription() {
        return StringExtensions.replace(mDescription, "\r\n", "\n");
    }

    public String getLink() {
        return mLink;
    }

    public String getName() {
        return mName;
    }

    public String getWpcfImagenCentro() {
        return mWpcfImagenCentro;
    }

    public String getWpcfTelefonoDeContactoCentro() {
        return mWpcfTelefonoDeContactoCentro;
    }

    public String getWpcfEmailDeContactoCentro() {
        return mWpcfEmailDeContactoCentro;
    }

    public String getWpcfPaginaWebDelCentroOArea() {
        return mWpcfPaginaWebDelCentroOArea;
    }

    public String getWpcfHorarioDeAtencionCentro() {
        return mWpcfHorarioDeAtencionCentro;
    }

    public String getWpcfDescripcionLargaDelCentroOArea() {
        return StringExtensions.replace(mWpcfDescripcionLargaDelCentroOArea, "\r\n", "\n");
    }

    public String getWpcfLocalizacionDelCentroOArea() {
        return StringExtensions.replace(mWpcfLocalizacionDelCentroOArea, "\r\n", "\n");
    }

    public String getWpcfAudioguiaDelCentroOArea() {
        return mWpcfAudioguiaDelCentroOArea;
    }

    public String getLatitude() {
        if (isNullOrEmpty(mLatitude)) {
            return "-1";
        }
        return mLatitude;
    }

    public String getLongitude() {
        if (isNullOrEmpty(mLongitude)) {
            return "-1";
        }
        return mLongitude;
    }

    public List<String> getWpcfGaleriaFotosCentro() {
        return mWpcfGaleriaFotosCentro;
    }
}
