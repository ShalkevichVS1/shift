import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;
import com.shalkevich.writer.OutputWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Проверка функциональности класса {@link OutputWriter}.
 */
class OutputWriterTest {

    /**
     * Проверка вывода данных фигуры в консоль.
     *
     * @throws IOException если происходит ошибка при записи в консоль.
     */
    @Test
    void testWriteOutputToConsole() throws IOException {
        Figure circle = new Circle(5);
        OutputWriter writer = new OutputWriter();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        writer.writeOutput(circle, "console");
        String expectedOutput = "Тип фигуры: Круг\nПлощадь: 78,54 кв. мм\nПериметр: 31,42 мм\nРадиус: 5,00 мм\nДиаметр: 10,00 мм\n";
        assertEquals(expectedOutput.trim(), out.toString().trim());
    }

    /**
     * Проверка вывода данных фигуры в файл.
     *
     * @throws IOException если происходит ошибка при записи в файл.
     */
    @Test
    void testWriteOutputToFile() throws IOException {
        Figure circle = new Circle(5);
        OutputWriter writer = new OutputWriter();
        String outputMode = "test_output.txt";
        Path outputDirPath = Paths.get("task2", "src", "main", "java", "com", "shalkevich", "output");
        Path outputFilePath = outputDirPath.resolve(outputMode);

        if (!Files.exists(outputDirPath))
            Files.createDirectories(outputDirPath);

        writer.writeOutput(circle, outputMode);

        assertTrue(Files.exists(outputFilePath), "Output file should exist");

        String expectedOutput = "Тип фигуры: Круг\nПлощадь: 78,54 кв. мм\nПериметр: 31,42 мм\nРадиус: 5,00 мм\nДиаметр: 10,00 мм\n";
        String fileContent = Files.readString(outputFilePath);
        assertEquals(expectedOutput.trim(), fileContent.trim());

        Files.delete(outputFilePath);
    }
}
