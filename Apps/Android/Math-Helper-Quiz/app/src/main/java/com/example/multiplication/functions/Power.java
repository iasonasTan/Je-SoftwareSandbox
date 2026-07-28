package com.example.multiplication.functions;

import com.example.multiplication.IntegerPair;
import com.example.multiplication.lib.Operation;

public class Power extends Operation {
    private final String[] SUPERSCRIPTS = {"⁰", "¹", "²", "³", "⁴", "⁵"};

    public Power(int valueLimit) {
        super(valueLimit);
    }

    @Override
    protected IntegerPair generateRandomValues(IntegerPair rand) {
        IntegerPair out;
        do {
            out = generateRandomPair(); // generated random pair based on objects limit
        }while(out.a==0&&out.b==0); // avoid 0^0
        return out;
    }

    @Override
    protected int solveFor(IntegerPair pair) {
        return (int)Math.pow(pair.a, pair.b);
    }

    @Override
    protected String getString(IntegerPair pair) {
        if(SUPERSCRIPTS.length<pair.b)
            throw new IllegalArgumentException("No superscripts available for value "+pair.b+".");
        return pair.a+SUPERSCRIPTS[pair.b];
    }
}
