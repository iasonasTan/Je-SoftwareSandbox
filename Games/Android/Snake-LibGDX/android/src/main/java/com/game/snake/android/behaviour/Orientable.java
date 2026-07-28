package com.game.snake.android.behaviour;

import com.game.snake.android.utils.Orientation;

public interface Orientable {
    Orientation getOrientation();
    void setOrientation(Orientation orientation);
}
