package org.terrasdepontevedra.petra.ui.adventure.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.ui.adventure.quiz.pager.QuizPagerFragment;
import org.terrasdepontevedra.petra.ui.adventure.result.ResultFragment;
import org.terrasdepontevedra.petra.ui.base.BaseActivity;
import org.terrasdepontevedra.petra.util.services.FragmentService;

import javax.inject.Inject;

import butterknife.OnClick;

public class QuizActivity extends BaseActivity implements ResultFragment.OnRestartQuiz {

    private static final String ARGS_QUIZ_NAME = "ARGS_QUIZ_NAME";
    private static final String ARGS_QUIZ_ID = "ARGS_QUIZ_ID";

    @Inject
    FragmentService mFragmentService;

    public static Intent getCallingIntent(Context context, Integer quizId, String quizName) {
        Intent intent = new Intent(context, QuizActivity.class);
        intent.putExtra(ARGS_QUIZ_ID, quizId);
        intent.putExtra(ARGS_QUIZ_NAME, quizName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startQuiz();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        startQuiz();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_quiz;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }


    @OnClick(R.id.image_back_toolbar)
    public void onClickBack() {
        finish();
    }

    @Override
    public void restart() {
        startQuiz();
    }

    private void startQuiz(){
        Intent intent = getIntent();
        QuizPagerFragment fragment = QuizPagerFragment.newInstance(intent.getIntExtra(ARGS_QUIZ_ID, -1), intent.getStringExtra(ARGS_QUIZ_NAME));
        fragment.setListener(this);
        mFragmentService.add(fragment, R.id.container_main);
    }
}
