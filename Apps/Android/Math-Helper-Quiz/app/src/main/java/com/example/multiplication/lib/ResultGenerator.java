package com.example.multiplication.lib;

import com.example.multiplication.R;

@FunctionalInterface
@Deprecated(forRemoval = true)
public interface ResultGenerator {
    int result(int a, int b);
}
