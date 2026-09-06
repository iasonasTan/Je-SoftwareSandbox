package com.example.multiplication.operation;

import com.example.multiplication.IntegerPair;

public class Removal extends AbstractOperation {
    public Removal(int valueLimit) {
        super(valueLimit);
    }

    @Override
    protected IntegerPair generateRandomValues(IntegerPair rand) {
        return rand;
    }

    @Override
    protected int solveFor(IntegerPair pair) {
        return Math.abs(pair.a - pair.b);
    }

    @Override
    protected String getString(IntegerPair pair) {
        String out = pair.a+" - "+pair.b;
        if(pair.a - pair.b < 0)
            return "|"+out+"|";
        return out;
    }
}
