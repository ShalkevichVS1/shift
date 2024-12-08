package com.shalkevich.description;

import com.shalkevich.figures.Figure;
import com.shalkevich.figures.Rectangle;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Сервис для генерации описания прямоугольника.
 */
public class RectangleDescriptionService implements DescriptionService {

    private final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
    private final DecimalFormat df = new DecimalFormat("0.00", symbols);

    /**
     * Возвращает описание прямоугольника.
     *
     * @param figure Объект прямоугольника.
     * @return Описание прямоугольника.
     */
    @Override
    public String generateDescription(Figure figure) {
        Rectangle rectangle = (Rectangle) figure;
        return String.format("Тип фигуры: %s\nПлощадь: %s кв. мм\nПериметр: %s мм\nДлина: %s мм\nШирина: %s мм\nДиагональ: %s мм",
                rectangle.getName(), df.format(rectangle.getArea()), df.format(rectangle.getPerimeter()), df.format(rectangle.getLength()), df.format(rectangle.getWidth()), df.format(rectangle.getDiagonal()));
    }
}
