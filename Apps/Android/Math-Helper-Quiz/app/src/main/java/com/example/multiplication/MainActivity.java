package com.example.multiplication;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.core.content.ContextCompat;

import com.example.multiplication.functions.Addition;
import com.example.multiplication.functions.Division;
import com.example.multiplication.functions.Multiplication;
import com.example.multiplication.lib.Operation;
import com.example.multiplication.functions.Power;
import com.example.multiplication.functions.Removal;
import com.example.multiplication.functions.SQRoot;
import com.example.multiplication.views.MyTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@SuppressLint("SetTextI18n")
public class MainActivity extends Activity implements View.OnClickListener {
    static final String SCORE_PROPERTIES_NAME = "multiplication.main.SCORE_PREFS";
    static final String BEST_SCORE_PREFS_KEY  = "multiplication.main.BEST_SCORE";

    // views
    private MyTextView mAnswerTextView, mQuestionView, mCounterView;

    // values
    private int mValueLimit, mSolution, mCounter;
    private final List<Operation> mOperationsInfo = new ArrayList<>();

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

    private void addListener(int id) {
        findViewById(id).setOnClickListener(this);
    }

    private void defineButtons() {
        addListener(R.id.button_equals);
        addListener(R.id.button_0);
        addListener(R.id.button_1);
        addListener(R.id.button_2);
        addListener(R.id.button_3);
        addListener(R.id.button_4);
        addListener(R.id.button_5);
        addListener(R.id.button_6);
        addListener(R.id.button_7);
        addListener(R.id.button_8);
        addListener(R.id.button_9);
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

//        mOperationsInfo.addAll(List.of(new OperationInfo(98, Integer::sum, (a,b) -> a+" + "+b, limit -> new int[]{RAND.apply(limit), RAND.apply(limit)}),
//                new OperationInfo(100, (a, b) -> Math.abs(a-b), (a,b) -> {
//                    String out = a+"-"+b;
//                    if(a-b<0)
//                        return "|"+out+"|";
//                    return out;
//                }, limit -> {
//                    var out = new int[]{RAND.apply(limit), RAND.apply(limit)};
//                    do {
//                        out[0]=RAND.apply(limit);
//                        out[1]=RAND.apply(limit);
//                    } while(out[0]%10==0||out[1]%10==0);
//                    return out;
//                }),
//                new OperationInfo(45, (a, b) -> a / b, (a,b)->a+" ÷ "+b, limit -> {
//                    int val1 = RAND.apply(limit);
//                    int val2 = RAND.apply(limit-1)+1;
//                    int result = val1/val2;
//                    val1 = result*val2;
//                    return new int[]{val1, val2};
//                }), new OperationInfo(13, (a, b) -> a*b, (a,b)->a+" × "+b, limit -> new int[]{RAND.apply(limit), RAND.apply(limit)}),
//                new OperationInfo(POW_OP_LIMIT,(a, b) -> (int) Math.pow(a, b), (a,b) -> a+SUPERSCRIPTS[b], limit -> {
//                    int val1 = RAND.apply(limit);
//                    int pow = RAND.apply(limit);
//                    return new int[]{val1, pow};
//                }), new OperationInfo(6, (a, b) -> (int)(Math.sqrt(a+b)), , limit -> {
//                    int val1, val2;
//
//                    return new int[]{val1, val2};
//                })));
    }

    private void nextQuestion() {
        Operation operationInfo = mOperationsInfo.get((int)(Math.random()*mOperationsInfo.size()));
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
