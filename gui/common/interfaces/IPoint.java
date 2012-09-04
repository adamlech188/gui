package cs3744.gui.common.interfaces;


import cs3744.vectorAlgebra.Point4f;


/**
 * The <CODE>IPoint</CODE> interface provides a contract for classes
 * implementing points in 2D.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IPoint {

    /**
     * Returns the x coordinate.
     * 
     * @return the x coordinate.
     */
    public abstract int getX();


    /**
     * Returns the y coordinate.
     * 
     * @return the y coordinate.
     */
    public abstract int getY();


    /**
     * Returns the wrapped Point4f.
     * 
     * @return the wrapped point.
     */
    public abstract Point4f getPoint();

}