/**
 *
 */
package cs3744.graphics.models;

import cs3744.graphics.primitives.CircleWithBorder;
import cs3744.graphics.interfaces.IPrimitive;
import javax.media.opengl.GL2;
import cs3744.graphics.models.interfaces.IGamePiece;
import cs3744.graphics.primitives.Primitive;

/**
 * Class provides methods and data to draw game piece on the board.
 *
 * @author Adam Lech (PID: adaml8)
 * @version 1.0
 */
public class GamePiece
    extends Primitive
    implements IGamePiece
{
    private int xPosition;
    private int yPosition;


    // ----------------------------------------------------------
    /**
     * Creates a game piece with position (0,0)
     */
    public GamePiece()
    {
        this(0, 0, null);
    }


    // ----------------------------------------------------------
    /**
     * Creates a copy of the game piece.
     *
     * @param primitiveToCopy
     * @param parent
     */
    public GamePiece(GamePiece primitiveToCopy, IPrimitive parent)
    {
        this(
            primitiveToCopy.getXPosition(),
            primitiveToCopy.getYPosition(),
            parent);

    }


    // ----------------------------------------------------------
    /**
     * Create a game piece at the given position (x,y)
     *
     * @param xPosition
     * @param yPosition
     */
    public GamePiece(int xPosition, int yPosition)
    {
        this(xPosition, yPosition, null);

    }


    // ----------------------------------------------------------
    /**
     * Create a game piece at the given position (x,y) and with parent.
     *
     * @param xPosition
     * @param yPosition
     * @param parent
     */
    public GamePiece(int xPosition, int yPosition, IPrimitive parent)
    {
        super(parent);
        this.xPosition = xPosition;
        this.yPosition = yPosition;

    }


    // ----------------------------------------------------------
    /**
     * Creates a game piece with position (0,0) and with parent.
     *
     * @param parent
     */
    public GamePiece(IPrimitive parent)
    {
        this(0, 0, parent);

    }


    /**
     * Paints the game piece using circle with border.
     *
     * @param gl2
     *            - OpenGL state machine
     */
    @Override
    public void paint(GL2 gl2)
    {

        CircleWithBorder gamepiece = new CircleWithBorder(this);
        gamepiece.setSolid(true);
        gamepiece.setColor(this.getColor());
        gamepiece.paint(gl2);

    }


    /**
     * Gets x position of the game piece
     */
    @Override
    public int getXPosition()
    {

        return xPosition;
    }


    /**
     * Sets x position of the game piece
     */
    @Override
    public void setXPosition(int xPosition)
    {

        this.xPosition = xPosition;

    }


    /**
     * Gets y position of the game piece.
     */
    @Override
    public int getYPosition()
    {

        return yPosition;
    }


    /**
     * Sets y position of the game piece
     */
    @Override
    public void setYPosition(int yPosition)
    {

        this.yPosition = yPosition;

    }


    @Override
    public IPrimitive copy()
    {
        return copy(null);
    }


    @Override
    public IPrimitive copy(IPrimitive parent)
    {

        return new GamePiece(this, parent);
    }

}
