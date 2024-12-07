package com.shalkevich.description;

import com.shalkevich.figures.Figure;
import com.shalkevich.figures.Rectangle;

/**
 * Сервис для генерации описания прямоугольника.
 */
public class RectangleDescriptionService implements DescriptionService {

    /**
     * Возвращает описание прямоугольника.
     *
     * @param figure Объект прямоугольника.
     * @return Описание прямоугольника.
     */
    @Override
    public String generateDescription(Figure figure) {
        Rectangle rectangle = (Rectangle) figure;
        return String.format("Тип фигуры: %s\nПлощадь: %.2f кв. мм\nПериметр: %.2f мм\nДлина: %.2f мм\nШирина: %.2f мм\nДиагональ: %.2f мм",
                rectangle.getName(), rectangle.getArea(), rectangle.getPerimeter(), rectangle.getLength(), rectangle.getWidth(), rectangle.getDiagonal());
    }
}
