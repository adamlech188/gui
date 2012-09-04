package cs3744.vectorAlgebra;


/**
 * The <CODE>Vector4f</CODE> class represents an immutable 3D vector in
 * homogeneous coordinates.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public class Vector4f
        extends Tuple4f {

    /**
     * The i unit vector.
     */
    public static final Vector4f I_UNIT_VECTOR = new Vector4f(1f, 0f, 0f);
    /**
     * The j unit vector.
     */
    public static final Vector4f J_UNIT_VECTOR = new Vector4f(0f, 1f, 0f);
    /**
     * The k unit vector.
     */
    public static final Vector4f K_UNIT_VECTOR = new Vector4f(0f, 0f, 1f);


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
     * Constructs a new 3D vector
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Y coordinate.
     */
    public Vector4f(final float x, final float y, final float z) {

        super(x, y, z, 0f);
    }


    /**
     * Copy constructor.
     * 
     * Creates a new vector from the tuple provided.
     * 
     * @param tuple
     *            The tuple to be copied.
     * @throws IllegalArgumentException
     *             if the tuple provided is not a vector
     */
    public Vector4f(final Tuple4f tuple) {

        super(tuple);


        if ((Math.abs(0f - tuple.getW()) > Tuple4f.DELTA)) {
            throw new IllegalArgumentException(
                    "Provided parameter is not a vector!");
        }
    }


    /**
     * Returns the length of the vector (its Euclidean norm).
     * 
     * @return the length of the vector.
     */
    public float length() {

        return (float) Math.sqrt(this.getX() * this.getX() + this.getY()
                * this.getY() + this.getZ() * this.getZ() + this.getW()
                * this.getW());
    }


    /**
     * Returns the normalized version of this vector.
     * 
     * @return the normalized vector.
     */
    public Vector4f normalizedVector() {

        if (this.length() != 0f) {
            return new Vector4f(this.getX() / this.length(), this.getY()
                    / this.length(), this.getZ() / this.length());
        }
        else {
            return new Vector4f(this);
        }
    }


    /**
     * Calculates the dot product of two vectors.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the dot product.
     */
    public static Float dotProduct(Vector4f lhs, Vector4f rhs) {

        if (lhs == null || rhs == null) {
            throw new IllegalArgumentException(
                    "Cannot calculate dot product without two vectors");
        }

        return (((lhs.getX() * rhs.getX()) + (lhs.getY() * rhs.getY()) + (lhs
                .getZ() * rhs.getZ())));
    }


    /**
     * Calculates the cross product of the two vectors.
     * 
     * @param lhs
     *            the left-hand side.
     * @param rhs
     *            the right-hand side.
     * @return the cross product (a vector orthogonal to both vectors provided).
     */
    public static Vector4f crossProduct(Vector4f lhs, Vector4f rhs) {

        float xValue = lhs.getY() * rhs.getZ() - lhs.getZ() * rhs.getY();
        float yValue = lhs.getZ() * rhs.getX() - lhs.getX() * rhs.getZ();
        float zValue = lhs.getX() * rhs.getY() - lhs.getY() * rhs.getX();

        return new Vector4f(xValue, yValue, zValue);
    }
}
