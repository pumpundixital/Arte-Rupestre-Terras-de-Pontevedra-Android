package org.terrasdepontevedra.petra.ui.itinerary.content;


import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.ui.base.BasePresenter;

public class ItineraryDetailPresenterImpl
        extends BasePresenter<ItineraryDetailMvpView>
        implements ItineraryDetailPresenter {

    private Itinerary mItinerary;

    public ItineraryDetailPresenterImpl() {
    }

    @Override
    public void init(Itinerary itinerary) {
        mItinerary = itinerary;
        mMvpView.loadTitleOnView(itinerary.getTitle().asString());
        mMvpView.loadLocaleOnView(itinerary.getLocale().asString());

        if (itinerary.hasDescription())
            mMvpView.loadDescriptionOnView(itinerary.getLongDescription().asString());
        else
            mMvpView.hideDescriptionView();
        if (itinerary.hasPhone())
            mMvpView.loadPhoneOnView(itinerary.getPhone().asString());
        else
            mMvpView.hidePhoneView();
        if (itinerary.hasEmail())
            mMvpView.loadEmailOnView(itinerary.getEmail().asString());
        else
            mMvpView.hideEmailView();
        if (itinerary.hasSchedule())
            mMvpView.loadScheduleOnView(itinerary.getSchedule().asString());
        else
            mMvpView.hideScheduleView();
        if (itinerary.hasWeb())
            mMvpView.loadWebOnView(itinerary.getWeb().asString());
        else
            mMvpView.hideWebView();
        if (itinerary.hasAudio())
            mMvpView.loadAudioPlayer(itinerary.getAudio().getUrl().asString());
        else
            mMvpView.hideAudioView();
        if (itinerary.hasGallery())
            mMvpView.showGalleryOnView(itinerary.getImageCollection().asStringList());
        else
            mMvpView.hideGalleryView();

        mMvpView.showMapOnView();
    }

    @Override
    public void goToPlace() {
        mMvpView.openGoogleMaps(mItinerary.getLocale());
    }

    @Override
    public void onWebItemClick() {
        String web = mItinerary.getWeb().asString();
        if (!web.startsWith("http://") && !web.startsWith(" https://"))
            web = "http://" + web;
        mMvpView.openNavigatorWith(web);
    }

    @Override
    public void onPhoneItemClick() {
        mMvpView.openPhoneWith(mItinerary.getPhone().asString());
    }

    @Override
    public void onEmailItemClick() {
        mMvpView.openSendMailWith(mItinerary.getEmail().asString());
    }
}
