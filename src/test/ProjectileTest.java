import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProjectileTest {

    private Projectile projectile;

    @Before
    public void setUp() {
        projectile = new Projectile(0, 0);
    }

    @Test
    public void testVelCalculate() {
        int x2 = 10;
        int y2 = 10;
        int speed = 10;

        projectile.velCalculate(x2, y2, speed);

        int expectedVelX = 7;
        int expectedVelY = 7;

        assertEquals(expectedVelX, projectile.getVelX());
        assertEquals(expectedVelY, projectile.getVelY());
    }

    private class Projectile {
        private int x;
        private int y;
        private int velX;
        private int velY;

        public Projectile(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void velCalculate(int x2, int y2, int speed) {
            int yc = y2 - y;
            int xc = x2 - x;
            double c = Math.sqrt((yc * yc) + (xc * xc));
            this.velX = (int) Math.round((speed * xc) / c);
            this.velY = (int) Math.round((speed * yc) / c);
        }

        public int getVelX() {
            return velX;
        }

        public int getVelY() {
            return velY;
        }
    }
}

