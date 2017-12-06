package org.terrasdepontevedra.petra.ui.center.list;

import android.view.View;

import org.terrasdepontevedra.petra.domain.model.Center;

interface OnCenterClick {
    void onCenterClicked(Center center, View transitionView);
}
