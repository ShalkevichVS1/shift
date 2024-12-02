package com.shalkevich.description;

import com.shalkevich.figures.Triangle;

/**
 * Сервис для генерации описания треугольника.
 */
public class TriangleDescriptionService {

    /**
     * Генерирует строку с описанием треугольника.
     *
     * @param triangle Объект треугольника.
     * @return Строка с описанием треугольника, включающая стороны A, B и C.
     */
    public String getDescription(Triangle triangle) {
        return String.format("\nСторона A: %.2f мм\nСторона B: %.2f мм\nСторона C: %.2f мм",
                triangle.getA(), triangle.getB(), triangle.getC());
    }
}
