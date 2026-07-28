package com.app.rps.settings;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;

public final class ConfigCheckBoxHandler {
    private final Context context;

    public ConfigCheckBoxHandler(Context context) {
        this.context = context;
    }

    public void handleCheckBox(CheckBox checkBox) {
        final String preferencesName = checkBox.getText().toString();
        boolean checked = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
                        .getBoolean(preferencesName, true);
        checkBox.setChecked(checked);
        checkBox.setOnCheckedChangeListener(new CheckBoxPreferencesListener(context, preferencesName));
    }

    private static final class CheckBoxPreferencesListener implements CompoundButton.OnCheckedChangeListener {
        private final String mPropertyName;
        private final Context context;

        private CheckBoxPreferencesListener(Context context, String propertyName) {
            this.mPropertyName = propertyName;
            this.context = context;
        }

        @Override
        public void onCheckedChanged(@NonNull CompoundButton compoundButton, boolean b) {
            context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE)
                    .edit()
                    .putBoolean(mPropertyName, b)
                    .apply();
        }
    }
}
