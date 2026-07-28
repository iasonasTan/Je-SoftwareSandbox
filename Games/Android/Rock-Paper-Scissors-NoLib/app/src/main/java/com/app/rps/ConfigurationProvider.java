package com.app.rps;

import android.content.Context;
import android.content.SharedPreferences;

public final class ConfigurationProvider {
    public static boolean sVibrationsEnabled, sAnimationsEnabled, sScoreAnimationsEnabled;

    public static void init(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("SETTINGS", Context.MODE_PRIVATE);
        sVibrationsEnabled = preferences.getBoolean(context.getString(R.string.enable_vibrations), true);
        sAnimationsEnabled = preferences.getBoolean(context.getString(R.string.enable_animations), true);
        sScoreAnimationsEnabled = preferences.getBoolean(context.getString(R.string.enable_score_animations), true);
    }
}
