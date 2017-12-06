package org.terrasdepontevedra.petra.ui.adventure.score;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Score;

import butterknife.BindView;
import butterknife.ButterKnife;

class ScoreViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_text_score)
    TextView mTvScore;
    @BindView(R.id.container_score)
    View mContainer;

    ScoreViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Score score) {
        mTvScore.setText(score.getTitle().asString());
        mContainer.setAlpha(score.isComplete() ? 1F : 0.3F);
    }
}
