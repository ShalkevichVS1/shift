package com.shalkevich.description;

import com.shalkevich.figures.Figure;
import com.shalkevich.figures.Triangle;

/**
 * Сервис для генерации описания треугольника.
 */
public class TriangleDescriptionService implements DescriptionService {

    /**
     * Возвращает описание треугольника.
     *
     * @param figure Объект треугольника.
     * @return Описание треугольника.
     */
    @Override
    public String generateDescription(Figure figure) {
        Triangle triangle = (Triangle) figure;
        return String.format("Тип фигуры: %s\nПлощадь: %.2f кв. мм\nПериметр: %.2f мм\nСторона A: %.2f мм\nСторона B: %.2f мм\nСторона C: %.2f мм",
                triangle.getName(), triangle.getArea(), triangle.getPerimeter(), triangle.getA(), triangle.getB(), triangle.getC());
    }
}
