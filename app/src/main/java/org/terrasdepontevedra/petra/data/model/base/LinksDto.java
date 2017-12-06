package org.terrasdepontevedra.petra.data.model.base;

import com.google.gson.annotations.SerializedName;

import java.util.List;

class LinksDto {
    @SerializedName("mSelfDto")
    private final List<SelfDto> mSelfDto;

    @SerializedName("mCollectionDto")
    private final List<CollectionDto> mCollectionDto;

    @SerializedName("mAboutDto")
    private final List<AboutDto> mAboutDto;

    @SerializedName("mReplies")
    private final List<RepliesDto> mReplies;

    @SerializedName("wp_featuredmedia")
    private final List<WpFeaturedmediaDto> mWpFeaturedmediaDto;

    @SerializedName("wp_attachment")
    private final List<WpAttachmentDto> mWpAttachmentDto;

    @SerializedName("wp_term")
    private final List<WpTermDto> mWpTermDto;

    @SerializedName("mCuries")
    private final List<CuriesDto> mCuries;

    public LinksDto(List<SelfDto> selfDto, List<CollectionDto> collectionDto, List<AboutDto> aboutDto,
                    List<RepliesDto> replies, List<WpFeaturedmediaDto> wpFeaturedmediaDto,
                    List<WpAttachmentDto> wpAttachmentDto, List<WpTermDto> wpTermDto, List<CuriesDto> curies) {
        this.mSelfDto = selfDto;
        this.mCollectionDto = collectionDto;
        this.mAboutDto = aboutDto;
        this.mReplies = replies;
        this.mWpFeaturedmediaDto = wpFeaturedmediaDto;
        this.mWpAttachmentDto = wpAttachmentDto;
        this.mWpTermDto = wpTermDto;
        this.mCuries = curies;
    }

    public List<SelfDto> getSelfDto() {
        return mSelfDto;
    }

    public List<CollectionDto> getCollectionDto() {
        return mCollectionDto;
    }

    public List<AboutDto> getAboutDto() {
        return mAboutDto;
    }

    public List<RepliesDto> getReplies() {
        return mReplies;
    }

    public List<WpFeaturedmediaDto> getWpFeaturedmediaDto() {
        return mWpFeaturedmediaDto;
    }

    public List<WpAttachmentDto> getWpAttachmentDto() {
        return mWpAttachmentDto;
    }

    public List<WpTermDto> getWpTermDto() {
        return mWpTermDto;
    }

    public List<CuriesDto> getCuries() {
        return mCuries;
    }

}