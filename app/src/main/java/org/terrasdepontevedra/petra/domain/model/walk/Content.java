package org.terrasdepontevedra.petra.domain.model.walk;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;


public class Content implements Parcelable{

    private int id;
    private int imageId;
    private Date createdAt;
    private Date modifiedAt;
    private String link;
    private String title;
    private String content;
    private String excerpt;

    private Image featuredImage;

    public Content(){

    }

    protected Content(Parcel in) {
        id = in.readInt();
        imageId = in.readInt();
        link = in.readString();
        title = in.readString();
        content = in.readString();
        excerpt = in.readString();
    }

    public static final Creator<Content> CREATOR = new Creator<Content>() {
        @Override
        public Content createFromParcel(Parcel in) {
            return new Content(in);
        }

        @Override
        public Content[] newArray(int size) {
            return new Content[size];
        }
    };

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Date modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public Image getFeaturedImage() {
        return featuredImage;
    }

    public void setFeaturedImage(Image featuredImage) {
        this.featuredImage = featuredImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(imageId);
        parcel.writeString(link);
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(excerpt);
    }
}
