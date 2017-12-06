package org.terrasdepontevedra.petra.ui.place;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.terrasdepontevedra.petra.domain.model.PlaceCollection;
import org.terrasdepontevedra.petra.ui.place.content.PlaceContentFragment;

public class PlacePagerAdapter extends FragmentPagerAdapter {
    private PlaceCollection mPlaceCollection;

    public PlacePagerAdapter(FragmentManager fm) {
        super(fm);
        mPlaceCollection = new PlaceCollection();
    }

    public void setPlaceCollection(PlaceCollection placeCollection) {
        mPlaceCollection = placeCollection;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        return PlaceContentFragment.newInstance(mPlaceCollection.get(position));
    }


    @Override
    public int getCount() {
        return mPlaceCollection.size();
    }

}