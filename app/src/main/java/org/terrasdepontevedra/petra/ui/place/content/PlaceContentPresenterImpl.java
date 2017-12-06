package org.terrasdepontevedra.petra.ui.place.content;

import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.ui.base.BasePresenter;

public class PlaceContentPresenterImpl
        extends BasePresenter<PlaceContentMvpView>
        implements PlaceContentPresenter {

    private Place mPlace;

    public PlaceContentPresenterImpl() {
    }


    @Override
    public void init(Place place) {
        mPlace = place;

        if (place.hasDescription())
            mMvpView.loadDescriptionOnView(place.getDescription().asString());
        else
            mMvpView.hideDescriptionView();
        if (place.hasSite())
            mMvpView.loadSiteOnView(place.getSite().asString());
        else
            mMvpView.hideSiteView();
        if (place.hasMorphology())
            mMvpView.loadMorphologyOnView(place.getMorphology().asString());
        else
            mMvpView.hideMorphologyView();
        if (place.hasAccess())
            mMvpView.loadAccessOnView(place.getAccess().asString());
        else
            mMvpView.hideAccessView();
        if (place.hasSynopsis())
            mMvpView.loadSynopsisOnView(place.getSynopsis().asString());
        else
            mMvpView.hideSynopsisView();
        if (place.hasGallery())
            mMvpView.loadGalleryOnView(place.getImageCollection().asStringList());
        else
            mMvpView.hideGalleryView();

    }

    @Override
    public void goToPlace() {
        mMvpView.openGoogleMaps(mPlace.getLocale());
    }


}
