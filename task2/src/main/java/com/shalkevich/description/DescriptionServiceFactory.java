package com.shalkevich.description;

import com.shalkevich.figures.Figure;
import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Rectangle;
import com.shalkevich.figures.Triangle;

/**
 * Фабрика для получения описательных сервисов в зависимости от типа фигуры.
 */
public class DescriptionServiceFactory {

    /**
     * Возвращает описательный сервис для заданной фигуры.
     *
     * @param figure Объект фигуры.
     * @return Объект DescriptionService.
     */
    public static DescriptionService getDescriptionService(Figure figure) {
        if (figure instanceof Circle) {
            return new CircleDescriptionService();
        } else if (figure instanceof Rectangle) {
            return new RectangleDescriptionService();
        } else if (figure instanceof Triangle) {
            return new TriangleDescriptionService();
        } else {
            throw new IllegalArgumentException("Unknown figure type: " + figure.getClass().getSimpleName());
        }
    }
}
