package org.terrasdepontevedra.petra.ui.adventure.quiz.question;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.quiz.Answer;
import org.terrasdepontevedra.petra.domain.model.quiz.AnswerCollection;
import org.terrasdepontevedra.petra.domain.model.quiz.Question;
import org.terrasdepontevedra.petra.domain.pojo.ActivateButtonNext;
import org.terrasdepontevedra.petra.domain.pojo.DeactivateButtonNext;
import org.terrasdepontevedra.petra.ui.adventure.quiz.QuizMvpView;
import org.terrasdepontevedra.petra.ui.adventure.quiz.QuizPresenter;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;

public class QuizFragment extends BaseFragment implements QuizMvpView, AnswerCheckedListener {

    @Inject
    QuizPresenter mQuizPresenter;

    @BindView(R.id.recycler_opinion)
    RecyclerView mRecycler;
    @BindView(R.id.opinion_title)
    TextView mTextTitle;

    private Question mQuestion;
    private final EventBus mEventBus = EventBus.getDefault();


    public static QuizFragment newInstance(Question question) {
        QuizFragment fragment = new QuizFragment();
        fragment.setQuestion(question);
        return fragment;
    }

    private void setQuestion(Question question) {
        mQuestion = question;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mQuizPresenter.attachView(this);
        mQuizPresenter.init(mQuestion);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mQuizPresenter.detachView();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_quiz;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void setQuestion(String question) {
        mTextTitle.setText(question);
    }

    @Override
    public void setAnswers(AnswerCollection possibleAnswer) {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecycler.setLayoutManager(manager);
        mRecycler.setAdapter(new QuizAdapter(possibleAnswer, this));
    }

    @Override
    public void onClickItem(Answer answer) {
        if (answer.isChecked())
            mEventBus.post(new ActivateButtonNext());
        else
            mEventBus.post(new DeactivateButtonNext());
    }

    public void tryActivateButtonNext() {
        if (mQuestion.hasSomeAnswerSelected())
            mEventBus.post(new ActivateButtonNext());
        else
            mEventBus.post(new DeactivateButtonNext());
    }
}
