package com.example.multiplication.operation;

import com.example.multiplication.IntegerPair;

public class Multiplication extends AbstractOperation {
    public Multiplication(int valueLimit) {
        super(valueLimit);
    }

    @Override
    protected IntegerPair generateRandomValues(IntegerPair rand) {
        return rand;
    }

    @Override
    protected int solveFor(IntegerPair pair) {
        return pair.a*pair.b;
    }

    @Override
    protected String getString(IntegerPair pair) {
        return pair.a+" × "+pair.b;
    }
}
