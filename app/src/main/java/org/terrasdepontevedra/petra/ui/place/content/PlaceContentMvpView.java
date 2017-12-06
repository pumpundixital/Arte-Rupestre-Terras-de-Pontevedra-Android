package org.terrasdepontevedra.petra.ui.place.content;

import org.terrasdepontevedra.petra.domain.model.base.Locale;
import org.terrasdepontevedra.petra.ui.base.MvpView;

import java.util.List;

interface PlaceContentMvpView extends MvpView {
    void loadDescriptionOnView(String descriptionAsString);

    void openGoogleMaps(Locale locale);

    void loadSiteOnView(String site);

    void loadMorphologyOnView(String morphology);

    void loadAccessOnView(String access);

    void loadSynopsisOnView(String synopsis);

    void loadGalleryOnView(List<String> imagesUrls);

    void hideDescriptionView();

    void hideSiteView();

    void hideMorphologyView();

    void hideAccessView();

    void hideSynopsisView();

    void hideGalleryView();
}
