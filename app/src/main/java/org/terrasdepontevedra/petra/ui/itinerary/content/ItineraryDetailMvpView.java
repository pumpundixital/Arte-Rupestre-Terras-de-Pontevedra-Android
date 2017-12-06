package org.terrasdepontevedra.petra.ui.itinerary.content;

import org.terrasdepontevedra.petra.domain.model.base.Locale;
import org.terrasdepontevedra.petra.ui.base.MvpView;

import java.util.ArrayList;

interface ItineraryDetailMvpView extends MvpView {
    void loadTitleOnView(String titleAsString);

    void loadDescriptionOnView(String descriptionAsString);

    void loadLocaleOnView(String localeAsString);

    void loadAudioPlayer(String audioUrl);

    void loadPhoneOnView(String phone);

    void loadEmailOnView(String email);

    void loadScheduleOnView(String schedule);

    void loadWebOnView(String web);

    void hidePhoneView();

    void hideEmailView();

    void hideScheduleView();

    void hideWebView();

    void showGalleryOnView(ArrayList<String> strings);

    void hideGalleryView();

    void hideAudioView();

    void hideDescriptionView();

    void showMapOnView();

    void openGoogleMaps(Locale locale);

    void openNavigatorWith(String web);

    void openPhoneWith(String phone);

    void openSendMailWith(String email);
}
