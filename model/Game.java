/**
 *
 */
package cs3744.model;

import cs3744.model.events.CollectionChangeEvent;
import cs3744.model.events.CollectionChangeSource;
import cs3744.model.events.DataChangeEvent;
import cs3744.model.events.DataChangeSource;
import cs3744.model.events.VetoableCollectionChangeSource;
import cs3744.model.events.VetoableDataChangeSource;
import cs3744.model.events.interfaces.*;
import cs3744.model.events.solution.CollectionChangeOperation;
import cs3744.model.exceptions.CollectionChangeVetoException;
import cs3744.model.exceptions.DataChangeVetoException;
import cs3744.model.interfaces.IGame;
import cs3744.model.interfaces.IGamePiece;
import cs3744.model.interfaces.IMessageChannel;
import cs3744.model.interfaces.IPlayer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * This class contains the list of players, game pieces , events and sources. It
 * has a function of data model that will store all necessary information that
 * will be represented to the viewer.
 *
 * @author Adam Lech (PID adaml8)
 * @version 1.0
 */
public class Game
    implements IGame
{
    private final Integer                         gameId;
    private GameState                             gamestate;
    private final Lock                            lockState;
    private final IPlayer                         owner;
    private final List<IPlayer>                   players;
    private final Lock                            playerLock;
    private final List<IGamePiece>                gamePieces;
    private final IMessageChannel                 messagechannel;
    private final IDataChangeSource               datachangeSource;
    private final IVetoableDataChangeSource       vetoabledataChangeSource;
    private final ICollectionChangeSource         collectionchangesource;
    private final IVetoableCollectionChangeSource vetoablecollectionChangeSource;
    private final Lock                            gamePieceLock;


    // ----------------------------------------------------------
    /**
     * This is constructor that creates game instance. It takes integer as a
     * unique game identifier and owner (which is a first player in the game) ,
     * as arguments.
     *
     * @param gameId
     *            - unique integer for the given instance of the game
     * @param owner
     *            - owner which is also first player in this game
     */
    public Game(int gameId, IPlayer owner)
    {

        if (owner == null)
        {
            throw new IllegalArgumentException(
                "Cannot create Game without Owner");

        }
        this.gameId = gameId;
        this.owner = owner;
        lockState = new ReentrantLock();
        gamestate = GameState.WAITING_FOR_PLAYERS;
        playerLock = new ReentrantLock();
        players = new ArrayList<IPlayer>();
        gamePieces = new ArrayList<IGamePiece>();
        messagechannel = new MessageChannel(this + " Message Channel");
        datachangeSource = new DataChangeSource();
        vetoabledataChangeSource = new VetoableDataChangeSource();
        collectionchangesource = new CollectionChangeSource();
        vetoablecollectionChangeSource = new VetoableCollectionChangeSource();
        gamePieceLock = new ReentrantLock();
        players.add(owner);

    }





    /**
     * Adds the provided listener to the list of listeners.
     *
     * @param listener
     *            - listener added to the list
     */
    @Override
    public void addDataChangeListener(IDataChangeListener listener)
    {

        datachangeSource.addDataChangeListener(listener);

    }


    /**
     * Removes the provided listener from the list of listeners.
     *
     * @param listener
     *            - listener that is removed from the list
     */
    @Override
    public void removeDataChangeListener(IDataChangeListener listener)
    {

        datachangeSource.removeDataChangeListener(listener);

    }


    /**
     * Notifies all listeners about the event.
     *
     * @param event
     *            - event about which all listeners are notified.
     */
    @Override
    public void fireDataChangeEvent(IDataChangeEvent event)
    {

        datachangeSource.fireDataChangeEvent(event);

    }


    /**
     * Adds the provided listener to the list of listeners.
     *
     * @param listener
     *            - listener that is added to the list
     */
    @Override
    public void addVetoableDataChangeListener(
        IVetoableDataChangeListener listener)
    {

        vetoabledataChangeSource.addVetoableDataChangeListener(listener);

    }


    /**
     * Removes the provided listener from the list of listeners.
     *
     * @param listener
     *            - listener that is removed from the list
     */
    @Override
    public void removeVetoableDataChangeListener(
        IVetoableDataChangeListener listener)
    {

        vetoabledataChangeSource.removeVetoableDataChangeListener(listener);

    }


    /**
     * Notifies all listeners of the event.
     *
     * @param event
     *            - event about which all listeners are notified
     */
    @Override
    public void fireVetoableDataChangeEvent(IDataChangeEvent event)
        throws DataChangeVetoException
    {

        vetoabledataChangeSource.fireVetoableDataChangeEvent(event);

    }


    /**
     * Returns list of all players as unmodifiable list.
     *
     * @return List<IPlayer> - list of players
     */
    @Override
    public List<IPlayer> getPlayers()
    {
        List<IPlayer> returnList;
        try
        { // Puts a lock on list , so that other threads cannot access it.
            playerLock.lock();
            returnList = Collections.unmodifiableList(players);
        }
        finally
        {
            playerLock.unlock();
        }

        return returnList;
    }


    /**
     * Adds player to the list.
     *
     * @param player
     *            - a player to be added
     */
    @Override
    public void addPlayer(IPlayer player)
        throws CollectionChangeVetoException
    { // Creates an enum type of operation about which listeners will be
      // notified
        CollectionChangeOperation operation = CollectionChangeOperation.ADD;
        // Instantiates an event about which all listeners will be notified.
        CollectionChangeEvent event =
            new CollectionChangeEvent(this, "players", player, operation);
        try
        {
            // Puts a lock on list , so that other threads cannot access it.
            playerLock.lock();
            vetoablecollectionChangeSource.fireVetoableElementAddedEvent(event);

            players.add(player);
            collectionchangesource.fireElementAddedEvent(event);

        }
        finally
        {
            playerLock.unlock();
        }

    }


    /**
     * Removes player from the list.
     *
     * @param player
     *            - player that is removed from the list
     */
    @Override
    public void removePlayer(IPlayer player)
        throws CollectionChangeVetoException
    {
        // Enum type of operation about which listeners will be notified
        CollectionChangeOperation operation = CollectionChangeOperation.ADD;
        // Instantiates an event about which all listeners will be notified.
        CollectionChangeEvent event =
            new CollectionChangeEvent(this, "players", player, operation);
        try
        {
            // Puts a lock on list , so that other threads cannot access it.
            playerLock.lock();
            vetoablecollectionChangeSource
                .fireVetoableElementRemovedEvent(event);

            players.add(player);
            collectionchangesource.fireElementRemovedEvent(event);

        }
        finally
        {
            playerLock.unlock();
        }

    }


    /**
     * Empties the list of of players.
     */
    @Override
    public void clearPlayers()
        throws CollectionChangeVetoException
    {

        CollectionChangeOperation operation = CollectionChangeOperation.CLEAR;
        CollectionChangeEvent event =
            new CollectionChangeEvent(this, "players", players, operation);
        try
        {

            playerLock.lock();
            vetoablecollectionChangeSource
                .fireVetoableCollectionClearedEvent(event);

            players.clear();
            collectionchangesource.fireCollectionClearedEvent(event);

        }
        finally
        {
            playerLock.unlock();
        }

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

        collectionchangesource.addCollectionChangeListener(listener);

    }


    /**
     * Removes listener from the list.
     *
     * @param listener
     *            - listener that is removed from the list
     */
    @Override
    public void removeCollectionChangeListener(
        ICollectionChangeListener listener)
    {

        collectionchangesource.removeCollectionChangeListener(listener);

    }


    /**
     * Notifies all liseners about the event.
     *
     * @param event
     *            - event about which all listeners are notified
     */
    @Override
    public void fireElementAddedEvent(ICollectionChangeEvent event)
    {

        collectionchangesource.fireElementAddedEvent(event);

    }


    /**
     * Notifies all listeners about the event.
     *
     * @param event
     *            - event about which all listeners are notified.
     */
    @Override
    public void fireElementRemovedEvent(ICollectionChangeEvent event)
    {

        collectionchangesource.fireElementRemovedEvent(event);

    }


    /**
     * Notifies all listeners about event.
     *
     * @param event
     *            - event about which all liseners are notified.
     */
    @Override
    public void fireCollectionClearedEvent(ICollectionChangeEvent event)
    {

        collectionchangesource.fireCollectionClearedEvent(event);

    }


    /**
     * Adds the provided listener to the list of listeners.
     *
     * @param listener
     *            - listener added to the list.
     */
    @Override
    public void addVetoableCollectionChangeListener(
        IVetoableCollectionChangeListener listener)
    {

        vetoablecollectionChangeSource
            .addVetoableCollectionChangeListener(listener);

    }


    /**
     * Removes the listener from the list of listeners.
     *
     * @param listener
     *            - listener removed from the list
     */
    @Override
    public void removeVetoableCollectionChangeListener(
        IVetoableCollectionChangeListener listener)
    {

        vetoablecollectionChangeSource
            .removeVetoableCollectionChangeListener(listener);

    }


    /**
     * Notifies all listeners about the event.
     *
     * @param event
     *            - event about which listeners are notified
     */
    @Override
    public void fireVetoableElementAddedEvent(ICollectionChangeEvent event)
        throws CollectionChangeVetoException
    {

        vetoablecollectionChangeSource.fireVetoableElementAddedEvent(event);

    }


    /**
     * Notifies all listeners about the event
     *
     * @param event
     *            - event about which all listeners are notified
     */
    @Override
    public void fireVetoableElementRemovedEvent(ICollectionChangeEvent event)
        throws CollectionChangeVetoException
    {

        vetoablecollectionChangeSource.fireVetoableElementRemovedEvent(event);

    }


    /**
     * Notifies all listeners about the event
     *
     * @param event
     *            - event about which all listeners are notified
     */
    @Override
    public void fireVetoableCollectionClearedEvent(ICollectionChangeEvent event)
        throws CollectionChangeVetoException
    {

        vetoablecollectionChangeSource
            .fireVetoableCollectionClearedEvent(event);

    }


    /**
     * Returns game state.
     *
     * @return game state
     */
    @Override
    public GameState getState()
    {
        GameState returnState;

        try
        { // Puts lock on variable
            lockState.lock();
            returnState = gamestate;

        }
        finally
        {
            lockState.unlock();
        }
        return returnState;
    }


    /**
     * Sets game state.
     *
     * @param state
     *            - state to which game is to be set
     */
    @Override
    public void setState(GameState state)
        throws DataChangeVetoException
    {

        DataChangeEvent event;
        event = new DataChangeEvent(this, "state", gamestate, state);

        try
        {
            lockState.lock();

            fireVetoableDataChangeEvent(event);
            gamestate = state;
            fireDataChangeEvent(event);
        }
        finally
        {
            lockState.unlock();
        }

    }


    /**
     * Gets the owner of the game.
     *
     * @return owner - owner of the game
     */
    @Override
    public IPlayer getOwner()
    {

        return owner;
    }


    /**
     * Returns the list of game pieces.
     *
     * @return List<IGamePiece> - list of game pieces
     */
    public List<IGamePiece> getGamePieces()
    {
        List<IGamePiece> returnList;
        try
        {
            gamePieceLock.lock();
            returnList = Collections.unmodifiableList(gamePieces);

        }
        finally
        {
            gamePieceLock.unlock();
        }
        return returnList;
    }


    /**
     * Returns player role of the given player
     *
     * @param player
     *            - player which role is to be returned
     * @return PlayerRole - player of the player
     */
    @Override
    public PlayerRole getPlayerRole(IPlayer player)
    {
        PlayerRole playerRole;

        try
        { // puts lock on the player
            playerLock.lock();
            int playerNumber = players.indexOf(player);
            // Player role is based on the index number in the list
            switch (playerNumber)
            {
                case 0:

                    playerRole = PlayerRole.PLAYER_ONE;
                    break;
                case 1:

                    playerRole = PlayerRole.PLAYER_TWO;
                    break;
                case 2:

                    playerRole = PlayerRole.PLAYER_THREE;
                    break;
                case 3:

                    playerRole = PlayerRole.PLAYER_FOUR;
                    break;
                default:

                    playerRole = PlayerRole.OBSERVER;
                    break;
            }
        }
        finally
        {
            playerLock.unlock();
        }

        return playerRole;
    }


    /**
     * Adds game piece to the list.
     *
     * @param gamePiece
     *            - game piece added to the list
     */
    @Override
    public void addGamePiece(IGamePiece gamePiece)
        throws CollectionChangeVetoException
    {
        // Operation about which all listeners will be notified
        CollectionChangeOperation operation = CollectionChangeOperation.ADD;
        // Instantiates event about which all listeners will be notified
        CollectionChangeEvent event =
            new CollectionChangeEvent(this, "gamePieces", gamePiece, operation);

        try
        {

            gamePieceLock.lock();
            vetoablecollectionChangeSource.fireVetoableElementAddedEvent(event);

            gamePieces.add(gamePiece);
            collectionchangesource.fireElementAddedEvent(event);

        }
        finally
        {
            gamePieceLock.unlock();
        }

    }


    /**
     * Removes game piece from the list.
     *
     * @param gamePiece
     *            - game piece removed from the list
     */
    @Override
    public void removeGamePiece(IGamePiece gamePiece)
        throws CollectionChangeVetoException
    {

        CollectionChangeOperation operation = CollectionChangeOperation.REMOVE;
        CollectionChangeEvent event =
            new CollectionChangeEvent(this, "gamePieces", gamePiece, operation);
        try
        {

            gamePieceLock.lock();
            // Notifies all listeners about the event
            vetoablecollectionChangeSource
                .fireVetoableElementRemovedEvent(event);

            gamePieces.remove(gamePiece);
            collectionchangesource.fireElementRemovedEvent(event);

        }
        finally
        {
            gamePieceLock.unlock();
        }

    }


    /**
     * Empties list of game pieces
     */
    @Override
    public void clearGamePieces()
        throws CollectionChangeVetoException
    {

        CollectionChangeOperation operation = CollectionChangeOperation.CLEAR;
        CollectionChangeEvent event =
            new CollectionChangeEvent(this, "gamePieces", gamePieces, operation);
        try
        {

            gamePieceLock.lock();
            vetoablecollectionChangeSource
                .fireVetoableCollectionClearedEvent(event);

            gamePieces.clear();
            collectionchangesource.fireCollectionClearedEvent(event);
        }
        finally
        {
            gamePieceLock.unlock();
        }

    }


    /**
     * Returns message channel
     *
     * @return IMessageChannel - message channel
     */
    @Override
    public IMessageChannel getMessageChannel()
    {

        return messagechannel;
    }


    /**
     * Returns string representation of the game.
     *
     * @return String - string representation of the game
     */
    public String toString()
    {
        return this.owner + "'s game:" + this.gameId;

    }
}
