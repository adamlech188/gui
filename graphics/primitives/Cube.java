package cs3744.graphics.primitives;

import cs3744.graphics.interfaces.ICube;
import cs3744.graphics.interfaces.IFace;
import cs3744.graphics.interfaces.IPoint;
import cs3744.graphics.interfaces.IPrimitive;
import java.util.List;
import javax.media.opengl.GL2;

// -------------------------------------------------------------------------
/**
 * A class that provides all the data and methods to draw a circle.
 *
 * @author Adam Lech (PID: adaml8)
 * @version Aug 10, 2012
 */
public class Cube
    extends Mesh
    implements ICube

{

    // ----------------------------------------------------------
    /**
     * Constructs a new Cube.
     */
    public Cube()
    {
        this(null);
    }


    // ----------------------------------------------------------
    /**
     * Copy constructor.
     *
     * @param primitiveToCopy
     * @param parent
     */
    public Cube(ICube primitiveToCopy, IPrimitive parent)
    {
        super(primitiveToCopy, parent);
    }


    // ----------------------------------------------------------
    /**
     * Constructs a new Cube with the provided parent.
     *
     * @param parent
     */
    public Cube(IPrimitive parent)
    {
        super(parent);
    }


    public IPrimitive copy()
    {

        return copy(null);
    }


    /**
     * Provides a copy of the current object and sets its parent.
     */
    @Override
    public IPrimitive copy(IPrimitive parent)
    {
        IPrimitive copy = new Cube(parent);
        return copy;
    }


    /**
     * Paints the primitive.
     */
    public void paint(GL2 gl2)
    {
        if (getFaceList().isEmpty())
        {
            // Drawing faces using points
            IPoint frontTopRight = new Point(0.5f, 0.5f, 0.5f, this);
            IPoint frontTopLeft = new Point(-0.5f, 0.5f, 0.5f, this);
            IPoint frontBottomRight = new Point(0.5f, -0.5f, 0.5f, this);
            IPoint frontBottomLeft = new Point(-0.5f, -0.5f, 0.5f, this);

            IPoint backTopRight = new Point(0.5f, 0.5f, -0.5f, this);
            IPoint backTopLeft = new Point(-0.5f, 0.5f, -0.5f, this);
            IPoint backBottomRight = new Point(0.5f, -0.5f, -0.5f, this);
            IPoint backBottomLeft = new Point(-0.5f, -0.5f, -0.5f, this);

            Rectangle frontFace =
                new Rectangle(
                    frontTopRight,
                    frontTopLeft,
                    frontBottomLeft,
                    frontBottomRight,
                    this);
            Rectangle backFace =
                new Rectangle(
                    backBottomLeft,
                    backTopLeft,
                    backTopRight,
                    backBottomRight,
                    this);
            Rectangle topFace =
                new Rectangle(
                    frontTopLeft,
                    frontTopRight,
                    backTopRight,
                    backTopLeft,
                    this);
            Rectangle bottomFace =
                new Rectangle(
                    backBottomLeft,
                    backBottomRight,
                    frontBottomRight,
                    frontBottomLeft,
                    this);
            Rectangle leftFace =
                new Rectangle(
                    backTopLeft,
                    backBottomLeft,
                    frontBottomLeft,
                    frontTopLeft,
                    this);
            Rectangle rightFace =
                new Rectangle(
                    frontBottomRight,
                    backBottomRight,
                    backTopRight,
                    frontTopRight,
                    this);

            this.addFace(topFace);
            this.addFace(bottomFace);
            this.addFace(backFace);
            this.addFace(frontFace);
            this.addFace(leftFace);
            this.addFace(rightFace);
            List<IFace> faceList = this.getFaceList();
            for (IFace face : faceList)
            {
                face.setSolid(this.isSolid());
                face.setFlat(true);
                face.setColor(this.getColor());
                face.setMaterial(this.getMaterial());
                face.paint(gl2);

            }

        }

    }

}
