package com.app.rps.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import com.app.rps.ConfigurationProvider;

public class ShapeView extends AppCompatImageView {
    public ShapeView(@NonNull Context context) {
        super(context);
    }

    public ShapeView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ShapeView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        if(ConfigurationProvider.sAnimationsEnabled) {
            anim();
        }
    }

    public void anim() {
        Runnable animationReset = () -> animate()
                .scaleX(1.0f)
                .scaleY(1.0f);
        animate().setDuration(50L)
                .scaleX(0.95f)
                .scaleY(0.95f)
                .withEndAction(animationReset);
    }
}
