/**
 * 
 */
package cs3744.graphics.interfaces;


/**
 * The <CODE>IDirectionalLightSource</CODE> interface provides a contract for
 * classes implementing directional lights.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IDirectionalLightSource
        extends ILightSource {

    /**
     * Returns the direction of this light.
     * 
     * @return the direction of this light.
     */
    public abstract IVector getDirection();


    /**
     * Sets the direction of this light.
     * 
     * @param direction
     *            the direction.
     */
    public abstract void setDirection(IVector direction);
}
