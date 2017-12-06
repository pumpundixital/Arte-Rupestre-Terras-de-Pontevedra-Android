package org.terrasdepontevedra.petra.ui.place.content;

import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.ui.base.Presenter;

public interface PlaceContentPresenter extends Presenter<PlaceContentMvpView> {
    void init(Place place);

    void goToPlace();
}
