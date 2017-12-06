package org.terrasdepontevedra.petra.ui.map.filter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.Itinerary;
import org.terrasdepontevedra.petra.domain.model.ItineraryCollection;
import org.terrasdepontevedra.petra.util.DimensUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItineraryFilterMenu extends PopupWindow implements ItineraryFilterMenuListener {


    @BindView(R.id.rc_filter_itinerary)
    RecyclerView mRecycler;

    private ItineraryFilterAdapter mAdapter;
    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private final ItineraryCollection mItineraryCollection;
    private final EventBus mEventBus;

    public ItineraryFilterMenu(Context context,
                               ItineraryCollection itineraryCollection,
                               EventBus eventBus) {
        super(context);
        mLayoutInflater = LayoutInflater.from(context);
        mContext = context;
        mItineraryCollection = itineraryCollection;
        mEventBus = eventBus;
        init();
    }

    @SuppressLint("InflateParams")
    private void init() {
        setAdapter();
        View view = mLayoutInflater.inflate(R.layout.dialog_filter_itineraries, null);
        ButterKnife.bind(this, view);
        setFocusable(true);
        setBackgroundDrawable(null);
        setWidth(DimensUtil.convertDpToPixel(mContext, 200));
        setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        setContentView(view);
        mRecycler.setLayoutManager(new LinearLayoutManager(mContext));
        mRecycler.setAdapter(mAdapter);
    }

    private void setAdapter() {
        Itinerary itinerary = Itinerary.seeAll(mContext.getResources().getString(R.string.see_all));
        if (!mItineraryCollection.contains(itinerary))
            mItineraryCollection.add(0, itinerary);

        if (!mItineraryCollection.hasSomeSelected())
            mItineraryCollection.getFirst().setChecked(true);

        mAdapter = new ItineraryFilterAdapter(mContext, mItineraryCollection, mEventBus);
        mAdapter.setItineraryFilterMenuListener(this);
    }

    @Override
    public void close() {
        dismiss();
    }

    @Override
    public void select(Itinerary itinerary) {
        mItineraryCollection.removeSelect();
        mItineraryCollection.select(itinerary);
    }
}
