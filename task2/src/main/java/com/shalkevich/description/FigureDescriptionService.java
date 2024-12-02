package com.shalkevich.description;

import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;
import com.shalkevich.figures.Rectangle;
import com.shalkevich.figures.Triangle;

/**
 * Сервис для генерации описательных строк фигур.
 */
public class FigureDescriptionService {

    /**
     * Генерирует строку с описанием фигуры.
     *
     * @param figure Объект фигуры.
     * @return Строка с описанием фигуры, включающая название, площадь, периметр и дополнительные характеристики.
     */
    public String generateOutputString(Figure figure) {
        String description = "";
        if (figure instanceof Circle circle) {
            CircleDescriptionService descriptionService = new CircleDescriptionService();
            description = descriptionService.getDescription(circle);
        } else if (figure instanceof Rectangle rectangle) {
            RectangleDescriptionService descriptionService = new RectangleDescriptionService();
            description = descriptionService.getDescription(rectangle);
        } else if (figure instanceof Triangle triangle) {
            TriangleDescriptionService descriptionService = new TriangleDescriptionService();
            description = descriptionService.getDescription(triangle);
        }
        return String.format("Тип фигуры: %s\nПлощадь: %.2f кв. мм\nПериметр: %.2f мм%s",
                figure.getName(), figure.getArea(), figure.getPerimeter(), description);
    }
}
