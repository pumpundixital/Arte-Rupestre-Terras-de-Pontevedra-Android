package org.terrasdepontevedra.petra.ui.gallery.horizontal;

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


public class GalleryHorizontalAdapter extends RecyclerView.Adapter<GalleryHorizontalAdapter.ViewHolder> {

    private final List<String> mValues;
    private OnClickImageGalleryAdapter mListener;

    GalleryHorizontalAdapter(List<String> items) {
        mValues = items;
    }

    public void setListener(OnClickImageGalleryAdapter listener) {
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gallery_horizontal, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        GlideApp
                .with(holder.mView.getContext())
                .load(holder.mItem)
                .centerCrop()
                .into(holder.mImage);

        holder.mImage.setOnClickListener(v -> mListener.onClickItem(position));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        String mItem;

        @BindView(R.id.image_item_gallery)
        ImageView mImage;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
            mView=view;
        }
    }

    public interface OnClickImageGalleryAdapter{
        void onClickItem(int position);
    }
}
