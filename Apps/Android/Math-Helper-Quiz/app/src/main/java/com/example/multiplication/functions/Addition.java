package com.example.multiplication.functions;

import com.example.multiplication.IntegerPair;
import com.example.multiplication.lib.Operation;

import java.util.Locale;

public class Addition extends Operation {
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
