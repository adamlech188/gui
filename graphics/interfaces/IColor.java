/**
 * 
 */
package cs3744.graphics.interfaces;


/**
 * Provides an interface for RGBA Color classes.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IColor
        extends IAttribute {

    /**
     * Returns the red color component.
     * 
     * @return the red color component.
     */
    public float getRedComponent();


    /**
     * Returns the green color component.
     * 
     * @return the green color component.
     */
    public float getGreenComponent();


    /**
     * Returns the blue color component.
     * 
     * @return the blue color component.
     */
    public float getBlueComponent();


    /**
     * Returns the alpha component.
     * 
     * @return the alhpa component.
     */
    public float getAlphaComponent();


    /**
     * Returns an array representation of the color.
     * 
     * @return an array representation of the color.
     */
    public float[] toArray();
}
