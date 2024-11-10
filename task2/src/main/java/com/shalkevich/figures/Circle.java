package com.shalkevich.figures;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Объект, круг.
 */
public class Circle extends AbstractFigure {

    private static final Logger logger = LoggerFactory.getLogger(Circle.class);

    private final double radius;

    /**
     * Конструктор для создания круга с заданным радиусом.
     *
     * @param radius Радиус круга.
     */
    public Circle(double radius) {
        this.radius = radius;
        logger.info("Circle created with radius: {}", radius);
    }

    @Override
    public String getName() {
        return "Круг";
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double getPerimeter() {
        return 2 * Math.PI * radius;
    }

    /**
     * Радиус круга.
     *
     * @return Радиус круга.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Диаметр круга.
     *
     * @return Диаметр круга.
     */
    public double getDiameter() {
        return 2 * radius;
    }
    
    @Override
    public String getSpecificCharacteristics() {
        logger.info("Getting specific characteristics for Circle");
        return String.format("\nРадиус: %.2f мм\nДиаметр: %.2f мм", getRadius(), getDiameter());
    }
}
