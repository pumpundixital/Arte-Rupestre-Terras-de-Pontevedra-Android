package org.terrasdepontevedra.petra.ui.walk.walks;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.daimajia.slider.library.GlideApp;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.data.walk.WalkApi;
import org.terrasdepontevedra.petra.domain.model.walk.Itinerary;
import org.terrasdepontevedra.petra.ui.itinerary.ItineraryActivity;
import org.terrasdepontevedra.petra.ui.walk.single.WalkActivity;
import org.terrasdepontevedra.petra.util.ScreenUtils;

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

        if(holder.mItem.getFeaturedImage()!=null) {
            GlideApp
                    .with(holder.mView.getContext())
                    .load(holder.mItem.getFeaturedImage().getUrlMedium())
                    .centerCrop()
                    .into(holder.mImage);
        }

        holder.mText.setText(Html.fromHtml(holder.mItem.getTitle()));

        holder.mTextDesc.setText(Html.fromHtml(holder.mItem.getExcerpt()));

        if(position!=0){
            LinearLayout ll = new LinearLayout(holder.mView.getContext());
            ll.setOrientation(LinearLayout.VERTICAL);

            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.mView.getLayoutParams();
            params.setMargins(0, 32, 0, 0);
        }

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
        @BindView(R.id.text_walks_desc)
        TextView mTextDesc;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            mView=view;
            mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v.getContext().startActivity(WalkActivity.getCallingIntent(v.getContext(),mItem.getId()));
                }
            });
        }
    }
}
