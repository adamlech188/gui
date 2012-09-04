/**
 * 
 */
package cs3744.graphics.common;


import cs3744.graphics.interfaces.ITranslation;


/**
 * A reference implementation of the <CODE>ITranslation</CODE> interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class Translation
        implements ITranslation {


    private final float xTranslation;
    private final float yTranslation;
    private final float zTranslation;


    @Override
    public float getXTranslation() {

        return this.xTranslation;
    }


    @Override
    public float getYTranslation() {

        return this.yTranslation;
    }


    @Override
    public float getZTranslation() {

        return this.zTranslation;
    }


    /**
     * Constructs a new <CODE>Translation</CODE> that represents no translation.
     */
    public Translation() {

        this(0, 0, 0);
    }


    /**
     * Constructs a new <CODE>Translation</CODE>.
     * 
     * @param xTranslation
     *            the x-translation.
     * @param yTranslation
     *            the y-translation.
     * @param zTranslation
     *            the z-translation.
     */
    public Translation(float xTranslation, float yTranslation,
            float zTranslation) {

        this.xTranslation = xTranslation;
        this.yTranslation = yTranslation;
        this.zTranslation = zTranslation;
    }


    @Override
    public String toString() {

        return "Tx: " + this.xTranslation + ", Ty: " + this.yTranslation
                + ", Tz: " + this.zTranslation;
    }
}
