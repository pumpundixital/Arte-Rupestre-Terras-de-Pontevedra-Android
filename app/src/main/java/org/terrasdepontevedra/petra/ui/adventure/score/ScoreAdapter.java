package org.terrasdepontevedra.petra.ui.adventure.score;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.ScoreCollection;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreViewHolder> {
    private ScoreCollection mScoreCollection;

    public ScoreAdapter() {
        mScoreCollection = new ScoreCollection();
    }

    @Override
    public ScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScoreViewHolder holder, int position) {
        holder.bind(mScoreCollection.get(position));
    }

    @Override
    public int getItemCount() {
        return mScoreCollection.size();
    }

    void setScoreCollection(ScoreCollection scoreCollection) {
        mScoreCollection = scoreCollection;
        notifyDataSetChanged();
    }
}
