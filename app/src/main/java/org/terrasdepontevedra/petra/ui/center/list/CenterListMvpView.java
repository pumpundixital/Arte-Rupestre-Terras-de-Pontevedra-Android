package org.terrasdepontevedra.petra.ui.center.list;

import org.terrasdepontevedra.petra.domain.model.CenterCollection;
import org.terrasdepontevedra.petra.ui.base.MvpView;
import org.terrasdepontevedra.petra.ui.base.StateView;

interface CenterListMvpView extends MvpView, StateView{
    void loadCenterCollectionOnView(CenterCollection centerCollection);
}
