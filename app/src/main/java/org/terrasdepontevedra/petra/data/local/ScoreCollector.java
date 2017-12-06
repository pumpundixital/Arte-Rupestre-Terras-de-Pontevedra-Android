package org.terrasdepontevedra.petra.data.local;

import org.terrasdepontevedra.petra.domain.model.ScoreCollection;
import org.terrasdepontevedra.petra.domain.model.quiz.Quiz;

public interface ScoreCollector {

    void setScore(ScoreCollection scoreCollection);

    ScoreCollection getScoreCollection();

    boolean hasScoreSaved();

    ScoreCollection getQuizScore();

    boolean hasQuizScore();

    void setQuizScore(Quiz quiz);
}
