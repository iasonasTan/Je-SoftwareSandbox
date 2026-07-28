package com.example.multiplication.lib;

import androidx.annotation.NonNull;

import com.example.multiplication.IntegerPair;

public abstract class Operation {
    private final int VALUE_LIMIT;
    private IntegerPair INTEGER_PAIR = new IntegerPair();

    public Operation(int valueLimit) {
        VALUE_LIMIT = valueLimit;
        init(valueLimit);
        randomizeValues();
    }

    public final int getLimit() {
        return VALUE_LIMIT;
    }

    public final void randomizeValues() {
        INTEGER_PAIR = generateRandomValues(generateRandomPair());
    }

    protected void init(int limit) {
    }

    protected abstract IntegerPair generateRandomValues(IntegerPair randPair);
    protected abstract int solveFor(IntegerPair pair);
    protected abstract String getString(IntegerPair pair);

    public final int calculateResult() {
        return solveFor(INTEGER_PAIR);
    }

    @NonNull
    @Override
    public final String toString() {
        return getString(INTEGER_PAIR);
    }

    protected final IntegerPair generateRandomPair() {
        int a = (int)(Math.random()*getLimit());
        int b = (int)(Math.random()*getLimit());
        return new IntegerPair(a, b);
    }
}
