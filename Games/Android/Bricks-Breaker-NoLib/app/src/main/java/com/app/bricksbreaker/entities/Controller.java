package com.app.bricksbreaker.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.app.bricksbreaker.Game;
import com.app.bricksbreaker.GamePanel;
import com.app.bricksbreaker.R;

public class Controller extends Entity {
    public Controller(Game g) {
        super(g);

        getImage();
        setDefaultValues();
    }

    @Override
    public void getImage() {
        images = new Bitmap[1];
        images[0] = BitmapFactory.decodeResource(game.context.getResources(), R.drawable.tape);
    }

    @Override
    public void draw(Canvas c) {
        c.drawBitmap(images[0], null, rect, null);
    }

    @Override
    public void update() {
        rect = new Rect(x, y, x+width, y+height);

        for (Ball ball : game.ballManager.getEntities()) {
            if (ball.isColliding(this)) {
                ball.go(Direction.UP);

                Rect r1 = new Rect(rect);
                r1.right = x + width / 2;
                r1.bottom = y + height / 2;
                if (ball.isColliding(r1)) {
                    ball.go(Direction.LEFT);
                } else {
                    ball.go(Direction.RIGHT);
                }
            }
        }

    }

    @Override
    public void setDefaultValues() {
        width = GamePanel.SCREEN_WIDTH/3;
        height = 85;

        x = GamePanel.SCREEN_WIDTH/2-width/2;
        y = GamePanel.SCREEN_HEIGHT-height;
    }

    public void goTo (int x) {
        this.x = x-width/2;
    }
}
