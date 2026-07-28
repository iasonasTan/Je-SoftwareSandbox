package com.app.bricksbreaker.manager;

import android.graphics.Canvas;

import com.app.bricksbreaker.Game;
import com.app.bricksbreaker.GamePanel;
import com.app.bricksbreaker.entities.Brick;
import com.app.bricksbreaker.entities.Entity;

public class BricksManager extends EntityManager<Brick> {

    public BricksManager (Game g) {
        super(g);
    }

    public void initBricks () {
        final int GAP = 20;
        int nextX = GAP;
        int nextY = GAP;

        do {
            addEntity(new Brick(game, nextX, nextY));
            nextX += GAP+getEntity(0).getWidth();
            if (nextX+getEntity(0).getWidth() > GamePanel.SCREEN_WIDTH) {
                nextX = GAP;
                nextY += GAP+getEntity(0).getHeight();
            }
        } while (nextY < GamePanel.SCREEN_HEIGHT/2);
    }

    public boolean removeBrickCollides (Entity e) {
        for (Brick b : getEntities()) {
            if (e.getRect().intersect(b.getRect())) {
                removeEntity(b);
                if (isEmpty()) {
                    game.mainActivity.gameOver(true);
                }
                return true; // only one at time
            }
        }

        return false;
    }

    public void update () {
        super.update();
    }

    public void draw (Canvas c) {
        super.draw(c);
    }

}
