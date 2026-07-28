package com.example.multiplication.functions;

import com.example.multiplication.IntegerPair;
import com.example.multiplication.lib.Operation;

public class Division extends Operation {
    public Division(int valueLimit) {
        super(valueLimit);
    }

    @Override
    protected IntegerPair generateRandomValues(IntegerPair rand) {
        do {
            rand = generateRandomPair();
        }while(rand.b==0);
        int result = rand.a/rand.b;
        rand.a = result*rand.b;
        return rand;
    }

    @Override
    protected int solveFor(IntegerPair pair) {
        return pair.a/pair.b;
    }

    @Override
    protected String getString(IntegerPair pair) {
        return pair.a+" ÷ "+pair.b;
    }
}
