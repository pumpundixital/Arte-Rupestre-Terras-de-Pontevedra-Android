package org.terrasdepontevedra.petra.ui.adventure.score.dialog;

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
import org.terrasdepontevedra.petra.ui.adventure.score.ScoreActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ScoreDialog extends DialogFragment {


    public static ScoreDialog newInstance() {
        return new ScoreDialog();
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
        return inflater.inflate(R.layout.dialog_score, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.btn_continue)
    public void onClick(){
        dismiss();
    }

    @OnClick(R.id.btn_show_scores)
    public void onClickScores(){
        dismiss();
        startActivity(ScoreActivity.getCallingIntent(getContext()));
    }

}
