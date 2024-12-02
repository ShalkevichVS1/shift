package com.shalkevich.writer;

import com.shalkevich.description.FigureDescriptionService;
import com.shalkevich.figures.Figure;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsoleOutputWriter implements OutputService {

    private static final Logger logger = LoggerFactory.getLogger(ConsoleOutputWriter.class);
    private final FigureDescriptionService descriptionService = new FigureDescriptionService();

    @Override
    public void writeOutput(Figure figure) {
        String output = descriptionService.generateOutputString(figure);
        System.out.println(output);
        logger.info("Output written to console");
    }
}
