package com.shalkevich.writer;

import com.shalkevich.figures.Figure;

/**
 * Интерфейс для вывода данных фигуры.
 */
public interface OutputService {
    /**
     * Записывает данные фигуры.
     *
     * @param figure Объект фигуры.
     */
    void writeOutput(Figure figure);
}
