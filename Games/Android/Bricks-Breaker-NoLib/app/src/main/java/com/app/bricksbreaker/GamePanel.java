package com.app.bricksbreaker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.WindowManager;

import com.app.bricksbreaker.entities.Controller;
import com.app.bricksbreaker.manager.BallManager;
import com.app.bricksbreaker.manager.BricksManager;

public class GamePanel extends SurfaceView {
    private final Game game;
    private final Bitmap backgroundImage;
    private boolean running = true;

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    private Controller controller;
    private BricksManager bricksManager;
    private BallManager ballManager;

    public GamePanel (Context x, AttributeSet as) {
        super(x, as);
        game = new Game((MainActivity) x, this);
        backgroundImage = BitmapFactory.decodeResource(x.getResources(), R.drawable.background);

        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm = (WindowManager) game.context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay().getMetrics(dm);
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;

        initGameComp();
    }

    public void initGameComp () {
        controller = new Controller(game);
        bricksManager = new BricksManager(game);
        ballManager = new BallManager(game);

        game.controller = controller;
        game.bricksManager = bricksManager;
        game.ballManager = ballManager;

        bricksManager.initBricks();
    }

    public boolean isRunning () {
        return running;
    }

    public void startGameThread () {
        resume();
        Thread gameThread = new Thread(this::loop);
        gameThread.start();
    }

    public void loop () {
        long currentTime;
        long previousTime;
        long wait;
        long diff;
        final long SEC = 1000;
        final long FPS = 60;

        while (true) {
            if (!running) {
                continue;
            }

            previousTime = System.currentTimeMillis();

            update();
            draw();

            currentTime = System.currentTimeMillis();
            diff = currentTime-previousTime;
            wait = SEC/FPS - diff;

            try {
                Thread.sleep(wait > 0 ? wait : 0);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
    }

    public void pause () {
        running = false;
    }

    public void resume () {
        running = true;
    }

    public void draw () {
        if (getHolder().getSurface().isValid()) {
            Canvas c = getHolder().lockCanvas();
            Rect r = new Rect(0,0,c.getWidth(),c.getHeight());
            Matrix m = new Matrix();
            c.drawBitmap(backgroundImage, m, null);

            // draw entities
            controller.draw(c);
            bricksManager.draw(c);
            ballManager.draw(c);

            getHolder().unlockCanvasAndPost(c);
        }
    }

    public void update () {
        controller.update();
        bricksManager.update();
        ballManager.update();
    }

    public void addBalls (int val) {
        while (val > 0) {
            ballManager.addBall();
            val--;
        }
    }

    @Override
    public boolean onTouchEvent (MotionEvent event) {
        if (!running) {
            return super.onTouchEvent(event);
        }

        int action = event.getAction();
        int touchX = (int) event.getX();

        if (action==MotionEvent.ACTION_DOWN || action==MotionEvent.ACTION_MOVE) {
            if (touchX > 0) {
                controller.goTo(touchX);
            }

            return true;
        }

        return super.onTouchEvent(event);
    }

    public void reset() {
        initGameComp();
    }
}
