package org.terrasdepontevedra.petra.domain.model.walk;


import android.os.Parcel;
import android.os.Parcelable;

import timber.log.Timber;

public class Itinerary extends Content implements Parcelable{

    public Itinerary(){

    }

    protected Itinerary(Parcel in) {
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

    public static Itinerary mapper(ItineraryDto itineraryDto){
        Itinerary itinerary = new Itinerary();
        if(itineraryDto.getTitle()!=null) {
            itinerary.setTitle(itineraryDto.getTitle().getRendered());
        }
            itinerary.setImageId(itineraryDto.getFeatured_media());

        itinerary.setId(itineraryDto.getId());
        itinerary.setContent(itineraryDto.getContent().getRendered());

        itinerary.setLink(itineraryDto.getLink());
        if(itineraryDto.getExcerpt()!=null) {
            itinerary.setExcerpt(itineraryDto.getExcerpt().getRendered());
        }

        if(itineraryDto.getBetter_featured_image()!=null && itineraryDto.getBetter_featured_image().getMedia_details()!=null && itineraryDto.getBetter_featured_image().getMedia_details().getSizes()!=null) {
            if (itineraryDto.getBetter_featured_image().getMedia_details().getSizes().getThumbnail() != null) {
                Timber.i("mapeando itineary, image ok");
                itinerary.setFeaturedImage(new Image(
                        itineraryDto.getBetter_featured_image().getId(),
                        itineraryDto.getBetter_featured_image().getMedia_details().getSizes().getThumbnail().getSource_url()
                ));
            }
        }

        return itinerary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
