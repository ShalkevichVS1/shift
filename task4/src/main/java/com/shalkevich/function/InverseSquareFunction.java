package com.shalkevich.function;

public class InverseSquareFunction implements FunctionStrategy {

    @Override
    public double compute(int i) {
        return i > 0 ? 1.0 / (i * i) : Double.POSITIVE_INFINITY; // Пример функции 2
    }
}
