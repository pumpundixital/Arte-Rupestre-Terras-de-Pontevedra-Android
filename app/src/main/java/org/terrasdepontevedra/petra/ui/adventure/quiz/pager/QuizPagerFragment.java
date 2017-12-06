package org.terrasdepontevedra.petra.ui.adventure.quiz.pager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.quiz.Quiz;
import org.terrasdepontevedra.petra.domain.pojo.ActivateButtonNext;
import org.terrasdepontevedra.petra.domain.pojo.DeactivateButtonNext;
import org.terrasdepontevedra.petra.ui.adventure.quiz.question.QuizFragment;
import org.terrasdepontevedra.petra.ui.adventure.result.ResultFragment;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.ui.views.NoSwipeViewPager;
import org.terrasdepontevedra.petra.util.services.FragmentService;
import org.terrasdepontevedra.petra.util.views.CirclePageIndicator;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class QuizPagerFragment extends BaseFragment implements QuizPagerMvpView {
    private static final String ARGS_QUIZ_NAME = "ARGS_QUIZ_NAME";
    private static final String ARGS_QUIZ_ID = "ARGS_QUIZ_ID";

    @Inject
    QuizPagerPresenter mQuizPagerPresenter;
    @Inject
    EventBus mEventBus;
    @Inject
    FragmentService mFragmentService;

    @BindView(R.id.pager_quiz)
    NoSwipeViewPager mViewPager;
    @BindView(R.id.indicator)
    CirclePageIndicator mIndicator;
    @BindView(R.id.text_opinion_next)
    TextView mViewNext;
    @BindView(R.id.container_quiz_bot)
    LinearLayout mLinearLayout;

    private int mQuizId;
    private String mQuizName;
    private ResultFragment.OnRestartQuiz mListener;

    public static QuizPagerFragment newInstance(Integer id, String name) {
        QuizPagerFragment quizPagerFragment = new QuizPagerFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARGS_QUIZ_ID, id);
        bundle.putString(ARGS_QUIZ_NAME, name);
        quizPagerFragment.setArguments(bundle);
        return quizPagerFragment;
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_page_quiz;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mQuizId = getArguments().getInt(ARGS_QUIZ_ID);
            mQuizName = getArguments().getString(ARGS_QUIZ_NAME);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mQuizPagerPresenter.attachView(this);
        mQuizPagerPresenter.init(mQuizId, mQuizName);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!mEventBus.isRegistered(this))
            mEventBus.register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mEventBus.isRegistered(this))
            mEventBus.unregister(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mQuizPagerPresenter.detachView();
    }

    @Override
    public void loadQuizOnView(Quiz quiz) {
        QuizPagerAdapter adapter = new QuizPagerAdapter(getFragmentManager(), quiz);
        mViewPager.setAdapter(adapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                ((QuizFragment) adapter.getItem(position)).tryActivateButtonNext();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mIndicator.setViewPager(mViewPager);
    }

    @Override
    public void next() {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
    }

    @Override
    public void showFinish(Quiz quiz) {
        ResultFragment fragment = ResultFragment.newInstance(quiz);
        fragment.setListener(mListener);
        fragment.show(getChildFragmentManager(), ResultFragment.class.getName());
    }

    @Override
    public void setFinishTextOnButton() {
        mViewNext.setText(R.string.action_finish);
    }

    @Override
    public void setNextTextOnButton() {
        mViewNext.setText(R.string.action_next);
    }

    @OnClick(R.id.text_opinion_next)
    void onClickNext() {
        mQuizPagerPresenter.onNextItem(mViewPager.getCurrentItem());
    }


    @OnClick(R.id.text_opinion_back)
    void onClickBack() {
        mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
    }

    @Subscribe
    public void onEvent(ActivateButtonNext activateButtonNext) {
        mViewNext.setEnabled(true);
    }

    @Subscribe
    public void onEvent(DeactivateButtonNext deactivateButtonNext) {
        mViewNext.setEnabled(false);
    }

    public void setListener(ResultFragment.OnRestartQuiz listener) {
        this.mListener = listener;
    }
}
