package com.app.irrverbquiz;

import android.content.Context;

public final class Utils {
    private Utils() {}

    public static int dpToPx(Context context, int dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density + 0.5f);
    }
}
