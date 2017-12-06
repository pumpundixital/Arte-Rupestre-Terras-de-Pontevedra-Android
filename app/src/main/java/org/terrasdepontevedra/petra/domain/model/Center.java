package org.terrasdepontevedra.petra.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.terrasdepontevedra.petra.domain.model.base.Audio;
import org.terrasdepontevedra.petra.domain.model.base.Description;
import org.terrasdepontevedra.petra.domain.model.base.Email;
import org.terrasdepontevedra.petra.domain.model.base.Image;
import org.terrasdepontevedra.petra.domain.model.base.ImageCollection;
import org.terrasdepontevedra.petra.domain.model.base.Link;
import org.terrasdepontevedra.petra.domain.model.base.Locale;
import org.terrasdepontevedra.petra.domain.model.base.Phone;
import org.terrasdepontevedra.petra.domain.model.base.Schedule;
import org.terrasdepontevedra.petra.domain.model.base.Title;
import org.terrasdepontevedra.petra.domain.model.base.Web;

public class Center implements Parcelable {
    private final Title mTitle;
    private final Description mDescription;
    private final Description mLongDescription;
    private final Image mImage;
    private final Phone mPhone;
    private final Email mEmail;
    private final Web mWeb;
    private final Schedule mSchedule;
    private final Locale mLocale;
    private final ImageCollection mImageCollection;
    private final Audio mAudio;
    private final Link mLink;

    public Center(Title title, Description description, Description longDescription, Image image,
                  Phone phone, Email email, Web web, Schedule schedule, Locale locale,
                  ImageCollection imageCollection, Audio audio, Link link) {
        mTitle = title;
        mDescription = description;
        mLongDescription = longDescription;
        mImage = image;
        mPhone = phone;
        mEmail = email;
        mWeb = web;
        mSchedule = schedule;
        mLocale = locale;
        mImageCollection = imageCollection;
        mAudio = audio;
        mLink = link;
        mImageCollection.add(mImage);
    }

    private Center(Parcel in) {
        mTitle = in.readParcelable(Title.class.getClassLoader());
        mDescription = in.readParcelable(Description.class.getClassLoader());
        mLongDescription = in.readParcelable(Description.class.getClassLoader());
        mImage = in.readParcelable(Image.class.getClassLoader());
        mPhone = in.readParcelable(Phone.class.getClassLoader());
        mEmail = in.readParcelable(Email.class.getClassLoader());
        mWeb = in.readParcelable(Web.class.getClassLoader());
        mSchedule = in.readParcelable(Schedule.class.getClassLoader());
        mLocale = in.readParcelable(Locale.class.getClassLoader());
        mImageCollection = in.readParcelable(ImageCollection.class.getClassLoader());
        mAudio = in.readParcelable(Audio.class.getClassLoader());
        mLink = in.readParcelable(Link.class.getClassLoader());
    }

    public static final Creator<Center> CREATOR = new Creator<Center>() {
        @Override
        public Center createFromParcel(Parcel in) {
            return new Center(in);
        }

        @Override
        public Center[] newArray(int size) {
            return new Center[size];
        }
    };

    public Title getTitle() {
        return mTitle;
    }

    public Description getDescription() {
        return mDescription;
    }

    public Description getLongDescription() {
        return mLongDescription;
    }

    public Image getImage() {
        return mImage;
    }

    public Phone getPhone() {
        return mPhone;
    }

    public Email getEmail() {
        return mEmail;
    }

    public Web getWeb() {
        return mWeb;
    }

    public Schedule getSchedule() {
        return mSchedule;
    }

    public Locale getLocale() {
        return mLocale;
    }

    public ImageCollection getImageCollection() {
        return mImageCollection;
    }

    public Audio getAudio() {
        return mAudio;
    }

    public Link getLink() {
        return mLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Center)) return false;

        Center center = (Center) o;

        return getTitle() != null ? getTitle().equals(center.getTitle()) : center.getTitle() == null
                && (getDescription() != null ? getDescription().equals(center.getDescription()) : center.getDescription() == null
                && (getLongDescription() != null ? getLongDescription().equals(center.getLongDescription()) : center.getLongDescription() == null
                && (getImage() != null ? getImage().equals(center.getImage()) : center.getImage() == null
                && (getPhone() != null ? getPhone().equals(center.getPhone()) : center.getPhone() == null
                && (getEmail() != null ? getEmail().equals(center.getEmail()) : center.getEmail() == null
                && (getWeb() != null ? getWeb().equals(center.getWeb()) : center.getWeb() == null
                && (getSchedule() != null ? getSchedule().equals(center.getSchedule()) : center.getSchedule() == null
                && (getLocale() != null ? getLocale().equals(center.getLocale()) : center.getLocale() == null))))))));

    }

    @Override
    public int hashCode() {
        int result = getTitle() != null ? getTitle().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getLongDescription() != null ? getLongDescription().hashCode() : 0);
        result = 31 * result + (getImage() != null ? getImage().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getWeb() != null ? getWeb().hashCode() : 0);
        result = 31 * result + (getSchedule() != null ? getSchedule().hashCode() : 0);
        result = 31 * result + (getLocale() != null ? getLocale().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Center{" +
                "mTitle=" + mTitle +
                ", mDescription=" + mDescription +
                ", mLongDescription=" + mLongDescription +
                ", mImage=" + mImage +
                ", mPhone=" + mPhone +
                ", mEmail=" + mEmail +
                ", mWeb=" + mWeb +
                ", mSchedule=" + mSchedule +
                ", mLocale=" + mLocale +
                '}';
    }

    public boolean hasDescription() {
        return mDescription != null && !mDescription.asString().trim().equals("");
    }

    public boolean hasSchedule() {
        return mSchedule != null && !mSchedule.asString().trim().equals("");
    }

    public boolean hasEmail() {
        return mEmail != null && !mEmail.asString().trim().equals("");
    }

    public boolean hasPhone() {
        return mPhone != null && !mPhone.asString().trim().equals("");
    }

    public boolean hasLocale() {
        return mLocale != null && !mLocale.asString().trim().equals("");
    }

    public boolean hasGallery() {
        return mImageCollection != null && !mImageCollection.isEmpty();
    }

    public boolean hasWeb() {
        return mWeb != null && !mWeb.asString().trim().equals("");
    }

    public boolean hasAudio() {
        return mAudio != null && mAudio.getUrl() != null && !mAudio.getUrl().asString().trim().equals("");
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mTitle, flags);
        dest.writeParcelable(mDescription, flags);
        dest.writeParcelable(mLongDescription, flags);
        dest.writeParcelable(mImage, flags);
        dest.writeParcelable(mPhone, flags);
        dest.writeParcelable(mEmail, flags);
        dest.writeParcelable(mWeb, flags);
        dest.writeParcelable(mSchedule, flags);
        dest.writeParcelable(mLocale, flags);
        dest.writeParcelable(mImageCollection, flags);
        dest.writeParcelable(mAudio, flags);
        dest.writeParcelable(mLink, flags);
    }
}
