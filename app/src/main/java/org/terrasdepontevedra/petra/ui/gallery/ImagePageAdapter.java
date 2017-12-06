package org.terrasdepontevedra.petra.ui.gallery;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;


class ImagePageAdapter extends FragmentPagerAdapter {

    private final List<String> mStrings;

    ImagePageAdapter(FragmentManager fm, List<String> strings) {
        super(fm);
        mStrings = strings;
    }

    @Override
    public Fragment getItem(int position) {
        return ImageFragment.newInstance(mStrings.get(position));
    }

    @Override
    public int getCount() {
        return mStrings.size();
    }
}
