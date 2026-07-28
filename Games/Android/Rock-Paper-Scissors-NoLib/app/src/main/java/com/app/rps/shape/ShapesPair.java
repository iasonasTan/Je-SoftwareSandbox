package com.app.rps.shape;

public class ShapesPair {
    public final ShapeResourceContainer shape1, shape2;
    public final int score1, score2;

    public ShapesPair(ShapeResourceContainer shape1, ShapeResourceContainer shape2, int score1, int score2) {
        this.shape1 = shape1;
        this.shape2 = shape2;
        this.score1 = score1;
        this.score2 = score2;
    }
}
