package com.shalkevich.figures;

/**
 * Класс, представляющий круг.
 */
public class Circle implements Figure {
    private final double radius;

    /**
     * Конструктор для создания круга с заданным радиусом.
     *
     * @param radius Радиус круга.
     */
    public Circle(double radius) {
        this.radius = radius;
    }

    /**
     * Возвращает название фигуры.
     *
     * @return Название фигуры.
     */
    @Override
    public String getName() {
        return "Круг";
    }

    /**
     * Вычисляет площадь круга.
     *
     * @return Площадь круга.
     */
    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    /**
     * Вычисляет периметр круга.
     *
     * @return Периметр круга.
     */
    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    /**
     * Возвращает радиус круга.
     *
     * @return Радиус круга.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Возвращает диаметр круга.
     *
     * @return Диаметр круга.
     */
    public double getDiameter() {
        return 2 * radius;
    }

    /**
     * Возвращает тип фигуры.
     *
     * @return Тип фигуры.
     */
    @Override
    public FigureType getFigureType() {
        return FigureType.CIRCLE;
    }
}
