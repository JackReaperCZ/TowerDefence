import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CalculateAngleTest {

    private GeometryCalculator geometryCalculator;

    @Before
    public void setUp() {
        geometryCalculator = new GeometryCalculator(0, 0);
    }

    @Test
    public void testCalculateAnglePositiveCoordinates() {
        assertEquals(0.0, geometryCalculator.calculateAngle(5, 0), 0.0001);
    }

    @Test
    public void testCalculateAngleNegativeCoordinates() {
        assertEquals(Math.PI, geometryCalculator.calculateAngle(-5, 0), 0.0001);
    }

    @Test
    public void testCalculateAngleQuadrant2() {
        assertEquals(Math.PI / 2, geometryCalculator.calculateAngle(0, 5), 0.0001);
    }

    @Test
    public void testCalculateAngleQuadrant3() {
        assertEquals(-Math.PI / 2, geometryCalculator.calculateAngle(0, -5), 0.0001);
    }

    private class GeometryCalculator {
        private int x;
        private int y;

        public GeometryCalculator(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double calculateAngle(int x2, int y2) {
            double deltaY = y2 - y;
            double deltaX = x2 - x;
            return Math.atan2(deltaY, deltaX);
        }
    }
}
