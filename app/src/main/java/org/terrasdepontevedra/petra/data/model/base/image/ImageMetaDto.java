package org.terrasdepontevedra.petra.data.model.base.image;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class ImageMetaDto {
    @SerializedName("aperture")
    private final String mAperture;

    @SerializedName("credit")
    private final String mCredit;

    @SerializedName("camera")
    private final String mCamera;

    @SerializedName("caption")
    private final String mCaption;

    @SerializedName("created_timestamp")
    private final String mCreatedTimestamp;

    @SerializedName("copyright")
    private final String mCopyright;

    @SerializedName("focal_length")
    private final String mFocalLength;

    @SerializedName("iso")
    private final String mIso;

    @SerializedName("shutter_speed")
    private final String mShutterSpeed;

    @SerializedName("title")
    private final String mTitle;

    @SerializedName("orientation")
    private final String mOrientation;

    @SerializedName("keywords")
    private final List<Object> mKeywords;

    public ImageMetaDto(String aperture, String credit, String camera, String caption,
                        String createdTimestamp, String copyright, String focalLength, String iso,
                        String shutterSpeed, String title, String orientation,
                        List<Object> keywords) {
        this.mAperture = aperture;
        this.mCredit = credit;
        this.mCamera = camera;
        this.mCaption = caption;
        this.mCreatedTimestamp = createdTimestamp;
        this.mCopyright = copyright;
        this.mFocalLength = focalLength;
        this.mIso = iso;
        this.mShutterSpeed = shutterSpeed;
        this.mTitle = title;
        this.mOrientation = orientation;
        this.mKeywords = keywords;
    }

    public String getAperture() {
        return mAperture;
    }

    public String getCredit() {
        return mCredit;
    }

    public String getCamera() {
        return mCamera;
    }

    public String getCaption() {
        return mCaption;
    }

    public String getCreatedTimestamp() {
        return mCreatedTimestamp;
    }

    public String getCopyright() {
        return mCopyright;
    }

    public String getFocalLength() {
        return mFocalLength;
    }

    public String getIso() {
        return mIso;
    }

    public String getShutterSpeed() {
        return mShutterSpeed;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getOrientation() {
        return mOrientation;
    }

    public List<Object> getKeywords() {
        return mKeywords;
    }
}
