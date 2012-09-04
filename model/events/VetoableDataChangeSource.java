/**
 *
 */
package cs3744.model.events;

import java.util.LinkedList;
import java.util.List;
import cs3744.model.events.interfaces.IDataChangeEvent;
import cs3744.model.events.interfaces.IVetoableDataChangeListener;
import cs3744.model.events.interfaces.IVetoableDataChangeSource;
import cs3744.model.exceptions.DataChangeVetoException;

/**
 * Creates VetoableDataChangeSource.
 *
 * @author Adam Lech
 * @version 1.0
 */
public class VetoableDataChangeSource
    implements IVetoableDataChangeSource
{
    private List<IVetoableDataChangeListener> eventlisteners;


    // ----------------------------------------------------------
    /**
     * Create a new VetoableDataChangeSource object. Instantiates list of event
     * listeners
     */
    public VetoableDataChangeSource()
    {
        eventlisteners = new LinkedList<IVetoableDataChangeListener>();
    }


    /**
     * Adds listener to the list.
     *
     * @param listener
     *            - listener added to the list.
     */
    @Override
    public synchronized void addVetoableDataChangeListener(
        IVetoableDataChangeListener listener)
    {
        eventlisteners.add(listener);

    }


    /**
     * Notifies listeners about the event.
     *
     * @param listener
     *            - event about which listeners are notified.
     */
    @Override
    public synchronized void removeVetoableDataChangeListener(
        IVetoableDataChangeListener listener)
    {
        eventlisteners.remove(listener);
    }


    /**
     * Notifies all listeners about the event.
     *
     * @param event
     *            - event about which all listeners are notified.
     */
    @Override
    public synchronized void fireVetoableDataChangeEvent(IDataChangeEvent event)
        throws DataChangeVetoException
    {
        for (IVetoableDataChangeListener listener : eventlisteners)
        {
            listener.vetoableDataChange(event);
        }

    }

}
