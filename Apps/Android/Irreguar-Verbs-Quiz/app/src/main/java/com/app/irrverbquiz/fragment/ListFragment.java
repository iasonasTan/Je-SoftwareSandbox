package com.app.irrverbquiz.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridLayout.LayoutParams;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import com.app.irrverbquiz.R;
import com.app.irrverbquiz.Utils;
import com.app.irrverbquiz.verbs.VerbInfo;
import com.app.irrverbquiz.verbs.VerbsLoader;

import java.util.ArrayList;
import java.util.List;

public final class ListFragment extends Fragment {
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("verb_list", "Created list fragment");
        GridLayout layout = view.findViewById(R.id.verbs_list);
        List<VerbInfo> verbs = new ArrayList<>();
        VerbsLoader.instance.loadVerbs(requireContext(), verbs);
        int row = 0;
        for (VerbInfo verb: verbs) {
            int color = row%2==0?Color.DKGRAY:Color.GRAY;
            createVerbViews(layout, verb, row, color);
            row++;
        }
    }

    private void createVerbViews(GridLayout layout, VerbInfo verb, int row, int color) {
        createVerbView(layout, verb.getBase(), 0, row, color);
        createVerbView(layout, verb.getPast(), 1, row, color);
        createVerbView(layout, verb.getParticiple(), 2, row, color);
        createVerbView(layout, verb.getMeaning(), 3, row, color);
    }

    private void createVerbView(GridLayout layout, String text, int col, int row, int color) {
        TextView view = new TextView(requireContext());
        view.setText(text);
        view.setBackgroundColor(color);
        view.setTypeface(ResourcesCompat.getFont(requireContext(), R.font.zalando_sans_bold));
        view.setTextSize(21);
        var params = new LayoutParams();
        params.width = Utils.dpToPx(requireContext(), 150);
        params.height = Utils.dpToPx(requireContext(), 40);
        params.rowSpec = GridLayout.spec(row);
        params.columnSpec = GridLayout.spec(col);
        int dp10 = Utils.dpToPx(requireContext(), 10);
        params.setMargins(0, dp10/3, 0, dp10/3);
        view.setPadding(0, dp10/2, 0, dp10/2);
        view.setLayoutParams(params);
        layout.addView(view);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }
}
