package ball;

import java.awt.*;
import java.awt.geom.Point2D;
import static org.junit.jupiter.api.Assertions.*;

class RubberBallTest {

    private static final int DEF_RADIUS = 10;
    private static final Color DEF_INNER_COLOR = new Color(255, 219, 88);
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();
    private final Point2D center = new Point2D.Double(300,430);
    RubberBall rubberBall = new RubberBall(center);

    @org.junit.jupiter.api.Test
    void move() {
        rubberBall.setSpeed(4,4);
        rubberBall.move();
        assertEquals(new Point2D.Double(304,434), rubberBall.getPosition());
    }

    @org.junit.jupiter.api.Test
    void moveTo() {
        rubberBall.moveTo(new Point(200,400));
        assertEquals(new Point2D.Double(200,400), rubberBall.getPosition());
    }


    @org.junit.jupiter.api.Test
    void reverseX() {
        rubberBall.setXSpeed(4);
        rubberBall.reverseX();
        assertEquals(-4,rubberBall.getSpeedX());
    }

    @org.junit.jupiter.api.Test
    void reverseY() {
        rubberBall.setYSpeed(4);
        rubberBall.reverseY();
        assertEquals(-4,rubberBall.getSpeedY());
    }


    @org.junit.jupiter.api.Test
    void makeBall() {
        assertEquals(rubberBall.getBallFace(), rubberBall.makeBall(center, DEF_RADIUS,DEF_RADIUS));
    }
}