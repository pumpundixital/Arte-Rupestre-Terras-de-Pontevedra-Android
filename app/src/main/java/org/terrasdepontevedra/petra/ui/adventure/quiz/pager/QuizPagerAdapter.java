package org.terrasdepontevedra.petra.ui.adventure.quiz.pager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import org.terrasdepontevedra.petra.domain.model.quiz.Quiz;
import org.terrasdepontevedra.petra.ui.adventure.quiz.question.QuizFragment;


class QuizPagerAdapter extends FragmentStatePagerAdapter {

    private final Quiz mSurveys;

    QuizPagerAdapter(FragmentManager fm, Quiz surveys) {
        super(fm);
        mSurveys = surveys;
    }

    @Override
    public Fragment getItem(int position) {
        return QuizFragment.newInstance(mSurveys.get(position));
    }

    @Override
    public int getCount() {
        return mSurveys.size();
    }


}
