package demo.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShapeTest {

    @Test
    void testCircleArea() {
        Circle circle = new Circle(5.0);
        assertEquals(78.53981633974483, circle.area(), 0.00001);
    }

    @Test
    void testCircleNegativeRadius() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Circle(-1.0);
        });
        assertEquals("radius must be greater than zero", exception.getMessage());
    }

    @Test
    void testRectangleArea() {
        Rectangle rectangle = new Rectangle(4.0, 6.0);
        assertEquals(24.0, rectangle.area(), 0.00001);
    }

    @Test
    void testRectangleNegativeWidth() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(-1.0, 5.0);
        });
        assertEquals("width and height must be greater than zero", exception.getMessage());
    }

    @Test
    void testRectangleNegativeHeight() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Rectangle(5.0, -1.0);
        });
        assertEquals("width and height must be greater than zero", exception.getMessage());
    }
}
