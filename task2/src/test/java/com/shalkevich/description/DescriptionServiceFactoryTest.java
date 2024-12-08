package com.shalkevich.description;

import com.shalkevich.figures.FigureType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Тесты для DescriptionServiceFactory.
 */
class DescriptionServiceFactoryTest {

    private final DescriptionServiceFactory factory = new DescriptionServiceFactory();

    /**
     * Проверка получения описательного сервиса для круга.
     */
    @Test
    void testGetDescriptionServiceForCircle() {
        DescriptionService service = factory.getDescriptionService(FigureType.CIRCLE);
        assertInstanceOf(CircleDescriptionService.class, service);
    }

    /**
     * Проверка получения описательного сервиса для прямоугольника.
     */
    @Test
    void testGetDescriptionServiceForRectangle() {
        DescriptionService service = factory.getDescriptionService(FigureType.RECTANGLE);
        assertInstanceOf(RectangleDescriptionService.class, service);
    }

    /**
     * Проверка получения описательного сервиса для треугольника.
     */
    @Test
    void testGetDescriptionServiceForTriangle() {
        DescriptionService service = factory.getDescriptionService(FigureType.TRIANGLE);
        assertInstanceOf(TriangleDescriptionService.class, service);
    }

    /**
     * Проверка получения описательного сервиса с некорректным типом.
     */
    @Test
    void testGetDescriptionServiceWithInvalidType() {
        assertThrows(IllegalArgumentException.class, () -> factory.getDescriptionService(null));
    }
}
