package cs3744.graphics.primitives;


import javax.media.opengl.GL;
import javax.media.opengl.GL2;

import cs3744.graphics.common.Operations;
import cs3744.graphics.interfaces.IPoint;
import cs3744.graphics.interfaces.IPrimitive;
import cs3744.graphics.interfaces.ITransformation;
import cs3744.vectorAlgebra.Point4f;


/**
 * Wrapper around a Point4f for drawing.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class Point
        extends Primitive
        implements IPoint {

    /**
     * The origin.
     */
    public static final IPoint ORIGIN = new Point(Point4f.ORIGIN);


    private final Point4f point;


    @Override
    public Point4f getPoint() {

        return this.point;
    }


    @Override
    public Float getX() {

        return this.point.getX();
    }


    @Override
    public Float getY() {

        return this.point.getY();
    }


    @Override
    public Float getZ() {

        return this.point.getZ();
    }


    @Override
    public Float getW() {

        return this.point.getW();
    }


    /**
     * Constructs a new <CODE>Point</CODE> with the provided point.
     * 
     * @param point
     *            The location of this point.
     */
    public Point(final Point4f point) {

        this(point, null);
    }


    /**
     * Constructs a new <CODE>Point</CODE> with the provided point.
     * 
     * @param point
     *            The location of this point.
     */
    public Point(final IPoint point) {

        this(point, null);
    }


    /**
     * Constructs a new <CODE>Point</CODE> by creating a new point at the
     * coordinates provided.
     * 
     * @param x
     *            The x-coordinate of the new point.
     * @param y
     *            The y coordinate of the new point.
     * @param z
     *            The z-coordinate of the new point.
     */
    public Point(final float x, final float y, final float z) {

        this(x, y, z, null);
    }


    /**
     * Constructs a new <CODE>Point</CODE> by creating a new point at the
     * coordinates provided. Sets the point's parent component to the provided
     * component.
     * 
     * @param x
     *            The x-coordinate of the new point.
     * @param y
     *            The y coordinate of the new point.
     * @param z
     *            The z-coordinate of the new point.
     * @param parent
     *            the parent of this Point.
     */
    public Point(final float x, final float y, final float z,
            final IPrimitive parent) {

        this(new Point4f(x, y, z), parent);
    }


    /**
     * Constructs a new <CODE>Point</CODE> with the provided location and
     * parent.
     * 
     * @param point
     *            The location of this point.
     * @param parent
     *            The parent of this point.
     */
    public Point(Point4f point, IPrimitive parent) {

        super(parent);
        this.point = point;
    }


    /**
     * Copy Constructor.
     * 
     * @param point
     *            The point to be copied.
     * @param parent
     *            The parent of this point.
     */
    public Point(IPoint point, IPrimitive parent) {

        super(point, parent);

        this.point = point.getPoint();
    }


    @Override
    public IPoint copy() {

        return this.copy(null);
    }


    @Override
    public IPoint copy(IPrimitive parent) {

        return new Point(this, parent);
    }


    @Override
    public float[] toArray() {

        return this.point.toArray();
    }


    @Override
    public void paint(GL2 gl2) {


        // System.out.println("\n\n==========================In point draw======================================");
        // Points that are not inside a container need to wrap themselves into a
        // glBegin() glEnd() block/
        if (this.getParent() == null) {

            gl2.glBegin(GL.GL_POINTS);
        }
        if (Primitive.lightingEnabled) {
            this.getMaterial().applyAttribute(gl2);
        }
        else {
            this.getColor().applyAttribute(gl2);
        }
        // System.out.println("Drawing " + this);


        // We retrieve the current transformation which is the result of this
        // object's transformation and its parent's current transformation.
        ITransformation currentTransformation = this.getCurrentTransformation();

        // System.out.println("\n=================CurrentTransformation=========================\n"
        // + currentTransformation);

        // We transform our point by our current transformation.
        IPoint transformedPoint = Operations.applyTransformation(
                currentTransformation, this);


        // Drawing the transformed point.
        // System.out.println("Drawing " + this.point);
        gl2.glVertex4f(transformedPoint.getPoint().getX(), transformedPoint
                .getPoint().getY(), transformedPoint.getPoint().getZ(),
                transformedPoint.getPoint().getW());
        // System.out.println("Transformed: " + transformedPoint);

        // End of the glBegin() glEnd() block.
        if (this.getParent() == null) {
            System.out.println("Single point end");
            gl2.glEnd();
        }
    }


    @Override
    public String toString() {

        return "Point at " + this.point.toString();
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null) {
            if (obj instanceof IPoint) {
                IPoint otherPoint = (IPoint) obj;

                return this.point.equals(otherPoint.getPoint());
            }
        }

        return false;
    }


    @Override
    public int hashCode() {

        return this.point.hashCode();
    }
}
