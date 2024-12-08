package com.shalkevich.figures;

/**
 * Класс, представляющий треугольник.
 */
public class Triangle implements Figure {
    private final double a;
    private final double b;
    private final double c;

    /**
     * Конструктор для создания треугольника с заданными сторонами.
     *
     * @param a Сторона A.
     * @param b Сторона B.
     * @param c Сторона C.
     */
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Возвращает название фигуры.
     *
     * @return Название фигуры.
     */
    @Override
    public String getName() {
        return "Треугольник";
    }

    /**
     * Вычисляет площадь треугольника по формуле Герона.
     *
     * @return Площадь треугольника.
     */
    @Override
    public double getArea() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    /**
     * Вычисляет периметр треугольника.
     *
     * @return Периметр треугольника.
     */
    @Override
    public double getPerimeter() {
        return a + b + c;
    }

    /**
     * Возвращает сторону A.
     *
     * @return Сторона A.
     */
    public double getA() {
        return a;
    }

    /**
     * Возвращает сторону B.
     *
     * @return Сторона B.
     */
    public double getB() {
        return b;
    }

    /**
     * Возвращает сторону C.
     *
     * @return Сторона C.
     */
    public double getC() {
        return c;
    }

    /**
     * Возвращает тип фигуры.
     *
     * @return Тип фигуры.
     */
    @Override
    public FigureType getFigureType() {
        return FigureType.TRIANGLE;
    }
}
