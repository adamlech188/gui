/**
 * 
 */
package cs3744.model.events.interfaces;


/**
 * The <CODE>IDataChangeSource</CODE> provides an interface for classes that
 * want to notify other classes of a change in one or multiple of their
 * elements.
 * 
 * Use the implementation of this interface to simplify synchronization of the
 * listener list!
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see IDataChangeListener
 * @see IDataChangeEvent
 */
public interface IDataChangeSource {

    /**
     * Adds the provided listener to the list of listeners.
     * 
     * @param listener
     *            The listener to be added.
     */
    public void addDataChangeListener(IDataChangeListener listener);


    /**
     * Removes the provided listener from the list of listeners.
     * 
     * @param listener
     *            The listener to be removed.
     */
    public void removeDataChangeListener(IDataChangeListener listener);


    /**
     * Notifies all listeners of the event.
     * 
     * @param event
     *            The event.
     */
    public void fireDataChangeEvent(IDataChangeEvent event);

}
