package wallBrick;

import ball.RubberBall;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class ClayBrickTest {
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;

    private final Point point = new Point(150,300);
    private final Dimension size = new Dimension(450,300);
    ClayBrick clayBrick = new ClayBrick(point,size);

    public static final int UP_IMPACT = 300;
    public static final int LEFT = 10;

    private final Point2D center = new Point2D.Double(300,430);
    RubberBall rubberBall = new RubberBall(center);

    @Test
    void setImpact() {
        assertTrue(clayBrick.setImpact(point, LEFT));
    }

    @Test
    void findImpact() {
        clayBrick.impact();
        assertEquals(0, clayBrick.findImpact(rubberBall));
    }

    @Test
    void isBroken() {
        assertEquals(false, clayBrick.isBroken());
    }

    @Test
    void repair() {
        assertFalse(clayBrick.isBroken());
    }

    @Test
    void impact() {
        clayBrick.impact();
        assertTrue(clayBrick.isBroken());
    }

    @Test
    void makeBrickFace() {
        assertEquals(new Rectangle(new Point(150, 300), new Dimension(450,300)), clayBrick.makeBrickFace(point, size));
    }

}