package com.app.rps;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import com.app.rps.game.GameHandler;
import com.app.rps.game.GameHandlerBuilder;
import com.app.rps.settings.SettingsActivity;

public class MainActivity extends AppCompatActivity {
    private GameHandler mGameHandler;

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("score_green", ((TextView)findViewById(R.id.score_green)).getText());
        outState.putCharSequence("score_red", ((TextView)findViewById(R.id.score_red)).getText());
        outState.putIntArray("scores", mGameHandler.getScores());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        WindowInsetsControllerCompat controller = WindowCompat.getInsetsController(getWindow(), getWindow().getDecorView());
        controller.setSystemBarsBehavior(WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        controller.hide(WindowInsetsCompat.Type.systemBars());

        ConfigurationProvider.init(this);

        ImageView greenImgView  = findViewById(R.id.player_green), redImgView   = findViewById(R.id.player_red);
        TextView greenScoreView = findViewById(R.id.score_green),  redScoreView = findViewById(R.id.score_red);
        GameHandler.ScoreUpdater greenScoreUpdater = score -> greenScoreView.setText(getString(R.string.score, score));
        GameHandler.ScoreUpdater redScoreUpdater   = score -> redScoreView.setText(getString(R.string.score, score));

        mGameHandler = new GameHandlerBuilder()
                .setImageUpdaters(greenImgView::setImageResource, redImgView::setImageResource)
                .setScoreUpdaters(greenScoreUpdater, redScoreUpdater)
                .build();

        findViewById(R.id.play_btn).setOnClickListener(new OnPlayListener(this, mGameHandler));
        findViewById(R.id.reset_btn).setOnClickListener(view -> mGameHandler.resetScores());
        findViewById(R.id.settings_btn).setOnClickListener(view -> {
            Intent settingsLaunchIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsLaunchIntent);
        });

        if(savedInstanceState != null) {
            greenScoreView.setText(savedInstanceState.getCharSequence("score_green"));
            redScoreView.setText(savedInstanceState.getCharSequence("score_red"));

            int[] scores = savedInstanceState.getIntArray("scores");
            if(scores != null) mGameHandler.setScores(scores);
        }
    }

    private static final class OnPlayListener implements ImageView.OnClickListener {
        private final Vibrator mVibrator;
        private final VibrationEffect mPlayVibEffect;
        private final AudioManager mAudioManager;

        private final GameHandler mGameHandler;

        public OnPlayListener(Context context, GameHandler gameHandler) {
            mGameHandler   = gameHandler;
            mAudioManager  = context.getSystemService(AudioManager.class);
            mVibrator      = context.getSystemService(Vibrator.class);
            mPlayVibEffect = VibrationEffect.createOneShot(150, VibrationEffect.EFFECT_DOUBLE_CLICK);
        }

        @Override
        public void onClick(View view) {
            int volume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            mGameHandler.play(volume);

            if(ConfigurationProvider.sVibrationsEnabled) {
                mVibrator.vibrate(mPlayVibEffect);
            }

            if(ConfigurationProvider.sAnimationsEnabled) {
                Runnable animationReset = () -> view.animate()
                        .scaleX(1.0f)
                        .scaleY(1.0f);
                view.animate().setDuration(75L)
                        .scaleX(0.95f)
                        .scaleY(0.95f)
                        .withEndAction(animationReset);
            }
        }
    }

}