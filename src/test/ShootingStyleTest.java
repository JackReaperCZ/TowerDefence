import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ShootingStyleTest {

    private ShootingStyle shootingStyle;

    @Before
    public void setUp() {
        shootingStyle = new ShootingStyle();
    }

    @Test
    public void testNextShootingStyle() {
        assertEquals(ShootingStyle.SHOOTING_STYLE.FIRST, shootingStyle.getShootingStyle());
        shootingStyle.nextShootingStyle();
        assertEquals(ShootingStyle.SHOOTING_STYLE.LAST, shootingStyle.getShootingStyle());
        shootingStyle.nextShootingStyle();
        assertEquals(ShootingStyle.SHOOTING_STYLE.WEAK, shootingStyle.getShootingStyle());
        shootingStyle.nextShootingStyle();
        assertEquals(ShootingStyle.SHOOTING_STYLE.STRONG, shootingStyle.getShootingStyle());
        shootingStyle.nextShootingStyle();
        assertEquals(ShootingStyle.SHOOTING_STYLE.FIRST, shootingStyle.getShootingStyle());
    }

    @Test
    public void testPreviousShootingStyle() {
        assertEquals(ShootingStyle.SHOOTING_STYLE.FIRST, shootingStyle.getShootingStyle());
        shootingStyle.previousShootingStyle();
        assertEquals(ShootingStyle.SHOOTING_STYLE.STRONG, shootingStyle.getShootingStyle());
        shootingStyle.previousShootingStyle();
        assertEquals(ShootingStyle.SHOOTING_STYLE.WEAK, shootingStyle.getShootingStyle());
        shootingStyle.previousShootingStyle();
        assertEquals(ShootingStyle.SHOOTING_STYLE.LAST, shootingStyle.getShootingStyle());
        shootingStyle.previousShootingStyle();
        assertEquals(ShootingStyle.SHOOTING_STYLE.FIRST, shootingStyle.getShootingStyle());
    }

    private class ShootingStyle {
        public SHOOTING_STYLE shootingStyle = SHOOTING_STYLE.FIRST;

        public enum SHOOTING_STYLE {
            FIRST,
            LAST,
            WEAK,
            STRONG
        }

        public void nextShootingStyle() {
            switch (shootingStyle) {
                case FIRST -> shootingStyle = SHOOTING_STYLE.LAST;
                case LAST -> shootingStyle = SHOOTING_STYLE.WEAK;
                case WEAK -> shootingStyle = SHOOTING_STYLE.STRONG;
                case STRONG -> shootingStyle = SHOOTING_STYLE.FIRST;
            }
        }

        public void previousShootingStyle() {
            switch (shootingStyle) {
                case FIRST -> shootingStyle = SHOOTING_STYLE.STRONG;
                case LAST -> shootingStyle = SHOOTING_STYLE.FIRST;
                case WEAK -> shootingStyle = SHOOTING_STYLE.LAST;
                case STRONG -> shootingStyle = SHOOTING_STYLE.WEAK;
            }
        }


        public SHOOTING_STYLE getShootingStyle() {
            return shootingStyle;
        }
    }
}
