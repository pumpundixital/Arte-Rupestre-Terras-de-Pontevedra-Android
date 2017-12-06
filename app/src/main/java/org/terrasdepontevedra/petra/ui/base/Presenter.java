package org.terrasdepontevedra.petra.ui.base;

public interface Presenter<T extends MvpView> {

    void attachView(T view);

    void detachView();
}
