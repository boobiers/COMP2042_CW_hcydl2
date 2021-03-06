package wallBrick;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This class is to make the Crack for the Brick
 */
public class Crack{

    private static final int CRACK_SECTIONS = 3;
    private static final double JUMP_PROBABILITY = 0.7;

    public static final int LEFT = 10;
    public static final int RIGHT = 20;
    public static final int UP = 30;
    public static final int DOWN = 40;
    public static final int VERTICAL = 100;
    public static final int HORIZONTAL = 200;

    public static Random rnd;

    private GeneralPath crack;

    private int crackDepth;
    private int steps;



    /**
     * how deep the crack that forms in the brick
     * @param crackDepth the depth of the crack that was form
     * @param steps the steps before brick breaking
     */
    public Crack(int crackDepth, int steps){

        crack = new GeneralPath();
        this.crackDepth = crackDepth;
        this.steps = steps;
        rnd = new Random();
    }


    /**
     * draw the bricks
     * @return crack
     */
    public GeneralPath draw(){

        return crack;
    }

    /**
     * resets the crack
     */
    public void reset(){
        crack.reset();
    }

    /**
     * make crack based on which direction the ball is coming from
     * @param point the point at which the ball hits the brick
     * @param direction the direction at which the ball is coming from
     * @param brickFace
     */
    protected void makeCrack(Point2D point, int direction, Shape brickFace){

        Rectangle bounds = brickFace.getBounds();

        Point impact = new Point((int)point.getX(),(int)point.getY());
        Point start = new Point();
        Point end = new Point();


        switch(direction){
            case LEFT:
                start.setLocation(bounds.x + bounds.width, bounds.y);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                Point tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case RIGHT:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,VERTICAL);
                makeCrack(impact,tmp);

                break;
            case UP:
                start.setLocation(bounds.x, bounds.y + bounds.height);
                end.setLocation(bounds.x + bounds.width, bounds.y + bounds.height);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);
                break;
            case DOWN:
                start.setLocation(bounds.getLocation());
                end.setLocation(bounds.x + bounds.width, bounds.y);
                tmp = makeRandomPoint(start,end,HORIZONTAL);
                makeCrack(impact,tmp);

                break;

        }
    }

    /**
     * make the small zig-zag line for the crack
     * @param start starting point of impact
     * @param end ending point of impact
     */
    protected void makeCrack(Point start, Point end){

        GeneralPath path = new GeneralPath();


        path.moveTo(start.x,start.y);

        double w = (end.x - start.x) / (double)steps;
        double h = (end.y - start.y) / (double)steps;

        int bound = crackDepth;
        int jump  = bound * 5;

        double x,y;

        for(int i = 1; i < steps;i++){

            x = (i * w) + start.x;
            y = (i * h) + start.y + randomInBounds(bound);

            /**if(inMiddle(i,CRACK_SECTIONS,steps))
             y += jumps(jump,JUMP_PROBABILITY);
             **/

            path.lineTo(x,y);

        }

        path.lineTo(end.x,end.y);
        crack.append(path,true);
    }

    /**
     * make ball bounce off in random direction
     * @param bound
     * @return random integer
     */
    private int randomInBounds(int bound){
        int n = (bound * 2) + 1;
        return rnd.nextInt(n) - bound;
    }


    /**
     * make a random point for the Crack
     * @param from from one point on the brick
     * @param to to one point on the brcik
     * @param direction to see if direction is vertical or horizontal
     * @return new point generated
     */
    private Point makeRandomPoint(Point from,Point to, int direction){

        Point out = new Point();
        int pos;

        switch(direction){
            case HORIZONTAL:
                pos = rnd.nextInt(to.x - from.x) + from.x;
                out.setLocation(pos,to.y);
                break;
            case VERTICAL:
                pos = rnd.nextInt(to.y - from.y) + from.y;
                out.setLocation(to.x,pos);
                break;
        }
        return out;
    }

}
