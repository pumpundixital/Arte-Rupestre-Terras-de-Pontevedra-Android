package org.terrasdepontevedra.petra.ui.walk.walks;

import org.terrasdepontevedra.petra.PetraApplication;
import org.terrasdepontevedra.petra.data.walk.WalkApi;
import org.terrasdepontevedra.petra.domain.model.walk.Itinerary;
import org.terrasdepontevedra.petra.domain.model.walk.ItineraryDto;
import org.terrasdepontevedra.petra.ui.walk.pmp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class WalksListPresenter extends BasePresenter<WalksListMvpView> {


    WalkApi walkApi;
    private int page;


    public WalksListPresenter() {
        walkApi = PetraApplication.getApp().getApi();
        page = 0;
    }

    public void loadWalks() {
        page++;
        walkApi.loadWalks("en",page).enqueue(new Callback<List<ItineraryDto>>() {
            @Override
            public void onResponse(Call<List<ItineraryDto>> call, Response<List<ItineraryDto>> response) {
                if(response.isSuccessful()){
                    List<Itinerary>itineraries = new ArrayList<>();
                    for (ItineraryDto itineraryDto : response.body()) {
                        Itinerary itinerary = Itinerary.mapper(itineraryDto);
                        itineraries.add(itinerary);
                    }
                    if(getMvpView()!=null){
                        getMvpView().onWalksLoaded(itineraries);
                    }
                }
                else{
                    Timber.i("error");
                }
            }

            @Override
            public void onFailure(Call<List<ItineraryDto>> call, Throwable t) {
                t.getMessage();
            }
        });
    }
}
