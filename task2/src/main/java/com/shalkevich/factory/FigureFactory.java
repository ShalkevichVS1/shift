package com.shalkevich.factory;

import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;
import com.shalkevich.figures.Rectangle;
import com.shalkevich.figures.Triangle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Фабрика фигур для создания объектов различных геометрических фигур.
 */
public class FigureFactory {
    
    private static final Logger logger = LoggerFactory.getLogger(FigureFactory.class);

    /**
     * Создает фигуру на основе заданного типа и параметров.
     *
     * @param type   Тип фигуры (например, "CIRCLE", "RECTANGLE", "TRIANGLE").
     * @param params Параметры фигуры (например, радиус для круга, длина и ширина для прямоугольника).
     * @return Созданная фигура.
     * @throws IllegalArgumentException если тип фигуры неизвестен.
     */
    public Figure createFigure(String type, String[] params) {
        logger.info("Creating figure of type: {}", type);
        return switch (type.toUpperCase()) {
            case "CIRCLE" -> new Circle(parseDouble(params[0]));
            case "RECTANGLE" -> new Rectangle(parseDouble(params[0]), parseDouble(params[1]));
            case "TRIANGLE" -> new Triangle(parseDouble(params[0]), parseDouble(params[1]), parseDouble(params[2]));
            default -> throw new IllegalArgumentException("Неизвестный тип фигуры: " + type);
        };
    }

    /**
     * Парсит строку в число с плавающей запятой.
     *
     * @param param Строка для парсинга.
     * @return Значение с плавающей запятой.
     */
    private double parseDouble(String param) {
        return Double.parseDouble(param);
    }
}
