package cs3744.gui.events.interfaces;

import java.util.EventListener;


/**
 * The <CODE>IMouseListener</CODE> interface provides a contract for classes
 * handling mouse events.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IMouseListener
        extends EventListener {

    /**
     * Invoked when the mouse button has been clicked (pressed and released) on
     * a component.
     * 
     * @param mouseEvent
     *            the mouse event.
     */
    public abstract void mouseClicked(IMouseEvent mouseEvent);


    /**
     * Invoked when the mouse enters a component.
     * 
     * @param mouseEvent
     *            the mouse event.
     */
    public abstract void mouseEntered(IMouseEvent mouseEvent);


    /**
     * Invoked when the mouse exits a component.
     * 
     * @param mouseEvent
     *            the mouse event.
     */
    public abstract void mouseExited(IMouseEvent mouseEvent);


    /**
     * Invoked when a mouse button has been pressed on a component.
     * 
     * @param mouseEvent
     *            the mouse event.
     */
    public abstract void mousePressed(IMouseEvent mouseEvent);


    /**
     * Invoked when a mouse button has been released on a component.
     * 
     * @param mouseEvent
     *            the mouse event.
     */
    public abstract void mouseReleased(IMouseEvent mouseEvent);

}
