package com.game.snake.android.behaviour;

import com.game.snake.android.utils.Direction;

public interface Movable extends Entity {
    void setDirection(Direction d);
    Direction getDirection();
    void move(float sX, float sY);
}
