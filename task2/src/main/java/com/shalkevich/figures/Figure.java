package com.shalkevich.figures;

/**
 * Интерфейс для всех геометрических фигур.
 */
public interface Figure {

    /**
     * Возвращает название фигуры.
     *
     * @return Название фигуры.
     */
    String getName();

    /**
     * Вычисляет площадь фигуры.
     *
     * @return Площадь фигуры.
     */
    double getArea();

    /**
     * Вычисляет периметр фигуры.
     *
     * @return Периметр фигуры.
     */
    double getPerimeter();

    /**
     * Возвращает тип фигуры.
     *
     * @return Тип фигуры.
     */
    FigureType getFigureType();
}
