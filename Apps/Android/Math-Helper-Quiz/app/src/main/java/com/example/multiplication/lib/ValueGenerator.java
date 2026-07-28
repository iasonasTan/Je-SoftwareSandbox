package com.example.multiplication.lib;

@Deprecated(forRemoval = true)
@FunctionalInterface
public interface ValueGenerator {
    int[] generateValues(int limit);
}
