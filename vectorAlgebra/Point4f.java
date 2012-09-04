package cs3744.vectorAlgebra;


/**
 * The <CODE>Point4f</CODE> class represents an immutable 3D point in
 * homogeneous coordinates.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public class Point4f
        extends Tuple4f {

    /**
     * The origin.
     */
    public static final Point4f ORIGIN = new Point4f(0f, 0f, 0f);


    @Override
    public float getX() {

        return super.getX();
    }


    @Override
    public float getY() {

        return super.getY();
    }


    @Override
    public float getZ() {

        return super.getZ();
    }


    @Override
    public float getW() {

        return super.getW();
    }


    /**
     * Constructs a new 3D point.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Y coordinate.
     */
    public Point4f(final float x, final float y, final float z) {

        super(x, y, z, 1f);
    }


    /**
     * Copy constructor.
     * 
     * Creates a new point from the tuple provided.
     * 
     * @param tuple
     *            The tuple to be copied.
     * @throws IllegalArgumentException
     *             if the tuple provided is not a point
     */
    public Point4f(final Tuple4f tuple) {

        super(tuple);

        if ((Math.abs(0f - tuple.getW()) < Tuple4f.DELTA)) {
            throw new IllegalArgumentException(
                    "Provided parameter is not a point!");
        }
    }
}
