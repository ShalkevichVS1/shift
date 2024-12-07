package com.shalkevich.writer;

import com.shalkevich.description.DescriptionServiceFactory;
import com.shalkevich.description.DescriptionService;
import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileOutputWriterTest {

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

        DescriptionService descriptionService = DescriptionServiceFactory.getDescriptionService(circle);
        String description = descriptionService.generateDescription(circle);

        writer.writeOutput(description);

        assertTrue(Files.exists(outputFilePath), "Output file should exist");

        String fileContent = Files.readString(outputFilePath);
        assertEquals(description, fileContent.trim());

        Files.delete(outputFilePath);
        assertTrue(Files.notExists(outputFilePath), "Output file should be deleted");
    }
}
