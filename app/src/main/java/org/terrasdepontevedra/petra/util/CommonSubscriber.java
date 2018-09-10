package org.terrasdepontevedra.petra.util;


import org.terrasdepontevedra.petra.ui.walk.base.BaseView;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.subscribers.ResourceSubscriber;

public abstract class CommonSubscriber<T> extends ResourceSubscriber<T> {
    private BaseView mView;

    protected CommonSubscriber(BaseView view) {
        this.mView = view;
    }

    protected CommonSubscriber() {

    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        if (mView == null)
            return;
        if (e instanceof CompositeException) {
            for (Throwable throwable : ((CompositeException) e).getExceptions()) {
                if (e.toString().contains("SocketTimeoutException"))
                    mView.stateError();
                if (throwable.toString().contains("java.net."))
                    mView.stateErrorNetWork();
            }
            return;
        }

        mView.stateError();
    }
}