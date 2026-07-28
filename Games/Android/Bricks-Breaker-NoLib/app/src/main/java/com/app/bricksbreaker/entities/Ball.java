package com.app.bricksbreaker.entities;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.app.bricksbreaker.Game;
import com.app.bricksbreaker.GamePanel;
import com.app.bricksbreaker.R;

public class Ball extends Entity {
    private int velocityY;
    private int velocityX;

    public Ball(Game g) {
        super(g);

        getImage();
        setDefaultValues();
    }

    public void increaseSpeed () {
        velocityY++; // TODO fix
        velocityX++;
    }

    @Override
    public void getImage() {
        images = new Bitmap[1];
        images[0] = BitmapFactory.decodeResource(game.context.getResources(), R.drawable.ball);
    }

    @Override
    public void draw(Canvas c) {
        c.drawBitmap(images[0], null, rect, null);
    }

    @Override
    public void update() {
        y += velocityY;
        x += velocityX;

        rect = new Rect(x, y, x+width, y+height);

        if (y <= 0) {
            velocityY = -velocityY;
            y = 1;
        }
        if (x <= 0) {
            go(Direction.RIGHT);
            x = 1;
        }
        if (x+width >= GamePanel.SCREEN_WIDTH) {
            go(Direction.LEFT);
            x = GamePanel.SCREEN_WIDTH-width-2;
        }

        if (game.bricksManager.removeBrickCollides(this)) {
            velocityY = velocityY > 0 ? -velocityY : Math.abs(velocityY);
        }

        if (y+height > game.controller.y+game.controller.height) {
            game.mainActivity.gameOver(false);
        }

        for (Ball ball : game.ballManager.getEntities()) {
            Rect r = ball.getRect();
            if (r==null || r.equals(rect)) {
                continue;
            }

            if (r.intersect(rect)) {
                ball.increaseSpeed();
                this.increaseSpeed();

                if (ball.x < this.x) {
                    ball.go(Direction.LEFT);
                    this.go(Direction.RIGHT);
                } else {
                    ball.go(Direction.RIGHT);
                    this.go(Direction.LEFT);
                }
                if (ball.y < this.y) {
                    ball.go(Direction.UP);
                    this.go(Direction.DOWN);
                } else {
                    ball.go(Direction.DOWN);
                    this.go(Direction.UP);
                }
            }
        }
    }

    public void go (Direction d) {
        switch (d) {
            case LEFT:
                velocityX-=2;
                if (velocityX==0) {
                    velocityX=-1;
                }
                break;
            case UP:
                velocityY = -velocityY;
                break;
            case RIGHT:
                velocityX+=2;
                if (velocityX==0) {
                    velocityX=1;
                }
                break;
            case DOWN:
                velocityY = Math.abs(velocityY);
                break;
            default:
                System.err.println("Invalid direction");
        }
    }

    @Override
    public void setDefaultValues() {
        width = GamePanel.SCREEN_WIDTH/7;
        height = width;

        velocityY = -10;
        velocityX = 0;

        x = GamePanel.SCREEN_WIDTH/2;
        y = (GamePanel.SCREEN_HEIGHT/3)*2;
    }

}
