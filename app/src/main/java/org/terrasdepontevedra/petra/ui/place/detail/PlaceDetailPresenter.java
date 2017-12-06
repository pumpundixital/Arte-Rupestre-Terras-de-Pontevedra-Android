package org.terrasdepontevedra.petra.ui.place.detail;

import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.ui.base.Presenter;

public interface PlaceDetailPresenter extends Presenter<PlaceDetailMvpView> {
    void init(PlaceCollection placeCollection, int position);

    void onPageSelected(int position);
}
