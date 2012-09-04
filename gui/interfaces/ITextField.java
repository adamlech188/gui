package cs3744.gui.interfaces;


import cs3744.gui.events.interfaces.IActionEventSource;
import cs3744.gui.events.interfaces.IGUIEvent;


/**
 * The <CODE>ITextField</CODE> interface provides a contract for classes
 * implementing a text field.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface ITextField
        extends IComponent, IActionEventSource {


    /**
     * Returns whether or not the text field has keyboard focus.
     * 
     * @return <CODE>true</CODE> if the text field has keyboard focus;
     *         <CODE>false</CODE> otherwise.
     */
    public abstract boolean hasFocus();


    /**
     * Sets whether the text field has keyboard focus. Invalidates the text
     * field.
     * 
     * @param hasFocus
     *            the new value.
     */
    public abstract void setFocus(Boolean hasFocus);


    /**
     * Returns the text of the text field.
     * 
     * @return the text.
     */
    public abstract String getText();


    /**
     * Sets the text of the text field. Invalidates the text field.
     * 
     * @param text
     *            the text.
     */
    public abstract void setText(String text);


    /**
     * Processes GUI events.
     * 
     * In case of a key event, the TextField listens for PRESSED and RELEASED
     * events. In case of a PRESSED event, the TextField appends the character
     * if the event contains TEXT. The TextField deletes the last character in
     * case of a RELEASED DELETE key. The TextField fires an action event in
     * case the RETURN key was released.
     * 
     * In case of a mouse event, the TextField only responds to MOUSE_CLICKED,
     * in which case it fires a FOCUS_GAINED event.
     * 
     * In case of a focus event, the TextField only responds to FOCUS_LOST if it
     * has focus and is identified as the component that lost focus.
     * 
     * @param event
     *            the event to process.
     */
    @Override
    public abstract void processGUIEvent(IGUIEvent event);
}
