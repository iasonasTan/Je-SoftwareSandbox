package com.app.bricksbreaker.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.app.bricksbreaker.Game;
import com.app.bricksbreaker.GamePanel;
import com.app.bricksbreaker.R;

public class Brick extends Entity {
    public Brick(Game g, int x, int y) {
        super(g);

        this.x = x;
        this.y = y;

        getImage();
        setDefaultValues();
    }

    @Override
    public void getImage() {
        images = new Bitmap[1];
        images[0] = BitmapFactory.decodeResource(game.context.getResources(), R.drawable.brick);
    }

    @Override
    public void draw(Canvas c) {
        c.drawBitmap(images[0], null, rect, null);
    }

    @Override
    public void update() {
        rect = new Rect(x, y, x+width, y+height);
    }

    @Override
    public void setDefaultValues() {
        width = GamePanel.SCREEN_WIDTH/5;
        height = (int)(width*0.75);
    }
}
