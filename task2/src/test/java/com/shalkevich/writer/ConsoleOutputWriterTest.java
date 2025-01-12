package com.shalkevich.writer;

import com.shalkevich.description.CircleDescriptionService;
import com.shalkevich.description.DescriptionService;
import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Тесты для ConsoleOutputWriter.
 */
class ConsoleOutputWriterTest {

    /**
     * Проверка записи вывода в консоль.
     */
    @Test
    void testWriteOutputToConsole() {
        Figure circle = new Circle(5);
        OutputService writer = new ConsoleOutputWriter();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        DescriptionService descriptionService = new CircleDescriptionService();
        String description = descriptionService.generateDescription(circle);

        writer.writeOutput(description);

        String actualOutput = out.toString().trim();
        assertEquals(description, actualOutput);
    }
}
