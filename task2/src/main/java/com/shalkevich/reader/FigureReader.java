package com.shalkevich.reader;

import com.shalkevich.description.DescriptionService;
import com.shalkevich.description.DescriptionServiceFactory;
import com.shalkevich.factory.FigureFactory;
import com.shalkevich.figures.*;
import com.shalkevich.writer.OutputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

/**
 * Класс для чтения данных фигур из файлов и их обработки.
 */
public class FigureReader {

    private static final Logger logger = LoggerFactory.getLogger(FigureReader.class);

    private final FigureFactory figureFactory;
    private final DescriptionServiceFactory descriptionServiceFactory;
    private final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
    private final DecimalFormat df = new DecimalFormat("0.00", symbols);

    /**
     * Конструктор для инициализации FigureReader с заданной фабрикой фигур и описательных сервисов.
     *
     * @param figureFactory             Фабрика фигур для создания объектов фигур.
     * @param descriptionServiceFactory Фабрика описательных сервисов.
     */
    public FigureReader(FigureFactory figureFactory, DescriptionServiceFactory descriptionServiceFactory) {
        this.figureFactory = figureFactory;
        this.descriptionServiceFactory = descriptionServiceFactory;
        logger.info("FigureReader initialized with FigureFactory and DescriptionServiceFactory");
    }

    /**
     * Читает фигуру из файла и обрабатывает ее.
     *
     * @param filePath Путь к файлу с данными фигуры.
     * @param writer   Объект OutputService для записи результатов.
     * @throws IOException если происходит ошибка при чтении файла.
     */
    public void readAndProcessFigure(String filePath, OutputService writer) throws IOException {
        Figure figure = readFigure(filePath);
        DescriptionService descriptionService = descriptionServiceFactory.getDescriptionService(determineFigureType(figure));
        String description = descriptionService.generateDescription(figure);
        writer.writeOutput(description);
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
            String typeStr = lines.get(0).trim();
            FigureType type;
            try {
                type = FigureType.valueOf(typeStr.toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Unknown figure type: " + typeStr);
            }
            String[] params = lines.get(1).trim().split(" ");
            validateParameters(params);
            logger.info("Figure type read: {}", type);
            return figureFactory.createFigure(type, params);
        }
    }

    /**
     * Валидация параметров фигуры.
     *
     * @param params Параметры для валидации.
     */
    private void validateParameters(String[] params) {
        for (String param : params) {
            try {
                df.parse(param);
            } catch (Exception e) {
                throw new IllegalArgumentException("Invalid parameter format: " + param);
            }
        }
    }

    /**
     * Определяет тип фигуры.
     *
     * @param figure Объект фигуры.
     * @return Тип фигуры.
     */
    private FigureType determineFigureType(Figure figure) {
        if (figure == null) {
            throw new IllegalArgumentException("Figure cannot be null");
        } else if (figure instanceof Circle) {
            return FigureType.CIRCLE;
        } else if (figure instanceof Rectangle) {
            return FigureType.RECTANGLE;
        } else if (figure instanceof Triangle) {
            return FigureType.TRIANGLE;
        } else {
            throw new IllegalArgumentException("Unknown figure type: " + figure.getClass().getSimpleName());
        }
    }
}
