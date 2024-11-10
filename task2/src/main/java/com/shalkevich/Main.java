package com.shalkevich;

import com.shalkevich.factory.FigureFactory;
import com.shalkevich.reader.FigureReader;
import com.shalkevich.writer.OutputWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Starting the program.
 */
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * The main application method that runs the program.
     *
     * @param args Command line arguments.
     *             The first argument is the path to the input file,
     *             second - output mode (optional).
     */
    public static void main(String[] args) {

        if (args.length < 1) {
            logger.error("No input file provided");
            System.out.println("Usage: java Main <inputFile> [outputMode]");
            return;
        }

        String inputFile = args[0];
        String outputMode = args.length > 1 ? args[1] : "console";

        FigureReader reader = new FigureReader(new FigureFactory());
        OutputWriter writer = new OutputWriter();

        try {
            reader.readAndProcessFigure(inputFile, outputMode, writer);
        } catch (Exception e) {
            logger.error("Error processing figure: ", e);
            e.printStackTrace();
        }
    }
}
