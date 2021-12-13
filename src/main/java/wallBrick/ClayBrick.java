package wallBrick;

import java.awt.*;
import java.awt.Point;


/**
 * this class makes clay bricks which inherits from bricks
 *
 */
public class ClayBrick extends Brick {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int Red_STRENGTH = 1;


    /**
     * this is to make clay brick
     * @param point the point (x,y) of the brick
     * @param size the dimension of the brick
     */
    public ClayBrick(Point point, Dimension size){
        super(NAME,point,size,DEF_BORDER,DEF_INNER,Red_STRENGTH);
    }

    /**
     * this is to make the face for the clay brick
     * @param pos position of the brick
     * @param size the size of the brick
     * @return clay brick
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }

    /**
     * this is to get the clay brick
     * @return clay brick
     */
    @Override
    public Shape getBrick() {
        return super.brickFace;
    }


}
