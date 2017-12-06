package org.terrasdepontevedra.petra.ui.center.detail;

import org.terrasdepontevedra.petra.domain.model.Center;
import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.ui.base.BasePresenter;

import static org.terrasdepontevedra.petra.util.FileUtils.generateUrlFrom;

public class CenterDetailPresenterImpl
        extends BasePresenter<CenterDetailMvpView>
        implements CenterDetailPresenter {

    private Center mCenter;

    @Override
    public void init(Center center) {
        mCenter = center;
        mMvpView.showTitle(center.getTitle().asString());
        if (center.hasDescription())
            mMvpView.showDescription(center.getLongDescription().asString());
        else
            mMvpView.hideDescriptionView();
        if (center.hasSchedule())
            mMvpView.showSchedule(center.getSchedule().asString());
        else
            mMvpView.hideScheduleView();
        if (center.hasEmail())
            mMvpView.showEmail(center.getEmail().asString());
        else
            mMvpView.hideEmailView();
        if (center.hasPhone())
            mMvpView.showPhone(center.getPhone().asString());
        else
            mMvpView.hidePhoneView();
        if (center.hasLocale())
            mMvpView.showLocale(center.getLocale().asString());
        else
            mMvpView.hideLocaleView();
        if (center.hasGallery())
            mMvpView.showGallery(center.getImageCollection().asStringList());
        if (center.hasWeb())
            mMvpView.showWebOnView(center.getWeb().asString());
        else
            mMvpView.hideWebView();
        if (center.hasAudio())
            mMvpView.showAudioOnView(generateUrlFrom(center.getAudio()));
        else
            mMvpView.hideAudioView();
        mMvpView.showMap(Place.from(center.getTitle(), center.getLocale()));
    }

    @Override
    public void goToPlace() {
        mMvpView.openGoogleMaps(mCenter.getLocale());
    }

    @Override
    public void onClickButtonShared() {
        mMvpView.actionsToShare(mCenter.getLink(), mCenter.getTitle());
    }

    @Override
    public void onWebItemClick() {
        String web = mCenter.getWeb().asString();
        if (!web.startsWith("http://") || !web.startsWith(" https://"))
            web = "http://" + web;
        mMvpView.openNavigatorWith(web);
    }

    @Override
    public void onPhoneItemClick() {
        mMvpView.openPhoneWith(mCenter.getPhone().asString());
    }

    @Override
    public void onEmailItemClick() {
        mMvpView.openSendMailWith(mCenter.getEmail().asString());
    }

}
