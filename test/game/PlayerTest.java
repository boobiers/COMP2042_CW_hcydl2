package game;

import ball.RubberBall;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Point2D;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    private static final int DEF_MOVE_AMOUNT = 5;
    private static final int DEF_WIDTH = 600;
    private static final int DEF_HEIGHT = 450;
    private final int playerWidth = 150;
    private final int playerHeight = 10;
    private final Point ballPoint = new Point(300,430);
    Player player = new Player(ballPoint, playerWidth, playerHeight, new Rectangle(0,0,DEF_WIDTH,DEF_HEIGHT));

    private final Point2D center = new Point2D.Double(300,430);
    RubberBall rubberball = new RubberBall(center);

    @Test
    void impact() {
        assertTrue(player.impact(rubberball));
    }

    @Test
    void move() {
        player.move();
        assertEquals(225, ballPoint.x - playerWidth/2);
    }

    @Test
    void moveLeft() {
        int moveAmount = -DEF_MOVE_AMOUNT;
        assertEquals(-5, moveAmount);
    }

    @Test
    void movRight() {
        int moveAmount = DEF_MOVE_AMOUNT;
        assertEquals(5, moveAmount);
    }

    @Test
    void stop() {
        int moveAmount = 0;
        assertEquals(0,moveAmount);
    }

    @Test
    void getPlayerFace() {
    }

    @Test
    void moveTo() {
        player.moveTo(new Point(350,300));
        assertEquals(275,ballPoint.x - playerWidth/2);
    }
}