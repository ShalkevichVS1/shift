package com.shalkevich.writer;

/**
 * Фабрика для создания объектов {@link OutputService}.
 */
public class OutputWriterFactory {

    /**
     * Возвращает реализацию {@link OutputService} в зависимости от режима вывода.
     *
     * @param outputMode Режим вывода (например, "console" или "file").
     * @return Объект {@link OutputService}.
     */
    public static OutputService getOutputWriter(String outputMode) {
        if ("file".equalsIgnoreCase(outputMode)) {
            return new FileOutputWriter("output.txt");
        } else {
            return new ConsoleOutputWriter();
        }
    }
}
