package org.terrasdepontevedra.petra.ui.walk.pmp;

/**
 * Created by pablopumpun on 18/1/18.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
