package com.shalkevich.writer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс для вывода данных фигуры в файл.
 */
public class FileOutputWriter implements OutputService {

    private static final Logger logger = LoggerFactory.getLogger(FileOutputWriter.class);
    private final String outputMode;

    /**
     * Конструктор для создания экземпляра FileOutputWriter с указанным режимом вывода.
     *
     * @param outputMode Режим вывода (имя файла).
     */
    public FileOutputWriter(String outputMode) {
        this.outputMode = outputMode;
    }

    /**
     * Записывает данные фигуры в файл.
     *
     * @param description Строка с описанием фигуры.
     */
    @Override
    public void writeOutput(String description) {
        Path pathToSave = Paths.get("task2", "src", "main", "java", "com", "shalkevich", "output", outputMode);
        try {
            Files.createDirectories(pathToSave.getParent());
            writeToFile(pathToSave, description);
        } catch (IOException e) {
            logger.error("Error writing output to file", e);
        }
    }

    private void writeToFile(Path outputPath, String description) throws IOException {
        try {
            Files.write(outputPath, description.getBytes());
            logger.info("Output written to file: {}", outputPath);
        } catch (IOException e) {
            logger.error("Error writing to file: {}", outputPath, e);
            throw e;
        }
    }
}
