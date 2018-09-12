package org.terrasdepontevedra.petra.ui.walk.place;


import org.terrasdepontevedra.petra.domain.model.walk.Place;
import org.terrasdepontevedra.petra.ui.walk.base.BasePresenter;
import org.terrasdepontevedra.petra.ui.walk.base.BaseView;

/**
 * Created by pumpun3 on 9/6/17.
 */


public interface PlaceContract {

    interface View extends BaseView {
        void showContent(Place place);
    }

    interface Presenter extends BasePresenter<View> {
        void getData(String link);
        void getData(int placeId);
    }
}