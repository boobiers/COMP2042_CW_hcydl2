package wallBrick;

import ball.Ball;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This class is to make Brick in game
 *
 */
abstract public class Brick  {

    public static final int MIN_CRACK = 1;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;


    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;


    private static Random rnd;

    private String name;
    Shape brickFace;

    private Color border;
    private Color inner;

    private int fullStrength;
    private int strength;

    private boolean broken;


    /**
     * this is to initialise the variables
     * @param name string to store name
     * @param pos position of the brick
     * @param size size of the brick
     * @param border border for the brick
     * @param inner inner color for the brick
     * @param strength strength of the brick
     */
    public Brick(String name, Point pos,Dimension size,Color border,Color inner,int strength){
        rnd = new Random();
        broken = false;
        this.name = name;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;

    }

    /**
     * This is to make the face of the brick
     * @param pos position of the brick
     * @param size the size of the brick
     * @return
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     * set the impact on the brick
     * @param point point of impact
     * @param dir direction
     * @return boolean
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * get the brick
     * @return the brick that was created
     */
    public abstract Shape getBrick();


    /**
     * get the border color of the brick
     * @return the border color of the brick
     */
    public Color getBorderColor(){
        return  border;
    }

    /**
     * get the inner color of the brick
     * @return the inner color of the brick
     */
    public Color getInnerColor(){
        return inner;
    }

    /**
     * to find the impact of the brick by the ball
     * @param b is the ball
     * @return the direction of impact on the brick by the ball
     */
    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getRight()))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * whether the brick is broken
     * @return boolean
     */
    public final boolean isBroken(){
        return broken;
    }

    /**
     * repair the cracked brick
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**
     * the impact the ball has on the brickM
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }




}





