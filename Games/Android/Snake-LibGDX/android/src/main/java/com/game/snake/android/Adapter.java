package com.game.snake.android;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.snake.android.behaviour.Collidable;
import com.game.snake.android.behaviour.Container;
import com.game.snake.android.behaviour.Player;
import com.game.snake.android.utils.Direction;
import com.game.snake.android.utils.UiThreadHandler;
import com.game.snake.android.model.Apple;
import com.game.snake.android.model.Rock;
import com.game.snake.android.model.RocksManager;
import com.game.snake.android.model.Snake;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Adapter extends ApplicationAdapter {
    private SpriteBatch mSpriteBatch;
    private Player mPlayer;
    private Texture mGameBackground, mControllerBackground;
    private Stage mStage;
    private List<Texture> mTextures;
    private Label mScoreLabel;
    private boolean mPaused = true;
    private Apple mApple;
    private Container<Rock> mRockManager;
    private int mBestScore;
    protected final Context context;
    private final UiThreadHandler mUiThreadHandler;
    private final DialogInterface.OnClickListener mReplayListener = (a, v) -> {
        Gdx.app.postRunnable(() -> {
            dispose();
            create();
        });
    };
    private final DialogInterface.OnClickListener mExitListener = (a, v) -> {
        Gdx.app.exit();
    };

    public Adapter(Context context, UiThreadHandler mUiThreadHandler) {
        this.context = context;
        this.mUiThreadHandler = mUiThreadHandler;
        Log.d("dev-test", "Game started!");
        Log.d("dev-test", "Context is "+context);
    }

    @Override
    public void create() {
        mTextures = new ArrayList<>();
        mPaused = true;
        mStage = new Stage(new ScreenViewport());
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        SharedPreferences preferences = context.getSharedPreferences("score", Context.MODE_PRIVATE);
        mBestScore = preferences.getInt("score", 0);
        mScoreLabel = new Label("Score: 0, Best Score: "+mBestScore, style);
        mScoreLabel.setSize(200, 50);
        mScoreLabel.setFontScale(5);
        mScoreLabel.setPosition(100, 20);
        mStage.addActor(mScoreLabel);
        Gdx.input.setInputProcessor(mStage);
        int size = Gdx.graphics.getWidth()/3, mar = (int) (size/3.5);
        createSquareImageButton("game/arrow_right.png", b -> mPlayer.setDirection(Direction.RIGHT), 2*size-mar, size, size, false);
        createSquareImageButton("game/arrow_left.png", b -> mPlayer.setDirection(Direction.LEFT), mar, size, size, false);
        createSquareImageButton("game/arrow_up.png", b -> mPlayer.setDirection(Direction.UP), size, 2*size-mar, size, false);
        createSquareImageButton("game/arrow_down.png", b -> mPlayer.setDirection(Direction.DOWN), size, mar, size, false);
        int size2 = (int) (size/1.5);
        Texture texture = new Texture("game/resume.png");
        Sprite sprite = new Sprite(texture);
        sprite.setSize(size2, size2);
        Drawable resumeTexture = new SpriteDrawable(sprite);
        Texture texture1 = new Texture("game/pause.png");
        Sprite sprite1 = new Sprite(texture1);
        sprite1.setSize(size2, size2);
        Drawable pauseTexture = new SpriteDrawable(sprite1);
        ImageButton pauseButton = createSquareImageButton("game/resume.png", b -> {
            mPaused = !mPaused;
            Drawable image = mPaused?resumeTexture:pauseTexture;
            b.getStyle().imageUp = image;
            b.getStyle().imageDown = image;

            Log.d("dev-test", "Button is "+(mPaused?"Paused.":"Resumed."));
        }, Gdx.graphics.getWidth()-size2, Gdx.graphics.getHeight()/2-size2, size2, true);

        mRockManager = new RocksManager(this);
        mPlayer = new Snake(this, context);
        mApple = new Apple(this);
        mRockManager.spawn(3);
        mSpriteBatch = new SpriteBatch();
        mGameBackground = new Texture("game/background.png");
        mControllerBackground = new Texture("game/controller_background.png");

        pauseButton.getClickListener().clicked(null, 0, 0);
    }

    public void updateScore(int s) {
        mScoreLabel.setText("Score: "+s+", Best Score: "+mBestScore);
        if(s%6==0&&(int)(Math.random()*100)>60) {
            mRockManager.add(new Rock(this));
        }
    }

    private ImageButton createSquareImageButton(String drawablePath, Consumer<ImageButton> action, int x, int y, int size, boolean onPaused) {
        Texture texture = new Texture(drawablePath);
        Sprite sprite = new Sprite(texture);
        sprite.setSize(size, size);
        Drawable image = new SpriteDrawable(sprite);
        ImageButton button = new ImageButton(image);
        button.setSize(size, size);
        button.setPosition(x, y);
        mStage.addActor(button);
        button.addListener(new ClickListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if(mPaused&&!onPaused) {
                    return false;
                } else {
                    action.accept((ImageButton) event.getListenerActor());
                    return true;
                }
            }
        });
        mTextures.add(texture);
        return button;
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void render() {
        if(!mPaused) {
            // update
            mStage.act(Gdx.graphics.getDeltaTime());
            mPlayer.update(Gdx.graphics.getDeltaTime());
            mApple.update(Gdx.graphics.getDeltaTime());
            mRockManager.update(Gdx.graphics.getDeltaTime());
        }

        // render
        final float SCREEN_WIDTH = Gdx.graphics.getWidth(), SCREEN_HEIGHT = Gdx.graphics.getHeight();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mSpriteBatch.begin();
        mSpriteBatch.draw(mGameBackground, 0, SCREEN_HEIGHT/2, SCREEN_WIDTH, SCREEN_HEIGHT/2);
        mSpriteBatch.draw(mControllerBackground, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT/2);
        mPlayer.render(mSpriteBatch);
        mApple.render(mSpriteBatch);
        mRockManager.render(mSpriteBatch);
        mSpriteBatch.end();
        mStage.draw();
    }

    @Override
    public void pause() {
        super.pause();
        mPaused = true;
    }

    @Override
    public void resume() {
        super.resume();
        mPaused = false;
    }

    @Override
    public void dispose() {
        mSpriteBatch.dispose();
        mGameBackground.dispose();
        mControllerBackground.dispose();
        mStage.dispose();
        mTextures.forEach(Texture::dispose);
    }

    public void gameOver() {
        Log.d("dev-test", "Game is over!");
        pause();
        Log.d("dev-test", "Game is over, collisions: "+mRockManager.getColliderOf((Collidable) mPlayer).isPresent());
        mUiThreadHandler.runOnUi(() -> new AlertDialog.Builder(context)
            .setTitle("Game Over!")
            .setCancelable(false)
            .setMessage("Game is over, would you like to replay?")
            .setPositiveButton("Replay", mReplayListener)
            .setNegativeButton("Exit", mExitListener)
            .show());
    }

    public Player getPlayer() {
        return mPlayer;
    }

    public Apple getApple() {
        return mApple;
    }

    public Container<Rock> getRockManager() {
        return mRockManager;
    }
}

