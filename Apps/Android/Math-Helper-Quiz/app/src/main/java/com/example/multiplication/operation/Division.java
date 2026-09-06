package com.example.multiplication.operation;

import com.example.multiplication.IntegerPair;

public class Division extends AbstractOperation {
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
