package com.example.multiplication.views;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.example.multiplication.R;

public class AppConfiguredButton extends Button {
    private Vibrator mVibrator;
    private final VibrationEffect mVibrationEffect = VibrationEffect.createOneShot(40, VibrationEffect.EFFECT_TICK);

    public AppConfiguredButton(Context context) {
        super(context);
        init();
    }

    public AppConfiguredButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AppConfiguredButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setBackgroundResource(R.drawable.view_shape);
        mVibrator = ContextCompat.getSystemService(getContext(), Vibrator.class);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        if(l==null) return;
        super.setOnClickListener(v -> {
            if(mVibrator!=null)
                mVibrator.vibrate(mVibrationEffect);
            l.onClick(v);
        });
    }

    @Override
    public boolean performClick() {
        animate()
                .scaleX(0.90f).scaleY(0.90f).setDuration(100L)
                .withEndAction(() -> animate().scaleX(1).scaleY(1).setDuration(100))
                .start();
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            performClick();
            return true;
        }
        return super.onTouchEvent(event);
    }
}
