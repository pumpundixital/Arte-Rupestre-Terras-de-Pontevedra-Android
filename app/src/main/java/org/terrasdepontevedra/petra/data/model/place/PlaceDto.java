package org.terrasdepontevedra.petra.data.model.place;

import com.google.gson.annotations.SerializedName;

import org.terrasdepontevedra.petra.data.model.base.image.BetterFeaturedImageDto;
import org.terrasdepontevedra.petra.util.extensions.CollectionExtension;

import java.util.Collections;
import java.util.List;

import static org.terrasdepontevedra.petra.util.extensions.StringExtensions.isNullOrEmpty;

public class PlaceDto {
    @SerializedName("id")
    private final int mId;

    @SerializedName("modified")
    private final String mModified;

    @SerializedName("link")
    private final String mLink;

    @SerializedName("slug")
    private final String mSlug;

    @SerializedName("title")
    private final TitleDto mTitle;

    @SerializedName("content")
    private final ContentDto mContent;

    @SerializedName("job_listing_category")
    private final List<Integer> mJobListingCategory;

    @SerializedName("better_featured_image")
    private final BetterFeaturedImageDto mBetterFeaturedImageDto;

    @SerializedName("geolocation_lat")
    private final String mLatitude;

    @SerializedName("geolocation_long")
    private final String mLongitude;

    @SerializedName("wpcf-localizacion")
    private final List<String> mWpcfLocalizacion;

    @SerializedName("wpcf-imagenes-adicionales-petroglifo")
    private final List<String> mWpcfImagenesAdicionalesPetroglifo;

    @SerializedName("wpcf-emplazamiento-petroglifo")
    private final List<String> mWpcfEmplazamientoPetroglifo;

    @SerializedName("wpcf-morfologia-petroglifo")
    private final List<String> mWpcfMorfologiaPetroglifo;

    @SerializedName("wpcf-acceso-al-petroglifo")
    private final List<String> mWpcfAccesoAlPetroglifo;

    @SerializedName("wpcf-descripcion-de-la-piedra-petroglifo")
    private final List<String> mWpcfDescripcionDeLaPiedraPetroglifo;

    public PlaceDto(int id, String modified,
                    String link, TitleDto title,
                    String slug,
                    ContentDto content,
                    List<Integer> jobListingCategory, BetterFeaturedImageDto betterFeaturedImageDto,
                    String latitude, String longitude,
                    List<String> wpcfLocalizacion, List<String> wpcfImagenesAdicionalesPetroglifo,
                    List<String> wpcfEmplazamientoPetroglifo, List<String> wpcfMorfologiaPetroglifo,
                    List<String> wpcfAccesoAlPetroglifo, List<String> wpcfDescripcionDeLaPiedraPetroglifo) {
        this.mId = id;
        this.mModified = modified;
        this.mLink = link;
        this.mSlug = slug;
        this.mTitle = title;
        this.mContent = content;
        this.mJobListingCategory = jobListingCategory;
        this.mBetterFeaturedImageDto = betterFeaturedImageDto;
        this.mLatitude = latitude;
        this.mLongitude = longitude;
        this.mWpcfLocalizacion = wpcfLocalizacion;
        this.mWpcfImagenesAdicionalesPetroglifo = wpcfImagenesAdicionalesPetroglifo;
        this.mWpcfEmplazamientoPetroglifo = wpcfEmplazamientoPetroglifo;
        this.mWpcfMorfologiaPetroglifo = wpcfMorfologiaPetroglifo;
        this.mWpcfAccesoAlPetroglifo = wpcfAccesoAlPetroglifo;
        this.mWpcfDescripcionDeLaPiedraPetroglifo = wpcfDescripcionDeLaPiedraPetroglifo;
    }

    public int getId() {
        return mId;
    }

    public String getModified() {
        return mModified;
    }

    public String getLink() {
        return mLink;
    }

    public String getSlug() {
        return mSlug;
    }

    public TitleDto getTitle() {
        return mTitle;
    }

    public ContentDto getContent() {
        return mContent;
    }

    public List<Integer> getJobListingCategory() {
        return mJobListingCategory;
    }

    public BetterFeaturedImageDto getBetterFeaturedImage() {
        return mBetterFeaturedImageDto;
    }

    public String getLatitude() {
        if (isNullOrEmpty(mLatitude))
            return "-1";
        return mLatitude;
    }

    public String getLongitude() {
        if (isNullOrEmpty(mLongitude))
            return "-1";
        return mLongitude;
    }

    public String getWpcfLocalizacion() {
        if (!CollectionExtension.isNullOrEmpty(mWpcfLocalizacion))
            return mWpcfLocalizacion.get(0);
        return "";
    }

    public List<String> getWpcfImagenesAdicionalesPetroglifo() {
        if (mWpcfImagenesAdicionalesPetroglifo != null) {
            return mWpcfImagenesAdicionalesPetroglifo;
        } else return Collections.emptyList();
    }

    public String getWpcfEmplazamientoPetroglifo() {
        if (CollectionExtension.isNullOrEmpty(mWpcfEmplazamientoPetroglifo))
            return "";
        return mWpcfEmplazamientoPetroglifo.get(0);
    }

    public String getWpcfMorfologiaPetroglifo() {
        if (CollectionExtension.isNullOrEmpty(mWpcfMorfologiaPetroglifo))
            return "";
        return mWpcfMorfologiaPetroglifo.get(0);
    }

    public String getWpcfAccesoAlPetroglifo() {
        if (CollectionExtension.isNullOrEmpty(mWpcfAccesoAlPetroglifo))
            return "";
        return mWpcfAccesoAlPetroglifo.get(0);
    }

    public String getWpcfDescripcionDeLaPiedraPetroglifo() {
        if (CollectionExtension.isNullOrEmpty(mWpcfDescripcionDeLaPiedraPetroglifo))
            return "";
        return mWpcfDescripcionDeLaPiedraPetroglifo.get(0);
    }

}
