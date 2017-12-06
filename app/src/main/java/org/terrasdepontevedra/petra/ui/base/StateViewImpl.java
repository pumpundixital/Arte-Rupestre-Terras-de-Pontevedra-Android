package org.terrasdepontevedra.petra.ui.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.terrasdepontevedra.petra.R;

import io.reactivex.exceptions.CompositeException;

@SuppressLint("InflateParams")
public class StateViewImpl implements StateView {

    private ViewGroup mRootView;
    private View mMainView;
    private final LayoutInflater mLayoutInflater;

    public StateViewImpl(Context context) {
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public void setMainView(View mainView) {
        mMainView = mainView;
        if (mMainView == null)
            return;
        mRootView = (ViewGroup) mMainView.getParent();
    }

    @Override
    public void showLoading() {
        if (mRootView == null)
            return;
        mRootView.removeAllViews();
        View view = mLayoutInflater.inflate(R.layout.view_loading, null);
        mRootView.addView(view);
    }

    @Override
    public void showMain() {
        if (mRootView == null)
            return;
        mRootView.removeAllViews();
        mRootView.addView(mMainView);
    }

    @Override
    public void showError() {
        showError(R.string.message_error_some_error_occur);
    }

    @Override
    public void showErrorNetwork() {
        showError(R.string.message_error_not_internet_conection);
    }

    @Override
    public void showEmptyView() {
        showError(R.string.message_error_empty_view);
    }

    @Override
    public void showError(Throwable e) {
        e.printStackTrace();
        if (e instanceof CompositeException) {
            for (Throwable throwable : ((CompositeException) e).getExceptions()) {
                if (e.toString().contains("SocketTimeoutException"))
                    showError();
                if (throwable.toString().contains("java.net."))
                    showErrorNetwork();
            }
            return;
        }

        showError();
    }


    private void showError(@StringRes int message) {
        if (mRootView == null)
            return;
        mRootView.removeAllViews();
        View view = mLayoutInflater.inflate(R.layout.view_error, null);
        TextView textView = view.findViewById(R.id.tv_message_error);
        textView.setText(message);
        mRootView.addView(view);
    }
}
