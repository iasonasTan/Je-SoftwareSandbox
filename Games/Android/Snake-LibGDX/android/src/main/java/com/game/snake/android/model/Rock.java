package com.game.snake.android.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.game.snake.android.Adapter;
import com.game.snake.android.behaviour.Collidable;

public class Rock extends AbstractEntity implements Collidable {
    private final Texture mTexture;
    private final Adapter mAdapter;

    public Rock(Adapter adapter) {
        super();
        mAdapter = adapter;
        mTexture = new Texture("game/rock.png");
        randomizeLocation();
    }

    @Override
    public void randomizeLocation() {
        super.randomizeLocation();
        if(hasCollisionWith(mAdapter.getApple())||
            distanceFrom(mAdapter.getPlayer())<getWidth()+10||
            mAdapter.getRockManager().getColliderOf(this).isPresent()||
            mAdapter.getRockManager().distanceFrom(this)<0||
            hasCollisionWith((Collidable) mAdapter.getPlayer())) {

            randomizeLocation();
        }

        Collidable player = (Collidable)mAdapter.getPlayer();
        Rectangle playerHitbox = new Rectangle(player.getHitbox());
        int margin = getWidth();
        playerHitbox.setX(playerHitbox.getX()-margin);
        playerHitbox.setY(playerHitbox.getY()-margin);
        playerHitbox.setWidth(playerHitbox.getWidth()+margin*2);
        playerHitbox.setHeight(playerHitbox.getHeight()+margin*2);
        if(getHitbox().overlaps(playerHitbox)) {
            randomizeLocation();
        }
    }

    @Override
    public void dispose() {
        mTexture.dispose();
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        batch.draw(mTexture, getScreenX(), getScreenY(), getWidth(), getHeight());

    }
}
