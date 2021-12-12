package wallBrick;

import ball.RubberBall;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class PinkBrickTest {

    private static final Color DEF_INNER = new Color(255, 147, 147);
    private static final Color DEF_BORDER = new Color(217, 199, 175);
    private final Point point = new Point(150,300);
    private final Dimension size = new Dimension(450,300);
    PinkBrick pinkBrick = new PinkBrick(point,size);

    public static final int UP_IMPACT = 300;
    public static final int RIGHT = 10;

    private final Point2D center = new Point2D.Double(300,430);
    RubberBall rubberBall = new RubberBall(center);

    @Test
    void findImpact() {
        pinkBrick.impact();
        assertEquals(UP_IMPACT, pinkBrick.findImpact(rubberBall));
    }

    @Test
    void impact() {
        pinkBrick.impact();
        assertFalse(pinkBrick.isBroken());
    }

    @Test
    void makeBrickFace() {
        assertEquals(new Rectangle(new Point(150, 300), new Dimension(450,300)), pinkBrick.makeBrickFace(point, size));
    }

    @Test
    void testRepair() {
        assertFalse(pinkBrick.setImpact(point, RIGHT));
    }
}