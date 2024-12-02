package com.shalkevich.factory;

import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;
import com.shalkevich.figures.Rectangle;
import com.shalkevich.figures.Triangle;

/**
 * Фабрика для создания объектов фигур.
 */
public class FigureFactory {

    /**
     * Создает фигуру в зависимости от типа и параметров.
     *
     * @param type   Тип фигуры.
     * @param params Параметры фигуры.
     * @return Объект фигуры.
     */
    public Figure createFigure(String type, String[] params) {
        return switch (type.toUpperCase()) {
            case "CIRCLE" -> new Circle(Double.parseDouble(params[0]));
            case "RECTANGLE" -> new Rectangle(Double.parseDouble(params[0]), Double.parseDouble(params[1]));
            case "TRIANGLE" ->
                    new Triangle(Double.parseDouble(params[0]), Double.parseDouble(params[1]), Double.parseDouble(params[2]));
            default -> throw new IllegalArgumentException("Unknown figure type: " + type);
        };
    }
}
