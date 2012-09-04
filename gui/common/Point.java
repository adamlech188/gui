package cs3744.gui.common;


import cs3744.gui.common.interfaces.IPoint;
import cs3744.vectorAlgebra.Point4f;


/**
 * The <CODE>Point</CODE> class provides a wrapper around the
 * <CODE>Point4f</CODE> class for 2D drawing.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * 
 * @see Point4f
 */
public class Point
        implements IPoint {

    private final Point4f point;


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.solution.common.IPoint#getX()
     */
    @Override
    public int getX() {

        return (int) this.point.getX();
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.solution.common.IPoint#getY()
     */
    @Override
    public int getY() {

        return (int) this.point.getY();
    }


    /*
     * (non-Javadoc)
     * 
     * @see cs3744.gui.solution.common.IPoint#getPoint()
     */
    @Override
    public Point4f getPoint() {

        return this.point;
    }


    /**
     * Constructs a new point at (0, 0)
     */
    public Point() {

        this(Point4f.ORIGIN);
    }


    /**
     * Constructs a new point with the x and y coordinates of the provided
     * <CODE>Point4f</CODE>.
     * 
     * @param point
     *            the point containing the x and y coordinates.
     */
    public Point(Point4f point) {

        this((int) point.getX(), (int) point.getY());
    }


    /**
     * Copy constructor.
     * 
     * @param point
     *            the point to copy.
     */
    public Point(IPoint point) {

        this(point.getX(), point.getY());
    }


    /**
     * Constructs a new point with the provided x and y coordinates.
     * 
     * @param x
     *            the x coordinate.
     * @param y
     *            the y coordinate.
     */
    public Point(int x, int y) {

        this.point = new Point4f(x, y, 0f);
    }


    @Override
    public String toString() {

        return "(" + this.getX() + ", " + this.getY() + ")";
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null && obj instanceof IPoint) {
            IPoint otherPoint = (IPoint) obj;
            return this.getPoint().equals(otherPoint.getPoint());
        }
        return false;
    }


    @Override
    public int hashCode() {

        return this.getPoint().hashCode();
    }
}
