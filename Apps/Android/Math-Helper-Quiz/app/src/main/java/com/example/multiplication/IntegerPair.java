package com.example.multiplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class IntegerPair {
    public int a, b;

    public IntegerPair() {
        a = 0;
        b = 0;
    }

    public IntegerPair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @NonNull
    @Override
    public String toString() {
        return "IntPair:{a:"+a+",b:"+b+"}";
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if(obj instanceof IntegerPair) {
            IntegerPair pair = (IntegerPair)obj;
            return pair.a==a&&pair.b==b;
        }
        return false;
    }
}
