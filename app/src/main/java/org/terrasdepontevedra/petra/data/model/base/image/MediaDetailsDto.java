package org.terrasdepontevedra.petra.data.model.base.image;

import com.google.gson.annotations.SerializedName;


public class MediaDetailsDto {
    @SerializedName("width")
    private final int mWidth;

    @SerializedName("height")
    private final int mHeight;

    @SerializedName("file")
    private final String mFile;

    @SerializedName("sizes")
    private final SizesDto mSizesDto;

    @SerializedName("image_meta")
    private final ImageMetaDto mImageMetaDto;

    public MediaDetailsDto(int width, int height, String file, SizesDto sizesDto,
                           ImageMetaDto imageMetaDto) {
        this.mWidth = width;
        this.mHeight = height;
        this.mFile = file;
        this.mSizesDto = sizesDto;
        this.mImageMetaDto = imageMetaDto;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public String getFile() {
        return mFile;
    }

    public SizesDto getSizes() {
        return mSizesDto;
    }

    public ImageMetaDto getImageMeta() {
        return mImageMetaDto;
    }

}
