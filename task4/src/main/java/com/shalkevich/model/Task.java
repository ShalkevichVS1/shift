package com.shalkevich.model;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Task implements Runnable {
    private final int start;
    private final int end;
    @Getter
    private double result;

    public Task(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public void run() {
        log.debug("Начало вычислений для диапазона: {} - {}", start, end);
        result = calculate();
        log.debug("Вычисления завершены для диапазона: {} - {}, результат: {}", start, end, result);
    }

    private double calculate() {
        double sum = 0.0;
        for (int i = start; i <= end; i++) {
            sum += 1.0 / (i * i);
        }
        return sum;
    }

}
