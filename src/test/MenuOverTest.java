import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class MenuOverTest {
    private MouseTracker mouseTracker = new MouseTracker();

    @Test
    public void testMouseOverInside() {
        // Test case: Mouse coordinates are inside the given rectangle
        assertTrue(mouseTracker.mouseOver(5, 5, 0, 0, 10, 10));
    }

    @Test
    public void testMouseOverOutsideLeft() {
        // Test case: Mouse coordinates are outside to the left of the given rectangle
        assertFalse(mouseTracker.mouseOver(-5, 5, 0, 0, 10, 10));
    }

    @Test
    public void testMouseOverOutsideRight() {
        // Test case: Mouse coordinates are outside to the right of the given rectangle
        assertFalse(mouseTracker.mouseOver(15, 5, 0, 0, 10, 10));
    }

    @Test
    public void testMouseOverOutsideTop() {
        // Test case: Mouse coordinates are outside above the given rectangle
        assertFalse(mouseTracker.mouseOver(5, -5, 0, 0, 10, 10));
    }

    @Test
    public void testMouseOverOutsideBottom() {
        // Test case: Mouse coordinates are outside below the given rectangle
        assertFalse(mouseTracker.mouseOver(5, 15, 0, 0, 10, 10));
    }

    @Test
    public void testMouseOverOnLeftEdge() {
        // Test case: Mouse coordinates are on the left edge of the given rectangle
        assertFalse(mouseTracker.mouseOver(0, 5, 0, 0, 10, 10));
    }

    @Test
    public void testMouseOverOnRightEdge() {
        // Test case: Mouse coordinates are on the right edge of the given rectangle
        assertFalse(mouseTracker.mouseOver(10, 5, 0, 0, 10, 10));
    }

    @Test
    public void testMouseOverOnTopEdge() {
        // Test case: Mouse coordinates are on the top edge of the given rectangle
        assertFalse(mouseTracker.mouseOver(5, 0, 0, 0, 10, 10));
    }

    @Test
    public void testMouseOverOnBottomEdge() {
        // Test case: Mouse coordinates are on the bottom edge of the given rectangle
        assertFalse(mouseTracker.mouseOver(5, 10, 0, 0, 10, 10));
    }

    private class MouseTracker {
        private boolean mouseOver(int mx, int my, int x, int y, int w, int h) {
            if (mx > x && mx < x + w) {
                if (my > y && my < y + h) {
                    return true;
                }
            }
            return false;
        }
    }
}
