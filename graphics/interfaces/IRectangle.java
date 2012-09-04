package cs3744.graphics.interfaces;


/**
 * The <CODE>IRectangle</CODE> interface provides a contract for classes
 * implementing a rectangle.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IRectangle
        extends IFace {

    /**
     * Returns the first point.
     * 
     * @return the first point.
     */
    public abstract IPoint getFirstPoint();


    /**
     * Returns the second point.
     * 
     * @return the second point.
     */
    public abstract IPoint getSecondPoint();


    /**
     * Returns the third point.
     * 
     * @return the third point.
     */
    public abstract IPoint getThirdPoint();


    /**
     * Returns the fourth point.
     * 
     * @return the fourth point.
     */
    public abstract IPoint getFourthPoint();

}