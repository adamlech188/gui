/**
 * 
 */
package cs3744.graphics.interfaces;


/**
 * The <CODE>IPositionallLightSource</CODE> interface provides a contract for
 * classes implementing positional lights.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IPositionalLightSource
        extends ILightSource {

    /**
     * Returns the position of this light.
     * 
     * @return the position of this light.
     */
    public abstract IPoint getPosition();


    /**
     * Sets the position of this light.
     * 
     * @param position
     *            the position.
     */
    public abstract void setPosition(IPoint position);
}
