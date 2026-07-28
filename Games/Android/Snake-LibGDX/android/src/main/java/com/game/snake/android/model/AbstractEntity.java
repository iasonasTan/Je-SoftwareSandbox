package com.game.snake.android.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.game.snake.android.behaviour.Collidable;
import com.game.snake.android.behaviour.Entity;

public abstract class AbstractEntity implements Entity {
    private final Rectangle mHitbox = new Rectangle();
    private final Texture mHitboxTexture;

    public AbstractEntity() {
        setWidth(Gdx.graphics.getWidth()/7);
        setHeight(getWidth());
        mHitboxTexture = new Texture("game/red_box.png");
    }

    @Override
    public void render(SpriteBatch batch) {
    }

    @Override
    public void dispose() {
        mHitboxTexture.dispose();
    }

    @Override
    public void update(float delta) {
    }

    public void move(float sX, float sY) {
        mHitbox.x += (int)sX;
        mHitbox.y += (int)sY;
    }

    public void randomizeLocation() {
        int margin = 2;
        // noinspection all
        int minX = margin, maxX = Gdx.graphics.getWidth()-margin-getWidth();
        int minY = Gdx.graphics.getHeight()/2+margin, maxY = Gdx.graphics.getHeight()-margin-getHeight();
        int randX = (int)(Math.random() * (maxX - minX + 1)) + minX;
        int randY = (int)(Math.random() * (maxY - minY + 1)) + minY;
        setScreenX(randX);
        setScreenY(randY);
    }

    public final int getScreenX() {
        return (int) mHitbox.getX();
    }

    public final int getScreenY() {
        return (int) mHitbox.getY();
    }

    public final int getWidth() {
        return (int) mHitbox.getWidth();
    }

    public final int getHeight() {
        return (int) mHitbox.getHeight();
    }

    public final void setScreenX(int mScreenX) {
        mHitbox.setX(mScreenX);
    }

    public final void setScreenY(int screenY) {
        mHitbox.setY(screenY);
    }

    public final void setWidth(int width) {
        mHitbox.setWidth(width);
    }

    public final void setHeight(int height) {
        mHitbox.setHeight(height);
    }

    @Override
    public int distanceFrom(Entity other) {
        int diffX = other.getScreenX()-getScreenX();
        int diffY = other.getScreenY()-getScreenY();
        return (int) Math.sqrt(diffX*diffX+diffY*diffY);
    }

    @Override
    public void setPosition(Vector2 pos) {
        setScreenX((int) pos.x);
        setScreenY((int) pos.y);
    }

    @Override
    public Vector2 getPosition() {
        return new Vector2(getScreenX(), getScreenY());
    }

    public boolean hasCollisionWith(Collidable other) {
        if(other.equals(this))
            return false;
        return getHitbox().overlaps(other.getHitbox());
    }

    public Rectangle getHitbox() {
        return mHitbox;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==this) return true;
        if(obj instanceof AbstractEntity entity) {
            return entity.getScreenX()==getScreenX()&&entity.getScreenY()==getScreenY()&&
                        entity.getWidth()==getWidth()&&entity.getHeight()==getHeight();
        } else {
            return false;
        }
    }
}
