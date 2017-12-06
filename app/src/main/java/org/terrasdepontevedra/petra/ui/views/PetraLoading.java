package org.terrasdepontevedra.petra.ui.views;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import org.terrasdepontevedra.petra.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PetraLoading extends FrameLayout {

    @BindView(R.id.image_loading_petra)
    ImageView mImageLoading;

    private final AnimatorSet animator = new AnimatorSet();
    private ObjectAnimator alphaAnimation;
    private ObjectAnimator rotateAnimation;

    public PetraLoading(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void setLoading(boolean loading){
        if(loading) {
            alphaAnimation.start();
            rotateAnimation.start();
            animator.playTogether(alphaAnimation, rotateAnimation);
            animator.start();
        }
        else{
            if(animator.isRunning()) {
                animator.end();
            }
        }
    }

    private void init(){
        View view = inflate(getContext(), R.layout.view_loading_petra, this);
        ButterKnife.bind(this);

        alphaAnimation = ObjectAnimator.ofFloat(mImageLoading, "alpha", 0.6f)
                .setDuration(3000);
        alphaAnimation.setRepeatCount(ObjectAnimator.INFINITE);
        alphaAnimation.setRepeatMode(ObjectAnimator.REVERSE);
        alphaAnimation.setInterpolator(new LinearInterpolator());

        rotateAnimation = ObjectAnimator.ofFloat(mImageLoading , "rotation", 0f, 360f);
        rotateAnimation.setDuration(1000); // miliseconds
        rotateAnimation.setRepeatCount(ObjectAnimator.INFINITE);
        rotateAnimation.setRepeatMode(ObjectAnimator.RESTART);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        setLoading(true);
    }


}

