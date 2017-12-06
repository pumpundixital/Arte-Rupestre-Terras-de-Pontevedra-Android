package org.terrasdepontevedra.petra.ui.adventure.quiz.question;

import org.terrasdepontevedra.petra.domain.model.quiz.Answer;

interface AnswerCheckedListener {
    void onClickItem(Answer answer);
}
