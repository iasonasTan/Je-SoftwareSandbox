package com.example.multiplication.views;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class AppConfiguredTextView extends TextView {
    private final Vibrator mVibrator;
    private final VibrationEffect mDenialVibeEffect;

    public AppConfiguredTextView(Context context) {
        this(context, null);
    }

    public AppConfiguredTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AppConfiguredTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    // constructor always runs
    public AppConfiguredTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mVibrator = context.getSystemService(Vibrator.class);
        mDenialVibeEffect = VibrationEffect.createOneShot(70, VibrationEffect.EFFECT_HEAVY_CLICK);
    }

    public void textChanged() {
        animate().scaleX(0.9f).scaleY(0.95f).setDuration(130L)
                .withEndAction(() -> animate().scaleX(1f).scaleY(1f).setDuration(100L).start())
                .start();
    }

    public void denial() {
        Runnable resetPositionAnimation = () -> animate().translationX(0f).setDuration(100L).start();
        Runnable slideRightAnimation = () -> animate().translationX(+10f).setDuration(100L).withEndAction(resetPositionAnimation).start();
        animate().translationX(-10f).setDuration(100L).withEndAction(slideRightAnimation).start();
        mVibrator.vibrate(mDenialVibeEffect);
    }
}
