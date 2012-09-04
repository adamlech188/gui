/**
 * 
 */
package cs3744.model.interfaces;


import cs3744.model.events.interfaces.IDataChangeSource;
import cs3744.model.events.interfaces.IVetoableDataChangeSource;
import cs3744.model.exceptions.DataChangeVetoException;


/**
 * The <CODE>IPlayer</CODE> interface provides the basic interface for a player
 * of a board game.
 * 
 * Currently, the player only has a name (that cannot change), the number of
 * games won and lost. Any change to the stats are <b>vetoable</b>.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IPlayer
        extends IVetoableDataChangeSource, IDataChangeSource {

    /**
     * Getter for the name field.
     * 
     * @return The name of the player.
     */
    public String getName();


    /**
     * Getter for the games won field.
     * 
     * @return The amount of games the player has won.
     */
    public Integer getGamesWon();


    /**
     * Setter for the games won field.
     * 
     * @param gamesWon
     *            The amount of games the player has won.
     * @throws DataChangeVetoException
     *             if the change is rejected.
     */
    public void setGamesWon(Integer gamesWon)
            throws DataChangeVetoException;


    /**
     * Getter for the games lost field.
     * 
     * @return The amount of games the player has lost.
     */
    public Integer getGamesLost();


    /**
     * Setter for the games lost field.
     * 
     * @param gamesLost
     *            The amount of games the player has lost.
     * @throws DataChangeVetoException
     *             if the change is rejected.
     */
    public void setGamesLost(Integer gamesLost)
            throws DataChangeVetoException;
}
