package com.example.multiplication.operation;

import com.example.multiplication.IntegerPair;

import java.util.Locale;

public class Addition extends AbstractOperation {
    public Addition(int valueLimit) {
        super(valueLimit);
    }

    @Override
    protected IntegerPair generateRandomValues(IntegerPair rand) {
        return rand;
    }

    @Override
    protected int solveFor(IntegerPair pair) {
        return pair.a + pair.b;
    }

    @Override
    protected String getString(IntegerPair pair) {
        return String.format(Locale.getDefault(), "%d + %d", pair.a, pair.b);
    }
}
