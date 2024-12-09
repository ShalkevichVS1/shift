package com.shalkevich.function;

public class ExponentialDecayFunction implements FunctionStrategy {

    @Override
    public double compute(int i) {
        return Math.pow(0.5, i); // Пример функции 1
    }
}
