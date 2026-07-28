package com.game.snake.android.behaviour;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public interface Entity {
    void render(SpriteBatch batch);
    void dispose();
    void
    update(float delta);
    int getScreenX();
    int getScreenY();
    void setScreenX(int x);
    void setScreenY(int y);
    int getWidth();
    int getHeight();
    void setWidth(int w);
    void setHeight(int h);
    int distanceFrom(Entity other);
    Vector2 getPosition();
    void setPosition(Vector2 playerPos);
}
