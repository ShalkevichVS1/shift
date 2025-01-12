package com.shalkevich.description;

import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Сервис для генерации описания круга.
 */
public class CircleDescriptionService implements DescriptionService {

    private final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
    private final DecimalFormat df = new DecimalFormat("0.00", symbols);

    /**
     * Возвращает описание круга.
     *
     * @param figure Объект круга.
     * @return Описание круга.
     */
    @Override
    public String generateDescription(Figure figure) {
        Circle circle = (Circle) figure;
        return String.format("Тип фигуры: %s\nПлощадь: %s кв. мм\nПериметр: %s мм\nРадиус: %s мм\nДиаметр: %s мм",
                circle.getName(), df.format(circle.getArea()), df.format(circle.getPerimeter()), df.format(circle.getRadius()), df.format(circle.getDiameter()));
    }
}
