package org.terrasdepontevedra.petra.domain.model.walk;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.util.List;



public class Place extends Content implements ClusterItem {

    private double lat;
    private double lng;
    private int category;
    private String address;
    private String website;
    private String phone;
    private List<String> imagesUrl;

    private Place() {
        super();
    }


    public static Place mapper(PlaceDto placeDto) {
        Place place = new Place();


        place.setTitle(placeDto.getTitle().getRendered());
        place.setContent(placeDto.getContent().getRendered());

        if (placeDto.getWpcflocalizacionlugar() != null) {
            place.setAddress(placeDto.getWpcflocalizacionlugar().get(0));
        }

        if (placeDto.getWpcfweblugar() != null) {
            place.setWebsite(placeDto.getWpcfweblugar().get(0));
        }

        if (placeDto.getWpcftelefonolugar() != null) {
            place.setPhone(placeDto.getWpcftelefonolugar().get(0));
        }

        place.setId(placeDto.getId());
        if (placeDto.getLink() != null) {
            place.setLink(placeDto.getLink());
        }
        place.setExcerpt(placeDto.getExcerpt().getRendered());

        if (placeDto.getLatitude() != null) {
            place.setLat(Double.parseDouble(placeDto.getLatitude().get(0)));
        }
        if (placeDto.getLongitude() != null) {
            place.setLng(Double.parseDouble(placeDto.getLongitude().get(0)));
        }
        if (placeDto.getTipodelugar() != null) {
            place.setCategory(placeDto.getTipodelugar().get(0));
        }

        if (placeDto.getImages() != null) {
            place.setImagesUrl(placeDto.getImages());
        }

        if (placeDto.getLatitude() != null && !placeDto.getLatitude().isEmpty()) {
            place.setLat(Double.parseDouble(placeDto.getLatitude().get(0)));
        }

        if (placeDto.getLongitude() != null && !placeDto.getLongitude().isEmpty()) {
            place.setLng(Double.parseDouble(placeDto.getLongitude().get(0)));
        }

        if (placeDto.getBetter_featured_image() != null
                && placeDto.getBetter_featured_image().getMedia_details() != null
                && placeDto.getBetter_featured_image().getMedia_details().getSizes() != null
                && placeDto.getBetter_featured_image().getMedia_details().getSizes().getThumbnail() != null) {
            place.setFeaturedImage(new Image(
                    placeDto.getBetter_featured_image().getId(),
                    placeDto.getBetter_featured_image().getMedia_details().getSizes().getThumbnail().getSource_url()
            ));
        }
        return place;
    }


    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    private void setLat(double lat) {
        this.lat = lat;
    }

    private void setLng(double lng) {
        this.lng = lng;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(lat, lng);
    }

    @Override
    public String getSnippet() {
        return null;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }


}
