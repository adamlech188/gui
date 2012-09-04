package cs3744.graphics.interfaces;


import cs3744.vectorAlgebra.Point4f;


/**
 * The <CODE>IPoint</CODE> interface provides a contract for classes
 * implementing points in homogeneous coordinates.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IPoint
        extends IPrimitive {

    /**
     * Returns the location of this point.
     * 
     * @return the location.
     */
    public Point4f getPoint();


    /**
     * Returns the x component.
     * 
     * @return the x component.
     */
    public Float getX();


    /**
     * Returns the y component.
     * 
     * @return the y component.
     */
    public Float getY();


    /**
     * Returns the z component.
     * 
     * @return the z component.
     */
    public Float getZ();


    /**
     * Returns the w component.
     * 
     * @return the w component.
     */
    public Float getW();


    /**
     * Returns an array representation of the enclosed point.
     * 
     * @return the array representation.
     */
    public float[] toArray();
}