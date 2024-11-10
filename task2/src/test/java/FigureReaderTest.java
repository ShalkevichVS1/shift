import com.shalkevich.factory.FigureFactory;
import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;
import com.shalkevich.reader.FigureReader;
import com.shalkevich.writer.OutputWriter;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Проверка функциональности класса {@link FigureReader}.
 */
class FigureReaderTest {
    private final FigureFactory factory = new FigureFactory();
    private final FigureReader reader = new FigureReader(factory);

    /**
     * Проверка чтения данных круга из файла.
     *
     * @throws IOException если происходит ошибка при чтении файла.
     */
    @Test
    void testReadFigureCircle() throws IOException {
        Figure figure = reader.readFigure("circle.txt");
        assertTrue(figure instanceof Circle);
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
        Path resourceDirPath = Paths.get("src", "main", "resources").toAbsolutePath();
        if (!Files.exists(resourceDirPath)) {
            Files.createDirectories(resourceDirPath);
        }
        Path invalidFilePath = resourceDirPath.resolve("invalid.txt");
        Files.writeString(invalidFilePath, "INVALID_DATA");

        assertTrue(Files.exists(invalidFilePath), "Файл должен существовать");

        InputStream is = getClass().getClassLoader().getResourceAsStream("invalid.txt");
        assertNotNull(is, "Файл должен быть найден в ресурсах");

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
        OutputWriter writer = new OutputWriter();
        reader.readAndProcessFigure("circle.txt", "console", writer);
    }
}
