package test;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;

/**
 * Created by filippo on 04/09/16.
 *
 */

/**
 * this class is to make the ball in the game
 */
abstract public class Ball {


    private Shape ballFace;

    private Point2D center;

    Point2D up;
    Point2D down;
    Point2D left;
    Point2D right;

    private Color border;
    private Color inner;

    private int speedX;
    private int speedY;

    /**
     * this is to initialise the variables
     * @param center is the center point of the ball
     * @param radiusA is the diameter of the ball from the most left of the ball to the most right of the ball
     * @param radiusB is the diameter of the ball from the top of the ball to the bottom of the ball
     * @param inner is the color of the ball
     * @param border is the border outline of the ball
     */
    public Ball(Point2D center,int radiusA,int radiusB,Color inner,Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));

        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());


        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * this is where makeBall takes in the value of center, radiusA, and radiusB to make the shape of the ball
     * @param center taking the points from the constructor
     * @param radiusA taking the diameter of the ball from the constructor
     * @param radiusB taking the diameter of the ball from the constructor
     * @return this returns the shape of the ball with the diameter radiusA, radiusB starting from the center parameter
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);


    /**
     * It's moving the ball
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);


        ballFace = tmp;
    }

    /**
     * set the speed of the ball
     * @param x set the speed at which the ball is going on the x axis
     * @param y set the speed at which the ball is going on the y axis
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * set the ball speed on the x axis
     * @param s speed of the ball at x axis
     */
    public void setXSpeed(int s){
        speedX = s;
    }

    /**
     * set the ball speed on the y axis
     * @param s speed of the ball at y axis
     */
    public void setYSpeed(int s){
        speedY = s;
    }

    /**
     * going in the opposite direction of speedX
     */
    public void reverseX(){
        speedX *= -1;
    }

    /**
     * going in the opposite direction of speedY
     */
    public void reverseY(){
        speedY *= -1;
    }

    /**
     * determine the border color of the ball
     * @return the border of the ball
     */
    public Color getBorderColor(){
        return border;
    }

    /**
     * determine the color of the ball
     * @return the color ball
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * get the x and y coordinates of the center
     * @return x and y coordinates of the center of the ball
     */
    public Point2D getPosition(){
        return center;
    }

    /**
     * get the shape of the ball
     * @return the ball with the shape
     */
    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * this is to set the ball back to its original position
     * @param p the point at which the ball is supposed to be before it starts moving
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();

        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    /**
     * set the point at which the ball is position
     * @param width the width of the ball
     * @param height the height of the ball
     */
    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    /**
     * returns the value of speedX
     * @return the value that was set in the method setSpeedX()
     */
    public int getSpeedX(){
        return speedX;
    }

    /**
     * returns the value of speedY
     * @return the value that was set in the method setSpeedY()
     */
    public int getSpeedY(){
        return speedY;
    }


}
