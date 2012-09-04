/**
 *
 */
package cs3744.graphics.primitives;

import cs3744.graphics.interfaces.IFace;
import cs3744.graphics.interfaces.IPoint;
import cs3744.graphics.interfaces.IVector;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import cs3744.graphics.interfaces.IColor;
import cs3744.graphics.interfaces.IPrimitive;
import cs3744.vectorAlgebra.Operations;
import java.util.ArrayList;
import java.util.List;
import javax.media.opengl.GL2;
import cs3744.graphics.interfaces.ICircle;

/**
 * Constructs circle using triangles or lines, depending whether it is solid or
 * wire frame.
 *
 * @author Your Adam Lech (PID: adaml8)
 * @version 1.0
 */
public class Circle
    extends Primitive
    implements ICircle
{

    private final Integer steps;
    private List<Point>   points;
    private IColor        currentColor;


    // ----------------------------------------------------------
    /**
     * Constructor that creates a unit circle of 50 steps.
     */
    public Circle()
    {

        this(50, null);

    }


    /**
     * Constructor that creates a unit circle of 50 steps with parent.
     *
     * @param parent
     *            - parent of the unit circle
     */
    public Circle(IPrimitive parent)
    {
        this(50, parent);

    }


    // ----------------------------------------------------------
    /**
     * Creates a circle with the given number of steps and a parent.
     *
     * @param steps
     * @param parent
     */
    public Circle(Integer steps, IPrimitive parent)
    {
        super(parent);

        this.points = new ArrayList<Point>();

        this.steps = steps;
    }


    // ----------------------------------------------------------
    /**
     * Constructor that creates a copy of the circle.
     *
     * @param primitiveToCopy
     * @param parent
     */
    public Circle(Circle primitiveToCopy, IPrimitive parent)
    {

        this(primitiveToCopy.getSteps(), parent);
    }


    /**
     * Gets the number of steps.
     *
     * @return number of steps
     */
    @Override
    public Integer getSteps()
    {

        return steps;
    }


    /**
     * Sets the color of the circle
     */
    public void setColor(IColor color)
    {
        currentColor = color;

    }


    /**
     * Paint method. It actually draws circle , either solid or wire frame ,
     * depending how flag is set.
     *
     * @param gl2
     *            - the openGL state machine
     */
    @Override
    public void paint(GL2 gl2)
    {

        float angle = Operations.degreeToRadian(360f / this.steps);

        if (this.points.isEmpty())
        {
            for (int i = 0; i < this.steps; ++i)
            {
                float sin = (float)Math.sin(i * angle);
                float cos = (float)Math.cos(i * angle);
                Point point = new Point(cos, sin, 0, this);
                point.setColor(currentColor);
                points.add(point);
            }
        }

        Point origin = new Point(0f, 0f, 0f, this);
        origin.setColor(currentColor);
        for (int i = 0; i < this.steps; i++)
        {

            Point currentPoint = this.points.get(i);
            Point nextPoint = this.points.get((i + 1) % this.steps);

            if (isSolid())
            {

                Triangle triangle =
                    new Triangle(origin, currentPoint, nextPoint, this);
                triangle.paint(gl2);

            }
            else
            {

                Line line = new Line(currentPoint, nextPoint, this);
                line.setColor(currentColor);
                line.paint(gl2);
            }

        }

    }


    @Override
    public IFace copy()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public IFace copy(IPrimitive parent)
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Boolean isFlat()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void setFlat(boolean isFlat)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public IVector getSurfaceNormal()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void setSurfaceNormal(IVector surfaceNormal)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public Map<IPoint, IVector> getVertexNormalMap()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Set<IPoint> getVertices()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public Collection<IVector> getNormals()
    {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public void addVertex(IPoint vertex)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void addVertex(IPoint vertex, IVector normal)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void removeVertex(IPoint vertex)
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void clearVertices()
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void calculateAndSetVertexNormals()
    {
        // TODO Auto-generated method stub

    }


    @Override
    public void calculateAndSetSurfaceNormal()
    {
        // TODO Auto-generated method stub

    }
}
