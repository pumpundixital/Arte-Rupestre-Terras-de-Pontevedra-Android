package org.terrasdepontevedra.petra.ui.walk.place;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daimajia.slider.library.GlideApp;

import org.terrasdepontevedra.petra.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by pablopumpun on 7/9/17.
 */

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.ViewHolder> {

    private final List<String> mValues;
    OnGalleryClick mListener;

    public GalleryAdapter(List<String> items, OnGalleryClick listener) {
        mValues = items;
        mListener=listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gallery, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        if(holder.mImage!=null && !holder.mItem.isEmpty()) {
            GlideApp.with(holder.mView.getContext())
                    .load(holder.mItem)
                    .into(holder.mImage);
            holder.mImage.setOnClickListener(view -> {
                Timber.i("on click");
                mListener.onClickImageWithUrl(holder.mItem);
            });
        }


    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public String mItem;

        @BindView(R.id.image_item_gallery)
        ImageView mImage;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            mView=view;
        }
    }

    public interface OnGalleryClick{
        void onClickImageWithUrl(String url);
    }
}
