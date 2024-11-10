package com.shalkevich.figures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Объект, треугольник.
 */
public class Triangle extends AbstractFigure {

    private static final Logger logger = LoggerFactory.getLogger(Triangle.class);

    private final double a;
    private final double b;
    private final double c;

    /**
     * Конструктор для создания треугольника с заданными сторонами.
     *
     * @param a Длина стороны A.
     * @param b Длина стороны B.
     * @param c Длина стороны C.
     */
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        logger.info("Triangle created with sides: {}, {}, {}", a, b, c);
    }

    @Override
    public String getName() {
        return "Треугольник";
    }

    @Override
    public double getArea() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    public double getPerimeter() {
        return a + b + c;
    }

    /**
     * Возвращает длину стороны A.
     *
     * @return Длина стороны A.
     */
    public double getA() {
        return a;
    }

    /**
     * Возвращает длину стороны B.
     *
     * @return Длина стороны B.
     */
    public double getB() {
        return b;
    }

    /**
     * Возвращает длину стороны C.
     *
     * @return Длина стороны C.
     */
    public double getC() {
        return c;
    }

    @Override
    public String getSpecificCharacteristics() {
        logger.info("Getting specific characteristics for Triangle");
        return String.format("\nСторона A: %.2f мм\nСторона B: %.2f мм\nСторона C: %.2f мм",
                getA(), getB(), getC());
    }
}
