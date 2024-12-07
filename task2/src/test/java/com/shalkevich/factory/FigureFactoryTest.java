package com.shalkevich.factory;

import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;
import com.shalkevich.figures.Rectangle;
import com.shalkevich.figures.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Проверка функциональности класса {@link FigureFactory}.
 */
class FigureFactoryTest {
    private final FigureFactory factory = new FigureFactory();

    /**
     * Проверка создания круга.
     */
    @Test
    void testCreateCircle() {
        Figure figure = factory.createFigure("CIRCLE", new String[]{"5"});
        assertEquals(Circle.class, figure.getClass());
        Circle circle = (Circle) figure;
        assertEquals(5, circle.getRadius(), 0.01);
    }

    /**
     * Проверка создания прямоугольника.
     */
    @Test
    void testCreateRectangle() {
        Figure figure = factory.createFigure("RECTANGLE", new String[]{"4", "6"});
        assertEquals(Rectangle.class, figure.getClass());
        Rectangle rectangle = (Rectangle) figure;
        assertEquals(4, rectangle.getLength(), 0.01);
        assertEquals(6, rectangle.getWidth(), 0.01);
    }

    /**
     * Проверка создания треугольника.
     */
    @Test
    void testCreateTriangle() {
        Figure figure = factory.createFigure("TRIANGLE", new String[]{"3", "4", "5"});
        assertEquals(Triangle.class, figure.getClass());
        Triangle triangle = (Triangle) figure;
        assertEquals(3, triangle.getA(), 0.01);
        assertEquals(4, triangle.getB(), 0.01);
        assertEquals(5, triangle.getC(), 0.01);
    }

    /**
     * Проверка создания фигуры с неизвестным типом.
     */
    @Test
    void testUnknownFigureType() {
        assertThrows(IllegalArgumentException.class, () -> factory.createFigure("UNKNOWN", new String[]{}));
    }
}
