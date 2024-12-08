package com.shalkevich.figures;

/**
 * Класс, представляющий прямоугольник.
 */
public class Rectangle implements Figure {
    private final double length;
    private final double width;

    /**
     * Конструктор для создания прямоугольника с заданными длиной и шириной.
     *
     * @param length Длина прямоугольника.
     * @param width  Ширина прямоугольника.
     */
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
    }

    /**
     * Возвращает название фигуры.
     *
     * @return Название фигуры.
     */
    @Override
    public String getName() {
        return "Прямоугольник";
    }

    /**
     * Вычисляет площадь прямоугольника.
     *
     * @return Площадь прямоугольника.
     */
    @Override
    public double getArea() {
        return length * width;
    }

    /**
     * Вычисляет периметр прямоугольника.
     *
     * @return Периметр прямоугольника.
     */
    @Override
    public double getPerimeter() {
        return 2 * (length + width);
    }

    /**
     * Возвращает длину прямоугольника.
     *
     * @return Длина прямоугольника.
     */
    public double getLength() {
        return length;
    }

    /**
     * Возвращает ширину прямоугольника.
     *
     * @return Ширина прямоугольника.
     */
    public double getWidth() {
        return width;
    }

    /**
     * Вычисляет диагональ прямоугольника.
     *
     * @return Диагональ прямоугольника.
     */
    public double getDiagonal() {
        return Math.sqrt(length * length + width * width);
    }

    /**
     * Возвращает тип фигуры.
     *
     * @return Тип фигуры.
     */
    @Override
    public FigureType getFigureType() {
        return FigureType.RECTANGLE;
    }
}
