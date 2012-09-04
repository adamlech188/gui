package cs3744.model;

import cs3744.model.events.solution.CollectionChangeOperation;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import cs3744.model.events.CollectionChangeEvent;
import cs3744.model.events.CollectionChangeSource;
import cs3744.model.events.VetoableCollectionChangeSource;
import cs3744.model.events.interfaces.ICollectionChangeEvent;
import cs3744.model.events.interfaces.ICollectionChangeListener;
import cs3744.model.events.interfaces.ICollectionChangeSource;
import cs3744.model.events.interfaces.IVetoableCollectionChangeListener;
import cs3744.model.events.interfaces.IVetoableCollectionChangeSource;
import cs3744.model.exceptions.CollectionChangeVetoException;
import cs3744.model.MessageChannel;
import cs3744.model.interfaces.ILobby;
import cs3744.model.interfaces.IMessageChannel;
import cs3744.model.interfaces.IPlayer;

/**
 * This class stores the players. It creates an instance of message Channel that
 * regulates the flow of messages issued by players.
 *
 * @author Adam Lech
 * @version 1.0
 */
public class Lobby
    implements ILobby
{

    private final IMessageChannel                 messagechannel;
    private final List<IPlayer>                   players;
    private final IVetoableCollectionChangeSource vetoableCollectionChangeSource;
    private final Lock                            playersLock;

    private final ICollectionChangeSource         collectionChangeSource;


    // ----------------------------------------------------------
    /**
     * Constructor that creates instances of players list and message channel.
     * Also lists of vetoableCollectionChangeSource and collectionChangeSource
     * are created.
     */
    public Lobby()
    {
        messagechannel = new MessageChannel("Lobby Message Channel");
        players = new LinkedList<IPlayer>();
        vetoableCollectionChangeSource = new VetoableCollectionChangeSource();
        collectionChangeSource = new CollectionChangeSource();
        playersLock = new ReentrantLock();

    }


    /**
     * Getter for message channel. Obtains instance of messagechannel.
     *
     * @return message channel
     */
    public IMessageChannel getMessageChannel()
    {
        return messagechannel;
    }


    /**
     * Obtains list of players.
     *
     * @return List of players.
     */
    @Override
    public List<IPlayer> getPlayers()
    {

        return Collections.unmodifiableList(players);
    }


    /**
     * Adds players to the list.
     *
     * @param player
     *            - player added to the list
     * @throws CollectionChangeVetoException
     */

    @Override
    public void addPlayer(IPlayer player)
        throws CollectionChangeVetoException
    {

        CollectionChangeOperation operation = CollectionChangeOperation.ADD;

        CollectionChangeEvent event =
            new CollectionChangeEvent(this, "players", player, operation);

        try
        {
            playersLock.lock();

            this.fireVetoableElementAddedEvent(event);
            players.add(player);
            this.fireElementAddedEvent(event);

        }
        finally
        {
            playersLock.unlock();
        }

    }


    /**
     * Removes player from the list.
     *
     * @param player
     *            removed from the list
     * @throws CollectionChangeVetoException
     */

    @Override
    public void removePlayer(IPlayer player)
        throws CollectionChangeVetoException
    {

        CollectionChangeOperation operation = CollectionChangeOperation.REMOVE;

        CollectionChangeEvent event =
            new CollectionChangeEvent(this, "players", player, operation);
        try
        {
            playersLock.lock();
            this.fireVetoableElementRemovedEvent(event);
            players.remove(player);

            this.fireElementRemovedEvent(event);
        }
        finally
        {
            playersLock.unlock();
        }
    }


    /**
     * Clears list of players.
     *
     * @throws CollectionChangeVetoException
     */

    @Override
    public void clearPlayers()
        throws CollectionChangeVetoException
    {
        try
        {
            playersLock.lock();

            CollectionChangeOperation operation =
                CollectionChangeOperation.CLEAR;

            CollectionChangeEvent event =
                new CollectionChangeEvent(this, "players", players, operation);

            this.fireVetoableCollectionClearedEvent(event);

            players.clear();
            this.fireCollectionClearedEvent(event);

        }
        finally
        {
            playersLock.unlock();
        }
    }


    /**
     * Adds vetoableCollectionChangeListener
     *
     * @listener listener added to the list
     * @throws IVetoableCollectionChangeListener
     */

    @Override
    public void addVetoableCollectionChangeListener(
        IVetoableCollectionChangeListener listener)
    {
        vetoableCollectionChangeSource
            .addVetoableCollectionChangeListener(listener);

    }


    /**
     * Removes VeotoableCollectionChangeListener Notifies all listeners about
     * the change.
     *
     * @param listener
     *            that is removed from the list
     * @throws IVetoableCollectionChangeListener
     */
    @Override
    public void removeVetoableCollectionChangeListener(
        IVetoableCollectionChangeListener listener)
    {
        vetoableCollectionChangeSource
            .removeVetoableCollectionChangeListener(listener);

    }


    /**
     * Notifies all the listeners about the event.
     *
     * @param event
     *            - event about which listeners are notfied
     * @throws CollectionChangeVetoException
     */

    @Override
    public void fireVetoableElementAddedEvent(ICollectionChangeEvent event)
        throws CollectionChangeVetoException
    {
        vetoableCollectionChangeSource.fireVetoableElementAddedEvent(event);

    }


    /**
     * Notifies all the listeners about the event.
     *
     * @param event
     *            about which listeners are notified
     * @throws CollectionChangeVetoException
     */
    @Override
    public void fireVetoableElementRemovedEvent(ICollectionChangeEvent event)
        throws CollectionChangeVetoException
    {
        vetoableCollectionChangeSource.fireVetoableElementRemovedEvent(event);

    }


    /**
     * Notifies all the listeners about the event.
     *
     * @param event
     *            that listeners are notified about
     * @throws CollectionChangeVetoException
     */
    @Override
    public void fireVetoableCollectionClearedEvent(ICollectionChangeEvent event)
        throws CollectionChangeVetoException
    {
        vetoableCollectionChangeSource
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
        collectionChangeSource.addCollectionChangeListener(listener);

    }


    /**
     * Removes the provided listener from the list of listeners.
     *
     * @param listener
     */

    @Override
    public void removeCollectionChangeListener(
        ICollectionChangeListener listener)
    {
        collectionChangeSource.removeCollectionChangeListener(listener);

    }


    /**
     * Notifies all listeners of the event.
     *
     * @param event
     */
    @Override
    public void fireElementAddedEvent(ICollectionChangeEvent event)
    {
        collectionChangeSource.fireElementAddedEvent(event);

    }


    /**
     * Notifies all listeners of the event.
     *
     * @param event
     */
    @Override
    public void fireElementRemovedEvent(ICollectionChangeEvent event)
    {
        collectionChangeSource.fireElementRemovedEvent(event);

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
        collectionChangeSource.fireCollectionClearedEvent(event);

    }


    /**
     * Gets string representation of the lobby.
     *
     * @return String "The lobby"
     */

    public String toString()
    {
        return "The Loby";
    }

}
