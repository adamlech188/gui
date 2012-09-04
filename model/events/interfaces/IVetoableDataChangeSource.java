/**
 * 
 */
package cs3744.model.events.interfaces;


import cs3744.model.exceptions.DataChangeVetoException;


/**
 * The <CODE>IVetoableDataChangeSource</CODE> provides an interface for classes
 * that want to provide other classes with the ability to veto a change in one
 * or multiple of their elements.
 * 
 * Use the implementation of this interface to simplify synchronization of the
 * listener list!
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see IVetoableDataChangeListener
 * @see IDataChangeEvent
 */
public interface IVetoableDataChangeSource {

    /**
     * Adds the provided listener to the list of listeners.
     * 
     * @param listener
     *            The listener to be added.
     */
    public void addVetoableDataChangeListener(
            IVetoableDataChangeListener listener);


    /**
     * Removes the provided listener from the list of listeners.
     * 
     * @param listener
     *            The listener to be removed.
     */
    public void removeVetoableDataChangeListener(
            IVetoableDataChangeListener listener);


    /**
     * Notifies all listeners of the event.
     * 
     * @param event
     *            The event.
     * @throws DataChangeVetoException
     *             if one of the listeners rejects the change.
     */
    public void fireVetoableDataChangeEvent(IDataChangeEvent event)
            throws DataChangeVetoException;

}
