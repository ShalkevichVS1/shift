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
        return switch (type) {
            case CIRCLE -> new CircleDescriptionService();
            case RECTANGLE -> new RectangleDescriptionService();
            case TRIANGLE -> new TriangleDescriptionService();
        };
    }
}
