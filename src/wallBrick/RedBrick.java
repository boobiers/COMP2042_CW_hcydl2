package wallBrick;

import java.awt.*;
import java.awt.Point;


/**
 * this class makes clay bricks which inherits from bricks
 *
 */
public class RedBrick extends Brick {

    private static final String NAME = "Red Brick";
    private static final Color DEF_INNER = new Color(255, 102, 102).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 3;


    /**
     * this is to make Red brick
     * @param point the point (x,y) of the brick
     * @param size the dimension of the brick
     */
    public RedBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
    }

    /**
     * this is to make the face for the Red brick
     * @param pos position of the brick
     * @param size the size of the brick
     * @return Red brick
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * this is to get the clay brick
     * @return Red brick
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}