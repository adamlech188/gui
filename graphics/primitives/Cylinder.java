package cs3744.graphics.primitives;

import cs3744.graphics.interfaces.IFace;
import cs3744.graphics.common.solution.Operations;
import cs3744.graphics.common.solution.Vector;
import cs3744.graphics.interfaces.IPoint;
import cs3744.graphics.common.Translation;
import cs3744.graphics.interfaces.ICircle;
import cs3744.graphics.primitives.Circle;
import cs3744.graphics.interfaces.IPrimitive;
import javax.media.opengl.GL2;
import cs3744.graphics.interfaces.ICylinder;

// -------------------------------------------------------------------------
/**
 * Class that provides all the necessary data and methods to draw a cylinder
 * with the given specs.
 *
 * @author Adam Lech (PID: adaml8)
 * @version Aug 10, 2012
 */
public class Cylinder
    extends Mesh
    implements ICylinder

{

    // ----------------------------------------------------------
    /**
     * Constructs a new Cube.
     */
    public Cylinder()
    {
        this(null);
    }


    // ----------------------------------------------------------
    /**
     * Constructs a new Cube with the given parent.
     *
     * @param parent
     */
    public Cylinder(IPrimitive parent)
    {
        super(parent);
    }


    // ----------------------------------------------------------
    /**
     * Copy constructor.
     *
     * @param primitiveToCopy
     * @param parent
     */
    public Cylinder(ICylinder primitiveToCopy, IPrimitive parent)
    {
        super(primitiveToCopy, parent);
    }


    /**
     * Provides a copy of the current object.
     */
    @Override
    public IPrimitive copy()
    {
        return this.copy(null);
    }


    /**
     * Provides a copy of the current object and sets its parent.
     */
    @Override
    public IPrimitive copy(IPrimitive parent)
    {

        return new Cylinder(this, parent);
    }


    /**
     * Paints the primitive.
     */
    @Override
    public void paint(GL2 gl2)
    {
        if (this.getFaceList().isEmpty())
        {
            ICircle topCircle = new Circle(20, this);
            ICircle bottomCircle = new Circle(20, this);
            topCircle.setTranslation(new Translation(0f, 0f, -1f));
            bottomCircle.setTranslation(new Translation(0f, 0f, 0f));
            topCircle.setColor(getColor());
            bottomCircle.setColor(getColor());
            bottomCircle.setMaterial(this.getMaterial());
            topCircle.setMaterial(this.getMaterial());

            this.addFace(topCircle);
            this.addFace(bottomCircle);

            IPoint[] points = topCircle.getVertices().toArray(new IPoint[0]);

            for (int i = 0; i < points.length; i++)
            {

                IPoint currentTop =
                    Operations.add(points[i], new Vector(0f, 0f, -1f));
                IPoint currentBottom =
                    Operations.add(currentTop, Vector.K_UNIT_VECTOR);
                IPoint nextTop =
                    Operations.add(points[(i + 1) % points.length], new Vector(
                        0f,
                        0f,
                        -1f));
                IPoint nextBottom =
                    Operations.add(nextTop, Vector.K_UNIT_VECTOR);
                Rectangle rectangle1 =
                    new cs3744.graphics.primitives.Rectangle(
                        nextTop,
                        nextBottom,
                        currentBottom,
                        currentTop,
                        this);
                Rectangle rectangle2 =
                    new cs3744.graphics.primitives.Rectangle(
                        currentTop,
                        currentBottom,
                        nextBottom,
                        nextTop,
                        this);
                rectangle1.setColor(this.getColor());
                rectangle1.setMaterial(this.getMaterial());
                rectangle2.setColor(this.getColor());
                rectangle2.setMaterial(this.getMaterial());
                this.addFace(rectangle1);
                this.addFace(rectangle2);

            }
        }

        for (IFace face : this.getFaceList())
        {
            face.setSolid(this.isSolid());
            face.setFlat(true);
            face.paint(gl2);
        }

    }

}
