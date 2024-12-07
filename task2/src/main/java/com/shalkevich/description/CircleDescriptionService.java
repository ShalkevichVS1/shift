package com.shalkevich.description;

import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;

/**
 * Сервис для генерации описания круга.
 */
public class CircleDescriptionService implements DescriptionService {

    /**
     * Возвращает описание круга.
     *
     * @param figure Объект круга.
     * @return Описание круга.
     */
    @Override
    public String generateDescription(Figure figure) {
        Circle circle = (Circle) figure;
        return String.format("Тип фигуры: %s\nПлощадь: %.2f кв. мм\nПериметр: %.2f мм\nРадиус: %.2f мм\nДиаметр: %.2f мм",
                circle.getName(), circle.getArea(), circle.getPerimeter(), circle.getRadius(), circle.getDiameter());
    }
}
