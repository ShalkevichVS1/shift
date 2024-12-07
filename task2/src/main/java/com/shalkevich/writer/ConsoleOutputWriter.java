package com.shalkevich.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс для вывода данных фигуры в консоль.
 */
public class ConsoleOutputWriter implements OutputService {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleOutputWriter.class);

    /**
     * Записывает данные фигуры в консоль.
     *
     * @param description Строка с описанием фигуры.
     */
    @Override
    public void writeOutput(String description) {
        System.out.println(description);
        logger.info("Output written to console");
    }
}
