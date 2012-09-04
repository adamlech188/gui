package cs3744.model;

import cs3744.model.events.DataChangeEvent;
import java.util.concurrent.locks.ReentrantLock;
import cs3744.model.events.VetoableDataChangeSource;
import cs3744.model.events.DataChangeSource;
import java.util.concurrent.locks.Lock;
import cs3744.model.events.interfaces.IVetoableDataChangeSource;
import cs3744.model.events.interfaces.IDataChangeSource;
import cs3744.model.events.interfaces.IDataChangeEvent;
import cs3744.model.events.interfaces.IDataChangeListener;
import cs3744.model.events.interfaces.IVetoableDataChangeListener;
import cs3744.model.exceptions.DataChangeVetoException;
import cs3744.model.interfaces.IGame;
import cs3744.model.interfaces.IGamePiece;
import cs3744.model.interfaces.IPlayer;

/**
 * This class contains all data necessary to represent given game piece in the
 * view. It has a function of data model.
 *
 * @author Adam Lech (PID: adaml8)
 * @version 1.0
 */
public class GamePiece
    implements IGamePiece
{
    // Data field
    private final IPlayer                   owner;
    private final IGame                     game;
    private final IDataChangeSource         dataChangeSource;
    private final IVetoableDataChangeSource vetoableDataChangeSource;
    private int                             position;
    private final Lock                      positionLock;
    private GamePieceState                  state;
    private final Lock                      stateLock;


    /**
     * Constructor method that creates an instance of the game piece. It
     * instantiates all the data necessary to represents game piece to the
     * viewer.
     *
     * @param owner
     *            - owner and first player of the game, where game piece is used
     * @param game
     *            - game werer game piece is used
     */
    public GamePiece(IPlayer owner, IGame game)
    {
        this.owner = owner;
        this.game = game;
        dataChangeSource = new DataChangeSource();
        vetoableDataChangeSource = new VetoableDataChangeSource();
        // instantiates position of the game piece with default position
        position = 0;
        positionLock = new ReentrantLock();
        state = GamePieceState.OUT;
        stateLock = new ReentrantLock();

    }


    /**
     * Adds the provided listener to the list of listeners.
     *
     * @param listener
     *            - listener that is added to the list
     */
    @Override
    public void addDataChangeListener(IDataChangeListener listener)
    {

        dataChangeSource.addDataChangeListener(listener);

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

        dataChangeSource.removeDataChangeListener(listener);

    }


    /**
     * Notifies all listeners of the event.
     *
     * @param event
     *            - event about which all listeners are notified
     */
    @Override
    public void fireDataChangeEvent(IDataChangeEvent event)
    {

        dataChangeSource.fireDataChangeEvent(event);

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

        vetoableDataChangeSource.addVetoableDataChangeListener(listener);

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

        vetoableDataChangeSource.removeVetoableDataChangeListener(listener);

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

        vetoableDataChangeSource.fireVetoableDataChangeEvent(event);

    }


    /**
     * Returns the owner of the game piece.
     *
     * @return IPlayer - owner of the game piece
     */
    @Override
    public IPlayer getOwner()
    {

        return owner;
    }


    /**
     * Gets the game
     *
     * @return IGame - game
     */
    @Override
    public IGame getGame()
    {

        return game;
    }


    /**
     * Gets the position of the game
     *
     * @return int - position of the game
     */
    @Override
    public int getPosition()
    {
        int returnValue;

        try
        {
            positionLock.lock();
            returnValue = position;

        }
        finally
        {
            positionLock.unlock();

        }
        return returnValue;
    }


    /**
     * Sets the position of the game.
     *
     * @param position
     *            - position of the game
     */
    @Override
    public void setPosition(int position)
        throws DataChangeVetoException
    {
        DataChangeEvent event =
            new DataChangeEvent(this, "positon", this.position, position);
        ;

        try
        {
            positionLock.lock();
            vetoableDataChangeSource.fireVetoableDataChangeEvent(event);
            this.position = position;
            dataChangeSource.fireDataChangeEvent(event);

        }
        finally
        {
            positionLock.unlock();
        }
    }


    /**
     * Returns state of the game piece.
     *
     * @return GamePieceState - state of the game piece
     */
    @Override
    public GamePieceState getState()
    {
        GamePieceState result;
        try
        {
            stateLock.lock();
            result = state;

        }
        finally
        {
            stateLock.unlock();
        }
        return result;
    }


    /**
     * Sets the state of the game piece.
     *
     * @param state
     *            - state to which game piece is set
     */
    @Override
    public void setState(GamePieceState state)
        throws DataChangeVetoException
    {
        IDataChangeEvent event =
            new DataChangeEvent(this, "state", this.state, state);
        ;
        try
        {
            stateLock.lock();
            vetoableDataChangeSource.fireVetoableDataChangeEvent(event);
            this.state = state;
            dataChangeSource.fireDataChangeEvent(event);
        }
        finally
        {
            stateLock.unlock();
        }

    }


    /**
     * String representation of the game piece in given state.
     *
     * @return String - string representation of the game piece.
     */
    @Override
    public String toString()
    {
        switch (this.state)
        {
            case GOAL:
                return this.getOwner() + "'s Game Piece at (" + this.position
                    + ") in Goal";
            case IN:
                return this.getOwner() + "'s Game Piece at (" + this.position
                    + ") in Game";
            case OUT:
                return this.getOwner() + "'s Game Piece  (" + this.position
                    + ") out of game";
            default:
                return this.getOwner() + "'s Game Piece at (" + this.position
                    + ") undefined";
        }
    }

}
