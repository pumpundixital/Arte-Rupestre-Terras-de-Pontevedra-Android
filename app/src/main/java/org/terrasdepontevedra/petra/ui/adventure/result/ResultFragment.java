package org.terrasdepontevedra.petra.ui.adventure.result;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.txusballesteros.widgets.FitChart;

import org.terrasdepontevedra.petra.PetraApplication;
import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.di.component.DaggerFragmentComponent;
import org.terrasdepontevedra.petra.di.component.FragmentComponent;
import org.terrasdepontevedra.petra.di.module.AdapterModule;
import org.terrasdepontevedra.petra.di.module.FragmentModule;
import org.terrasdepontevedra.petra.domain.model.quiz.Question;
import org.terrasdepontevedra.petra.domain.model.quiz.Quiz;
import org.terrasdepontevedra.petra.ui.adventure.score.ScoreActivity;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.widget.ListPopupWindow.WRAP_CONTENT;


public class ResultFragment extends DialogFragment implements ResultMvpView {


    @Inject
    ResultPresenter mResultPresenter;

    @BindView(R.id.tv_result)
    TextView mTvResult;

    @BindView(R.id.chart)
    FitChart mChart;

    @BindView(R.id.layout_results_chart)
    View mLayoutResultsChart;

    @BindViews({R.id.layout_question_1, R.id.layout_question_2, R.id.layout_question_3, R.id.layout_question_4, R.id.layout_question_5})
    List<View> mLayoutQuestions;

    private OnRestartQuiz mListener;

    private Quiz mQuiz;

    public ResultFragment() {
        // Required empty public constructor
    }


    public static ResultFragment newInstance(Quiz quiz) {
        ResultFragment fragment = new ResultFragment();
        fragment.setQuiz(quiz);
        return fragment;
    }

    private void setQuiz(Quiz quiz) {
        mQuiz = quiz;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        Window window = getDialog().getWindow();
        if (window != null) {
            window.requestFeature(Window.FEATURE_NO_TITLE);
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        return inflater.inflate(R.layout.fragment_result, container, false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentComponent fragmentComponent = DaggerFragmentComponent.builder()
                .fragmentModule(new FragmentModule(this))
                .adapterModule(new AdapterModule(getChildFragmentManager()))
                .applicationComponent(PetraApplication.getApplicationComponent())
                .build();

        fragmentComponent.inject(this);


        Dialog dialog = getDialog();
        if (dialog != null) {
            Window window = dialog.getWindow();
            if (window != null)
                window.setLayout((int) (getScreenWidth(getActivity()) * .7), WRAP_CONTENT);
            dialog.setCancelable(false);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mResultPresenter.attachView(this);
        mResultPresenter.init(mQuiz);
    }


    private static int getScreenWidth(Activity activity) {
        Point size = new Point();
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }

    public void setListener(OnRestartQuiz listener) {
        this.mListener = listener;
    }

    @OnClick(R.id.btn_continue)
    void onClickContinue() {
        this.dismiss();
        if(mQuiz.isAllCorrect()){
            getActivity().setResult(Activity.RESULT_OK,new Intent());
            getActivity().finish();
        }

        getActivity().finish();
    }

    @OnClick(R.id.btn_retry)
    void onClickRetry() {
        this.dismiss();
        mListener.restart();
    }

    @Override
    public void showResult(int result, int size) {
        mChart.setMinValue(0);
        mChart.setMaxValue(size);
        mChart.setValue(result);
        mTvResult.setText(String.format(Locale.getDefault(), "%d / %d", result, size));
        showResults();
    }

    public interface OnRestartQuiz {
        void restart();
    }

    public void showResults() {
        for (int i = 0; i < mQuiz.asList().size(); i++) {
            Question question = mQuiz.get(i);
            if (question.isCorrect())
                mLayoutQuestions.get(i).setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), android.R.color.holo_green_dark)));
        }
    }


}