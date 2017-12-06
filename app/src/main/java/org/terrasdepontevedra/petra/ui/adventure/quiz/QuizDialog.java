package org.terrasdepontevedra.petra.ui.adventure.quiz;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.util.Constants;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class QuizDialog extends DialogFragment {


    public static QuizDialog newInstance() {
        return new QuizDialog();
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
        return inflater.inflate(R.layout.dialog_quiz, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.btn_quiz_1)
    public void onClickQuiz1() {
        startActivityForResult(QuizActivity.getCallingIntent(getActivity(), R.raw.quiz1, getString(R.string.quiz_1)), Constants.RESULT_QUIZ);
        dismiss();
    }

    @OnClick(R.id.btn_quiz_2)
    public void onClickQuiz2() {
        startActivityForResult(QuizActivity.getCallingIntent(getActivity(), R.raw.quiz2, getString(R.string.quiz_2)), Constants.RESULT_QUIZ);
        dismiss();
    }

    @OnClick(R.id.btn_quiz_3)
    public void onClickQuiz3() {
        startActivityForResult(QuizActivity.getCallingIntent(getActivity(), R.raw.quiz3, getString(R.string.quiz_3)), Constants.RESULT_QUIZ);
        dismiss();
    }

    @OnClick(R.id.btn_quiz_4)
    public void onClickQuiz4() {
        startActivityForResult(QuizActivity.getCallingIntent(getActivity(), R.raw.quiz4, getString(R.string.quiz_4)), Constants.RESULT_QUIZ);
        dismiss();
    }

    @OnClick(R.id.btn_quiz_5)
    public void onClickQuiz5() {
        startActivityForResult(QuizActivity.getCallingIntent(getActivity(), R.raw.quiz5, getString(R.string.quiz_5)), Constants.RESULT_QUIZ);
        dismiss();
    }
}
