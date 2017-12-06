package org.terrasdepontevedra.petra.ui.help;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.hanks.htextview.typer.TyperTextView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.ui.base.BaseFragment;
import org.terrasdepontevedra.petra.util.interfaces.Action;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DefaultObserver;
import io.reactivex.schedulers.Schedulers;

public class HelpFragment extends BaseFragment {

    private final String EMPTY = "";

    @BindView(R.id.tv_user)
    TyperTextView mTvUser;
    @BindView(R.id.tv_petra)
    TyperTextView mTvPetra;
    @BindView(R.id.btn_next)
    FrameLayout mFabNext;
    @BindView(R.id.img_arrow)
    ImageView mImageView;

    private int mCurrentPositionAction = 0;
    private SparseArray<Action> mActionSparseArray;
    private final AnimatorSet mAnimationSet = new AnimatorSet();
    private boolean mMustBeShowed;

    public HelpFragment() {
        // Required empty public constructor
    }

    public static HelpFragment newInstance() {
        return new HelpFragment();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_help;
    }

    @Override
    protected void inject() {
        getFragmentComponent().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActionSparseArray = new SparseArray<>();
        mActionSparseArray.put(0, () -> mTvUser.animateText(getString(R.string.help_petra_1)));
        mActionSparseArray.put(1, () -> {
            mMustBeShowed = true;
            mTvPetra.animateText(getString(R.string.help_petra_2));
        });
        mActionSparseArray.put(2, () -> {
            mMustBeShowed = true;
            mTvPetra.animateText(getString(R.string.help_petra_3));
        });
        mActionSparseArray.put(3, () -> {
            mMustBeShowed = true;
            mTvPetra.animateText(getString(R.string.help_petra_4));
        });
        mActionSparseArray.put(4, () -> mTvPetra.animateText(getString(R.string.help_petra_5)));
        mActionSparseArray.put(5, () -> {
            mTvUser.animateText(getString(R.string.help_petra_6));
            mTvPetra.setText(EMPTY);
        });
        mActionSparseArray.put(6, () -> {
            mMustBeShowed = true;
            mTvPetra.animateText(getString(R.string.help_petra_7));
        });
        mActionSparseArray.put(7, () -> {
            mMustBeShowed = true;
            mTvPetra.animateText(getString(R.string.help_petra_8));
        });
        mActionSparseArray.put(8, () -> mTvPetra.animateText(getString(R.string.help_petra_9)));
        mActionSparseArray.put(9, () -> {
            mTvPetra.setText(EMPTY);
            mTvUser.animateText("\uD83D\uDE31 " + getString(R.string.help_petra_10));
        });
        mActionSparseArray.put(10, () -> {
            mMustBeShowed = true;
            mTvPetra.animateText(getString(R.string.help_petra_11));
        });
        mActionSparseArray.put(11, () -> {
            mMustBeShowed = true;
            mTvPetra.animateText(getString(R.string.help_petra_12));

        });
        mActionSparseArray.put(12, () -> mTvPetra.animateText(getString(R.string.help_petra_13)));
        mActionSparseArray.put(13, () -> {
            mTvUser.animateText(getString(R.string.help_petra_14) + " \uD83D\uDC4D");
            mTvPetra.setText(EMPTY);
        });
        mActionSparseArray.put(14, () -> mTvUser.animateText(getString(R.string.help_petra_15)));
        mActionSparseArray.put(15, () -> mTvPetra.animateText(getString(R.string.help_petra_16)));
        mActionSparseArray.put(16, () -> {
            mTvUser.animateText(getString(R.string.help_petra_17));
            mTvPetra.setText(EMPTY);
        });
        mActionSparseArray.put(17, () -> mTvPetra.animateText(getString(R.string.help_petra_18)));
        mActionSparseArray.put(18, () -> {
            mTvUser.animateText(getString(R.string.help_petra_19));
            mTvPetra.setText(EMPTY);
        });
        mActionSparseArray.put(19, () -> {
            mTvPetra.animateText(getString(R.string.help_petra_20));
            mFabNext.setVisibility(View.GONE);
        });
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mImageView.setVisibility(View.INVISIBLE);

        RxTextView
                .afterTextChangeEvents(mTvPetra)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .debounce(200, TimeUnit.MILLISECONDS)
                .subscribe(textViewAfterTextChangeEvent -> {
                            if (isAdded() && getActivity() != null)
                                getActivity().runOnUiThread(this::showArrowNext);
                        }
                );

        Observable
                .timer(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultObserver<Long>() {
                    @Override
                    public void onNext(Long aLong) {
                        onClickNext();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick(R.id.btn_next)
    public void onClickNext() {
        resetImageView();
        mActionSparseArray.get(mCurrentPositionAction).execute();
        mCurrentPositionAction++;
    }

    private void showArrowNext() {
        if (mMustBeShowed) {
            mImageView.setVisibility(View.VISIBLE);
            setAlphaAnimation(mImageView);
        }
    }

    private void resetImageView() {
        mImageView.setVisibility(View.INVISIBLE);
        mMustBeShowed = false;
        stopAnimation();
    }


    private void setAlphaAnimation(View v) {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(v, "alpha", 1f, .3f);
        fadeOut.setDuration(1000);
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(v, "alpha", .3f, 1f);
        fadeIn.setDuration(1000);
        mAnimationSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                mAnimationSet.start();
            }
        });
        mAnimationSet.play(fadeIn).after(fadeOut);
        mAnimationSet.start();
    }

    private void stopAnimation() {
        mAnimationSet.cancel();
    }
}
