package com.app.rps.game;

public final class GameHandlerBuilder {
    private final GameHandler mGameHandler = new GameHandler();

    public GameHandlerBuilder setImageUpdaters(GameHandler.ImageUpdater u1, GameHandler.ImageUpdater u2) {
        if(u1 == null || u2 == null)
            throw new NullPointerException("No updater can be null...");
        mGameHandler.setImageUpdaters(u1, u2);
        return this;
    }

    public GameHandlerBuilder setScoreUpdaters(GameHandler.ScoreUpdater u1, GameHandler.ScoreUpdater u2) {
        if(u1 == null || u2 == null)
            throw new NullPointerException("No updater can be null...");
        mGameHandler.setScoreUpdaters(u1, u2);
        return this;
    }

    public GameHandler build() {
        return mGameHandler;
    }
}
