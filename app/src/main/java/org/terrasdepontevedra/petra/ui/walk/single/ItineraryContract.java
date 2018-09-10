package org.terrasdepontevedra.petra.ui.walk.single;



import org.terrasdepontevedra.petra.domain.model.walk.Itinerary;
import org.terrasdepontevedra.petra.ui.walk.base.BasePresenter;
import org.terrasdepontevedra.petra.ui.walk.base.BaseView;

/**
 * Created by pablopumpun on 22/9/17.
 */

public class ItineraryContract {

    interface View extends BaseView {
        void showContent(Itinerary itinerary);
    }

    interface Presenter extends BasePresenter<View> {
        void getItineraryFromSlug(String slug);
    }

}
