package com.shalkevich.description;

import com.shalkevich.figures.Figure;
import com.shalkevich.figures.Triangle;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Сервис для генерации описания треугольника.
 */
public class TriangleDescriptionService implements DescriptionService {

    private final DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.ENGLISH);
    private final DecimalFormat df = new DecimalFormat("0.00", symbols);

    /**
     * Возвращает описание треугольника.
     *
     * @param figure Объект треугольника.
     * @return Описание треугольника.
     */
    @Override
    public String generateDescription(Figure figure) {
        Triangle triangle = (Triangle) figure;
        return String.format("Тип фигуры: %s\nПлощадь: %s кв. мм\nПериметр: %s мм\nСторона A: %s мм\nСторона B: %s мм\nСторона C: %s мм",
                triangle.getName(), df.format(triangle.getArea()), df.format(triangle.getPerimeter()), df.format(triangle.getA()), df.format(triangle.getB()), df.format(triangle.getC()));
    }
}
