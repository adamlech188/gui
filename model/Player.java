/**
 *
 */
package cs3744.model;

import cs3744.model.events.DataChangeEvent;
import cs3744.model.events.interfaces.IDataChangeEvent;
import cs3744.model.events.interfaces.IDataChangeListener;
import cs3744.model.events.interfaces.IDataChangeSource;
import cs3744.model.events.interfaces.IVetoableDataChangeListener;
import cs3744.model.events.interfaces.IVetoableDataChangeSource;
import cs3744.model.events.solution.DataChangeSource;
import cs3744.model.events.solution.VetoableDataChangeSource;
import cs3744.model.exceptions.DataChangeVetoException;
import cs3744.model.interfaces.IPlayer;

/**
 * Player class.Implements IPlayer interface. Stores all the relevant data
 * fields for player object.
 *
 * @author Adam Lech
 * @version 1.0
 */

public class Player
    implements IPlayer
{

    // ~Data field
    private final IDataChangeSource         datachangesource;
    private final IVetoableDataChangeSource vetoablechangesource;
    private final String                    name;
    private Integer                         gamewon;
    private Integer                         gamelost;


    // ----------------------------------------------------------
    /**
     * Constructor method. Creates a new Player object.
     *
     * @param name
     *            - name of player
     */
    public Player(String name)
    {
        this.name = name;
        datachangesource = new DataChangeSource();
        vetoablechangesource = new VetoableDataChangeSource();
        gamewon = 0;
        gamelost = 0;

    }


    /**
     * Adds the listener to the list of listeners.
     *
     * @param listener
     *            added to the list
     */
    @Override
    public void addVetoableDataChangeListener(
        IVetoableDataChangeListener listener)
    {
        vetoablechangesource.addVetoableDataChangeListener(listener);

    }


    /**
     * Adds the listener to the list of listeners.
     *
     * @param listener
     *            removed from the list
     */
    @Override
    public void removeVetoableDataChangeListener(
        IVetoableDataChangeListener listener)
    {
        vetoablechangesource.removeVetoableDataChangeListener(listener);

    }


    /**
     * Adds event to the list of listeners.
     *
     * @param event
     *            about which listeners are notified
     */
    @Override
    public void fireVetoableDataChangeEvent(IDataChangeEvent event)
        throws DataChangeVetoException
    {
        vetoablechangesource.fireVetoableDataChangeEvent(event);

    }


    /**
     * Adds the provided listener to the list of listeners
     *
     * @param listener
     *            added to the list
     */

    @Override
    public void addDataChangeListener(IDataChangeListener listener)
    {
        datachangesource.addDataChangeListener(listener);

    }


    /**
     * Removes the provided listener from the list of listeners
     *
     * @param listener
     *            removed from the list
     */
    @Override
    public void removeDataChangeListener(IDataChangeListener listener)
    {
        datachangesource.removeDataChangeListener(listener);

    }


    /**
     * Notifies all listeners about the event.
     *
     * @param event
     *            about which listeners are notified.
     */
    @Override
    public void fireDataChangeEvent(IDataChangeEvent event)
    {
        datachangesource.fireDataChangeEvent(event);

    }


    /**
     * Gets the name of the player.
     *
     * @return String name of the player
     */
    @Override
    public String getName()
    {
        return name;
    }


    /**
     * Gets the number of games won
     *
     * @return Integer gamewon
     */
    @Override
    public synchronized Integer getGamesWon()
    {
        return gamewon;
    }


    /**
     * Sets the value of the games that are won.
     *
     * @param gamesWon
     *            - number of games won by the player
     * @throws DataChangeVetoException
     */
    @Override
    public synchronized void setGamesWon(Integer gamesWon)
        throws DataChangeVetoException
    {
        DataChangeEvent event;

        event = new DataChangeEvent(this, "gamesWon", gamewon, gamesWon);
        this.fireVetoableDataChangeEvent(event);
        gamewon = gamesWon;
        this.fireDataChangeEvent(event);

    }


    /**
     * Gets the value of GamesLost
     *
     * @return number of games lost by the player
     */
    @Override
    public synchronized Integer getGamesLost()
    {
        return gamelost;
    }


    /**
     * Returns the string representation of Player class which is player's name
     *
     * @return player's name
     */
    public String toString()
    {
        return name;
    }


    /**
     * Sets the value of Game Lost
     *
     * @param gamesLost
     *            number of games won by the player
     * @throws DataChangeVetoException
     */
    @Override
    public synchronized void setGamesLost(Integer gamesLost)
        throws DataChangeVetoException
    {
        DataChangeEvent event;
        event = new DataChangeEvent(this, "gamesLost", gamelost, gamesLost);

        this.fireVetoableDataChangeEvent(event);
        gamelost = gamesLost;
        this.fireDataChangeEvent(event);

    }


    /**
     * Checks if the provided object is this instance of the player.
     *
     * @param object
     *            to be compared
     * @return true if the provided object is the same as instance of the
     *         player, false otherwise
     */
    public boolean equals(Object object)
    {
        if (object instanceof IPlayer)
        {
            IPlayer objectToCompare = (IPlayer)object;
            if (this.name.equals(objectToCompare.getName()))
            {
                return true;
            }
        }
        return false;
    }


    /**
     * Provides a hash code based on the name of the player.
     *
     * @return hash code
     */
    public int hashCode()
    {
        return name.hashCode();
    }

}
