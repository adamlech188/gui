package cs3744.vectorAlgebra;


/**
 * Provides a tuple representation for vectors and points with up to three
 * dimensions.
 * 
 * @author Peter j. Radics
 * @version 1.0
 * 
 */
public class Tuple4f {

    /**
     * Precision for float comparisons.
     */
    public static final float DELTA = 0.0001f;

    private final float x;
    private final float y;
    private final float z;
    private final float w;


    /**
     * Getter for the X coordinate.
     * 
     * @return The X coordinate.
     */
    public float getX() {

        return this.x;
    }


    /**
     * Getter for the Y coordinate.
     * 
     * @return The Y coordinate.
     */
    public float getY() {

        return this.y;
    }


    /**
     * Getter for the Z coordinate.
     * 
     * @return The Z coordinate.
     */
    public float getZ() {

        return this.z;
    }


    /**
     * Getter for the additional coordinate.
     * 
     * @return The additional coordinate.
     */
    public float getW() {

        return this.w;
    }


    /**
     * Constructs a new tuple with the provided values.
     * 
     * @param x
     *            The X coordinate.
     * @param y
     *            The Y coordinate.
     * @param z
     *            The Y coordinate.
     * @param w
     *            The additional coordinate.
     */
    public Tuple4f(final float x, final float y, final float z, final float w) {

        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }


    /**
     * Copy Constructor.
     * 
     * @param tupleToCopy
     *            the tuple to copy.
     */
    public Tuple4f(final Tuple4f tupleToCopy) {

        this(tupleToCopy.getX(), tupleToCopy.getY(), tupleToCopy.getZ(),
                tupleToCopy.getW());
    }


    /**
     * Creates an array representation of the tuple.
     * 
     * @return The array representation of the tuple.
     */
    public float[] toArray() {

        return new float[] { this.x, this.y, this.z, this.w };
    }


    @Override
    public String toString() {


        return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w
                + ")";
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null) {
            if (obj instanceof Tuple4f) {
                Tuple4f otherTuple = (Tuple4f) obj;

                if ((Math.abs(this.x - otherTuple.getX()) < DELTA)
                        && (Math.abs(this.y - otherTuple.getY()) < DELTA)
                        && (Math.abs(this.z - otherTuple.getZ()) < DELTA)
                        && (Math.abs(this.w - otherTuple.getW()) < DELTA)) {
                    return true;
                }
            }
        }

        return false;
    }


    @Override
    public int hashCode() {

        return 5 + new Float(this.x).hashCode() + new Float(this.y).hashCode()
                + new Float(this.z).hashCode() + new Float(this.w).hashCode();
    }

}
