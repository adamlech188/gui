/**
 * 
 */
package cs3744.graphics.interfaces;


import cs3744.vectorAlgebra.ShearBy;


/**
 * Provides a representation of a shearing component along with an identifier of
 * the shear.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IShear {

    /**
     * Returns the shear component.
     * 
     * @return the shear component.
     */
    public float getShearComponent();


    /**
     * Returns the identifier of the shear
     * 
     * @return the identifier.
     */
    public ShearBy getShearBy();
}
