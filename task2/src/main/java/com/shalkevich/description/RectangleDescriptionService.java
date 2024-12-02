package com.shalkevich.description;

import com.shalkevich.figures.Rectangle;

/**
 * Сервис для генерации описания прямоугольника.
 */
public class RectangleDescriptionService {

    /**
     * Генерирует строку с описанием прямоугольника.
     *
     * @param rectangle Объект прямоугольника.
     * @return Строка с описанием прямоугольника, включающая длину, ширину и диагональ.
     */
    public String getDescription(Rectangle rectangle) {
        return String.format("\nДлина: %.2f мм\nШирина: %.2f мм\nДиагональ: %.2f мм",
                rectangle.getLength(), rectangle.getWidth(), rectangle.getDiagonal());
    }
}
