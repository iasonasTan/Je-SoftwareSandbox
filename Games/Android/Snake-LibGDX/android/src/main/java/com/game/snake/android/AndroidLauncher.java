package com.game.snake.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.game.snake.android.utils.UiThreadHandler;

/** Launches the Android application. */
public class AndroidLauncher extends AndroidApplication implements UiThreadHandler {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.useImmersiveMode = true; // Recommended, but not required.
        initialize(new Adapter(getContext(), this), configuration);
    }

    @Override
    public void runOnUi(Runnable runnable) {
        super.runOnUiThread(runnable);
    }
}
