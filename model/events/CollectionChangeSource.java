package cs3744.model.events;

import java.util.LinkedList;
import java.util.List;
import cs3744.model.events.interfaces.ICollectionChangeEvent;
import cs3744.model.events.interfaces.ICollectionChangeListener;
import cs3744.model.events.interfaces.ICollectionChangeSource;

/**
 * Class that contains CollectionChangeSource. Provides methods necessary to add
 * listeners and notify them about the events.
 *
 * @author Adam Lech
 * @version 1.0
 */
public class CollectionChangeSource
    implements ICollectionChangeSource
{
    private List<ICollectionChangeListener> eventlistener;


    // ----------------------------------------------------------
    /**
     * Constructor method. Creates a new CollectionChangeSource object.
     * Instantiates an eventlistener list.
     */
    public CollectionChangeSource()
    {
        eventlistener = new LinkedList<ICollectionChangeListener>();
    }


    /**
     * Adds the provided listener to the list of listeners.
     *
     * @param listener
     *            - listener added to the list
     */
    @Override
    public void addCollectionChangeListener(ICollectionChangeListener listener)
    {
        eventlistener.add(listener);

    }


    /**
     * Removes the provided listener from the list of listeners.
     *
     * @param listener
     *            - listener added to the list
     */

    @Override
    public void removeCollectionChangeListener(
        ICollectionChangeListener listener)
    {
        eventlistener.remove(listener);

    }


    /**
     * Notifies all listeners of the event.
     *
     * @param event
     *            -event about which listeners are notified
     */
    @Override
    public void fireElementAddedEvent(ICollectionChangeEvent event)
    {
        for (ICollectionChangeListener listener : eventlistener)
        {
            listener.elementAdded(event);
        }

    }


    /**
     * Notifies all listeners of the event.
     *
     * @param event
     *            -event about which listeners are notified
     */
    @Override
    public void fireElementRemovedEvent(ICollectionChangeEvent event)
    {
        for (ICollectionChangeListener listener : eventlistener)
        {
            listener.elementRemoved(event);
        }

    }


    /**
     * Notifies all listeners of the event.
     *
     * @param event
     *            -event about which listeners are notified
     */
    @Override
    public void fireCollectionClearedEvent(ICollectionChangeEvent event)
    {
        for (ICollectionChangeListener listener : eventlistener)
        {
            listener.collectionCleared(event);
        }

    }

}
