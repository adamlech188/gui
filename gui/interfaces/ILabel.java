/**
 * 
 */
package cs3744.gui.interfaces;


import cs3744.gui.common.interfaces.IGraphics;


/**
 * The <CODE>ILabel</CODE> interface provides a contract for classes specifying
 * labels.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface ILabel
        extends IComponent {

    /**
     * Returns the text alignment of this label.
     * 
     * @return the text alignment.
     */
    public abstract TextAlignment getTextAlignment();


    /**
     * Sets the text alignment of this label.
     * 
     * @param textAlignment
     *            the text alignment to set.
     */
    public abstract void setTextAlignment(TextAlignment textAlignment);


    /**
     * Returns the text of this label.
     * 
     * @return the text.
     */
    public abstract String getText();


    /**
     * Sets the text of this label.
     * 
     * @param text
     *            the text to set.
     */
    public abstract void setText(String text);


    /**
     * The Label draws a border around itself (given that the borderWidth is
     * greater than zero). Furthermore, it renders its label text offset by the
     * borderWidth.
     * 
     * @param graphics
     *            the graphics context.
     */
    @Override
    public abstract void paintBody(IGraphics graphics);
}
