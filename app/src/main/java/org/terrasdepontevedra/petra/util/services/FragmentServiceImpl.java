package org.terrasdepontevedra.petra.util.services;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import org.terrasdepontevedra.petra.R;

public class FragmentServiceImpl implements FragmentService {

    private final FragmentManager mFragmentManager;

    public FragmentServiceImpl(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }


    @Override
    public void add(Fragment fragment, @IdRes int idRes) {
        add(fragment, idRes, fragment.getTag());

    }

    @Override
    public void add(Fragment fragment, @IdRes int idRes, String tag) {
        mFragmentManager
                .beginTransaction()
                .add(idRes, fragment, tag)
                .commit();
    }

    @Override
    public void replaceWithVerticalAnimation(Fragment fragment, @IdRes int idRes) {
        mFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.enter_from_top, R.anim.exit_from_bottom)
                .replace(idRes, fragment)
                .commit();

    }

    @Override
    public void replaceAndAddToBackStack(Fragment fragment, @IdRes int idRes) {
        mFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right
                )
                .replace(idRes, fragment)
                .addToBackStack(fragment.getClass().getName())
                .commit();
    }

    @Override
    public void replace(Fragment fragment, @IdRes int idRes) {
        mFragmentManager
                .beginTransaction()
                .setCustomAnimations(
                        R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right
                )
                .replace(idRes, fragment)
                .commit();
    }

    @Override
    public int getCount() {
        return mFragmentManager.getBackStackEntryCount();
    }

    @Override
    public void popStack() {
        mFragmentManager.popBackStack();
    }


}
