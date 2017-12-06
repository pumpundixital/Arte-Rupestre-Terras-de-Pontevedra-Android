package org.terrasdepontevedra.petra.ui.adventure.quiz.question;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.domain.model.quiz.Answer;
import org.terrasdepontevedra.petra.domain.model.quiz.AnswerCollection;

class QuizAdapter extends RecyclerView.Adapter<AnswerViewHolder> implements AnswerCheckedListener {
    private final AnswerCollection mAnswerCollection;
    private final AnswerCheckedListener mListener;

    QuizAdapter(AnswerCollection answerCollection, AnswerCheckedListener listener) {
        mAnswerCollection = answerCollection;
        mListener = listener;
    }

    @Override
    public AnswerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_quiz, parent, false);
        return new AnswerViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(AnswerViewHolder holder, int position) {
        holder.bind(mAnswerCollection.get(position));
    }

    @Override
    public int getItemCount() {
        return mAnswerCollection.size();
    }

    @Override
    public void onClickItem(Answer answer) {
        mAnswerCollection.deselect();
        mAnswerCollection.select(answer);
        mListener.onClickItem(answer);
        notifyDataSetChanged();
    }
}
