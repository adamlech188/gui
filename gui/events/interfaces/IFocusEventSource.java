/**
 * 
 */
package cs3744.gui.events.interfaces;


/**
 * The <CODE>IFocusEventSource</CODE> provides a contract for classes that
 * public focus events.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IFocusEventSource {

    /**
     * Adds the listener to the list.
     * 
     * @param listener
     *            the listener to add.
     */
    public abstract void addFocusListener(IFocusListener listener);


    /**
     * Removes the listener from the list.
     * 
     * @param listener
     *            the listener to remove.
     */
    public abstract void removeFocusListener(IFocusListener listener);


    /**
     * Clears the listener list.
     */
    public abstract void clearFocusListeners();


    /**
     * Fires the <CODE>FocusEvent</CODE>
     * 
     * @param event
     *            the event to fire.
     */
    public abstract void fireFocusEvent(IFocusEvent event);

}
