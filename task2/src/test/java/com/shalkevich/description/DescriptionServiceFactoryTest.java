package com.shalkevich.description;

import com.shalkevich.figures.Circle;
import com.shalkevich.figures.Figure;
import com.shalkevich.figures.Rectangle;
import com.shalkevich.figures.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DescriptionServiceFactoryTest {

    @Test
    void testGetCircleDescriptionService() {
        Figure circle = new Circle(5);
        DescriptionService service = DescriptionServiceFactory.getDescriptionService(circle);
        assertInstanceOf(CircleDescriptionService.class, service);
    }

    @Test
    void testGetRectangleDescriptionService() {
        Figure rectangle = new Rectangle(4, 6);
        DescriptionService service = DescriptionServiceFactory.getDescriptionService(rectangle);
        assertInstanceOf(RectangleDescriptionService.class, service);
    }

    @Test
    void testGetTriangleDescriptionService() {
        Figure triangle = new Triangle(3, 4, 5);
        DescriptionService service = DescriptionServiceFactory.getDescriptionService(triangle);
        assertInstanceOf(TriangleDescriptionService.class, service);
    }

    @Test
    void testGetUnknownDescriptionService() {
        Figure unknownFigure = new Figure() {
            @Override
            public String getName() {
                return "Unknown";
            }

            @Override
            public double getArea() {
                return 0;
            }

            @Override
            public double getPerimeter() {
                return 0;
            }
        };
        assertThrows(IllegalArgumentException.class, () ->
                DescriptionServiceFactory.getDescriptionService(unknownFigure));
    }
}
