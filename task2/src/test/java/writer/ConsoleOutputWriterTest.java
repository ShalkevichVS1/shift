package writer;

import com.shalkevich.description.FigureDescriptionService;
import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;
import com.shalkevich.writer.ConsoleOutputWriter;
import com.shalkevich.writer.OutputService;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Проверка функциональности класса {@link ConsoleOutputWriter}.
 */
class ConsoleOutputWriterTest {

    private final FigureDescriptionService descriptionService = new FigureDescriptionService();

    /**
     * Проверка вывода данных фигуры в консоль.
     */
    @Test
    void testWriteOutputToConsole() {
        Figure circle = new Circle(5);
        OutputService writer = new ConsoleOutputWriter();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        writer.writeOutput(circle);
        String expectedOutput = descriptionService.generateOutputString(circle).trim();
        
        String actualOutput = out.toString().replaceAll("(?m)^\\d{4}-\\d{2}-\\d{2}.*$", "").trim();

        assertEquals(expectedOutput, actualOutput);
    }
}
