/**
 * 
 */
package cs3744.graphics.interfaces;


/**
 * Provides a representation of scaling components.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IScaling {

    /**
     * Returns the scale factor for the x-dimension.
     * 
     * @return the scale factor.
     */
    public float getScaleFactorX();


    /**
     * Returns the scale factor for the y-dimension.
     * 
     * @return the scale factor.
     */
    public float getScaleFactorY();


    /**
     * Returns the scale factor for the z-dimension.
     * 
     * @return the scale factor.
     */
    public float getScaleFactorZ();
}
