package org.terrasdepontevedra.petra.ui.views.audioPlayer;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import org.terrasdepontevedra.petra.R;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import co.mobiwise.library.ProgressLayout;
import timber.log.Timber;

public class AudioPlayerView extends FrameLayout {

    @BindView(R.id.mProgressAudio)
    ProgressLayout progressLayout;

    @BindView(R.id.button_play_pause)
    PlayPauseView mButtonPlay;

    @BindView(R.id.loading_audio)
    ImageView mImageLoading;

    @BindView(R.id.text_audio_duration)
    TextView mTextAudioDuration;

    private MediaPlayer mPlayer;
    private final Handler mHandler = new Handler();

    private boolean isPlaying = false;
    private int currentDuration = 0;
    private String mAudioUrl;

    public AudioPlayerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        onCreate();
    }

    private void onCreate() {
        View view = inflate(getContext(), R.layout.view_audio_player, this);
        ButterKnife.bind(this, view);

    }

    public void destroy() {
        if(mPlayer!=null) {
            mPlayer.stop();
        }
        if(mHandler!=null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    public void init(String url) {
        mAudioUrl = url;
        mButtonPlay.toggle(); //Start with icon play
        progressLayout.setCurrentProgress(currentDuration);
    }

    @OnClick(R.id.button_play_pause)
    void onClickPlayPause() {
        if (mPlayer == null) {
            setupMediaPlayer();
        } else {
            togglePlayingStatus();
            mButtonPlay.toggle();
        }
    }

    private void togglePlayingStatus() {
        this.isPlaying = !isPlaying;
        if (isPlaying) {
            Timber.i("resume sound");
            progressLayout.start();
            mHandler.postDelayed(mUpdateTimeTask, 0);
            resumeSound();
        } else {
            Timber.i("pause sound");
            progressLayout.stop();
            mHandler.removeCallbacks(mUpdateTimeTask);
            pauseSound();
        }
    }


    private void pauseSound() {

        Handler handler = new Handler();

        final Runnable r = () -> {

            if (mPlayer != null) {
                if (mPlayer.isPlaying()) {
                    mPlayer.pause();
                }
            }

        };

        handler.postDelayed(r, 0);
    }

    private void resumeSound() {

        Handler handler = new Handler();

        final Runnable r = () -> {

            if (mPlayer != null) {
                mPlayer.start();
            }

        };

        handler.postDelayed(r, 0);

    }


    private void setupMediaPlayer() {
        showLoading(true);
        Handler handler = new Handler();

        final Runnable r = () -> {
            mPlayer = new MediaPlayer();
            try {
                mPlayer = new MediaPlayer();
                mPlayer.reset();
                mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mPlayer.setDataSource(mAudioUrl);
                mPlayer.setOnCompletionListener(mediaPlayer -> {
                    isPlaying = false;
                    currentDuration = 0;
                    if (!mButtonPlay.isPlay()) {
                        mButtonPlay.toggle();
                    }
                });
                mPlayer.setOnPreparedListener(mediaPlayer -> {
                    //notificateLoadingCompleted();
                    setAudioDurationText();
                    progressLayout.setMaxProgress(mPlayer.getDuration() / 1000);
                    updateProgressBar();
                    showLoading(false);
                    mButtonPlay.toggle();
                    mPlayer.start();
                    progressLayout.start();
                    isPlaying = true;
                });
                mPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }

        };

        handler.postDelayed(r, 0);

    }

    private void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 100);
    }


    private final Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            Timber.i("current duration:" + currentDuration);
            currentDuration += 1;
            mHandler.postDelayed(mUpdateTimeTask, 1000);

        }
    };

    private static String milliSecondsToTimer(long milliseconds) {
        String finalTimerString = "";
        String secondsString;

        // Convert total duration into time
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) (milliseconds % (1000 * 60 * 60)) / (1000 * 60);
        int seconds = (int) ((milliseconds % (1000 * 60 * 60)) % (1000 * 60) / 1000);
        // Add hours if there
        if (hours > 0) {
            finalTimerString = hours + ":";
        }

        // Prepending 0 to seconds if it is one digit
        if (seconds < 10) {
            secondsString = "0" + seconds;
        } else {
            secondsString = "" + seconds;
        }

        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        // return timer string
        return finalTimerString;
    }

    private static int getProgressPercentage(long currentDuration, long totalDuration) {
        Double percentage;

        long currentSeconds = (int) (currentDuration / 1000);
        long totalSeconds = (int) (totalDuration / 1000);

        // calculating percentage
        percentage = (((double) currentSeconds) / totalSeconds) * 100;

        // return percentage
        return percentage.intValue();
    }

    private void showLoading(boolean loading) {
        mButtonPlay.setVisibility(loading ? GONE : VISIBLE);
        mImageLoading.setVisibility(loading ? VISIBLE : GONE);


        AnimatorSet s = new AnimatorSet();
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(mImageLoading, "alpha", 0.6f)
                .setDuration(3000);
        objectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        objectAnimator.setRepeatMode(ObjectAnimator.REVERSE);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.start();

        ObjectAnimator imageViewObjectAnimator = ObjectAnimator.ofFloat(mImageLoading,
                "rotation", 0f, 360f);
        imageViewObjectAnimator.setDuration(1000); // miliseconds
        imageViewObjectAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        imageViewObjectAnimator.setRepeatMode(ObjectAnimator.RESTART);
        imageViewObjectAnimator.setInterpolator(new LinearInterpolator());
        imageViewObjectAnimator.start();


        if (loading) {
            s.playTogether(objectAnimator, imageViewObjectAnimator);
        } else {
            if (s.isRunning()) {
                s.end();
            }
        }

        //mImageLoading.startAnimation(animation);
        s.start();
    }

    private void setAudioDurationText() {
        int duration = mPlayer.getDuration();
        mTextAudioDuration.setText(milliSecondsToTimer(duration));
    }


}
