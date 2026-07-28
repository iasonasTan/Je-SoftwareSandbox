package com.app.rps.game;

import com.app.rps.shape.ShapeSupplier;
import com.app.rps.shape.ShapesPair;

public final class GameHandler {
    private static final int VOLUME_RED_WINS   = 0;
    private static final int VOLUME_GREEN_WINS = 1;

    private ImageUpdater mGreenImageUpdater, mRedImageUpdater;
    private ScoreUpdater mGreenScoreUpdater, mRedScoreUpdater;

    private int mGreenScore, mRedScore;

    private final ShapeSupplier mSupplier = new ShapeSupplier();

    GameHandler() {
    }

    public int[] getScores() {
        return new int[]{mGreenScore, mRedScore};
    }

    public void setScores(int[] scores) {
        mGreenScore = scores[0];
        mRedScore   = scores[1];
    }

    public void play(int volume) {
        int imageGreenId, imageRedId;

        ShapesPair shapes;
        if(volume == VOLUME_RED_WINS) {
            shapes = mSupplier.generatePair(ShapeSupplier.TYPE_SHAPE_1_WINS);
        } else if (volume == VOLUME_GREEN_WINS) {
            shapes = mSupplier.generatePair(ShapeSupplier.TYPE_SHAPE_2_WINS);
        } else { // COMPLETELY RANDOM
            shapes = mSupplier.generatePair(ShapeSupplier.TYPE_RANDOM_SHAPE);
        }

        imageRedId   =  shapes.shape1.redResource();
        imageGreenId =  shapes.shape2.greenResource();

        if(shapes.score1 != 0) {
            mRedScore += shapes.score1;
            mRedScoreUpdater.updateScore(mRedScore);
        }

        if(shapes.score2 != 0) {
            mGreenScore += shapes.score2;
            mGreenScoreUpdater.updateScore(mGreenScore);
        }

        mRedImageUpdater.updateImage(imageRedId);
        mGreenImageUpdater.updateImage(imageGreenId);
    }

    public void setImageUpdaters(ImageUpdater greenImageUpdater, ImageUpdater redImageUpdater) {
        mGreenImageUpdater = greenImageUpdater;
        mRedImageUpdater   = redImageUpdater;
    }

    public void setScoreUpdaters(ScoreUpdater greenScoreUpdater, ScoreUpdater redScoreUpdater) {
        mGreenScoreUpdater = greenScoreUpdater;
        mRedScoreUpdater   = redScoreUpdater;
    }

    public void resetScores() {
        mGreenScore = 0;
        mRedScore = 0;
        mGreenScoreUpdater.updateScore(mGreenScore);
        mRedScoreUpdater.updateScore(mRedScore);
    }

    public interface ImageUpdater {
        void updateImage(int res);
    }

    public interface ScoreUpdater {
        void updateScore(int score);
    }
}
