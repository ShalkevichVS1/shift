package com.shalkevich.service;

import com.shalkevich.function.ExponentialDecayFunction;
import com.shalkevich.function.FunctionStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculationServiceTest {

    private CalculationService calculationService;
    private FunctionStrategy strategy;

    @BeforeEach
    public void setup() {
        calculationService = new CalculationService();
        strategy = new ExponentialDecayFunction();
    }

    @Test
    public void testCalculateSum() {
        double result = calculationService.calculateSum(10, strategy);
        assertEquals(1.998046875, result, 0.00001);
    }
}
