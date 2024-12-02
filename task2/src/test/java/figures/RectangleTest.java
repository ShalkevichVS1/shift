package figures;

import com.shalkevich.figures.Rectangle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Проверка создания объекта Rectangle и корректности вычисляемых характеристик.
 */
class RectangleTest {

    /**
     * Проверка создания и корректности характеристик прямоугольника.
     */
    @Test
    void testRectangleCreationAndProperties() {
        Rectangle rectangle = new Rectangle(5, 3);
        assertEquals("Прямоугольник", rectangle.getName());
        assertEquals(15, rectangle.getArea(), 0.01);
        assertEquals(16, rectangle.getPerimeter(), 0.01);
        assertEquals(5, rectangle.getLength(), 0.01);
        assertEquals(3, rectangle.getWidth(), 0.01);
        assertEquals(5.83, rectangle.getDiagonal(), 0.01);
    }
}
