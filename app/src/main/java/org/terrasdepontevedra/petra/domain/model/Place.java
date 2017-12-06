package org.terrasdepontevedra.petra.domain.model;


import android.os.Parcel;
import android.os.Parcelable;
import android.text.Html;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import org.terrasdepontevedra.petra.domain.model.base.Access;
import org.terrasdepontevedra.petra.domain.model.base.Description;
import org.terrasdepontevedra.petra.domain.model.base.Image;
import org.terrasdepontevedra.petra.domain.model.base.ImageCollection;
import org.terrasdepontevedra.petra.domain.model.base.Locale;
import org.terrasdepontevedra.petra.domain.model.base.Morphology;
import org.terrasdepontevedra.petra.domain.model.base.Site;
import org.terrasdepontevedra.petra.domain.model.base.Synopsis;
import org.terrasdepontevedra.petra.domain.model.base.Title;

public class Place implements ClusterItem, Parcelable {
    private final PlaceIdentity mPlaceIdentity;
    private final Title mTitle;
    private final ItineraryIdentity mItineraryIdentity;
    private final Description mDescription;
    private Image mImage;
    private final ImageCollection mImageCollection;
    private final Locale mLocale;
    private final Site mSite;
    private final Morphology mMorphology;
    private final Access mAccess;
    private final Synopsis mSynopsis;

    Place(Parcel in) {
        mPlaceIdentity = in.readParcelable(PlaceIdentity.class.getClassLoader());
        mTitle = in.readParcelable(Title.class.getClassLoader());
        mItineraryIdentity = in.readParcelable(ItineraryIdentity.class.getClassLoader());
        mDescription = in.readParcelable(Description.class.getClassLoader());
        mImage = in.readParcelable(Image.class.getClassLoader());
        mImageCollection = in.readParcelable(ImageCollection.class.getClassLoader());
        mLocale = in.readParcelable(Locale.class.getClassLoader());
        mSite = in.readParcelable(Site.class.getClassLoader());
        mMorphology = in.readParcelable(Morphology.class.getClassLoader());
        mAccess = in.readParcelable(Access.class.getClassLoader());
        mSynopsis = in.readParcelable(Synopsis.class.getClassLoader());
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

    public static Place from(Title title, Locale locale) {
        return new Place(PlaceIdentity.Empty,
                title, ItineraryIdentity.Empty,
                Description.Empty,
                new ImageCollection(),
                locale,
                Site.Empty, Morphology.Empty, Access.Empty, Synopsis.Empty);
    }


    public Place(PlaceIdentity placeIdentity, Title title, ItineraryIdentity itineraryIdentity,
                 Description description, ImageCollection imageCollection, Locale locale, Site site,
                 Morphology morphology, Access access, Synopsis synopsis) {
        mPlaceIdentity = placeIdentity;
        mTitle = title;
        mItineraryIdentity = itineraryIdentity;
        mDescription = description;
        mImageCollection = imageCollection;
        mLocale = locale;
        mSite = site;
        mMorphology = morphology;
        mAccess = access;
        mSynopsis = synopsis;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(mLocale.getLatitude(), mLocale.getLongitude());
    }

    @Override
    public String getTitle() {
        return mTitle.asString();
    }

    @Override
    public String getSnippet() {
        return Html.fromHtml(mDescription.asString()).toString();
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

    public Locale getLocale() {
        return mLocale;
    }

    public PlaceIdentity getPlaceIdentity() {
        return mPlaceIdentity;
    }

    public Site getSite() {
        return mSite;
    }

    public Morphology getMorphology() {
        return mMorphology;
    }

    public Access getAccess() {
        return mAccess;
    }

    public Synopsis getSynopsis() {
        return mSynopsis;
    }

    public void setImage(Image image) {
        this.mImage = image;
    }

    public boolean hasDescription() {
        return mDescription != null && !mDescription.asString().trim().equals("");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place)) return false;

        Place place = (Place) o;

        return getPlaceIdentity().equals(place.getPlaceIdentity());
    }

    @Override
    public int hashCode() {
        return getPlaceIdentity().hashCode();
    }

    @Override
    public String toString() {
        return "Place{" +
                "mPlaceIdentity=" + mPlaceIdentity +
                ", mTitle=" + mTitle +
                ", mItineraryIdentity=" + mItineraryIdentity +
                ", mDescription=" + mDescription +
                ", mImage=" + mImage +
                ", mImageCollection=" + mImageCollection +
                ", mLocale=" + mLocale +
                ", mSite=" + mSite +
                ", mMorphology=" + mMorphology +
                ", mAccess=" + mAccess +
                ", mSynopsis=" + mSynopsis +
                '}';
    }

    public boolean hasSite() {
        return mSite != null && !mSite.asString().trim().equals("");
    }

    public boolean hasMorphology() {
        return mMorphology != null && !mMorphology.asString().trim().equals("");
    }

    public boolean hasAccess() {
        return mAccess != null && !mAccess.asString().trim().equals("");
    }

    public boolean hasSynopsis() {
        return mSynopsis != null && !mSynopsis.asString().trim().equals("");
    }

    public boolean hasGallery() {
        return mImageCollection != null && !mImageCollection.isEmpty();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(mPlaceIdentity, flags);
        dest.writeParcelable(mTitle, flags);
        dest.writeParcelable(mItineraryIdentity, flags);
        dest.writeParcelable(mDescription, flags);
        dest.writeParcelable(mImage, flags);
        dest.writeParcelable(mImageCollection, flags);
        dest.writeParcelable(mLocale, flags);
        dest.writeParcelable(mSite, flags);
        dest.writeParcelable(mMorphology, flags);
        dest.writeParcelable(mAccess, flags);
        dest.writeParcelable(mSynopsis, flags);
    }
}
