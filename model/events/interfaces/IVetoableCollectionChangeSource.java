/**
 * 
 */
package cs3744.model.events.interfaces;


import cs3744.model.exceptions.CollectionChangeVetoException;


/**
 * The <CODE>IVetoableCollectionChangeSource</CODE> provides an interface for
 * classes that want to provide other classes with the ability to veto a change
 * in one or multiple of their collections.
 * 
 * Use the implementation of this interface to simplify synchronization of the
 * listener list!
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see IVetoableCollectionChangeListener
 * @see ICollectionChangeEvent
 */
public interface IVetoableCollectionChangeSource {

    /**
     * Adds the provided listener to the list of listeners.
     * 
     * @param listener
     *            The listener to be added.
     */
    public void addVetoableCollectionChangeListener(
            IVetoableCollectionChangeListener listener);


    /**
     * Removes the provided listener from the list of listeners.
     * 
     * @param listener
     *            The listener to be removed.
     */
    public void removeVetoableCollectionChangeListener(
            IVetoableCollectionChangeListener listener);


    /**
     * Notifies all listeners of the event.
     * 
     * @param event
     *            The event.
     * @throws CollectionChangeVetoException
     *             if the change is rejected.
     */
    public void fireVetoableElementAddedEvent(ICollectionChangeEvent event)
            throws CollectionChangeVetoException;


    /**
     * Notifies all listeners of the event.
     * 
     * @param event
     *            The event.
     * @throws CollectionChangeVetoException
     *             if the change is rejected.
     */
    public void fireVetoableElementRemovedEvent(ICollectionChangeEvent event)
            throws CollectionChangeVetoException;


    /**
     * Notifies all listeners of the event.
     * 
     * @param event
     *            The event.
     * @throws CollectionChangeVetoException
     *             if the change is rejected.
     */
    public void fireVetoableCollectionClearedEvent(ICollectionChangeEvent event)
            throws CollectionChangeVetoException;

}
