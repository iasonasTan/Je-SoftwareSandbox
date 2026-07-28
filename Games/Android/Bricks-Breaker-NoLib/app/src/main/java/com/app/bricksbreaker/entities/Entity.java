package com.app.bricksbreaker.entities;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.app.bricksbreaker.Game;

public abstract class Entity {
    protected int x, y;
    protected int width, height;
    protected Bitmap[] images;
    protected Game game;
    protected Rect rect;

    public Entity (Game g) {
        game = g;

    }

    public boolean isColliding (Entity e) {
        if (e.rect==null || rect==null)
            return false;

        return e.rect.intersect(rect);
    }

    public boolean isColliding (Rect r) {
        if (r==null || rect==null)
            return false;

        return r.intersect(rect);
    }

    public Rect getRect () {
        return rect;
    }

    public void goTo (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    abstract public void getImage();
    abstract public void draw (Canvas c);
    abstract public void update();
    abstract public void setDefaultValues();

    public enum Direction {
        LEFT,
        RIGHT,
        UP,
        DOWN;
    }
}
