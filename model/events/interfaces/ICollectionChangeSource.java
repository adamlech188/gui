/**
 * 
 */
package cs3744.model.events.interfaces;


/**
 * The <CODE>ICollectionChangeSource</CODE> provides an interface for classes
 * that want to notify other classes of a change in one or multiple of their
 * collections.
 * 
 * Use the implementation of this interface to simplify synchronization of the
 * listener list!
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see ICollectionChangeListener
 * @see ICollectionChangeEvent
 */
public interface ICollectionChangeSource {

    /**
     * Adds the provided listener to the list of listeners.
     * 
     * @param listener
     *            The listener to be added.
     */
    public void addCollectionChangeListener(ICollectionChangeListener listener);


    /**
     * Removes the provided listener from the list of listeners.
     * 
     * @param listener
     *            The listener to be removed.
     */
    public void removeCollectionChangeListener(
            ICollectionChangeListener listener);


    /**
     * Notifies all listeners of the event.
     * 
     * @param event
     *            The event.
     */
    public void fireElementAddedEvent(ICollectionChangeEvent event);


    /**
     * Notifies all listeners of the event.
     * 
     * @param event
     *            The event.
     */
    public void fireElementRemovedEvent(ICollectionChangeEvent event);


    /**
     * Notifies all listeners of the event.
     * 
     * @param event
     *            The event.
     */
    public void fireCollectionClearedEvent(ICollectionChangeEvent event);

}
