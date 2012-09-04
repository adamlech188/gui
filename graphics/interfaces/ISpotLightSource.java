/**
 * 
 */
package cs3744.graphics.interfaces;


/**
 * The <CODE>ISpotLightSource</CODE> interface provides a contract for classes
 * implementing a spot light source with a cutoff angle, a spot exponent for
 * attenuation, a direction, and position.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface ISpotLightSource
        extends ILightSource {

    /**
     * Returns the cutoff angle.
     * 
     * @return the cutoff angle.
     */
    public abstract float getCutoffAngle();


    /**
     * Sets the cutoff angle.
     * 
     * @param cutoffAngle
     *            the cutoff angle.
     */
    public abstract void setCutoffAngle(float cutoffAngle);


    /**
     * Returns the spot exponent.
     * 
     * @return the spot exponent.
     */
    public abstract float getSpotExponent();


    /**
     * Sets the spot exponent.
     * 
     * @param spotExponent
     *            the spot exponent.
     */
    public abstract void setSpotExponent(float spotExponent);


    /**
     * Returns the spot direction.
     * 
     * @return the spot direction.
     */
    public abstract IVector getSpotDirection();


    /**
     * Sets the spot direction.
     * 
     * @param spotDirection
     *            the spot direction.
     */
    public abstract void setSpotDirection(IVector spotDirection);


    /**
     * Returns the spot position.
     * 
     * @return the spot position.
     */
    public abstract IPoint getSpotPosition();


    /**
     * Sets the spot position.
     * 
     * @param spotPosition
     *            the spot direction.
     */
    public abstract void setSpotPosition(IPoint spotPosition);
}
