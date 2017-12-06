package org.terrasdepontevedra.petra.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.terrasdepontevedra.petra.PetraApplication;
import org.terrasdepontevedra.petra.di.component.DaggerFragmentComponent;
import org.terrasdepontevedra.petra.di.component.FragmentComponent;
import org.terrasdepontevedra.petra.di.module.AdapterModule;
import org.terrasdepontevedra.petra.di.module.FragmentModule;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements StateView {
    @Inject
    StateView mStateView;

    private Unbinder mUnbind;
    private Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
        inject();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutRes(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnbind = ButterKnife.bind(this, view);
        setMainView(getView());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mUnbind != null)
            mUnbind.unbind();
    }

    @Override
    public Context getContext() {
        return mContext;
    }


    @Override
    public void showEmptyView() {
        mStateView.showEmptyView();
    }

    @Override
    public void showError() {
        mStateView.showError();
    }

    @Override
    public void showErrorNetwork() {
        mStateView.showErrorNetwork();
    }

    @Override
    public void showLoading() {
        mStateView.showLoading();
    }

    @Override
    public void showMain() {
        mStateView.showMain();
    }

    @Override
    public void showError(Throwable throwable) {
        mStateView.showError(throwable);
    }

    @Override
    public void setMainView(View view) {
        mStateView.setMainView(view);
    }

    protected FragmentComponent getFragmentComponent() {
        return DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .adapterModule(new AdapterModule(getChildFragmentManager()))
                .applicationComponent(PetraApplication.getApplicationComponent())
                .build();
    }

    protected PetraApplication getApplication() {
        return ((PetraApplication) getActivity().getApplication());
    }

    @LayoutRes
    protected abstract int getLayoutRes();

    protected abstract void inject();

}
