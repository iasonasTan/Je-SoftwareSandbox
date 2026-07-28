package com.game.snake.android.behaviour;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Optional;

public interface Container<T extends Collidable & Entity> {
    void update(float delta);
    void render(SpriteBatch batch);
    Optional<T> getColliderOf(Collidable another);
    void add(T t);
    void remove(T t);
    int size();
    void spawn(int i);
    void randomize();
    int distanceFrom(Entity entity);
}
