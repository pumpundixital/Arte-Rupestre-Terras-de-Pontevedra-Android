package org.terrasdepontevedra.petra.ui.adventure.quiz.question;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.quiz.Answer;
import org.terrasdepontevedra.petra.ui.views.SmoothCheckBox;
import org.terrasdepontevedra.petra.util.interfaces.Binder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

class AnswerViewHolder extends RecyclerView.ViewHolder implements Binder<Answer> {
    @BindView(R.id.text_item_survey_title)
    TextView mText;

    @BindView(R.id.check_survey)
    SmoothCheckBox mCheck;

    private Answer mAnswer;
    private final AnswerCheckedListener mListener;

    AnswerViewHolder(View view, AnswerCheckedListener listener) {
        super(view);
        mListener = listener;
        ButterKnife.bind(this, view);

        mCheck.setOnCheckedChangeListener(null);
        mCheck.setOnClickListener(null);
    }

    @Override
    public void bind(Answer answer) {
        mAnswer = answer;
        mText.setText(answer.asString());
        mCheck.setChecked(answer.isChecked());
    }

    @OnClick(R.id.container_item_answer)
    public void onClickItem() {
        mListener.onClickItem(mAnswer);
    }
}
