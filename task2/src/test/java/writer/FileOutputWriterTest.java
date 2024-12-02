package writer;

import com.shalkevich.description.FigureDescriptionService;
import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;
import com.shalkevich.writer.FileOutputWriter;
import com.shalkevich.writer.OutputService;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Проверка функциональности класса {@link FileOutputWriter}.
 */
class FileOutputWriterTest {

    private final FigureDescriptionService descriptionService = new FigureDescriptionService();

    /**
     * Проверка вывода данных фигуры в файл.
     *
     * @throws IOException если происходит ошибка при записи в файл.
     */
    @Test
    void testWriteOutputToFile() throws IOException {
        Figure circle = new Circle(5);
        String outputMode = "test_output.txt";
        OutputService writer = new FileOutputWriter(outputMode);
        Path outputDirPath = Paths.get("task2", "src", "main", "java", "com", "shalkevich", "output");
        Path outputFilePath = outputDirPath.resolve(outputMode);

        if (!Files.exists(outputDirPath)) {
            Files.createDirectories(outputDirPath);
        }

        writer.writeOutput(circle);

        assertTrue(Files.exists(outputFilePath), "Output file should exist");

        String expectedOutput = descriptionService.generateOutputString(circle);
        String fileContent = Files.readString(outputFilePath);
        assertEquals(expectedOutput.trim(), fileContent.trim());

        Files.delete(outputFilePath);
        assertTrue(Files.notExists(outputFilePath), "Output file should be deleted");
    }
}
