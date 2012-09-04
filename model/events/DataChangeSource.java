package cs3744.model.events;

import cs3744.model.events.interfaces.IDataChangeEvent;
import cs3744.model.events.interfaces.IDataChangeListener;
import cs3744.model.events.interfaces.IDataChangeSource;
import java.util.LinkedList;
import java.util.List;

/**
 * Class that holds DataChangeSource. Provides methods to add listeners and
 * notify them about events.
 *
 * @author Adam Lech
 * @version 1.0
 */
public class DataChangeSource
    implements IDataChangeSource
{
    // Data field
    private List<IDataChangeListener> eventlistener;


    // ----------------------------------------------------------
    /**
     * Constructor method.Create a new DataChangeSource object. Instantiates
     * List of listeners.
     */
    public DataChangeSource()
    {
        eventlistener = new LinkedList<IDataChangeListener>();
    }


    /**
     * Adds listener to the list.
     *
     * @param listener
     *            - listener added to the list.
     */
    @Override
    public synchronized void addDataChangeListener(IDataChangeListener listener)
    {

        eventlistener.add(listener);
    }


    /**
     * Removes data change listener
     *
     * @param listener
     *            - listener added to the list.
     */
    @Override
    public synchronized void removeDataChangeListener(
        IDataChangeListener listener)
    {
        eventlistener.remove(listener);
    }


    /**
     * Notifies all listeners about the event.
     *
     * @param event
     *            - event about which listeners are notified.
     */
    @Override
    public synchronized void fireDataChangeEvent(IDataChangeEvent event)
    {

        for (IDataChangeListener listener : eventlistener)
        {

            listener.dataChanged(event);
        }

    }
}
