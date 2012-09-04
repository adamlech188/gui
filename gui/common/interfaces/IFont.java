package cs3744.gui.common.interfaces;


import java.awt.Font;


/**
 * The <CODE>IFont</CODE> interface provides a contract for classes that provide
 * a thin wrapper around a java.awt.Font.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IFont {

    /**
     * Returns the wrapped <CODE>java.awt.Font</CODE>
     * 
     * @return the wrapped font.
     */
    public abstract Font getAWTFont();

}