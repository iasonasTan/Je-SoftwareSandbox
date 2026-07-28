package com.example.multiplication.functions;

import com.example.multiplication.IntegerPair;
import com.example.multiplication.lib.Operation;

public class SQRoot extends Operation {
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
//        int a = (int)(Math.random()*perfectSquare);
//        int b = perfectSquare-a;
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

