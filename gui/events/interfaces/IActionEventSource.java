/**
 * 
 */
package cs3744.gui.events.interfaces;


/**
 * @author Peter J. Radics
 * @version 1.0
 *
 */
public interface IActionEventSource {
    /**
     * Adds the listener to the list.
     * 
     * @param listener
     *            the listener to add.
     */
    public abstract void addActionListener(IActionListener listener);


    /**
     * Removes the listener from the list.
     * 
     * @param listener
     *            the listener to remove.
     */
    public abstract void removeActionListener(IActionListener listener);


    /**
     * Clears the listener list.
     */
    public abstract void clearActionListeners();


    /**
     * Fires the <CODE>ActionEvent</CODE>
     * 
     * @param event
     *            the event to fire.
     */
    public abstract void fireActionEvent(IActionEvent event);
}
