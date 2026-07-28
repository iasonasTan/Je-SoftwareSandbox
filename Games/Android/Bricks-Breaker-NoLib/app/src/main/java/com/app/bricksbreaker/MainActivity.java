package com.app.bricksbreaker;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private GamePanel gamePanel;
    private Button restartBtn, pauseBtn;
    private TextView statusOutput;
    private EditText difficultyInput;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_game);

        gamePanel = findViewById(R.id.gameSurface);
        restartBtn = findViewById(R.id.restart);
        pauseBtn = findViewById(R.id.pause);
        statusOutput = findViewById(R.id.status_output);
        difficultyInput = findViewById(R.id.difficulty_input);
        progressBar = findViewById(R.id.progress_bar);
        setListeners();

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }

    private void setListeners () {
        restartBtn.setOnClickListener(this::startGame);
        pauseBtn.setOnClickListener(this::pauseGame);
    }

    public void gameOver (boolean win) {
        runOnUiThread(() -> {
            gamePanel.pause();
            restartBtn.setVisibility(View.VISIBLE);
            statusOutput.setVisibility(View.VISIBLE);
            pauseBtn.setVisibility(View.GONE);
            difficultyInput.setVisibility(View.VISIBLE);

            String msg = "YOU LOSE!";
            if (win) {
                msg = "YOU WIN!";
            }

            statusOutput.setText(msg);
        });
    }

    public void startGame (View v) {
        gamePanel.pause();
        runOnUiThread(() -> {
            progressBar.setVisibility(View.VISIBLE);
            restartBtn.setVisibility(View.GONE);
            statusOutput.setVisibility(View.GONE);
            pauseBtn.setVisibility(View.VISIBLE);
            difficultyInput.setVisibility(View.GONE);
        });
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(difficultyInput.getWindowToken(), 0);
        gamePanel.reset();
        int diff = 2;
        try {
            diff = Integer.parseInt(difficultyInput.getText().toString());
        } catch (NumberFormatException e) {
            e.printStackTrace(System.out); // no reason
        }
        gamePanel.addBalls(diff);

        gamePanel.startGameThread();

        runOnUiThread(() -> progressBar.setVisibility(View.GONE));
    }

    public void pauseGame (View v) {
        if (gamePanel.isRunning()) {
            gamePanel.pause();
        } else {
            gamePanel.resume();
        }
    }

}