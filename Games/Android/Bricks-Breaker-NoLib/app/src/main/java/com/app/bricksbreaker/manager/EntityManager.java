package com.app.bricksbreaker.manager;

import android.graphics.Canvas;

import com.app.bricksbreaker.Game;
import com.app.bricksbreaker.entities.Entity;

import java.util.ArrayList;

public abstract class EntityManager <E extends Entity> {
    private final ArrayList<E> entities = new ArrayList<>();
    protected Game game;

    public EntityManager (Game g) {
        game = g;
    }

    public void addEntity (E e) {
        entities.add(e);
    }

    public E getEntity (int idx) {
        return entities.get(idx);
    }

    public boolean isEmpty () {
        return entities.isEmpty();
    }

    public void removeEntity (E e) {
        entities.remove(e);
    }

    public ArrayList<E> getEntities () {
        return entities;
    }

    public void draw (Canvas c) {
        entities.forEach(e -> {
            e.draw(c);
        });
    }

    public void update () {
        entities.forEach(E::update);
    }

}
