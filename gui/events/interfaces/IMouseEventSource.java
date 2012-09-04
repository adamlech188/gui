/**
 * 
 */
package cs3744.gui.events.interfaces;


/**
 * @author Peter J. Radics
 * @version 1.0
 *
 */
public interface IMouseEventSource {
    /**
     * Adds the listener to the list.
     * 
     * @param listener
     *            the listener to add.
     */
    public abstract void addMouseListener(IMouseListener listener);


    /**
     * Removes the listener from the list.
     * 
     * @param listener
     *            the listener to remove.
     */
    public abstract void removeMouseListener(IMouseListener listener);


    /**
     * Clears the listener list.
     */
    public abstract void clearMouseListeners();


    /**
     * Fires the <CODE>MouseEvent</CODE>
     * 
     * @param event
     *            the event to fire.
     */
    public abstract void fireMouseEvent(IMouseEvent event);
}
