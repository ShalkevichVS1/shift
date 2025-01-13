package com.shalkevich.service;

import com.shalkevich.function.ExponentialDecayFunction;
import com.shalkevich.function.FunctionStrategy;
import com.shalkevich.model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskExecutorTest {

    private TaskExecutor taskExecutor;
    private FunctionStrategy strategy;

    @BeforeEach
    public void setup() {
        taskExecutor = new TaskExecutor();
        strategy = new ExponentialDecayFunction();
    }

    @Test
    public void testCreateTasks() {
        List<Task> tasks = taskExecutor.createTasks(10, strategy);
        assertEquals(4, tasks.size());
    }

    @Test
    public void testExecuteTasks() throws ExecutionException, InterruptedException {
        List<Task> tasks = taskExecutor.createTasks(10, strategy);
        List<Future<Double>> results = taskExecutor.executeTasks(tasks);

        double totalSum = 0.0;
        for (Future<Double> future : results) {
            totalSum += future.get();
        }

        assertEquals(1.998046875, totalSum, 0.00001);
    }
}
