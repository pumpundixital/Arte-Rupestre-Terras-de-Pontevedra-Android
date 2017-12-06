package org.terrasdepontevedra.petra.ui.base;

import android.view.View;

public interface StateView {

    void setMainView(View view);

    void showLoading();

    void showMain();

    void showError();

    void showErrorNetwork();

    void showEmptyView();

    void showError(Throwable throwable);

}
