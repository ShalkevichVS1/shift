package com.shalkevich.factory;

import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;
import com.shalkevich.figures.FigureType;
import com.shalkevich.figures.Rectangle;
import com.shalkevich.figures.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для FigureFactory.
 */
class FigureFactoryTest {

    private final FigureFactory factory = new FigureFactory();

    /**
     * Проверка создания круга.
     */
    @Test
    void testCreateCircle() {
        Figure figure = factory.createFigure(FigureType.CIRCLE, new String[]{"10"});
        assertEquals(Circle.class, figure.getClass());
        Circle circle = (Circle) figure;
        assertEquals(10, circle.getRadius());
    }

    /**
     * Проверка создания прямоугольника.
     */
    @Test
    void testCreateRectangle() {
        Figure figure = factory.createFigure(FigureType.RECTANGLE, new String[]{"20", "30"});
        assertEquals(Rectangle.class, figure.getClass());
        Rectangle rectangle = (Rectangle) figure;
        assertEquals(20, rectangle.getLength());
        assertEquals(30, rectangle.getWidth());
    }

    /**
     * Проверка создания треугольника.
     */
    @Test
    void testCreateTriangle() {
        Figure figure = factory.createFigure(FigureType.TRIANGLE, new String[]{"3", "4", "5"});
        assertEquals(Triangle.class, figure.getClass());
        Triangle triangle = (Triangle) figure;
        assertEquals(3, triangle.getA());
        assertEquals(4, triangle.getB());
        assertEquals(5, triangle.getC());
    }

    /**
     * Проверка создания фигуры с некорректным типом.
     */
    @Test
    void testCreateFigureWithInvalidType() {
        assertThrows(IllegalArgumentException.class, () -> factory.createFigure(null, new String[]{"10"}));
    }

    /**
     * Проверка создания фигуры с некорректными параметрами.
     */
    @Test
    void testCreateFigureWithInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> factory.createFigure(FigureType.CIRCLE, new String[]{"10", "20"}));
    }
}
