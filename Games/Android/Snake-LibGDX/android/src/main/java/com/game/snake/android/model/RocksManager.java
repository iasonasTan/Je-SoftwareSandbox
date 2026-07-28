package com.game.snake.android.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.snake.android.Adapter;
import com.game.snake.android.behaviour.Collidable;
import com.game.snake.android.behaviour.Container;
import com.game.snake.android.behaviour.Entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RocksManager implements Container<Rock> {
    private final List<Rock> mRocks = new ArrayList<>();
    private final Adapter mAdapter;

    public RocksManager(Adapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    @Override
    public void update(float delta) {
        mRocks.forEach(rock -> rock.update(delta));
    }

    @Override
    public void render(SpriteBatch batch) {
        mRocks.forEach(rock -> rock.render(batch));
    }

    @Override
    public Optional<Rock> getColliderOf (Collidable another) {
        if(another == null)
            throw new NullPointerException();
        for (int i = 0; i < mRocks.size(); i++) {
            Rock rock = mRocks.get(i);
            if(another.hasCollisionWith(rock)) {
                return Optional.of(rock);
            }
        }
        return Optional.empty();
    }

    @Override
    public void add(Rock rock) {
        mRocks.add(rock);
    }

    @Override
    public void remove(Rock rock) {
        //rock.dispose();
        mRocks.remove(rock);
    }

    @Override
    public int size() {
        return mRocks.size();
    }

    @Override
    public void spawn(int i) {
        for(; i>0; i--) {
            add(new Rock(mAdapter));
        }
    }

    @Override
    public void randomize() {
        for (int i = 0; i < size(); i++) {
            mRocks.get(i).randomizeLocation();
        }
    }

    @Override
    public int distanceFrom(Entity entity) {
        if(mRocks.isEmpty()) return Integer.MAX_VALUE;
        int minDistance = entity.distanceFrom(mRocks.get(0));
        for (int i = 1; i < mRocks.size(); i++) {
            Entity rock = mRocks.get(i);
            if(rock == entity) continue;
            int distance = rock.distanceFrom(entity);
            minDistance = Math.min(minDistance, distance);
        }
        return minDistance;
    }
}
