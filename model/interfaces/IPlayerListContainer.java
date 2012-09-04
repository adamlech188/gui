/**
 * 
 */
package cs3744.model.interfaces;


import java.util.List;

import cs3744.model.events.interfaces.ICollectionChangeSource;
import cs3744.model.events.interfaces.IVetoableCollectionChangeSource;
import cs3744.model.exceptions.CollectionChangeVetoException;


/**
 * The <CODE>IPlayerListContainer</CODE> interface represents objects that
 * contain a list of players.
 * 
 * Any changes to the list are <b>vetoable</b>.
 * 
 * @author Peter J. Radics
 * @version 1.1
 * 
 * @see IPlayer
 */
public interface IPlayerListContainer
        extends ICollectionChangeSource, IVetoableCollectionChangeSource {

    /**
     * Returns a list of players.
     * 
     * @return A list of players.
     */
    public List<IPlayer> getPlayers();


    /**
     * Adds a player to the list.
     * 
     * @param player
     *            The player to be added.
     * @throws CollectionChangeVetoException
     *             if the player cannot be added.
     */
    public void addPlayer(IPlayer player)
            throws CollectionChangeVetoException;


    /**
     * Removes a player from the list.
     * 
     * @param player
     *            The player to be removed.
     * @throws CollectionChangeVetoException
     *             if the player cannot be removed.
     */
    public void removePlayer(IPlayer player)
            throws CollectionChangeVetoException;


    /**
     * Removes all players from the list.
     * 
     * @throws CollectionChangeVetoException
     *             if the change is rejected.
     */
    public void clearPlayers()
            throws CollectionChangeVetoException;
}
