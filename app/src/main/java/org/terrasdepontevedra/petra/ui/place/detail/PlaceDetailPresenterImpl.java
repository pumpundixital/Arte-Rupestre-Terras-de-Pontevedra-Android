package org.terrasdepontevedra.petra.ui.place.detail;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.domain.model.Place;
import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.domain.pojo.OnPlaceChangePage;
import org.terrasdepontevedra.petra.ui.base.BasePresenter;

public class PlaceDetailPresenterImpl
        extends BasePresenter<PlaceDetailMvpView>
        implements PlaceDetailPresenter {

    private PlaceCollection mPlaceCollection;
    private final EventBus mEventBus;

    public PlaceDetailPresenterImpl(EventBus eventBus) {
        mEventBus = eventBus;
    }

    @Override
    public void init(PlaceCollection placeCollection, int position) {
        if (placeCollection.isEmpty())
            return;

        if (placeCollection.hasOnlyOne())
            mMvpView.hideArrowViewPager();

        mPlaceCollection = placeCollection;
        mMvpView.loadPlacesOnView(placeCollection);
        mMvpView.loadPlaceOnView(position == -1 ? 0 : position);
        onPageSelected(position == -1 ? 0 : position);
    }

    @Override
    public void onPageSelected(int position) {
        Place place = mPlaceCollection.get(position);
        mMvpView.loadTitleOnView(place.getTitle());
        mMvpView.loadSubtitleOnView(place.getLocale().asString());
        mEventBus.post(new OnPlaceChangePage(place, position));
    }
}
