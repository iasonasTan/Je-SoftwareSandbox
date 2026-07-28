package com.app.irrverbquiz.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.app.irrverbquiz.R;
import com.app.irrverbquiz.verbs.VerbInfo;
import com.app.irrverbquiz.VerbsComtdActivity;
import com.app.irrverbquiz.verbs.VerbsLoader;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class QuizFragment extends Fragment {
    private final List<VerbInfo> mVerbInfos = new ArrayList<>();
    private VerbInfo mVerb = new VerbInfo();
    private MaterialTextView mQuestionView, mIndexView;
    private TextInputEditText mPastInput, mParticipleInput;
    private int mVerbIndex = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        VerbsLoader.instance.loadVerbs(requireContext(), mVerbInfos);
        mVerbIndex = requireContext().getSharedPreferences("preferences", Context.MODE_PRIVATE).getInt("index", 0);
        initViews(view);
        nextVerb();
        if(savedInstanceState != null) {
            mPastInput.setText(savedInstanceState.getCharSequence("currentPast", ""));
            mParticipleInput.setText(savedInstanceState.getCharSequence("currentParticiple", ""));
        }
    }

    public void checkAnswer(View ignored) {
        if(checkVerb()) {
            nextVerb();
            // focus on first input
            mPastInput.requestFocus();
        }
    }

    private void initViews(View view) {
        view.findViewById(R.id.confirm_button).setOnClickListener(this::checkAnswer);
        view.findViewById(R.id.skip_button).setOnClickListener(v -> showMessageDialog());
        mQuestionView = view.findViewById(R.id.question_view);
        mParticipleInput = view.findViewById(R.id.participle_input);
        mPastInput = view.findViewById(R.id.past_input);
        mIndexView = view.findViewById(R.id.index_view);
        mParticipleInput.setOnEditorActionListener((a, b, c) -> {
            if(b == EditorInfo.IME_ACTION_DONE)
                checkAnswer(null);
            return false;
        });
    }

    private void showMessageDialog() {
        new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Question Skipped.")
                .setMessage("Base: "+mVerb.getBase()+" ("+mVerb.getMeaning()+")\nPast: "+mVerb.getPast()+"\nParticiple: "+mVerb.getParticiple())
                .setCancelable(false)
                .setPositiveButton("OK", ((dialog, which) -> {
                    dialog.dismiss();
                    nextVerb();
                }))
                .show();
    }

    private boolean checkVerb() {
        // get values
        String past = getText(mPastInput);
        String participle = getText(mParticipleInput);
        // warn for invalid words
        boolean eqPast =mVerb.past(past);
        boolean eqParticiple =mVerb.participle(participle);
        int redColor = requireContext().getColor(R.color.red);
        if(!eqPast) {
            mPastInput.setBackgroundColor(redColor);
            mPastInput.requestFocus();
        } else if(!eqParticiple) {
            mParticipleInput.setBackgroundColor(redColor);
            mParticipleInput.requestFocus();
        }
        // return result
        return eqParticiple&&eqPast;
    }

    @SuppressLint("SetTextI18n")
    public void nextVerb() {
        // check if all are completed
        if(mVerbIndex>=mVerbInfos.size()) {
            VerbsLoader.instance.loadVerbs(requireContext(), mVerbInfos);
            Intent intent = new Intent(requireContext(), VerbsComtdActivity.class);
            requireContext().startActivity(intent);
        }
        // load & show next verb
        mVerb = mVerbInfos.get(mVerbIndex);
        mIndexView.setText(mVerbIndex+"/"+mVerbInfos.size());
        mQuestionView.setText(mVerb.getBase()+"\n"+ mVerb.getMeaning());
        mVerbIndex++;
        requireContext().getSharedPreferences("preferences", Context.MODE_PRIVATE).edit().putInt("index", mVerbIndex).apply();
        // reset input colors
        mPastInput.setText("");
        mParticipleInput.setText("");
        mPastInput.setBackgroundColor(0);
        mParticipleInput.setBackgroundColor(0);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putCharSequence("currentPast", mPastInput.getText());
        outState.putCharSequence("currentParticiple", mParticipleInput.getText());
    }

    private String getText(EditText et) {
        return et.getText().toString().toLowerCase().replace(" ", "");
    }
}
