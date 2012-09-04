package cs3744.gui.common;


import java.io.Serializable;

import cs3744.gui.common.interfaces.IFont;


/**
 * The <CODE>Font</CODE> class provides a thin wrapper around
 * <CODE>java.awt.Font</CODE>.
 * 
 * @author Peter J. Radics.
 * @version 1.0
 * 
 */
public class Font
        implements Serializable, IFont {

    private static final long serialVersionUID = 1L;

    /**
     * The default font (SansSerif, bold, 18pt)
     */
    public static final IFont DEFAULT_FONT = new Font();


    private final java.awt.Font font;


    /**
     * Creates a new default font.
     */
    public Font() {

        this("SansSerif", java.awt.Font.BOLD, 18);
    }


    /**
     * Creates the font with the provided name, style, and size.
     * 
     * @param name
     *            the font name.
     * @param style
     *            the font style (e.g., bold)
     * @param size
     *            the font size.
     */
    public Font(String name, int style, int size) {

        this.font = new java.awt.Font(name, style, size);
    }


    /* (non-Javadoc)
     * @see cs3744.gui.solution.common.IFont#getAWTFont()
     */
    @Override
    public java.awt.Font getAWTFont() {

        return this.font;
    }


}
