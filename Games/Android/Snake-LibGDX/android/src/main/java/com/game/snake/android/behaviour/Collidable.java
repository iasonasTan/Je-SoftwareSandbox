package com.game.snake.android.behaviour;

import com.badlogic.gdx.math.Rectangle;

public interface Collidable {
    Rectangle getHitbox();
    boolean hasCollisionWith(Collidable other);
}
