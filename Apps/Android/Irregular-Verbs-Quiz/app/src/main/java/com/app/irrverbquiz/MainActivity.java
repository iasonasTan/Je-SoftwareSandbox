package com.app.irrverbquiz;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.app.irrverbquiz.fragment.ListFragment;
import com.app.irrverbquiz.fragment.QuizFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public final class MainActivity extends AppCompatActivity {
    private Fragment mQuizFragment, mTableFragment;

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
        BottomNavigationView bottomNavigationView = findViewById(R.id.bot_nav_view);
        ViewCompat.setOnApplyWindowInsetsListener(bottomNavigationView, (v, insets) -> {
            Insets systemInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemInsets.left, systemInsets.top, systemInsets.right, 0);
            return insets;
        });

        mQuizFragment = new QuizFragment();
        mTableFragment = new ListFragment();
        transactFragment(mTableFragment, true);
        transactFragment(mQuizFragment, true);
        addListeners();
    }

    private void addListeners() {
        BottomNavigationView mBotNavView = findViewById(R.id.bot_nav_view);
        mBotNavView.setOnItemSelectedListener(menuItem -> {
            int itemId = menuItem.getItemId();
            if (itemId == R.id.nav_home) {
                transactFragment(mQuizFragment, false);
                return true;
            } else if (itemId == R.id.nav_table) {
                transactFragment(mTableFragment, false);
                return true;
            }
            return false;
        });
    }

    private void transactFragment(Fragment fragment, boolean add) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (add) {
            // add fragment
            transaction.add(R.id.fragment_container, fragment);
        } else {
            // hide all fragments
            transaction.hide(mQuizFragment)
                            .hide(mTableFragment);
            // show selected fragment
            transaction.show(fragment);
        }
        transaction.show(fragment).commit();
    }
}