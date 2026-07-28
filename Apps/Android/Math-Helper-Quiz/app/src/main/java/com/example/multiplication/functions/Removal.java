package com.example.multiplication.functions;

import com.example.multiplication.IntegerPair;
import com.example.multiplication.lib.Operation;

public class Removal extends Operation {
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
