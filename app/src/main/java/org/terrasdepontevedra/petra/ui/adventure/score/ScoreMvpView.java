package org.terrasdepontevedra.petra.ui.adventure.score;

import org.terrasdepontevedra.petra.domain.model.ScoreCollection;
import org.terrasdepontevedra.petra.ui.base.MvpView;
import org.terrasdepontevedra.petra.ui.base.StateView;

interface ScoreMvpView extends MvpView, StateView{
    void loadScoreOnView(ScoreCollection scoreCollection);

    void showTitle();
}
