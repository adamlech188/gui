/**
 * 
 */
package cs3744.model.interfaces;


import java.util.List;

import cs3744.model.events.interfaces.ICollectionChangeSource;
import cs3744.model.events.interfaces.IVetoableCollectionChangeSource;
import cs3744.model.exceptions.CollectionChangeVetoException;


/**
 * The <CODE>IGameListContainer</CODE> interface represents objects that
 * contain a list of games.
 * 
 * Any changes to the list are <b>vetoable</b>.
 * 
 * @author Peter J. Radics
 * @version 1.1
 * 
 * @see IGame
 */
public interface IGameListContainer
        extends ICollectionChangeSource, IVetoableCollectionChangeSource {

    /**
     * Returns a list of games.
     * 
     * @return A list of games.
     */
    public List<IGame> getGames();


    /**
     * Adds a game to the list.
     * 
     * @param game
     *            The game to be added.
     * @throws CollectionChangeVetoException
     *             if the game cannot be added.
     */
    public void addGame(IGame game)
            throws CollectionChangeVetoException;


    /**
     * Removes a game from the list.
     * 
     * @param game
     *            The game to be removed.
     * @throws CollectionChangeVetoException
     *             if the game cannot be removed.
     */
    public void removeGame(IGame game)
            throws CollectionChangeVetoException;


    /**
     * Removes all games from the list.
     * 
     * @throws CollectionChangeVetoException
     *             if the change is rejected.
     */
    public void clearGames()
            throws CollectionChangeVetoException;
}
