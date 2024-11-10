package com.shalkevich.writer;

import com.shalkevich.figures.Figure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Вывода данных фигуры.
 */
public class OutputWriter {

    private static final Logger logger = LoggerFactory.getLogger(OutputWriter.class);

    /**
     * Записывает выходные данные фигуры в консоль или файл в зависимости от указанного режима.
     *
     * @param figure     Объект фигуры.
     * @param outputMode Режим вывода (консоль или файл).
     * @throws IOException если происходит ошибка при записи в файл.
     */
    public void writeOutput(Figure figure, String outputMode) throws IOException {
        String output = generateOutputString(figure);
        logger.info("Writing output for figure: {}", figure.getName());

        if ("console".equalsIgnoreCase(outputMode)) {
            System.out.println(output);
            logger.info("Output written to console");
        } else {
            Path pathToSave = Paths.get("task2", "src", "main", "java", "com", "shalkevich", "output", outputMode);
            writeToFile(pathToSave, output);
        }
    }

    /**
     * Генерирует строку с выходными данными для фигуры.
     *
     * @param figure Объект фигуры.
     * @return Строка с выходными данными.
     */
    private String generateOutputString(Figure figure) {
        return String.format("Тип фигуры: %s\nПлощадь: %.2f кв. мм\nПериметр: %.2f мм%s",
                figure.getName(), figure.getArea(), figure.getPerimeter(), figure.getSpecificCharacteristics());
    }

    /**
     * Записывает строку с выходными данными в файл.
     *
     * @param outputPath Путь к файлу.
     * @param output     Строка с выходными данными.
     * @throws IOException если происходит ошибка при записи в файл.
     */
    private void writeToFile(Path outputPath, String output) throws IOException {
        Files.write(outputPath, output.getBytes());
        logger.info("Output written to file: {}", outputPath);
    }
}
