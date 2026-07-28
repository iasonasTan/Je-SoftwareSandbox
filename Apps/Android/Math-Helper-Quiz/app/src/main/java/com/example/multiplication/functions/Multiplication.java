package com.example.multiplication.functions;

import com.example.multiplication.IntegerPair;
import com.example.multiplication.lib.Operation;

public class Multiplication extends Operation {
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
