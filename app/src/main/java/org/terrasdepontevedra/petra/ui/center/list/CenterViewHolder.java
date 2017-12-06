package org.terrasdepontevedra.petra.ui.center.list;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.GlideApp;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Center;
import org.terrasdepontevedra.petra.util.interfaces.Binder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

class CenterViewHolder extends RecyclerView.ViewHolder implements Binder<Center> {
    @BindView(R.id.tv_title)
    TextView mTvTitle;
    @BindView(R.id.tv_description)
    TextView mTvDescription;
    @BindView(R.id.img_center)
    ImageView mImgCenter;

    private final View mItemView;
    private final OnCenterClick mOnCenterClick;
    private Center mCenter;

    CenterViewHolder(View itemView, OnCenterClick onCenterClick) {
        super(itemView);
        mItemView = itemView;
        mOnCenterClick = onCenterClick;
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(Center center) {
        mCenter = center;
        mTvTitle.setText(center.getTitle().asString());
        if (center.getDescription().asString() != null) {
            mTvDescription.setText(center.getDescription().asString().replace("\n", "").replace("<p>", "").replace("</p>", ""));
        }

        GlideApp
                .with(mImgCenter.getContext())
                .load(center.getImage().getUrl().asString())
                .centerCrop()
                .into(mImgCenter);
    }

    @OnClick({R.id.container_center, R.id.btn_see_more})
    public void onClickCenter() {
        mOnCenterClick.onCenterClicked(mCenter, mItemView);
    }
}
