package org.terrasdepontevedra.petra.ui.adventure.quiz.pager;

import org.terrasdepontevedra.petra.ui.base.Presenter;


public interface QuizPagerPresenter extends Presenter<QuizPagerMvpView> {
    void init(Integer id, String name);

    void onNextItem(int currentItemPosition);

}
