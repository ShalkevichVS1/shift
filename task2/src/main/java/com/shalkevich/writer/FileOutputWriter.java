package com.shalkevich.writer;

import com.shalkevich.description.FigureDescriptionService;
import com.shalkevich.figures.Figure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOutputWriter implements OutputService {

    private static final Logger logger = LoggerFactory.getLogger(FileOutputWriter.class);
    private final FigureDescriptionService descriptionService = new FigureDescriptionService();
    private final String outputMode;

    public FileOutputWriter(String outputMode) {
        this.outputMode = outputMode;
    }

    @Override
    public void writeOutput(Figure figure) {
        String output = descriptionService.generateOutputString(figure);
        Path pathToSave = Paths.get("task2", "src", "main", "java", "com", "shalkevich", "output", outputMode);
        try {
            Files.createDirectories(pathToSave.getParent());
            writeToFile(pathToSave, output);
        } catch (IOException e) {
            logger.error("Error writing output to file", e);
        }
    }

    private void writeToFile(Path outputPath, String output) throws IOException {
        try {
            Files.write(outputPath, output.getBytes());
            logger.info("Output written to file: {}", outputPath);
        } catch (IOException e) {
            logger.error("Error writing to file: {}", outputPath, e);
            throw e;
        }
    }
}
