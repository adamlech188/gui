/**
 *
 */
package cs3744.graphics.primitives;

import cs3744.graphics.interfaces.IFace;
import cs3744.graphics.interfaces.IPoint;
import cs3744.graphics.interfaces.IPrimitive;
import cs3744.graphics.interfaces.IRectangle;
import cs3744.graphics.interfaces.ITriangle;
import cs3744.graphics.interfaces.IVector;
import java.util.Set;
import javax.media.opengl.GL2;

/**
 * Class that provides method to draw a solid or wire frame rectangle.
 *
 * @author Adam Lech (PID: adaml8)
 * @version 1.0
 */
public class Rectangle
    extends Face
    implements IRectangle

{

    // ----------------------------------------------------------
    /**
     * Constructor that creates a unit square.
     */
    public Rectangle()
    {
        this(
            new Point(-0.5f, -0.5f, 0f),
            new Point(0.5f, -0.5f, 0f),
            new Point(0.5f, 0.5f, 0f),
            new Point(-0.5f, 0.5f, 0f),
            null);
    }


    // ----------------------------------------------------------
    /**
     * Constructor that creates a unit square, with parent.
     *
     * @param parent
     *            - parent of the unit square.
     */
    public Rectangle(IPrimitive parent)
    {
        this(
            new Point(-0.5f, -0.5f, 0f),
            new Point(0.5f, -0.5f, 0f),
            new Point(0.5f, 0.5f, 0f),
            new Point(-0.5f, 0.5f, 0f),
            parent);
    }


    // ----------------------------------------------------------
    /**
     * Constructor that creates a recangle based on given points.
     *
     * @param firstPoint
     * @param secondPoint
     * @param thirdPoint
     * @param fourthPoint
     */
    public Rectangle(
        IPoint firstPoint,
        IPoint secondPoint,
        IPoint thirdPoint,
        IPoint fourthPoint)
    {

        this(firstPoint, secondPoint, thirdPoint, fourthPoint, null);
    }


    // ----------------------------------------------------------
    /**
     * Constructor that creates a triangle based on given points with the given
     * parent.
     *
     * @param firstPoint
     * @param secondPoint
     * @param thirdPoint
     * @param fourthPoint
     * @param parent
     */
    public Rectangle(
        IPoint firstPoint,
        IPoint secondPoint,
        IPoint thirdPoint,
        IPoint fourthPoint,
        IPrimitive parent)
    {
        super(parent);
        if (firstPoint == null || secondPoint == null || thirdPoint == null
            || fourthPoint == null)
        {
            throw new NullPointerException(
                "Rectangle has to have four points! One point is missin!");
        }
        IPoint firstpoint = new Point(firstPoint, this);
        this.addVertex(firstpoint);
        IPoint secondpoint = new Point(secondPoint, this);
        this.addVertex(secondpoint);
        IPoint thirdpoint = new Point(thirdPoint, this);
        this.addVertex(thirdpoint);
        IPoint fourthpoint = new Point(fourthPoint, this);
        this.addVertex(fourthpoint);

    }


    // ----------------------------------------------------------
    /**
     * Copy constructor.
     *
     * @param primitiveToCopy
     * @param parent
     */
    public Rectangle(IRectangle primitiveToCopy, IPrimitive parent)
    {
        super(primitiveToCopy, parent);

    }


    /**
     * Paints the primitives.
     *
     * @param gl2
     *            - openGL state machine
     */
    @Override
    public void paint(GL2 gl2)
    {
        IPoint firstPoint = this.getFirstPoint();

        IPoint secondPoint = this.getSecondPoint();
        IPoint thirdPoint = this.getThirdPoint();
        IPoint fourthPoint = this.getFourthPoint();
        // Checks whether the figure is set to be solid.
        if (isSolid())
        {

            ITriangle bottomTriangle =
                new Triangle(firstPoint, secondPoint, thirdPoint, this);
            ITriangle topTriangle =
                new Triangle(firstPoint, thirdPoint, fourthPoint, this);
            bottomTriangle.setFlat(this.isFlat());
            topTriangle.setFlat(this.isFlat());
            bottomTriangle.setColor(this.getColor());
            topTriangle.setMaterial(this.getMaterial());
            if (isFlat())
            {
                bottomTriangle.setSurfaceNormal(this.getSurfaceNormal());
                topTriangle.setSurfaceNormal(this.getSurfaceNormal());
            }
            else
            {
                bottomTriangle.addVertex(getFirstPoint(), getVertexNormalMap()
                    .get(getFirstPoint()));
                bottomTriangle.addVertex(getSecondPoint(), getVertexNormalMap()
                    .get(getSecondPoint()));
                bottomTriangle.addVertex(getThirdPoint(), getVertexNormalMap()
                    .get(getThirdPoint()));
                bottomTriangle.addVertex(getFourthPoint(), getVertexNormalMap()
                    .get(getFourthPoint()));
                topTriangle.addVertex(getFirstPoint(), getVertexNormalMap()
                    .get(getFirstPoint()));
                topTriangle.addVertex(getSecondPoint(), getVertexNormalMap()
                    .get(getSecondPoint()));
                topTriangle.addVertex(getThirdPoint(), getVertexNormalMap()
                    .get(getThirdPoint()));
                topTriangle.addVertex(getFourthPoint(), getVertexNormalMap()
                    .get(getFourthPoint()));

            }
            bottomTriangle.paint(gl2);
            topTriangle.paint(gl2);

        }
        else
        {
            Line base = new Line(firstPoint, secondPoint, this);
            Line rightUp = new Line(secondPoint, thirdPoint, this);
            Line top = new Line(thirdPoint, fourthPoint, this);
            Line leftUp = new Line(fourthPoint, firstPoint, this);
            base.paint(gl2);
            rightUp.paint(gl2);
            top.paint(gl2);
            leftUp.paint(gl2);

        }

    }


    /**
     * Gets the first point.
     *
     * @return firstPoint
     */

    public IPoint getFirstPoint()
    {
        Set<IPoint> vertices = this.getVertices();
        Object[] verticesArray = vertices.toArray();
        IPoint firstPoint = (IPoint)verticesArray[0];

        return firstPoint;
    }


    /**
     * Gets the second point.
     *
     * @return secondPoint
     */

    public IPoint getSecondPoint()
    {

        Set<IPoint> vertices = this.getVertices();
        Object[] verticesArray = vertices.toArray();
        IPoint secondPoint = (IPoint)verticesArray[1];

        return secondPoint;
    }


    /**
     * Gets the third point.
     *
     * @return thirdPoint
     */

    public IPoint getThirdPoint()
    {

        Set<IPoint> vertices = this.getVertices();
        Object[] verticesArray = vertices.toArray();
        IPoint thirdPoint = (IPoint)verticesArray[2];

        return thirdPoint;
    }


    /**
     * Gets the fourth point.
     *
     * @return IPoints
     */
    public IPoint getFourthPoint()
    {

        Set<IPoint> vertices = this.getVertices();
        Object[] verticesArray = vertices.toArray();
        IPoint fourthPoint = (IPoint)verticesArray[3];

        return fourthPoint;
    }


    @Override
    public void addVertex(IPoint vertex, IVector normal)
    {

        if (this.getVertices().size() < 4
            || this.getVertices().contains(vertex))
        {
            super.addVertex(vertex, normal);
        }

    }


    @Override
    public void removeVertex(IPoint vertex)
    {
        if (this.getVertices().size() > 4)
        {
            super.removeVertex(vertex);
        }

    }


    /**
     * Prevents from clearing all vertices.
     */
    @Override
    public void clearVertices()
    {
        // Prevents from clearing all vertices.
    }


    @Override
    public IFace copy()
    {
        return this.copy(null);
    }


    @Override
    public IFace copy(IPrimitive parent)
    {

        return new Rectangle(this, parent);
    }

}
