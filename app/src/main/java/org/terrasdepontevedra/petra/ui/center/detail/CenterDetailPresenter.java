package org.terrasdepontevedra.petra.ui.center.detail;

import org.terrasdepontevedra.petra.domain.model.Center;
import org.terrasdepontevedra.petra.ui.base.Presenter;

public interface CenterDetailPresenter extends Presenter<CenterDetailMvpView>{
    void init(Center center);

    void goToPlace();

    void onClickButtonShared();

    void onWebItemClick();

    void onPhoneItemClick();

    void onEmailItemClick();
}
