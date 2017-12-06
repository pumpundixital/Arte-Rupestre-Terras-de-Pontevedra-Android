package org.terrasdepontevedra.petra.ui.itinerary.list;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.GlideApp;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.util.interfaces.Binder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

class ItineraryViewHolder extends RecyclerView.ViewHolder implements Binder<Itinerary> {

    @BindView(R.id.tv_description)
    TextView mDescription;
    @BindView(R.id.layout_container_itinerary_image)
    View mViewContainerImage;
    @BindView(R.id.image_item_itinerary)
    ImageView mImage;
    @BindView(R.id.tv_title)
    TextView mTvTitle;

    private Itinerary mItem;

    private final OnItinerariesListListener mListener;

    ItineraryViewHolder(View view, OnItinerariesListListener listener) {
        super(view);
        ButterKnife.bind(this, view);
        mListener = listener;
    }


    @Override
    public void bind(Itinerary entity) {
        mItem = entity;
        mTvTitle.setText(Html.fromHtml(mItem.getTitle().asString()));
        mDescription.setText(Html.fromHtml(mItem.getDescription().asString()));

        GlideApp
                .with(mImage.getContext())
                .load(mItem.getImage().getUrl().asString())
                .centerCrop()
                .into(mImage);
    }


    @OnClick({R.id.image_item_itinerary, R.id.layout_btn_start_itinerary, R.id.btn_see_more})
    void onClickItem() {
        if (null != mListener) {
            mListener.onClickItinerary(mItem, mViewContainerImage);
        }
    }

    @OnClick(R.id.btn_see_map)
    void onClickSeeMap() {
        if (null != mListener) {
            mListener.onClickSeeMap(mItem);
        }
    }
}
