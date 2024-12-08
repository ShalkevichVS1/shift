package com.shalkevich.factory;

import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;
import com.shalkevich.figures.FigureType;
import com.shalkevich.figures.Rectangle;
import com.shalkevich.figures.Triangle;

/**
 * Фабрика для создания объектов фигур.
 */
public class FigureFactory {

    /**
     * Создает объект фигуры на основе типа и параметров.
     *
     * @param type   Тип фигуры (например, CIRCLE, RECTANGLE, TRIANGLE).
     * @param params Параметры фигуры.
     * @return Объект Figure.
     */
    public Figure createFigure(FigureType type, String[] params) {
        if (type == null) {
            throw new IllegalArgumentException("Figure type cannot be null");
        }
        switch (type) {
            case CIRCLE:
                if (params.length != 1) throw new IllegalArgumentException("Invalid number of parameters for CIRCLE.");
                return new Circle(Double.parseDouble(params[0]));
            case RECTANGLE:
                if (params.length != 2)
                    throw new IllegalArgumentException("Invalid number of parameters for RECTANGLE.");
                return new Rectangle(Double.parseDouble(params[0]), Double.parseDouble(params[1]));
            case TRIANGLE:
                if (params.length != 3)
                    throw new IllegalArgumentException("Invalid number of parameters for TRIANGLE.");
                return new Triangle(Double.parseDouble(params[0]), Double.parseDouble(params[1]), Double.parseDouble(params[2]));
            default:
                throw new IllegalArgumentException("Unknown figure type: " + type);
        }
    }
}
