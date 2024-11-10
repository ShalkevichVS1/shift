package com.shalkevich.figures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Объект, прямоугольник.
 */
public class Rectangle extends AbstractFigure {

    private static final Logger logger = LoggerFactory.getLogger(Rectangle.class);

    private final double length;
    private final double width;

    /**
     * Конструктор для создания прямоугольника с заданной длиной и шириной.
     *
     * @param length Длина прямоугольника.
     * @param width  Ширина прямоугольника.
     */
    public Rectangle(double length, double width) {
        this.length = length;
        this.width = width;
        logger.info("Rectangle created with length: {} and width: {}", length, width);
    }

    @Override
    public String getName() {
        return "Прямоугольник";
    }

    @Override
    public double getArea() {
        return length * width;
    }

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
    
    @Override
    public String getSpecificCharacteristics() {
        logger.info("Getting specific characteristics for Rectangle");
        return String.format("\nДлина: %.2f мм\nШирина: %.2f мм\nДиагональ: %.2f мм",
                getLength(), getWidth(), getDiagonal());
    }
}
