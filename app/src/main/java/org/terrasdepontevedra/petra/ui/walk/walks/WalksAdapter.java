package org.terrasdepontevedra.petra.ui.walk.walks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.GlideApp;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.walk.Itinerary;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WalksAdapter extends RecyclerView.Adapter<WalksAdapter.ViewHolder> {

    private final List<Itinerary> mValues;

    public WalksAdapter(List<Itinerary> itineraries) {
        mValues = itineraries;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_walks, parent, false);
        ButterKnife.bind(this,view);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        GlideApp
                .with(holder.mView.getContext())
                .load(holder.mItem.getFeaturedImage().getUrlMedium())
                .centerCrop()
                .into(holder.mImage);

        holder.mText.setText(holder.mItem.getTitle());

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public Itinerary mItem;

        @BindView(R.id.image_walks)
        ImageView mImage;

        @BindView(R.id.text_walks)
        TextView mText;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            mView=view;
        }
    }
}
