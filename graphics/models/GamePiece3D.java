package cs3744.graphics.models;

import cs3744.graphics.primitives.Cylinder;
import cs3744.graphics.interfaces.IPrimitive;
import javax.media.opengl.GL2;
import cs3744.graphics.models.interfaces.IGamePiece;
import cs3744.graphics.primitives.Primitive;

// -------------------------------------------------------------------------
/**
 * This class provides all the necessary data and methods to 3D game piece with
 * the given specs.
 *
 * @author Adam Lech(PID: adaml8)
 * @version Aug 10, 2012
 */
public class GamePiece3D
    extends Primitive
    implements IGamePiece

{
    private int xPosition;
    private int yPosition;


    // ----------------------------------------------------------
    /**
     * Constructs a new GamePiece at position (0, 0) on the grid.
     */
    public GamePiece3D()
    {
        this(0, 0, null);

    }


    // ----------------------------------------------------------
    /**
     * Copy constructor.
     *
     * @param primitiveToCopy
     * @param parent
     */
    public GamePiece3D(IGamePiece primitiveToCopy, IPrimitive parent)
    {
        super(primitiveToCopy, parent);
        xPosition = primitiveToCopy.getXPosition();
        yPosition = primitiveToCopy.getYPosition();

    }


    // ----------------------------------------------------------
    /**
     * Constructs a new GamePiece at position (xPosition, yPosition) on the
     * grid.
     *
     * @param xPosition
     * @param yPosition
     */
    public GamePiece3D(int xPosition, int yPosition)
    {
        this(xPosition, yPosition, null);

    }


    // ----------------------------------------------------------
    /**
     * Constructs a new GamePiece at position (xPosition, yPosition) on the grid
     * with the provided parent.
     *
     * @param xPosition
     * @param yPosition
     * @param parent
     */
    public GamePiece3D(int xPosition, int yPosition, IPrimitive parent)
    {
        super(parent);
        this.xPosition = xPosition;
        this.yPosition = yPosition;

    }


    // ----------------------------------------------------------
    /**
     * Constructs a new GamePiece at position (0, 0) on the grid and sets its
     * parent.
     *
     * @param parent
     */
    public GamePiece3D(IPrimitive parent)
    {
        this(0, 0, parent);
    }


    @Override
    public IPrimitive copy()
    {

        return this.copy(null);
    }


    @Override
    public IPrimitive copy(IPrimitive parent)
    {

        return new GamePiece3D(this, parent);
    }


    public void paint(GL2 gl2)
    {
        Cylinder gamePiece = new Cylinder(this);
        gamePiece.setSolid(this.isSolid());
        gamePiece.setMaterial(this.getMaterial());
        gamePiece.setColor(this.getColor());
        gamePiece.paint(gl2);

    }


    @Override
    public int getXPosition()
    {

        return xPosition;
    }


    @Override
    public void setXPosition(int xPosition)
    {
        this.xPosition = xPosition;
    }


    @Override
    public int getYPosition()
    {

        return yPosition;
    }


    @Override
    public void setYPosition(int yPosition)
    {
        this.yPosition = yPosition;

    }

}
