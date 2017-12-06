package org.terrasdepontevedra.petra.ui.center.detail;

import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.domain.model.base.Link;
import org.terrasdepontevedra.petra.domain.model.base.Locale;
import org.terrasdepontevedra.petra.domain.model.base.Title;
import org.terrasdepontevedra.petra.ui.base.MvpView;

import java.util.List;

interface CenterDetailMvpView extends MvpView{
    void showTitle(String title);

    void showDescription(String description);

    void showSchedule(String schedule);

    void showEmail(String email);

    void showPhone(String phone);

    void showLocale(String locale);

    void showMap(Place place);

    void showGallery(List<String> imagesUrl);

    void openGoogleMaps(Locale locale);

    void showWebOnView(String web);

    void hideDescriptionView();

    void hideScheduleView();

    void hideEmailView();

    void hidePhoneView();

    void hideLocaleView();

    void hideWebView();

    void showAudioOnView(String audio);

    void hideAudioView();

    void actionsToShare(Link link, Title title);

    void openNavigatorWith(String web);

    void openPhoneWith(String phone);

    void openSendMailWith(String email);
}
