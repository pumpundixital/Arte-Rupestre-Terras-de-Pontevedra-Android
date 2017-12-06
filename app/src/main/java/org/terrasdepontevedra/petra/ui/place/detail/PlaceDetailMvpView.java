package org.terrasdepontevedra.petra.ui.place.detail;

import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.ui.base.MvpView;

interface PlaceDetailMvpView extends MvpView {
    void loadPlacesOnView(PlaceCollection placeCollection);

    void loadTitleOnView(String title);

    void loadSubtitleOnView(String subtitle);

    void hideArrowViewPager();

    void loadPlaceOnView(int position);
}
