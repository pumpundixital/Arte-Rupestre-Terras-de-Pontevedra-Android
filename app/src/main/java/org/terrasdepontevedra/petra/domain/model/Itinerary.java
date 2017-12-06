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

public class Itinerary implements Parcelable {
    private final ItineraryIdentity mItineraryIdentity;
    private final Title mTitle;
    private Description mDescription;
    private Description mLongDescription;
    private Image mImage;
    private ImageCollection mImageCollection;
    private PlaceCollection mPlaceCollection;
    private Locale mLocale;
    private Audio mAudio;
    private Phone mPhone;
    private Email mEmail;
    private Web mWeb;
    private Schedule mSchedule;
    private Link mLink;

    private boolean mChecked;

    private Itinerary(Parcel in) {
        mItineraryIdentity = in.readParcelable(ItineraryIdentity.class.getClassLoader());
        mTitle = in.readParcelable(Title.class.getClassLoader());
        mDescription = in.readParcelable(Description.class.getClassLoader());
        mLongDescription = in.readParcelable(Description.class.getClassLoader());
        mImage = in.readParcelable(Image.class.getClassLoader());
        mImageCollection = in.readParcelable(ImageCollection.class.getClassLoader());
        mPlaceCollection = in.readParcelable(PlaceCollection.class.getClassLoader());
        mLocale = in.readParcelable(Locale.class.getClassLoader());
        mAudio = in.readParcelable(Audio.class.getClassLoader());
        mPhone = in.readParcelable(Phone.class.getClassLoader());
        mEmail = in.readParcelable(Email.class.getClassLoader());
        mWeb = in.readParcelable(Web.class.getClassLoader());
        mSchedule = in.readParcelable(Schedule.class.getClassLoader());
        mLink = in.readParcelable(Link.class.getClassLoader());
        mChecked = in.readByte() != 0;
    }

    public static final Creator<Itinerary> CREATOR = new Creator<Itinerary>() {
        @Override
        public Itinerary createFromParcel(Parcel in) {
            return new Itinerary(in);
        }

        @Override
        public Itinerary[] newArray(int size) {
            return new Itinerary[size];
        }
    };

    public static Itinerary seeAll(String title) {
        return new Itinerary(ItineraryIdentity.Empty, Title.from(title));
    }

    public Itinerary(ItineraryIdentity itineraryIdentity, Title title,
                     Description description, Description longDescription,
                     Image image, ImageCollection imageCollection,
                     PlaceCollection placeCollection, Locale locale,
                     Audio audio, Phone phone, Email email, Web web, Schedule schedule, Link link) {
        mItineraryIdentity = itineraryIdentity;
        mTitle = title;
        mDescription = description;
        mLongDescription = longDescription;
        mImage = image;
        mImageCollection = imageCollection;
        mPlaceCollection = placeCollection;
        mLocale = locale;
        mAudio = audio;
        mPhone = phone;
        mEmail = email;
        mWeb = web;
        mSchedule = schedule;
        mLink = link;
    }

    private Itinerary(ItineraryIdentity itineraryIdentity, Title title) {
        mItineraryIdentity = itineraryIdentity;
        mTitle = title;
        mPlaceCollection = new PlaceCollection();
    }

    public ItineraryIdentity getItineraryIdentity() {
        return mItineraryIdentity;
    }

    public Title getTitle() {
        return mTitle;
    }

    public Description getDescription() {
        return mDescription;
    }

    public Image getImage() {
        return mImage;
    }

    public ImageCollection getImageCollection() {
        return mImageCollection;
    }

    public PlaceCollection getPlaceCollection() {
        return mPlaceCollection;
    }

    public Description getLongDescription() {
        return mLongDescription;
    }

    public Locale getLocale() {
        return mLocale;
    }

    public Audio getAudio() {
        return mAudio;
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

    public void setPlaceCollection(PlaceCollection placeCollection) {
        mPlaceCollection = placeCollection;
    }

    public boolean hasPhone() {
        return mPhone != null && !mPhone.asString().trim().equals("");
    }

    public boolean hasEmail() {
        return mEmail != null && !mEmail.asString().trim().equals("");
    }

    public boolean hasSchedule() {
        return mSchedule != null && !mSchedule.asString().trim().equals("");
    }

    public boolean hasWeb() {
        return mWeb != null && !mWeb.asString().trim().equals("");
    }

    public boolean hasGallery() {
        return mImageCollection != null && !mImageCollection.isEmpty();
    }

    public boolean hasDescription() {
        return mDescription != null && !mDescription.asString().trim().equals("");
    }

    public boolean hasAudio() {
        return mAudio != null && mAudio.getUrl() != null && !mAudio.getUrl().asString().trim().equals("");
    }

    public Link getLink() {
        return mLink;
    }

    public boolean isChecked() {
        return mChecked;
    }

    public void setChecked(boolean checked) {
        mChecked = checked;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Itinerary)) return false;

        Itinerary itinerary = (Itinerary) o;

        return getItineraryIdentity().equals(itinerary.getItineraryIdentity());

    }

    @Override
    public int hashCode() {
        return getItineraryIdentity().hashCode();
    }

    @Override
    public String toString() {
        return "Itinerary{" +
                "mItineraryIdentity=" + mItineraryIdentity +
                ", mTitle=" + mTitle +
                ", mDescription=" + mDescription +
                ", mImage=" + mImage +
                ", mImageCollection=" + mImageCollection +
                ", mPlaceCollection=" + mPlaceCollection +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mItineraryIdentity, flags);
        dest.writeParcelable(mTitle, flags);
        dest.writeParcelable(mDescription, flags);
        dest.writeParcelable(mLongDescription, flags);
        dest.writeParcelable(mImage, flags);
        dest.writeParcelable(mImageCollection, flags);
        dest.writeParcelable(mPlaceCollection, flags);
        dest.writeParcelable(mLocale, flags);
        dest.writeParcelable(mAudio, flags);
        dest.writeParcelable(mPhone, flags);
        dest.writeParcelable(mEmail, flags);
        dest.writeParcelable(mWeb, flags);
        dest.writeParcelable(mSchedule, flags);
        dest.writeParcelable(mLink, flags);
        dest.writeByte((byte) (mChecked ? 1 : 0));
    }
}
