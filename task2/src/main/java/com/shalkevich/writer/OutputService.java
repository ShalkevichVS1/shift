package com.shalkevich.writer;

/**
 * Интерфейс для вывода данных фигуры.
 */
public interface OutputService {
    /**
     * Записывает данные фигуры.
     *
     * @param description Строка с описанием фигуры.
     */
    void writeOutput(String description);
}
