package com.shalkevich.service;

import com.shalkevich.model.Task;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class TaskExecutor {
    private static final int NUM_THREADS = 4; // Количество потоков

    public List<Future<Double>> executeTasks(int n) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        List<Future<Double>> results = new ArrayList<>();

        int chunkSize = n / NUM_THREADS;
        for (int i = 0; i < NUM_THREADS; i++) {
            int start = i * chunkSize + 1;
            int end = (i == NUM_THREADS - 1) ? n : (i + 1) * chunkSize;
            Task task = new Task(start, end);
            results.add(executorService.submit(() -> {
                task.run();
                return task.getResult();
            }));
        }

        executorService.shutdown();
        return results;
    }
}
