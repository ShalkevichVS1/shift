package figures;

import com.shalkevich.figures.Circle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Проверка функциональности класса {@link Circle}.
 */
class CircleTest {

    /**
     * Проверка создания объекта Circle и корректности вычисляемых характеристик.
     */
    @Test
    void testCircleCreationAndProperties() {
        Circle circle = new Circle(5);
        assertEquals("Круг", circle.getName());
        assertEquals(78.54, circle.getArea(), 0.01);
        assertEquals(31.42, circle.getPerimeter(), 0.01);
        assertEquals(5, circle.getRadius(), 0.01);
        assertEquals(10, circle.getDiameter(), 0.01);
    }
}
