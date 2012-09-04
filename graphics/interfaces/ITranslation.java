/**
 * 
 */
package cs3744.graphics.interfaces;


/**
 * Provides an interface for the translation of an object.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface ITranslation {

    /**
     * The translation in the x-dimension.
     * 
     * @return the translation in the x-dimension.
     */
    public float getXTranslation();


    /**
     * The translation in the y-dimension.
     * 
     * @return the translation in the y-dimension.
     */
    public float getYTranslation();


    /**
     * The translation in the x-dimension.
     * 
     * @return the translation in the y-dimension.
     */
    public float getZTranslation();
}
