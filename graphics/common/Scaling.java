/**
 * 
 */
package cs3744.graphics.common;


import cs3744.graphics.interfaces.IScaling;


/**
 * Provides a reference implementation of the <CODE>IScaling</CODE> interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class Scaling
        implements IScaling {


    private final float scaleFactorX;
    private final float scaleFactorY;
    private final float scaleFactorZ;


    @Override
    public float getScaleFactorX() {

        return this.scaleFactorX;
    }


    @Override
    public float getScaleFactorY() {

        return this.scaleFactorY;
    }


    @Override
    public float getScaleFactorZ() {

        return this.scaleFactorZ;
    }


    /**
     * Constructs a new <CODE>Scaling</CODE>.
     * 
     * @param scaleFactorX
     *            the x scale factor.
     * @param scaleFactorY
     *            the y scale factor.
     * @param scaleFactorZ
     *            the z scale factor.
     */
    public Scaling(float scaleFactorX, float scaleFactorY, float scaleFactorZ) {

        this.scaleFactorX = scaleFactorX;
        this.scaleFactorY = scaleFactorY;
        this.scaleFactorZ = scaleFactorZ;
    }


    @Override
    public String toString() {

        return "Sx: " + this.scaleFactorX + ", Sy: " + this.scaleFactorY
                + ", Sz: " + this.scaleFactorZ;
    }
}
