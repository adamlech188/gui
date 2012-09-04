package cs3744.graphics.interfaces;


/**
 * Provides an interface for a drawable object with a border.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IPrimitiveWithBorder
        extends IPrimitive {

    /**
     * Returns the border width.
     * 
     * @return the border width.
     */
    public float getBorderWidth();
}
