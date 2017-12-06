package org.terrasdepontevedra.petra.ui.map.filter;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.pojo.FilterMap;
import org.terrasdepontevedra.petra.util.interfaces.Binder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

class ItineraryFilterViewHolder extends RecyclerView.ViewHolder implements Binder<Itinerary> {

    @BindView(R.id.item_filter_itinerary)
    Button mBtnItinerary;

    private final EventBus mEventBus;
    private final ItineraryFilterMenuListener mItineraryFilterMenuListener;
    private Itinerary mItinerary;

    ItineraryFilterViewHolder(View itemView, EventBus eventBus,
                              ItineraryFilterMenuListener itineraryFilterMenuListener) {
        super(itemView);
        mEventBus = eventBus;
        mItineraryFilterMenuListener = itineraryFilterMenuListener;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Itinerary itinerary) {
        mItinerary = itinerary;
        mBtnItinerary.setText(itinerary.getTitle().asString());
        if (mItinerary.isChecked())
            mBtnItinerary.setBackgroundColor(ContextCompat.getColor(mBtnItinerary.getContext(), R.color.blue_light));
        else
            mBtnItinerary.setBackgroundColor(ContextCompat.getColor(mBtnItinerary.getContext(), R.color.white));
    }

    @OnClick
    void onClickBtnItinerary() {
        if (mItineraryFilterMenuListener != null) {
            mItineraryFilterMenuListener.close();
            mItineraryFilterMenuListener.select(mItinerary);
        }
        mEventBus.post(new FilterMap(mItinerary));
    }
}
