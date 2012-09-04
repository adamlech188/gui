package cs3744.graphics.common;


import cs3744.vectorAlgebra.Tuple4f;


/**
 * Thin wrapper around a Tuple4f.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class Tuple {


    private final Tuple4f tuple;


    /**
     * Returns the tuple.
     * 
     * @return the tuple.
     */
    public Tuple4f getTuple() {

        return this.tuple;
    }


    /**
     * Returns the x component.
     * 
     * @return the x component.
     */
    public Float getX() {

        return this.tuple.getX();
    }


    /**
     * Returns the y component.
     * 
     * @return the y component.
     */
    public Float getY() {

        return this.tuple.getY();
    }


    /**
     * Returns the z component.
     * 
     * @return the z component.
     */
    public Float getZ() {

        return this.tuple.getZ();
    }


    /**
     * Returns the w component.
     * 
     * @return the w component.
     */
    public Float getW() {

        return this.tuple.getW();
    }


    /**
     * Constructs a new <CODE>Tuple</CODE> by creating a new tuple with the
     * components provided.
     * 
     * @param x
     *            The x-component of the new tuple.
     * @param y
     *            The y-component of the new tuple.
     * @param z
     *            The z-component of the new tuple.
     * @param w
     *            The homogeneous component of the tuple.
     */
    public Tuple(final float x, final float y, final float z, float w) {

        this(new Tuple4f(x, y, z, w));
    }


    /**
     * Constructs a new <CODE>Tuple</CODE> with the provided direction.
     * 
     * @param tuple
     *            The tuple.
     */
    public Tuple(Tuple4f tuple) {

        this.tuple = tuple;
    }


    /**
     * Copy Constructor.
     * 
     * @param tuple
     *            The vector to be copied.
     */
    public Tuple(Tuple tuple) {

        this.tuple = tuple.getTuple();

    }


    @Override
    public String toString() {

        return this.tuple.toString();
    }


    @Override
    public boolean equals(Object obj) {

        if (obj != null) {
            if (obj instanceof Tuple) {
                Tuple otherTuple = (Tuple) obj;

                return this.tuple.equals(otherTuple.getTuple());
            }
        }

        return false;
    }


    @Override
    public int hashCode() {

        return this.tuple.hashCode();
    }

}
