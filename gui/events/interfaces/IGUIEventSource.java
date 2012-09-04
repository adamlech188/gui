package cs3744.gui.events.interfaces;


/**
 * The <CODE>IGUIEventSource</CODE> interface provides a contract for classes
 * implementing a source of GUIEvents.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IGUIEventSource {

    /**
     * Adds the listener to the list.
     * 
     * @param listener
     *            the listener to add.
     */
    public abstract void addGUIListener(IGUIListener listener);


    /**
     * Removes the listener from the list.
     * 
     * @param listener
     *            the listener to remove.
     */
    public abstract void removeGUIListener(IGUIListener listener);


    /**
     * Clears the listener list.
     */
    public abstract void clearGUIListeners();


    /**
     * Fires the <CODE>GUIEvent</CODE>
     * 
     * @param event
     *            the event to fire.
     */
    public abstract void fireGUIEvent(IGUIEvent event);

}