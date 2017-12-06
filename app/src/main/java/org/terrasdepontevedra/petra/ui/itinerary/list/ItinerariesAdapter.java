package org.terrasdepontevedra.petra.ui.itinerary.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.ItineraryCollection;


public class ItinerariesAdapter extends RecyclerView.Adapter<ItineraryViewHolder> {

    private ItineraryCollection mItineraryCollection;
    private OnItinerariesListListener mListener;

    public ItinerariesAdapter() {
        mItineraryCollection = new ItineraryCollection();
    }

    void setListener(OnItinerariesListListener listener) {
        mListener = listener;
    }

    @Override
    public ItineraryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_itinerary, parent, false);
        return new ItineraryViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(final ItineraryViewHolder holder, int position) {
        holder.bind(mItineraryCollection.get(position));
    }

    @Override
    public int getItemCount() {
        return mItineraryCollection.size();
    }

    void setCollection(ItineraryCollection collection) {
        mItineraryCollection = collection;
        notifyDataSetChanged();
    }
}
