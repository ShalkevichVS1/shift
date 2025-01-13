package com.shalkevich.function;

/**
 * Определяет метод для вычисления значения функции.
 */
public interface FunctionStrategy {

    /**
     * Вычисляет значение функции для заданного аргумента i.
     *
     * @param i Аргумент функции.
     * @return Вычисленное значение функции.
     */
    double compute(int i);
}
