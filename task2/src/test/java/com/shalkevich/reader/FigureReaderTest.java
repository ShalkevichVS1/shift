package com.shalkevich.reader;

import com.shalkevich.factory.FigureFactory;
import com.shalkevich.description.DescriptionServiceFactory;
import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;
import com.shalkevich.writer.OutputService;
import com.shalkevich.writer.OutputWriterFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для FigureReader.
 */
class FigureReaderTest {
    private FigureReader reader;

    /**
     * Инициализация перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        FigureFactory figureFactory = new FigureFactory();
        DescriptionServiceFactory descriptionServiceFactory = new DescriptionServiceFactory();
        reader = new FigureReader(figureFactory, descriptionServiceFactory);
    }

    /**
     * Проверка чтения данных круга из файла.
     *
     * @throws IOException если происходит ошибка при чтении файла.
     */
    @Test
    void testReadFigureCircle() throws IOException {
        Figure figure = reader.readFigure("circle.txt");
        assertInstanceOf(Circle.class, figure);
        Circle circle = (Circle) figure;
        assertEquals(5, circle.getRadius(), 0.01);
    }

    /**
     * Проверка поведения при отсутствии файла.
     */
    @Test
    void testReadFigureFileNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> reader.readFigure("nonexistent.txt"));
        assertEquals("Файл не найден: nonexistent.txt", exception.getMessage());
    }

    /**
     * Проверка поведения при некорректном формате файла.
     *
     * @throws IOException если происходит ошибка при записи тестового файла.
     */
    @Test
    void testReadFigureInvalidFileFormat() throws IOException {
        Path invalidFilePath = Paths.get("src/test/resources/invalid.txt");
        Files.writeString(invalidFilePath, "INVALID_DATA");

        Exception exception = assertThrows(IllegalArgumentException.class, () -> reader.readFigure("invalid.txt"));
        assertEquals("Файл должен содержать хотя бы две строки.", exception.getMessage());
    }

    /**
     * Проверка чтения и обработки фигуры.
     *
     * @throws IOException если происходит ошибка при чтении файла.
     */
    @Test
    void testReadAndProcessFigure() throws IOException {
        OutputService writer = OutputWriterFactory.getOutputWriter("console");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        reader.readAndProcessFigure("circle.txt", writer);

        String expectedOutput = "Тип фигуры: Круг\nПлощадь: 78.54 кв. мм\nПериметр: 31.42 мм\nРадиус: 5.00 мм\nДиаметр: 10.00 мм";
        String actualOutput = out.toString().trim();

        assertEquals(expectedOutput, actualOutput);
    }
}
