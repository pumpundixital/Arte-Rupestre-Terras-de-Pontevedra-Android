package org.terrasdepontevedra.petra.data.model.base.image;

import com.google.gson.annotations.SerializedName;

public class BetterFeaturedImageDto {
    @SerializedName("id")
    private final int mId;

    @SerializedName("alt_text")
    private final String mAltText;

    @SerializedName("caption")
    private final String mCaption;

    @SerializedName("description")
    private final String mDescription;

    @SerializedName("media_type")
    private final String mMediaType;

    @SerializedName("media_details")
    private final MediaDetailsDto mMediaDetailsDto;

    @SerializedName("post")
    private final Object mPost;

    @SerializedName("source_url")
    private final String mSourceUrl;

    public BetterFeaturedImageDto(int id, String altText, String caption, String description,
                                  String mediaType, MediaDetailsDto mediaDetailsDto, Object post, String sourceUrl) {
        this.mId = id;
        this.mAltText = altText;
        this.mCaption = caption;
        this.mDescription = description;
        this.mMediaType = mediaType;
        this.mMediaDetailsDto = mediaDetailsDto;
        this.mPost = post;
        this.mSourceUrl = sourceUrl;
    }

    public int getId() {
        return mId;
    }

    public String getAltText() {
        return mAltText;
    }

    public String getCaption() {
        return mCaption;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getMediaType() {
        return mMediaType;
    }

    public MediaDetailsDto getMediaDetails() {
        return mMediaDetailsDto;
    }

    public Object getPost() {
        return mPost;
    }

    public String getSourceUrl() {
        return mSourceUrl;
    }

}
