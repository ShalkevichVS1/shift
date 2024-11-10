package com.shalkevich.reader;

import com.shalkevich.factory.FigureFactory;
import com.shalkevich.writer.OutputWriter;
import com.shalkevich.figures.Figure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Чтения данных фигур из файлов и их обработка.
 */
public class FigureReader {

    private static final Logger logger = LoggerFactory.getLogger(FigureReader.class);

    private final FigureFactory factory;

    /**
     * Конструктор для инициализации FigureReader с заданной фабрикой фигур.
     *
     * @param factory Фабрика фигур для создания объектов фигур.
     */
    public FigureReader(FigureFactory factory) {
        this.factory = factory;
        logger.info("FigureReader initialized with FigureFactory");
    }

    /**
     * Читает фигуру из файла и обрабатывает ее.
     *
     * @param filePath   Путь к файлу с данными фигуры.
     * @param outputMode Режим вывода (консоль или файл).
     * @param writer     Объект OutputWriter для записи результатов.
     * @throws IOException если происходит ошибка при чтении файла.
     */
    public void readAndProcessFigure(String filePath, String outputMode, OutputWriter writer) throws IOException {
        Figure figure = readFigure(filePath);
        writer.writeOutput(figure, outputMode);
        logger.info("Figure processed and output written");
    }

    /**
     * Читает фигуру из файла.
     *
     * @param filePath Путь к файлу с данными фигуры.
     * @return Объект Figure, соответствующий прочитанным данным.
     * @throws IOException если происходит ошибка при чтении файла.
     */
    public Figure readFigure(String filePath) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(filePath);
        if (is == null) {
            logger.error("File not found: {}", filePath);
            throw new IllegalArgumentException("Файл не найден: " + filePath);
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            List<String> lines = reader.lines().toList();
            if (lines.size() < 2) {
                logger.error("File must contain at least two lines: {}", filePath);
                throw new IllegalArgumentException("Файл должен содержать хотя бы две строки.");
            }
            String type = lines.get(0).trim();
            String[] params = lines.get(1).trim().split(" ");
            logger.info("Figure type read: {}", type);
            return factory.createFigure(type, params);
        }
    }
}
