package com.shalkevich.writer;

/**
 * Класс для записи вывода в консоль.
 */
public class ConsoleOutputWriter implements OutputService {


    /**
     * Записывает описание в консоль.
     *
     * @param description Описание фигуры.
     */
    @Override
    public void writeOutput(String description) {
        System.out.println(description);
    }
}
