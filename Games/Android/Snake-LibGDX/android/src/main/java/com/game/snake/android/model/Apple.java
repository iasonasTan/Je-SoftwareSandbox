package com.game.snake.android.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.snake.android.Adapter;
import com.game.snake.android.behaviour.Collidable;

public class Apple extends AbstractEntity implements Collidable {
    private final Texture mTexture;
    private final Adapter mAdapter;

    public Apple(Adapter adapter) {
        mAdapter = adapter;
        mTexture = new Texture("game/apple.png");
        setHeight((int) (getWidth()*1.3));
        randomizeLocation();
    }

    @Override
    public void randomizeLocation() {
        super.randomizeLocation();
        if(hasCollisionWith((Collidable) mAdapter.getPlayer())||
            mAdapter.getRockManager().getColliderOf(this).isPresent()||
            distanceFrom(mAdapter.getPlayer())<getWidth()) {

            randomizeLocation();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        batch.draw(mTexture, getScreenX(), getScreenY(), getWidth(), getHeight());
    }

    @Override
    public void dispose() {
        mTexture.dispose();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        // detect collision
        if(hasCollisionWith((Collidable) mAdapter.getPlayer())) {
            mAdapter.getPlayer().increaseScore();
            randomizeLocation();
        }
    }
}
