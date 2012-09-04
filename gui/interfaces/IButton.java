/**
 * 
 */
package cs3744.gui.interfaces;


import cs3744.gui.common.interfaces.IGraphics;
import cs3744.gui.events.interfaces.IActionEventSource;
import cs3744.gui.events.interfaces.IGUIEvent;


/**
 * The <CODE>IButton</CODE> interface provides a contract for classes
 * implementing buttons.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IButton
        extends IComponent, IActionEventSource {


    /**
     * Returns whether or not the text field has keyboard focus.
     * 
     * @return <CODE>true</CODE> if the text field has keyboard focus;
     *         <CODE>false</CODE> otherwise.
     */
    public abstract boolean hasFocus();


    /**
     * Sets whether the text field has keyboard focus. Invalidates the button.
     * 
     * @param hasFocus
     *            the new value.
     */
    public abstract void setFocus(Boolean hasFocus);


    /**
     * Returns the button's label text.
     * 
     * @return the label text.
     */
    public abstract String getLabelText();


    /**
     * Sets the button's label text. Invalidates the button.
     * 
     * @param labelText
     *            the label text to set
     */
    public abstract void setLabelText(String labelText);


    /**
     * Returns whether or not the button is pressed.
     * 
     * @return <CODE>true</CODE>, if the button is pressed; <CODE>false</CODE>
     *         otherwise.
     */
    public abstract boolean isPressed();


    /**
     * Sets the value of the isPressed flag. Invalidates the button.
     * 
     * @param pressed
     *            the value to set.
     */
    public abstract void setPressed(boolean pressed);


    /**
     * Processes GUI events.
     * 
     * In case of a key event, the button only processes events if it is
     * focused. If it is, the button checks for a release of the ENTER key and
     * fires an action event.
     * 
     * In case of a mouse event, the button listens for MOUSE_CLICKED.
     * MOUSE_PRESSED, and MOUSE_RELEASED events. In case of MOUSE_CLICKED, if
     * the button did not have focus, it sets its own focus and fires a
     * FOCUS_GAINED event. Regardless of focus change, it also fires an action
     * event. In case of a MOUSE_PRESSED event, the button sets its pressed
     * state to true. In case of a MOUSE_RELEASED event, the button sets its
     * pressed state to false.
     * 
     * In case of a focus event, the button only cares about FOCUS_LOST, as it
     * will send the other type itself. In case of a loss of focus, the button
     * sets its focus to false should it currently have the focus and be
     * identified correctly as the opposite party.
     * 
     * @param event
     *            the event to process.
     */
    @Override
    public void processGUIEvent(IGUIEvent event);


    /**
     * The button consists of two things: a frame and a label.
     * 
     * The button will paint a frame of borderWidth around itself using
     * quadrilaterals. The top and left sides of the frame have different colors
     * than the botom and right sides. depending on the state of the button.
     * 
     * If the button is pressed, top and left frame edges scale the backround
     * color by 0.3, bottom and right 0.6.
     * 
     * If the button has focus, top and left frame edges scale the backround
     * color by 0.5, bottom and right 0.4.
     * 
     * If the button has neither focus nor is pressed, top and left frame edges
     * scale the backround color by 0.6, bottom and right 0.3.
     * 
     * 
     * The button's label will be drawn in the foreground color offset by the
     * width of the border.
     * 
     * @param graphics
     *            the graphics context.
     */
    @Override
    public void paintBody(IGraphics graphics);

}
