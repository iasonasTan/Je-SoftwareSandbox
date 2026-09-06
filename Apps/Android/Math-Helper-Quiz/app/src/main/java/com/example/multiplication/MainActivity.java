package com.example.multiplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import com.example.multiplication.operation.Addition;
import com.example.multiplication.operation.Division;
import com.example.multiplication.operation.Multiplication;
import com.example.multiplication.operation.AbstractOperation;
import com.example.multiplication.operation.Power;
import com.example.multiplication.operation.Removal;
import com.example.multiplication.operation.SQRoot;
import com.example.multiplication.views.AppConfiguredTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;

@SuppressLint("SetTextI18n")
public class MainActivity extends Activity implements View.OnClickListener {
    static final String SCORE_PROPERTIES_NAME = "multiplication.main.SCORE_PREFS";
    static final String BEST_SCORE_PREFS_KEY  = "multiplication.main.BEST_SCORE";

    private AppConfiguredTextView mAnswerTextView, mQuestionView, mCounterView;

    // values
    private int mValueLimit, mSolution, mCounter;
    private final List<AbstractOperation> mOperationsInfo = new ArrayList<>();

    @Override
    public void onCreate (Bundle savedStateInstance) {
        super.onCreate(savedStateInstance);
        setContentView(R.layout.activity_main);

        defineButtons();
        initOperationInfo();

        mAnswerTextView = findViewById(R.id.answer_input);
        mQuestionView = findViewById(R.id.question_output);
        mCounterView = findViewById(R.id.counter_output);

        SharedPreferences preferences = getSharedPreferences(SCORE_PROPERTIES_NAME, Context.MODE_PRIVATE);
        mCounter = preferences.getInt(BEST_SCORE_PREFS_KEY, 0);
        mCounterView.setText(ContextCompat.getString(getApplicationContext(), R.string.count) +" "+ mCounter);
        mAnswerTextView.setText(ContextCompat.getString(getApplicationContext(), R.string.answer) +" "+ 0);

        nextQuestion();
    }

    private void defineButtons() {
        final Consumer<Integer> listenerAdder = id -> findViewById(id).setOnClickListener(this);
        listenerAdder.accept(R.id.button_equals);
        listenerAdder.accept(R.id.button_0);
        listenerAdder.accept(R.id.button_1);
        listenerAdder.accept(R.id.button_2);
        listenerAdder.accept(R.id.button_3);
        listenerAdder.accept(R.id.button_4);
        listenerAdder.accept(R.id.button_5);
        listenerAdder.accept(R.id.button_6);
        listenerAdder.accept(R.id.button_7);
        listenerAdder.accept(R.id.button_8);
        listenerAdder.accept(R.id.button_9);
    }

    private void initOperationInfo() {
        mOperationsInfo.addAll(List.of(
                new Addition(100),
                new Removal(100),
                new Multiplication(25),
                new Division(30),
                new Power(5),
                new SQRoot(6)
        ));
    }

    private void nextQuestion() {
        AbstractOperation operationInfo = mOperationsInfo.get((int)(Math.random()*mOperationsInfo.size()));
        mValueLimit = (int)Math.pow(operationInfo.getLimit(), operationInfo.getLimit());
        operationInfo.randomizeValues();
        mSolution = operationInfo.calculateResult();
        mQuestionView.setText(operationInfo.toString());
        mQuestionView.textChanged();
    }

    void updateScore() {
        mCounterView.setText(ContextCompat.getString(getApplicationContext(), R.string.count)+" "+ mCounter);
        SharedPreferences preferences = getSharedPreferences(SCORE_PROPERTIES_NAME, Context.MODE_PRIVATE);
        preferences.edit().putInt(BEST_SCORE_PREFS_KEY, mCounter).apply();
    }

    @Override
    public void onClick(View view) {
        if (view instanceof Button) {
            Button b = (Button) view;
            String bT = b.getText().toString();
            if (bT.equals("=>")) {
                checkAnswer();
            } else {
                String prevText = mAnswerTextView.getText().toString();
                String newText = prevText + bT;
                try {
                    long val = Long.parseLong(newText.split(" ")[1]);
                    if(val < mValueLimit) {
                        mAnswerTextView.setText(ContextCompat.getString(this, R.string.answer) + " " +
                                String.format(Locale.getDefault(), "%d", val));
                    }
                } catch (Exception e) {
                    // remove invalid string
                    mAnswerTextView.setText(ContextCompat.getString(getApplicationContext(), R.string.answer) +" "+ 0);
                }
            }
        }
    }

    @SuppressWarnings("all")
    void checkAnswer() {
        String inputT = mAnswerTextView.getText().toString();
        if (inputT.isBlank()) return;
        try {
            int answer = Integer.parseInt(inputT.split(" ")[1]);
            mAnswerTextView.setText(ContextCompat.getString(getApplicationContext(), R.string.answer) + " " + 0);
            if (answer == mSolution) {
                nextQuestion();
                mCounter++;
                mCounterView.setText(ContextCompat.getString(getApplicationContext(), R.string.count) + mCounter);
                updateScore();
            } else {
                mAnswerTextView.denial();
            }
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
            mAnswerTextView.setText("0");
        }
    }

}
