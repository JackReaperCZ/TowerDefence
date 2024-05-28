import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GoToTest {

    private MovableObject movableObject;
    private static final int SPEED = 5;

    @Before
    public void setUp() {
        movableObject = new MovableObject(0, 0, SPEED);
    }

    @Test
    public void testGoToSamePosition() {
        movableObject.goTo(0, 0);
        assertEquals(0, movableObject.getVelX());
        assertEquals(0, movableObject.getVelY());
    }

    @Test
    public void testGoToPositiveX() {
        movableObject.goTo(10, 0);
        assertEquals(SPEED, movableObject.getVelX());
        assertEquals(0, movableObject.getVelY());
    }

    @Test
    public void testGoToNegativeX() {
        movableObject.goTo(-10, 0);
        assertEquals(-SPEED, movableObject.getVelX());
        assertEquals(0, movableObject.getVelY());
    }

    @Test
    public void testGoToPositiveY() {
        movableObject.goTo(0, 10);
        assertEquals(0, movableObject.getVelX());
        assertEquals(SPEED, movableObject.getVelY());
    }

    @Test
    public void testGoToNegativeY() {
        movableObject.goTo(0, -10);
        assertEquals(0, movableObject.getVelX());
        assertEquals(-SPEED, movableObject.getVelY());
    }

    @Test
    public void testGoToPositiveXY() {
        movableObject.goTo(10, 10);
        assertEquals(SPEED, movableObject.getVelX());
        assertEquals(SPEED, movableObject.getVelY());
    }

    @Test
    public void testGoToNegativeXY() {
        movableObject.goTo(-10, -10);
        assertEquals(-SPEED, movableObject.getVelX());
        assertEquals(-SPEED, movableObject.getVelY());
    }

    private class MovableObject {
        private int x;
        private int y;
        private int xToGo;
        private int yToGo;
        private int velX;
        private int velY;
        private int speed;

        public MovableObject(int x, int y, int speed) {
            this.x = x;
            this.y = y;
            this.speed = speed;
        }

        public void goTo(int x, int y) {
            this.xToGo = x;
            this.yToGo = y;
            int xi = this.x - x;
            int yi = this.y - y;
            if (yi == 0) {
                setVelY(0);
            } else if (yi > 0) {
                setVelY(-1 * speed);
            } else {
                setVelY(speed);
            }
            if (xi == 0) {
                setVelX(0);
            } else if (xi > 0) {
                setVelX(-1 * speed);
            } else {
                setVelX(speed);
            }
        }

        public int getVelX() {
            return velX;
        }

        public int getVelY() {
            return velY;
        }

        private void setVelX(int velX) {
            this.velX = velX;
        }

        private void setVelY(int velY) {
            this.velY = velY;
        }
    }
}
