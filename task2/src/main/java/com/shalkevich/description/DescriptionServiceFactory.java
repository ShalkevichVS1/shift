package com.shalkevich.description;

import com.shalkevich.figures.FigureType;

/**
 * Класс для создания описательных сервисов.
 */
public class DescriptionServiceFactory {

    /**
     * Возвращает описательный сервис для заданного типа фигуры.
     *
     * @param type Тип фигуры.
     * @return Описательный сервис.
     */
    public DescriptionService getDescriptionService(FigureType type) {
        if (type == null) {
            throw new IllegalArgumentException("Figure type cannot be null");
        }
        switch (type) {
            case CIRCLE:
                return new CircleDescriptionService();
            case RECTANGLE:
                return new RectangleDescriptionService();
            case TRIANGLE:
                return new TriangleDescriptionService();
            default:
                throw new IllegalArgumentException("Unknown figure type: " + type);
        }
    }
}
