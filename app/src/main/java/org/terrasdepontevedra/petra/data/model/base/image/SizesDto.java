package org.terrasdepontevedra.petra.data.model.base.image;

import com.google.gson.annotations.SerializedName;


public class SizesDto {
    @SerializedName("thumbnail")
    private final ThumbnailDto mThumbnail;

    @SerializedName("medium")
    private final MediumDto mMediumDto;

    @SerializedName("medium_large")
    private final MediumLargeDto mMediumLargeDto;

    public SizesDto(ThumbnailDto thumbnail, MediumDto mediumDto, MediumLargeDto mediumLargeDto) {
        this.mThumbnail = thumbnail;
        this.mMediumDto = mediumDto;
        this.mMediumLargeDto = mediumLargeDto;
    }

    public ThumbnailDto getThumbnail() {
        return mThumbnail;
    }

    public MediumDto getMedium() {
        return mMediumDto;
    }

    public MediumLargeDto getMediumLarge() {
        return mMediumLargeDto;
    }

}
