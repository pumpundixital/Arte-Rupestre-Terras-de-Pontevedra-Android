package org.terrasdepontevedra.petra.ui.walk.base;

public interface BaseView {

    void stateError();

    void stateEmpty();

    void stateLoading();

    void stateMain();

    void stateErrorNetWork();
}