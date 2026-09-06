package com.example.multiplication.operation;

import com.example.multiplication.IntegerPair;

public class SQRoot extends AbstractOperation {
    private int[] PERFECT_SQUARES;

    public SQRoot(int valueLimit) {
        super(valueLimit);
    }

    @Override
    protected void init(int limit) {
        PERFECT_SQUARES = new int[limit];
        for (int i = 0; i <limit; i++) {
            PERFECT_SQUARES[i] = i*i;
        }
    }

    @Override
    protected IntegerPair generateRandomValues(IntegerPair ignored) {
        int perfectSquare = PERFECT_SQUARES[(int)(Math.random()*PERFECT_SQUARES.length)];
        double half = perfectSquare/2.0;
        return new IntegerPair((int)half, (int)Math.ceil(half));
    }

    @Override
    protected int solveFor(IntegerPair pair) {
        return (int) Math.sqrt(pair.a+pair.b);
    }

    @Override
    protected String getString(IntegerPair pair) {
        return "√("+pair.a+"+"+pair.b+")";
    }
}

