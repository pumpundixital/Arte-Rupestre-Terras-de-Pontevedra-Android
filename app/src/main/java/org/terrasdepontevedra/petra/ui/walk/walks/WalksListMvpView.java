package org.terrasdepontevedra.petra.ui.walk.walks;

import org.terrasdepontevedra.petra.domain.model.walk.Itinerary;
import org.terrasdepontevedra.petra.ui.walk.pmp.MvpView;

import java.util.List;

public interface WalksListMvpView extends MvpView {

    void onWalksLoaded(List<Itinerary> itineraryList);

}
