package org.terrasdepontevedra.petra.ui.base;

public class BasePresenter<T extends MvpView> implements Presenter<T> {
    protected T mMvpView;

    @Override
    public void attachView(T view) {
        mMvpView = view;
    }

    @Override
    public void detachView() {
        mMvpView = null;
    }
}
