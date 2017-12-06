package org.terrasdepontevedra.petra.ui;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import org.terrasdepontevedra.petra.R;
import org.terrasdepontevedra.petra.data.local.PreferencesCollector;
import org.terrasdepontevedra.petra.ui.base.BaseActivity;
import org.terrasdepontevedra.petra.ui.wizard.WizardActivity;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {

    private static final int ANIM_ITEM_DURATION = 1000;
    @Inject
    PreferencesCollector mPreferencesCollector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        super.onCreate(savedInstanceState);
        startTimer();
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            return;
        }
        animate();
        super.onWindowFocusChanged(true);
    }


    private void animate() {

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.fade);
        TextView text = findViewById(R.id.text_splash);
        text.startAnimation(animation);


        ImageView logoImageView = findViewById(R.id.img_logo);

        ViewCompat.animate(logoImageView)
                .translationY(-150)
                .setStartDelay(300)
                .setListener(new ViewPropertyAnimatorListener() {
                    @Override
                    public void onAnimationStart(View view) {

                    }

                    @Override
                    public void onAnimationEnd(View view) {
                    }

                    @Override
                    public void onAnimationCancel(View view) {

                    }
                })
                .setDuration(ANIM_ITEM_DURATION).setInterpolator(
                new LinearInterpolator()).start();


    }


    private void startTimer() {
        Observable
                .timer(2500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(second -> {
                    if (mPreferencesCollector.isFirstTime())
                        startActivity(WizardActivity.getCallingIntent(this, WizardActivity.WizardType.INTRO));
                    else
                        startActivity(MainActivity.getCallingIntent(this));
                    this.finish();

                });
    }


}
