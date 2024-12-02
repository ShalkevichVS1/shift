package com.shalkevich.description;

import com.shalkevich.figures.Circle;

/**
 * Сервис для генерации описания круга.
 */
public class CircleDescriptionService {

    /**
     * Генерирует строку с описанием круга.
     *
     * @param circle Объект круга.
     * @return Строка с описанием круга, включающая радиус и диаметр.
     */
    public String getDescription(Circle circle) {
        return String.format("\nРадиус: %.2f мм\nДиаметр: %.2f мм", circle.getRadius(), circle.getDiameter());
    }
}
