package figures;

import com.shalkevich.figures.Triangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Проверка создания объекта Triangle и корректности вычисляемых характеристик.
 */
class TriangleTest {

    /**
     * Проверка создания и корректности характеристик треугольника.
     */
    @Test
    void testTriangleCreationAndProperties() {
        Triangle triangle = new Triangle(3, 4, 5);
        assertEquals("Треугольник", triangle.getName());
        assertEquals(6, triangle.getArea(), 0.01);
        assertEquals(12, triangle.getPerimeter(), 0.01);
        assertEquals(3, triangle.getA(), 0.01);
        assertEquals(4, triangle.getB(), 0.01);
        assertEquals(5, triangle.getC(), 0.01);
    }
}
