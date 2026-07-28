package com.game.snake.android.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.snake.android.Adapter;
import com.game.snake.android.behaviour.Collidable;
import com.game.snake.android.behaviour.Movable;
import com.game.snake.android.behaviour.Player;
import com.game.snake.android.utils.Direction;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Snake extends AbstractEntity implements Player, Collidable, Movable {
    private Direction mDirection = Direction.NONE;
    private final Map<Direction, Texture> mTextures;
    private int mScore = 0, mSpeed = 3;
    private final Adapter mAdapter;
    protected Context context;

    public Snake(Adapter adapter, Context context) {
        super();
        mAdapter = adapter;
        this.context = context;
        Map<Direction, Texture> textures = new HashMap<>();
        textures.put(Direction.UP, new Texture("game/snake_head_up.png"));
        textures.put(Direction.DOWN, new Texture("game/snake_head_down.png"));
        textures.put(Direction.LEFT, new Texture("game/snake_head_left.png"));
        textures.put(Direction.RIGHT, new Texture("game/snake_head_right.png"));
        textures.put(Direction.NONE, new Texture("game/snake_head_right.png"));
        // noinspection all
        mTextures = Collections.unmodifiableMap(textures);

        Log.d("dev-test", "Snake created!");
        setScreenX(Gdx.graphics.getWidth()/2-getWidth()/2);
        setScreenY(Gdx.graphics.getHeight()/2-getHeight()/2+400);
        setDirection(Direction.RIGHT);
    }

//    private static class SnakeTail extends AbstractEntity implements Collidable, Movable {
//        private final Map<Orientation, Texture> mTextures;
//        private Optional<SnakeTail> mChildOpt = Optional.empty();
//        private Direction mDirection;
//
//        public SnakeTail() {
//            mDirection = Direction.LEFT;
//            Map<Orientation, Texture> textures = new HashMap<>();
//            textures.put(Orientation.HORIZONTAL, new Texture("game/snake_tail_horizontal.png"));
//            textures.put(Orientation.VERTICAL, new Texture("game/snake_tail_vertical.png"));
//            textures.put(Orientation.OTHER, new Texture("game/snake_tail_vertical.png"));
//            // noinspection all
//            mTextures = Collections.unmodifiableMap(textures);
//        }
//
//        public SnakeTail(SnakeTail child) {
//            this();
//            mChildOpt = Optional.of(child);
//        }
//
//        @Override
//        public void render(final SpriteBatch batch) {
//            super.render(batch);
//            batch.draw(mTextures.get(getDirection().toOrientation()), getScreenX(), getScreenY(), getWidth(), getHeight());
//            mChildOpt.ifPresent(c -> c.render(batch));
//        }
//
//        @Override
//        public void update(float delta) {
//            super.update(delta);
//            mChildOpt.ifPresent(c -> c.setPosition(getPosition()));
//        }
//
//        @Override
//        public void move(float sX, float sY) {
//            super.move(sX, sY);
//            if(sX>sY) {
//                setDirection(sX<0?Direction.LEFT:Direction.RIGHT);
//            } else {
//                setDirection(sY<0?Direction.DOWN:Direction.UP);
//            }
//        }
//
//        @Override
//        public void dispose() {
//            mTextures.values().forEach(Texture::dispose);
//        }
//
//        @Override
//        public void setDirection(Direction d) {
//            mDirection =d;
//        }
//
//        @Override
//        public Direction getDirection() {
//            return mDirection;
//        }
//    }

    @Override
    public void dispose() {
        mTextures.values().forEach(Texture::dispose);
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        updatePosition(mSpeed);
        if(getScreenX()<0||getScreenY()<Gdx.graphics.getHeight()/2||
            getScreenX()+getWidth()>Gdx.graphics.getWidth()||getScreenY()+getHeight()>Gdx.graphics.getHeight()||
            mAdapter.getRockManager().getColliderOf(this).isPresent()) {

            mAdapter.gameOver();
        }
    }

    public void updatePosition(int diff) {
        switch(getDirection()) {
            case UP: move(0, diff); break;
            case DOWN: move(0, -diff); break;
            case LEFT: move(-diff, 0); break;
            case RIGHT: move(diff, 0); break;
        }
    }

    @Override
    public void setDirection(Direction direction) {
        mDirection = direction;
    }

    @Override
    public void move(float sX, float sY) {
        super.move(sX, sY);
    }

    @Override
    public Direction getDirection() {
        return mDirection;
    }

    @Override
    public void increaseScore() {
        Log.d("dev-test", "Increase score...");
        mScore++;
        mAdapter.updateScore(mScore);
        if(mScore%10==0)
            mSpeed++;
        if(mScore%3==0)
            mAdapter.getRockManager().randomize();

        SharedPreferences preferences = context.getSharedPreferences("score", Context.MODE_PRIVATE);
        int bScore = preferences.getInt("score", 0);
        if(mScore > bScore) {
            preferences.edit().putInt("score", mScore).apply();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        super.render(batch);
        batch.draw(mTextures.get(mDirection), getScreenX(), getScreenY(), getWidth(), getHeight());
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "Snake: {x:%d, y:%d}", getScreenX(), getScreenY());
    }
}
