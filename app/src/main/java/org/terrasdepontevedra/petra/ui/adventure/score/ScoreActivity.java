package org.terrasdepontevedra.petra.ui.adventure.score;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.ScoreCollection;
import org.terrasdepontevedra.petra.ui.adventure.quiz.QuizDialog;
import org.terrasdepontevedra.petra.ui.adventure.score.dialog.ScoreDialog;
import org.terrasdepontevedra.petra.ui.base.BaseActivity;
import org.terrasdepontevedra.petra.util.Constants;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ScoreActivity extends BaseActivity implements ScoreMvpView {

    @Inject
    ScorePresenter mScorePresenter;
    @Inject
    ScoreAdapter mScoreAdapter;

    @BindView(R.id.rc_score)
    RecyclerView mRcScore;
    @BindView(R.id.tv_title)
    AppCompatTextView mTvTitle;

    private boolean showDialog = false;

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ScoreActivity.class);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        showDialog = intent.getBooleanExtra(Constants.ARG_SHOW_DIALOG,false);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_score;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onNewIntent(getIntent());

        mRcScore.setLayoutManager(new GridLayoutManager(this, 2));
        mRcScore.setAdapter(mScoreAdapter);
        mScorePresenter.attachView(this);
        mScorePresenter.init();
    }

    @Override
    public void loadScoreOnView(ScoreCollection scoreCollection) {
        mScoreAdapter.setScoreCollection(scoreCollection);
    }

    @Override
    public void showTitle() {
        mTvTitle.setText(R.string.score);
    }

    @OnClick(R.id.image_back_toolbar)
    public void onClickBack() {
        finish();
    }


}
