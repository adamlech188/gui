/**
 *
 */
package cs3744.graphics.primitives;

import cs3744.graphics.interfaces.IVector;
import java.util.Set;
import cs3744.graphics.interfaces.IFace;
import cs3744.graphics.interfaces.IPoint;
import cs3744.graphics.interfaces.IPrimitive;
import cs3744.graphics.interfaces.ITriangle;
import javax.media.opengl.GL;
import javax.media.opengl.GL2;

/**
 * Class that provides methods to draw a solid or wire frame triangle.
 *
 * @author Adam Lech (PID: adaml8)
 * @version 1.0
 */
public class Triangle
    extends Face
    implements ITriangle
{

    // ----------------------------------------------------------
    /**
     * Constructor that creates a unit equilateral triangle.
     */
    public Triangle()
    {

        this(new Point(-0.5f, (float)-Math.tan(Math.PI / 6), 0f), new Point(
            0.5f,
            (float)-Math.tan(Math.PI / 6),
            0f), new Point(0f, (float)Math.sqrt(0.75)
            + (float)-Math.tan(Math.PI / 6), 0f));
    }


    // ----------------------------------------------------------
    /**
     * Constructor that creates a unit equilateral triangle with the parent.
     *
     * @param parent
     */
    public Triangle(IPrimitive parent)
    {
        this(new Point(-0.5f, (float)-Math.tan(Math.PI / 6), 0f), new Point(
            0.5f,
            (float)-Math.tan(Math.PI / 6),
            0f), new Point(0f, (float)Math.sqrt(0.75)
            + (float)-Math.tan(Math.PI / 6), 0f), parent);

    }


    /**
     * Creates a copy of the triangle with the parent.
     *
     * @param triangleToCopy
     * @param parent
     */
    public Triangle(ITriangle triangleToCopy, IPrimitive parent)
    {
        super(triangleToCopy, parent);

    }


    // ----------------------------------------------------------
    /**
     * Creates a triangle based on tree points.
     *
     * @param firstPoint
     * @param secondPoint
     * @param thirdPoint
     */
    public Triangle(IPoint firstPoint, IPoint secondPoint, IPoint thirdPoint)
    {
        this(firstPoint, secondPoint, thirdPoint, null);

    }


    // ----------------------------------------------------------
    /**
     * Creates a triangle based on tree points with the parent.
     *
     * @param firstPoint
     * @param secondPoint
     * @param thirdPoint
     * @param parent
     */
    public Triangle(
        IPoint firstPoint,
        IPoint secondPoint,
        IPoint thirdPoint,
        IPrimitive parent)
    {
        super(parent);
        this.addVertex(firstPoint);
        this.addVertex(secondPoint);
        this.addVertex(thirdPoint);

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
        IPoint[] points = this.getVertices().toArray(new Point[0]);
        // Checks whether the figure is set to be solid.
        if (isSolid())

        {
            gl2.glBegin(GL.GL_TRIANGLES);
            if (isFlat())
            {

                getSurfaceNormal().paint(gl2);
                for (IPoint point : points)
                {
                    point.paint(gl2);
                }
            }
            else
            {

                for (IPoint point : points)
                {

                    IVector normal = getVertexNormalMap().get(point);
                    if (normal == null)
                    {
                        this.calculateAndSetVertexNormals();
                        normal = getVertexNormalMap().get(point);

                    }
                    point.paint(gl2);
                    normal.paint(gl2);
                }
            }
            gl2.glEnd();

        }
        else
        {
            Line line1 =
                new Line(this.getFirstPoint(), this.getSecondPoint(), this);
            Line line2 =
                new Line(this.getSecondPoint(), this.getThirdPoint(), this);
            Line line3 =
                new Line(this.getThirdPoint(), this.getFirstPoint(), this);
            line1.paint(gl2);
            line2.paint(gl2);
            line3.paint(gl2);

        }

    }


    /**
     * Gets the first point.
     *
     * @return firstPoint
     */

    public IPoint getFirstPoint()
    {
        Object[] verticesArray = this.getVertices().toArray();
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


    @Override
    public IFace copy()
    {
        return copy(null);
    }


    @Override
    public IFace copy(IPrimitive parent)
    {
        return new Triangle(this, parent);
    }


    @Override
    public void addVertex(IPoint vertex, IVector normal)
    {

        if (this.getVertices().size() < 3
            || this.getVertices().contains(vertex))
        {
            super.addVertex(vertex, normal);
        }

    }


    @Override
    public void removeVertex(IPoint vertex)
    {
        if (this.getVertices().size() > 3)
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
        // Shouldn't clear vertices
    }


    public String toString()
    {
        StringBuilder returnValue =
            (new StringBuilder()).append(getFirstPoint()).append(" -> ")
                .append(getSecondPoint()).append(" -> ")
                .append(getThirdPoint());
        return returnValue.toString();
    }

}
