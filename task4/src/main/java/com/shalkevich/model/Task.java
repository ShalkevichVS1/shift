package com.shalkevich.model;

import com.shalkevich.function.FunctionStrategy;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Класс Task представляет собой вычислительную задачу, которая выполняется в заданном диапазоне.
 */
@Data
@Slf4j
public class Task implements Runnable {
    private final int start;
    private final int end;
    private final FunctionStrategy functionStrategy;
    private double result = 0.0;

    /**
     * Конструктор для создания задачи.
     *
     * @param start            Начало диапазона.
     * @param end              Конец диапазона.
     * @param functionStrategy Стратегия для вычислений.
     */
    public Task(int start, int end, FunctionStrategy functionStrategy) {
        this.start = start;
        this.end = end;
        this.functionStrategy = functionStrategy;
    }

    @Override
    public void run() {
        log.debug("Начало вычислений для диапазона: {} - {}", start, end);
        result = calculate();
        log.debug("Вычисления завершены для диапазона: {} - {}, результат: {}", start, end, result);
    }

    /**
     * Выполняет вычисления для заданного диапазона.
     *
     * @return Сумма вычисленных значений.
     */
    private double calculate() {
        double sum = 0.0;
        for (int i = start; i <= end; i++) {
            double term = functionStrategy.compute(i);
            if (Double.isInfinite(term)) {
                log.warn("Значение стало бесконечным на шаге: {}", i);
                break;
            }
            sum += term;
            log.debug("Шаг {}: значение {}", i, term);
        }
        return sum;
    }
}
