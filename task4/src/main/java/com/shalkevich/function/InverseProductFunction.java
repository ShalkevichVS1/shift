package com.shalkevich.function;

public class InverseProductFunction implements FunctionStrategy {

    @Override
    public double compute(int i) {
        return i > 0 ? 1.0 / (i * (i + 1)) : Double.POSITIVE_INFINITY; // Пример функции 3
    }
}
