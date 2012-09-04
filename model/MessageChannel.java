/**
 *
 */
package cs3744.model;

import cs3744.model.events.CollectionChangeEvent;
import cs3744.model.events.CollectionChangeSource;
import cs3744.model.events.interfaces.ICollectionChangeEvent;
import cs3744.model.events.interfaces.ICollectionChangeListener;
import cs3744.model.events.interfaces.ICollectionChangeSource;
import cs3744.model.events.interfaces.IVetoableCollectionChangeListener;
import cs3744.model.events.interfaces.IVetoableCollectionChangeSource;
import cs3744.model.events.solution.CollectionChangeOperation;
import cs3744.model.events.solution.VetoableCollectionChangeSource;
import cs3744.model.exceptions.CollectionChangeVetoException;
import cs3744.model.interfaces.IMessage;
import cs3744.model.interfaces.IMessageChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class that contains all data necessary to store content and other data
 * relevant for the given message.
 *
 * @author Adam Lech
 * @version 1.0
 */
public class MessageChannel
    implements IMessageChannel
{

    private final List<IMessage>                  messages;
    private final String                          name;
    private final IVetoableCollectionChangeSource vetoablecollectionchangesource;
    private final ICollectionChangeSource         collectionchangesource;


    // private Lock lockMessages ;

    // ----------------------------------------------------------
    /**
     * Create a new MessageChannel object with the provided name.
     *
     * @param name
     */
    public MessageChannel(String name)
    {
        if (name == null)
        {
            throw new IllegalArgumentException(
                "Message Channel has to have name !");
        }
        this.name = name;
        messages = new ArrayList<IMessage>();
        vetoablecollectionchangesource = new VetoableCollectionChangeSource();
        collectionchangesource = new CollectionChangeSource();
    }


    /**
     * Adds the provided listener to the list of listeners.
     *
     * @param listener
     *            added to the list
     */
    @Override
    public void addVetoableCollectionChangeListener(
        IVetoableCollectionChangeListener listener)
    {
        vetoablecollectionchangesource
            .addVetoableCollectionChangeListener(listener);

    }


    /**
     * Adds the provided listener to the list of listeners.
     *
     * @param listener
     *            removed from the list
     */
    @Override
    public void removeVetoableCollectionChangeListener(
        IVetoableCollectionChangeListener listener)
    {
        vetoablecollectionchangesource
            .removeVetoableCollectionChangeListener(listener);

    }


    /**
     * Notifies all listeners of the event.
     *
     * @param event
     *            about which listeners are notified
     * @throws CollectionChangeVetoException
     */

    @Override
    public void fireVetoableElementAddedEvent(ICollectionChangeEvent event)
        throws CollectionChangeVetoException
    {
        vetoablecollectionchangesource.fireVetoableElementAddedEvent(event);

    }


    /**
     * Notifies all listeners of the event.
     *
     * @param event
     *            about which listeners are notified
     * @throws CollectionChangeVetoException
     */

    @Override
    public void fireVetoableElementRemovedEvent(ICollectionChangeEvent event)
        throws CollectionChangeVetoException
    {
        vetoablecollectionchangesource.fireVetoableElementRemovedEvent(event);

    }


    /**
     * Notifies all listeners of the event.
     *
     * @param event
     *            about which listeners are notified
     * @throws CollectionChangeVetoException
     */

    @Override
    public void fireVetoableCollectionClearedEvent(ICollectionChangeEvent event)
        throws CollectionChangeVetoException
    {
        vetoablecollectionchangesource
            .fireVetoableCollectionClearedEvent(event);

    }


    /**
     * Adds the provided listener to the list of listeners.
     *
     * @param listener
     *            that is added to the list
     */

    @Override
    public void addCollectionChangeListener(ICollectionChangeListener listener)
    {
        collectionchangesource.addCollectionChangeListener(listener);

    }


    /**
     * Adds the provided listener to the list of listeners.
     *
     * @param listener
     *            that is added to the list
     */
    @Override
    public void removeCollectionChangeListener(
        ICollectionChangeListener listener)
    {
        collectionchangesource.removeCollectionChangeListener(listener);

    }


    /**
     * Notifies all listeners of the event.
     *
     * @param event
     *            about which listeners are notified.
     */
    @Override
    public void fireElementAddedEvent(ICollectionChangeEvent event)
    {
        collectionchangesource.fireElementAddedEvent(event);

    }


    /**
     * Notifies all listeners of the event.
     *
     * @param event
     *            about which listeners are notified
     */
    @Override
    public void fireElementRemovedEvent(ICollectionChangeEvent event)
    {
        collectionchangesource.fireElementRemovedEvent(event);

    }


    /**
     * Notifies all listeners of the event.
     *
     * @param event
     *            about which listeners are notified
     */

    @Override
    public void fireCollectionClearedEvent(ICollectionChangeEvent event)
    {
        collectionchangesource.fireCollectionClearedEvent(event);

    }


    /**
     * Getter for the name of message channel.
     *
     * @return String name
     */
    @Override
    public String getName()
    {
        return name;
    }


    /**
     * Adds the message to the channel's list of messages.
     *
     * @param message
     *            added to the list
     * @throws CollectionChangeVetoException
     */

    @Override
    public synchronized void addMessage(IMessage message)
        throws CollectionChangeVetoException
    {

        CollectionChangeOperation operation = CollectionChangeOperation.ADD;

        CollectionChangeEvent event;
        event = new CollectionChangeEvent(this, "messages", message, operation);
        this.fireVetoableElementAddedEvent(event);

        messages.add(message);

        this.fireElementAddedEvent(event);

    }


    /**
     * Getter for the list of messages.
     *
     * @return List of messages
     */
    @Override
    public List<IMessage> getMessages()
    {
        return Collections.unmodifiableList(messages);
    }


    /**
     * Gets the string representation of the Message Channel
     *
     * @return String name
     */
    public String toString()
    {
        return name;
    }

}
