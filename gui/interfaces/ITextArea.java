/**
 * 
 */
package cs3744.gui.interfaces;


/**
 * The <CODE>ITextField</CODE> interface provides a contract for classes
 * implementing a text area.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface ITextArea
        extends IComponent {

    /**
     * Returns the text of the text area.
     * 
     * @return the text.
     */
    public abstract String getText();


    /**
     * Sets the text of the text area.
     * 
     * @param text
     *            the text.
     */
    public abstract void setText(String text);


    /**
     * Appends the text to the text area's text.
     * 
     * @param text
     *            the text to append.
     */
    public abstract void appendText(String text);
}
