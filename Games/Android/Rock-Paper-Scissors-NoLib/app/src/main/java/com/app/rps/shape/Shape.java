package com.app.rps.shape;

import com.app.rps.R;

enum Shape implements ShapeResourceContainer {
    NONE(R.drawable.blank_green, R.drawable.blank_red),
    ROCK(R.drawable.rock_green, R.drawable.rock_red),
    PAPER(R.drawable.paper_green, R.drawable.paper_red),
    SCISSORS(R.drawable.scissors_green, R.drawable.scissors_red);

    public final int greenResID;
    public final int redResID;

    Shape(int grID, int rrID) {
        greenResID = grID;
        redResID   = rrID;
    }

    public Shape worse() {
        if(this == PAPER) {
            return ROCK;
        }
        if(this == ROCK) {
            return SCISSORS;
        }
        if(this == SCISSORS) {
            return PAPER;
        }
        if(this == NONE) {
            return NONE;
        }
        throw new RuntimeException();
    }

    @Override
    public int greenResource() {
        return greenResID;
    }

    @Override
    public int redResource() {
        return redResID;
    }

    public static Shape random() {
        Shape[] values = values();
        return values[(int) (Math.random() * values.length)];
    }

    public static Shape randomShapeExcept(Shape prev) {
        Shape out;
        do {
            out = random();
        } while (out == prev || out == NONE);
        return out;
    }
}
