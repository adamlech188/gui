/**
 * 
 */
package cs3744.graphics.common;


import cs3744.graphics.interfaces.IShear;
import cs3744.vectorAlgebra.ShearBy;


/**
 * Provides a reference implementation of the <CODE>IShear</CODE> interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 */
public class Shear
        implements IShear {


    private final float shearCoefficient;
    private final ShearBy shearBy;


    @Override
    public float getShearComponent() {

        return this.shearCoefficient;
    }


    @Override
    public ShearBy getShearBy() {

        return this.shearBy;
    }


    /**
     * Constructs a new <CODE>Shear</CODE>.
     * 
     * @param shearCoefficient
     *            the shear coefficient.
     * @param shearBy
     *            the shear by instructions.
     */
    public Shear(float shearCoefficient, ShearBy shearBy) {

        this.shearCoefficient = shearCoefficient;
        this.shearBy = shearBy;
    }
}
