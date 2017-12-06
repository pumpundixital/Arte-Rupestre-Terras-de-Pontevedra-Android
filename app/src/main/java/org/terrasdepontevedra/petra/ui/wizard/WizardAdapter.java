package org.terrasdepontevedra.petra.ui.wizard;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.terrasdepontevedra.petra.R;

import java.util.ArrayList;
import java.util.List;


public class WizardAdapter extends FragmentPagerAdapter {
    private int LAST_POSITION;
    private List<Fragment> mFragments;

    public WizardAdapter(FragmentManager fm) {
        super(fm);
    }


    void loadFragments(WizardActivity.WizardType wizardType){
        mFragments = new ArrayList<>();
        if(wizardType== WizardActivity.WizardType.INTRO) {
            LAST_POSITION = 4;
            mFragments.add(WizardFragment.newInstance(0, R.drawable.ic_map, R.string.wizard_title_1, R.string.wizard_paragraph_1));
            mFragments.add(WizardFragment.newInstance(1, R.drawable.ic_adventure, R.string.wizard_title_2, R.string.wizard_paragraph_2));
            mFragments.add(WizardFragment.newInstance(2, R.drawable.ic_itinerary, R.string.wizard_title_3, R.string.wizard_paragraph_3));
            mFragments.add(WizardFragment.newInstance(3, R.drawable.spiral, R.string.wizard_title_4, R.string.wizard_paragraph_4));
            mFragments.add(WizardFragment.newInstance(LAST_POSITION, R.drawable.ic_rock_art, R.string.wizard_title_5, R.string.wizard_paragraph_5));
        }
        else if(wizardType== WizardActivity.WizardType.YINCANA){
            LAST_POSITION = 3;
            mFragments.add(WizardFragment.newInstance(0, R.drawable.ic_troglodyte, R.string.wizard_title_1_yincana, R.string.wizard_desc_1_yincana));
            mFragments.add(WizardFragment.newInstance(1, R.drawable.ic_house, R.string.wizard_title_2_yincana, R.string.wizard_desc_2_yincana));
            mFragments.add(WizardFragment.newInstance(2, R.drawable.ic_rock_art, R.string.wizard_title_3_yincana, R.string.wizard_desc_3_yincana));
            mFragments.add(WizardFragment.newInstance(LAST_POSITION, R.drawable.ic_wheel, R.string.wizard_title_4_yincana, R.string.wizard_desc_4_yincana));
        }
        notifyDataSetChanged();
    }


    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        if(mFragments!=null) {
            return mFragments.size();
        }
        else{
            return 0;
        }
    }

    public int getLastPosition() {
        return LAST_POSITION;
    }
}
