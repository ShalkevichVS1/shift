package com.shalkevich.function;

import lombok.extern.slf4j.Slf4j;

/**
 * Создает стратегии функций на основе выбора пользователя.
 */
@Slf4j
public class FunctionFactory {

    /**
     * Возвращает стратегию функции на основе выбора пользователя.
     *
     * @param functionChoice Выбор пользователя.
     * @return Стратегия функции.
     */
    public static FunctionStrategy getFunctionStrategy(int functionChoice) {
        return switch (functionChoice) {
            case 1 -> new ExponentialDecayFunction();
            case 2 -> new InverseSquareFunction();
            case 3 -> new InverseProductFunction();
            case 4 -> new HarmonicFunction();
            default -> {
                log.error("Недопустимый выбор функции.");
                throw new IllegalArgumentException("Недопустимый выбор функции");
            }
        };
    }
}
