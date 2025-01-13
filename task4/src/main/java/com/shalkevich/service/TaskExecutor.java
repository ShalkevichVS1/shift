package com.shalkevich.service;

import com.shalkevich.function.FunctionStrategy;
import com.shalkevich.model.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

/**
 * Класс TaskExecutor управляет созданием и выполнением задач в многопоточном режиме.
 */
@Slf4j
public class TaskExecutor {
    private static final int NUM_THREADS = 4; // Количество потоков

    /**
     * Создает задачи для выполнения в потоках.
     *
     * @param n                Количество элементов.
     * @param functionStrategy Стратегия для вычислений.
     * @return Список задач.
     */
    public List<Task> createTasks(int n, FunctionStrategy functionStrategy) {
        log.debug("Создание задач для N = {}", n);
        List<Task> tasks = new CopyOnWriteArrayList<>();
        int chunkSize = (int) Math.ceil((double) n / NUM_THREADS);

        IntStream.range(0, NUM_THREADS).forEach(i -> {
            int start = i * chunkSize;
            int end = Math.min((i + 1) * chunkSize - 1, n - 1);
            tasks.add(new Task(start, end, functionStrategy));
            log.debug("Создана задача для диапазона: {} - {}", start, end);
        });

        return tasks;
    }

    /**
     * Выполняет задачи параллельно.
     *
     * @param tasks Список задач.
     * @return Список Future объектов с результатами выполнения.
     */
    public List<Future<Double>> executeTasks(List<Task> tasks) {
        log.debug("Запуск задач...");
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Double>> results = new CopyOnWriteArrayList<>();

        try {
            tasks.forEach(task -> results.add(executorService.submit(() -> {
                log.debug("Запуск задачи для диапазона: {} - {}", task.getStart(), task.getEnd());
                task.run();
                log.debug("Задача для диапазона: {} - {} завершена, результат: {}", task.getStart(), task.getEnd(), task.getResult());
                return task.getResult();
            })));
        } finally {
            executorService.shutdown();
        }

        log.debug("Все задачи запущены.");
        return results;
    }
}
