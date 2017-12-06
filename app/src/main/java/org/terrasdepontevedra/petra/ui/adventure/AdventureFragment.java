package org.terrasdepontevedra.petra.ui.adventure;


import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.ui.adventure.quiz.QuizDialog;
import org.terrasdepontevedra.petra.ui.adventure.score.ScoreActivity;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.ui.wizard.WizardActivity;

import butterknife.OnClick;

public class AdventureFragment extends BaseFragment {

    public AdventureFragment() {
        // Required empty public constructor
    }

    public static AdventureFragment newInstance() {
        return new AdventureFragment();
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_adventure;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @OnClick(R.id.btn_quiz)
    public void onClickQuiz() {
        QuizDialog.newInstance().show(getChildFragmentManager(), QuizDialog.class.getName());
    }

    @OnClick(R.id.btn_yincana)
    public void onClickYincana() {
        startActivity(WizardActivity.getCallingIntent(getContext(), WizardActivity.WizardType.YINCANA));
    }

    @OnClick(R.id.btn_score)
    public void onClickScore() {
        startActivity(ScoreActivity.getCallingIntent(getContext()));
    }

}
