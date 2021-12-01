/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package wallBrick;

import ball.Ball;
import ball.RubberBall;
import game.*;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * This class is to create the wall for the game
 */
public class Wall {

    private static final int LEVELS_COUNT = 4;

    private static final int CLAY = 1;
    private static final int STEEL = 2;
    private static final int CEMENT = 3;

    private Random rnd;
    private Rectangle area;

    private Brick[] bricks;
    private Ball ball;
    private Player player;

    private Brick[][] levels;
    private int level;

    private Point startPoint;
    private int brickCount;
    private int ballCount;
    private boolean ballLost;

    /**
     * this is to initialise the variables in Wall class
     * @param drawArea calculate the area of the player
     * @param brickCount count the number of bricks
     * @param lineCount number of rows of brick
     * @param brickDimensionRatio the height to width ratio of the bricks
     * @param ballPos the position of the ball
     */
    public Wall(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        this.startPoint = new Point(ballPos);

        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;

        ballCount = 3;
        ballLost = false;

        rnd = new Random();

        makeBall(ballPos);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        getBall().setSpeed(speedX,speedY);

        setPlayer(new Player((Point) ballPos.clone(),150,10, drawArea));

        area = drawArea;


    }

    /**
     * this is to make a level for the game
     * @param drawArea area where the bricks are on
     * @param brickCnt the number of bricks
     * @param lineCnt the number of rows of bricks
     * @param brickSizeRatio the height to width ratio
     * @param type types of brick (Clay, Steel, or Cement)
     * @return the brick of the level
     */
    private Brick[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrick(p,brickSize);
        }
        return tmp;

    }

    /**
     * making a level that alternates the different bricks.
     * @param drawArea area where the bricks are on
     * @param brickCnt the number of bricks
     * @param lineCnt the number of rows of bricks
     * @param brickSizeRatio the height to width ratio for the brick
     * @param typeA types of brick (Clay, Steel, or Cement)
     * @param typeB types of brick (Clay, Steel, or Cement)
     * @return the bricks for the level
     */
    private Brick[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        Brick[] tmp  = new Brick[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
    }

    /**
     * this is to make the rubber ball
     * @param ballPos position of the ball
     */
    private void makeBall(Point2D ballPos){
        setBall(new RubberBall(ballPos));
    }

    /**
     * this is to make the level for the game
     * @param drawArea area where the bricks are on
     * @param brickCount the number of bricks
     * @param lineCount the number of rows of bricks
     * @param brickDimensionRatio the height to width ratio for the brick
     * @return the tmp variable which holds the levels in game
     */
    private Brick[][] makeLevels(Rectangle drawArea,int brickCount,int lineCount,double brickDimensionRatio){
        Brick[][] tmp = new Brick[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,CEMENT);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY,STEEL);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL,CEMENT);
        return tmp;
    }

    /**
     * player moving and the ball moving
     */
    public void move(){
        getPlayer().move();
        getBall().move();
    }

    /**
     * to see whether the ball impact the any surface
     */
    public void findImpacts(){
        if(getPlayer().impact(getBall())){
            getBall().reverseY();
        }
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
            * because for every brick program checks for horizontal and vertical impacts
            */
            brickCount--;
        }
        else if(impactBorder()) {
            getBall().reverseX();
        }
        else if(getBall().getPosition().getY() < area.getY()){
            getBall().reverseY();
        }
        else if(getBall().getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            ballLost = true;
        }
    }

    /**
     * when the ball impact the walls of the brick
     * @return boolean
     */
    private boolean impactWall(){
        for(Brick b : getBricks()){
            switch(b.findImpact(getBall())) {
                //Vertical Impact
                case Brick.UP_IMPACT:
                    getBall().reverseY();
                    return b.setImpact(getBall().getDown(), Brick.Crack.UP);
                case Brick.DOWN_IMPACT:
                    getBall().reverseY();
                    return b.setImpact(getBall().getUp(),Brick.Crack.DOWN);

                //Horizontal Impact
                case Brick.LEFT_IMPACT:
                    getBall().reverseX();
                    return b.setImpact(getBall().getRight(),Brick.Crack.RIGHT);
                case Brick.RIGHT_IMPACT:
                    getBall().reverseX();
                    return b.setImpact(getBall().getLeft(),Brick.Crack.LEFT);
            }
        }
        return false;
    }

    /**
     * see if the ball hits the border
     * @return boolean
     */
    private boolean impactBorder(){
        Point2D p = getBall().getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * get the brick count
     * @return the number of bricks
     */
    public int getBrickCount(){
        return brickCount;
    }

    /**
     * get the number of balls
     * @return the number of bricks
     */
    public int getBallCount(){
        return ballCount;
    }

    /**
     * check if the ball is missing
     * @return lost ball
     */
    public boolean isBallLost(){
        return ballLost;
    }

    /**
     * this is to reset the ball position
     */
    public void ballReset(){
        getPlayer().moveTo(startPoint);
        getBall().moveTo(startPoint);
        int speedX,speedY;
        do{
            speedX = rnd.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -rnd.nextInt(3);
        }while(speedY == 0);

        getBall().setSpeed(speedX,speedY);
        ballLost = false;
    }

    /**
     * reset the wall and reset ball count to 3
     */
    public void wallReset(){
        for(Brick b : getBricks())
            b.repair();
        brickCount = getBricks().length;
        ballCount = 3;
    }

    /**
     * when there's no balls left
     * @return ball count 0
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * check to see if the level is done
     * @return boolean
     */
    public boolean isDone(){
        return brickCount == 0;
    }

    /**
     * change the level of the game
     */
    public void nextLevel(){
        setBricks(levels[level++]);
        this.brickCount = getBricks().length;
    }

    /**
     * check to see if there's still more level
     * @return boolean
     */
    public boolean hasLevel(){
        return level < levels.length;
    }

    /**
     * set the speed of the ball on the x axis
     * @param s speed of the ball (x axis)
     */
    public void setBallXSpeed(int s){
        getBall().setXSpeed(s);
    }

    /**
     * set the speed of the ball on the y axis
     * @param s speed of the ball (y axis)
     */
    public void setBallYSpeed(int s){
        getBall().setYSpeed(s);
    }

    /**
     * reset the number of balls
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    /**
     * make the bricks
     * @param point the x and y coordinate of the brick
     * @param size the size of the brick
     * @param type the type of bricks
     * @return brick
     */
    private Brick makeBrick(Point point, Dimension size, int type){
        Brick out;
        switch(type){
            case CLAY:
                out = new ClayBrick(point,size);
                break;
            case STEEL:
                out = new SteelBrick(point,size);
                break;
            case CEMENT:
                out = new CementBrick(point, size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }

    public Brick[] getBricks() {
        return bricks;
    }

    public void setBricks(Brick[] bricks) {
        this.bricks = bricks;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
