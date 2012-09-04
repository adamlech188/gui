/**
 * 
 */
package cs3744.graphics.interfaces;


/**
 * Provides an interface for a rotation in axis-angle notation.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IRotation {

    /**
     * Returns the rotation axis.
     * 
     * @return the rotation axis.
     */
    public IVector getRotationAxis();


    /**
     * Returns the rotation angle.
     * 
     * @return the rotation angle.
     */
    public float getRotationAngle();
}
