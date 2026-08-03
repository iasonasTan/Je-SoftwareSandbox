package com.app.rps.view;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.app.rps.ConfigurationProvider;

public class ScoreView extends AppCompatTextView {
    public ScoreView(@NonNull Context context) {
        super(context);
    }

    public ScoreView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ScoreView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if(ConfigurationProvider.sScoreAnimationsEnabled && !getText().equals(text)) {
            anim();
        }
        super.setText(text, type);
    }

    public void anim() {
        Runnable animationReset = () -> animate()
                .scaleX(1.0f)
                .scaleY(1.0f);
        animate().setDuration(100L)
                .scaleX(1.25f)
                .scaleY(1.25f)
                .withEndAction(animationReset);
    }
}
