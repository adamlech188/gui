/**
 *
 */
package cs3744.model.events;

import java.util.LinkedList;
import java.util.List;
import cs3744.model.events.interfaces.ICollectionChangeEvent;
import cs3744.model.events.interfaces.IVetoableCollectionChangeListener;
import cs3744.model.events.interfaces.IVetoableCollectionChangeSource;
import cs3744.model.exceptions.CollectionChangeVetoException;

/**
 * Creates VetoableCollectionChangeSource.Provides all methods necessary to
 * notify listeners about changed events.
 *
 * @author Adam Lech
 * @version 1.0
 */
public class VetoableCollectionChangeSource
    implements IVetoableCollectionChangeSource
{
    private final List<IVetoableCollectionChangeListener> eventlisteners;


    // ----------------------------------------------------------
    /**
     * Constructor method. Create a new VetoableCollectionChangeSource object.
     * Instantiates a list of event listeners.
     */
    public VetoableCollectionChangeSource()
    {
        eventlisteners = new LinkedList<IVetoableCollectionChangeListener>();
    }


    /**
     * Adds listener to the list.
     *
     * @param listener
     *            - listener added to the list.
     */
    @Override
    public synchronized void addVetoableCollectionChangeListener(
        IVetoableCollectionChangeListener listener)
    {
        eventlisteners.add(listener);

    }


    /**
     * Adds listener to the list.
     *
     * @param listener
     *            - listener added to the list
     */
    @Override
    public synchronized void removeVetoableCollectionChangeListener(
        IVetoableCollectionChangeListener listener)
    {
        eventlisteners.remove(listener);

    }


    /**
     * Notifies listeners about the event.
     *
     * @param event
     *            - event about which listeners are notified.
     */
    @Override
    public synchronized void fireVetoableElementAddedEvent(
        ICollectionChangeEvent event)
        throws CollectionChangeVetoException
    {
        for (IVetoableCollectionChangeListener listener : eventlisteners)
        {
            listener.vetoableElementAdded(event);

        }

    }

    /**
     * Notifies listeners about the event.
     *
     * @param event
     *            - event about which listeners are notified.
     */
    @Override
    public synchronized void fireVetoableElementRemovedEvent(
        ICollectionChangeEvent event)
        throws CollectionChangeVetoException
    {
        for (IVetoableCollectionChangeListener listener : eventlisteners)
        {
            listener.vetoableElementRemoved(event);
        }

    }

    /**
     * Notifies listeners about the event.
     *
     * @param event
     *            - event about which listeners are notified.
     */
    @Override
    public synchronized void fireVetoableCollectionClearedEvent(
        ICollectionChangeEvent event)
        throws CollectionChangeVetoException
    {
        for (IVetoableCollectionChangeListener listener : eventlisteners)
        {
            listener.vetoableCollectionCleared(event);
        }

    }

}
