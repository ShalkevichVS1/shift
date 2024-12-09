package com.shalkevich.model;

import com.shalkevich.function.ExponentialDecayFunction;
import com.shalkevich.function.FunctionStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    private Task task;

    @BeforeEach
    public void setup() {
        FunctionStrategy strategy = new ExponentialDecayFunction();
        task = new Task(0, 10, strategy);
    }

    @Test
    public void testCalculate() {
        task.run();
        assertEquals(1.9990234375, task.getResult(), 0.00001);
    }
}
