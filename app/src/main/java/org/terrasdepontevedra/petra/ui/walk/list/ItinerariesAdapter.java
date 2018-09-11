package org.terrasdepontevedra.petra.ui.walk.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.GlideApp;
import com.google.android.gms.maps.model.LatLng;


import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.walk.Place;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class ItinerariesAdapter extends RecyclerView.Adapter<ItinerariesAdapter.ViewHolder> {

    private List<Place> mPlaces;
    private ItineraryListFragment mFragment;
    Random r = new Random();

    public ItinerariesAdapter(ItineraryListFragment fragment) {
        mFragment = fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_walk, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Place place = mPlaces.get(position);
        holder.setPlaceUrl(place.getLink());
        holder.setupTimeline(position);
        //holder.setMyLocation(mFragment.getLatLng(),place.getPosition());
        holder.setupTitle(place.getTitle());
        if(place.getFeaturedImage()!=null) {
            holder.setupImage(place.getFeaturedImage().getUrlMedium());
        }


    }

    @Override
    public int getItemCount() {
        if (mPlaces != null) {
            return mPlaces.size();
        } else return 0;
    }

    public void setItems(List<Place> places) {
        mPlaces = places;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //Timeline details design
        @BindView(R.id.etapa_timeline_start)
        View mTimelineStart;
        @BindView(R.id.etapa_timeline_normal)
        View mTimelineNormal;
        @BindView(R.id.etapa_timeline_end)
        View mTimelineEnd;
        @BindView(R.id.text_item_itinerary_title)
        TextView mTextTitle;
        @BindView(R.id.text_item_itinerary_desc)
        TextView mTextDesc;
        @BindView(R.id.diagonal_image_item_itinerary)
        ImageView mDiagonalImage;

        @BindView(R.id.text_itinerary_distance)
        TextView mTextDistance;

        private String mPlaceUrl;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }

        void setPlaceUrl(String placeUrl) {
            mPlaceUrl = placeUrl;
        }

        void setupImage(String imageUrl){
            GlideApp.with(mDiagonalImage.getContext()).load(imageUrl).into(mDiagonalImage);
        }

        void setMyLocation(LatLng myPos, LatLng placePos){

            if(myPos.latitude!=-1){
                //TODO: Formatear distancia
               // mTextDistance.setText((int)AndroidUtils.calculateDistance(myPos,placePos)+"m");
            }
            else{
               // mTextDistance.setVisibility(View.GONE);
            }
        }

        public void setupTimeline(int position) {

            if (position == 0) {
                mTimelineNormal.setVisibility(View.GONE);
                mTimelineStart.setVisibility(View.VISIBLE);
                mTimelineEnd.setVisibility(View.GONE);
            } else if (position == getItemCount() - 1) {
                mTimelineStart.setVisibility(View.GONE);
                mTimelineNormal.setVisibility(View.GONE);
                mTimelineEnd.setVisibility(View.VISIBLE);
            } else {
                mTimelineNormal.setVisibility(View.VISIBLE);
                mTimelineStart.setVisibility(View.GONE);
                mTimelineEnd.setVisibility(View.GONE);
            }
        }

        void setupTitle(String title) {
            mTextTitle.setText(title);
        }

        @OnClick()
        void onClickPlace() {
            mFragment.onClickPlace(mPlaceUrl);
        }

    }


}
