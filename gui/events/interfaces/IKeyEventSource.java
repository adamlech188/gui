/**
 * 
 */
package cs3744.gui.events.interfaces;


/**
 * @author Peter J. Radics
 * @version 1.0
 *
 */
public interface IKeyEventSource {
    /**
     * Adds the listener to the list.
     * 
     * @param listener
     *            the listener to add.
     */
    public abstract void addKeyListener(IKeyListener listener);


    /**
     * Removes the listener from the list.
     * 
     * @param listener
     *            the listener to remove.
     */
    public abstract void removeKeyListener(IKeyListener listener);


    /**
     * Clears the listener list.
     */
    public abstract void clearKeyListeners();


    /**
     * Fires the <CODE>KeyEvent</CODE>
     * 
     * @param event
     *            the event to fire.
     */
    public abstract void fireKeyEvent(IKeyEvent event);
}
