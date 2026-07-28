package com.game.snake.android.behaviour;

import com.game.snake.android.utils.Direction;

public interface Player extends Entity {
    void setDirection(Direction direction);
    Direction getDirection();
    void increaseScore();
}
