package com.game.snake.android.utils;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    NONE;

    public Direction opposite() {
        return switch(this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
            case NONE -> NONE;
        };
    }

    public Orientation toOrientation() {
        return switch(this) {
            case UP, DOWN -> Orientation.VERTICAL;
            case LEFT, RIGHT -> Orientation.HORIZONTAL;
            case NONE -> Orientation.OTHER;
        };
    }
}
