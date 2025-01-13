package com.shalkevich.function;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FunctionStrategyTest {

    @Test
    public void testExponentialDecayFunction() {
        FunctionStrategy strategy = new ExponentialDecayFunction();
        assertEquals(1.0, strategy.compute(0), 0.00001);
        assertEquals(0.5, strategy.compute(1), 0.00001);
        assertEquals(0.25, strategy.compute(2), 0.00001);
    }

    @Test
    public void testInverseSquareFunction() {
        FunctionStrategy strategy = new InverseSquareFunction();
        assertEquals(Double.POSITIVE_INFINITY, strategy.compute(0), 0.00001);
        assertEquals(1.0, strategy.compute(1), 0.00001);
        assertEquals(0.25, strategy.compute(2), 0.00001);
    }

    @Test
    public void testInverseProductFunction() {
        FunctionStrategy strategy = new InverseProductFunction();
        assertEquals(Double.POSITIVE_INFINITY, strategy.compute(0), 0.00001);
        assertEquals(1.0 / 2, strategy.compute(1), 0.00001);
        assertEquals(1.0 / 6, strategy.compute(2), 0.00001);
    }

    @Test
    public void testHarmonicFunction() {
        FunctionStrategy strategy = new HarmonicFunction();
        assertEquals(Double.POSITIVE_INFINITY, strategy.compute(0), 0.00001);
        assertEquals(1.0, strategy.compute(1), 0.00001);
        assertEquals(0.5, strategy.compute(2), 0.00001);
    }

    @Test
    public void testFunctionFactory() {
        assertThrows(IllegalArgumentException.class, () -> FunctionFactory.getFunctionStrategy(0));
        assertThrows(IllegalArgumentException.class, () -> FunctionFactory.getFunctionStrategy(5));
    }
}
