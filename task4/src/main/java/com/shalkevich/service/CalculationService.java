package com.shalkevich.service;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.Future;

@Slf4j
public class CalculationService {

    public double calculateSum(int n) {
        TaskExecutor taskExecutor = new TaskExecutor();
        List<Future<Double>> results = taskExecutor.executeTasks(n);

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
