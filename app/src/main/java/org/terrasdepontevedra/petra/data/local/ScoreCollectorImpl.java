package org.terrasdepontevedra.petra.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.terrasdepontevedra.petra.domain.model.Score;
import org.terrasdepontevedra.petra.domain.model.ScoreCollection;
import org.terrasdepontevedra.petra.domain.model.base.Title;
import org.terrasdepontevedra.petra.domain.model.quiz.Quiz;

public class ScoreCollectorImpl implements ScoreCollector {

    private static final String SCORE_FILE = "SCORE_FILE";
    private static final String SCORE = "SCORE";
    private static final String DEFAULT_VALUE = "";
    private static final String SCORE_QUIZ = "SCORE_QUIZ";
    private final SharedPreferences mSharedPreferences;
    private final Gson mGson;

    public ScoreCollectorImpl(Context context, Gson gson) {
        mSharedPreferences = context.getSharedPreferences(SCORE_FILE, Context.MODE_PRIVATE);
        mGson = gson;
    }

    @Override
    public void setScore(ScoreCollection scoreCollection) {
        SharedPreferences.Editor edit = mSharedPreferences.edit();
        edit.putString(SCORE, mGson.toJson(scoreCollection));
        edit.apply();
    }

    @Override
    public ScoreCollection getScoreCollection() {
        return mGson.fromJson(mSharedPreferences.getString(SCORE, DEFAULT_VALUE), ScoreCollection.class);
    }

    @Override
    public boolean hasScoreSaved() {
        return mSharedPreferences.contains(SCORE);
    }

    @Override
    public ScoreCollection getQuizScore() {
        if (hasQuizScore())
            return getScoreQuiz();
        return generateEmptyCollection();
    }

    private ScoreCollection generateEmptyCollection() {
        ScoreCollection scoreCollection = new ScoreCollection();
        scoreCollection.add(new Score(Title.from("Quiz 1"), false));
        scoreCollection.add(new Score(Title.from("Quiz 2"), false));
        scoreCollection.add(new Score(Title.from("Quiz 3"), false));
        scoreCollection.add(new Score(Title.from("Quiz 4"), false));
        scoreCollection.add(new Score(Title.from("Quiz 5"), false));
        return scoreCollection;
    }

    @Override
    public boolean hasQuizScore() {
        return mSharedPreferences.contains(SCORE_QUIZ);
    }

    @Override
    public void setQuizScore(Quiz quiz) {
        Score quizScore = new Score(quiz.getTitle(), quiz.isAllCorrect());
        if (hasQuizScoreComplete(quizScore))
            return;

        ScoreCollection scoreQuiz = getScoreQuiz();
        if (scoreQuiz == null || scoreQuiz.isEmpty())
            scoreQuiz = generateEmptyCollection();

        scoreQuiz.set(scoreQuiz.indexOf(quizScore), quizScore);

        if (hasScoreSaved()) {
            ScoreCollection collection = getScoreCollection();
            int position = collection.indexOf(quizScore);
            collection.set(position, quizScore);
            setScore(collection);
        } else {
            SharedPreferences.Editor edit = mSharedPreferences.edit();
            edit.putString(SCORE_QUIZ, mGson.toJson(scoreQuiz));
            edit.apply();
        }
    }

    private boolean hasQuizScoreComplete(Score quiz) {
        try {
            return getQuizScore().get(quiz).isComplete();
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    private ScoreCollection getScoreQuiz() {
        return mGson.fromJson(mSharedPreferences.getString(SCORE_QUIZ, DEFAULT_VALUE), ScoreCollection.class);
    }
}
