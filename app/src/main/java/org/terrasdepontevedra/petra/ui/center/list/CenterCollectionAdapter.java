package org.terrasdepontevedra.petra.ui.center.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.CenterCollection;


public class CenterCollectionAdapter extends RecyclerView.Adapter<CenterViewHolder> {

    private CenterCollection mCenterCollection;
    private OnCenterClick mOnCenterClick;

    public CenterCollectionAdapter() {
        mCenterCollection = new CenterCollection();
    }

    void setListener(OnCenterClick onCenterClick){

        mOnCenterClick = onCenterClick;
    }

    @Override
    public CenterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.item_center, parent, false);
        return new CenterViewHolder(view, mOnCenterClick);
    }

    @Override
    public void onBindViewHolder(CenterViewHolder holder, int position) {
        holder.bind(mCenterCollection.get(position));
    }

    @Override
    public int getItemCount() {
        return mCenterCollection.size();
    }

    void setCenterCollection(CenterCollection centerCollection) {
        mCenterCollection = centerCollection;
        notifyDataSetChanged();
    }
}
