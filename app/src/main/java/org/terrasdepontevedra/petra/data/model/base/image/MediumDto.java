package org.terrasdepontevedra.petra.data.model.base.image;

import com.google.gson.annotations.SerializedName;


public class MediumDto {
    @SerializedName("file")
    private final String mFile;

    @SerializedName("width")
    private final int mWidth;

    @SerializedName("height")
    private final int mHeight;

    @SerializedName("mime-type")
    private final String mMimeType;

    @SerializedName("source_url")
    private final String mSourceUrl;

    public MediumDto(String file, int width, int height, String mimeType,
                     String sourceUrl) {
        this.mFile = file;
        this.mWidth = width;
        this.mHeight = height;
        this.mMimeType = mimeType;
        this.mSourceUrl = sourceUrl;
    }

    public String getFile() {
        return mFile;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }

    public String getMimeType() {
        return mMimeType;
    }

    public String getSourceUrl() {
        return mSourceUrl;
    }
}
