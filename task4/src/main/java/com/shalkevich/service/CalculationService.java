package com.shalkevich.service;

import com.shalkevich.function.FunctionStrategy;
import com.shalkevich.model.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Future;

/**
 * Класс CalculationService управляет процессом вычисления сумм значений функций.
 */
@Slf4j
public class CalculationService {

    /**
     * Выполняет расчет суммы значений выбранной функции для заданного N.
     *
     * @param n                Количество элементов.
     * @param functionStrategy Стратегия для вычислений.
     * @return Общая сумма значений.
     */
    public double calculateSum(int n, FunctionStrategy functionStrategy) {
        TaskExecutor taskExecutor = new TaskExecutor();
        List<Task> tasks = taskExecutor.createTasks(n, functionStrategy);
        List<Future<Double>> results = taskExecutor.executeTasks(tasks);

        double totalSum = 0.0;
        try {
            for (Future<Double> future : results) {
                totalSum += future.get();
            }
        } catch (Exception e) {
            log.error("Ошибка при выполнении вычислений", e);
        }

        log.info("Общая сумма вычислений: {}", totalSum);
        return totalSum;
    }
}
