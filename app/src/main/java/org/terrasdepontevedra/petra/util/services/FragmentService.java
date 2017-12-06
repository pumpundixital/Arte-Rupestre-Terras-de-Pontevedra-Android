package org.terrasdepontevedra.petra.util.services;


import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;

public interface FragmentService {

    void add(Fragment fragment, @IdRes int idRes);

    void add(Fragment fragment, @IdRes int idRes, String tag);

    void replaceWithVerticalAnimation(Fragment fragment, @IdRes int idRes);

    void replaceAndAddToBackStack(Fragment fragment, @IdRes int idRes);

    void replace(Fragment fragment, @IdRes int idRes);

    int getCount();

    void popStack();
}
