package org.terrasdepontevedra.petra.ui.map.filter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.ItineraryCollection;

class ItineraryFilterAdapter extends RecyclerView.Adapter<ItineraryFilterViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final ItineraryCollection mItineraryCollection;
    private final EventBus mEventBus;
    private ItineraryFilterMenuListener mItineraryFilterMenuListener;

    ItineraryFilterAdapter(Context context, ItineraryCollection itineraryCollection, EventBus eventBus) {
        mLayoutInflater = LayoutInflater.from(context);
        mItineraryCollection = itineraryCollection;
        mEventBus = eventBus;
    }

    void setItineraryFilterMenuListener(ItineraryFilterMenuListener itineraryFilterMenuListener) {
        mItineraryFilterMenuListener = itineraryFilterMenuListener;
    }

    @Override
    public ItineraryFilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.item_filter_itinerary, parent, false);
        return new ItineraryFilterViewHolder(view, mEventBus, mItineraryFilterMenuListener);
    }

    @Override
    public void onBindViewHolder(ItineraryFilterViewHolder holder, int position) {
        holder.bind(mItineraryCollection.get(position));
    }

    @Override
    public int getItemCount() {
        return mItineraryCollection.size();
    }

}
