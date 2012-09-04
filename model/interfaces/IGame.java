package cs3744.model.interfaces;


import java.util.List;

import cs3744.model.GameState;
import cs3744.model.PlayerRole;
import cs3744.model.events.interfaces.IDataChangeSource;
import cs3744.model.events.interfaces.IVetoableDataChangeSource;
import cs3744.model.exceptions.CollectionChangeVetoException;
import cs3744.model.exceptions.DataChangeVetoException;


/**
 * The <CODE>IGame</CODE> interface provides a contract for a class representing
 * a game with game pieces, players, and a message channel.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IGame
        extends IDataChangeSource, IVetoableDataChangeSource,
        IPlayerListContainer {

    /**
     * Returns the game state.
     * 
     * @return the game state.
     */
    public abstract GameState getState();


    /**
     * Sets the game state.
     * 
     * @param state
     *            the game state.
     * @throws DataChangeVetoException
     *             if the change was vetoed.
     */
    public abstract void setState(GameState state)
            throws DataChangeVetoException;


    /**
     * Returns the owner (creator) of the game.
     * 
     * @return the owner.
     */
    public abstract IPlayer getOwner();


    /**
     * Returns an unmodifiable list of game pieces.
     * 
     * @return an unmodifiable list of game pieces.
     */
    public abstract List<IGamePiece> getGamePieces();


    /**
     * Returns the role of a player. This means Player 1 through 4 for the first
     * four players, and observer for the remaining players.
     * 
     * @param player
     *            the player whose role is queried.
     * @return the role of the player.
     */
    public abstract PlayerRole getPlayerRole(IPlayer player);


    /**
     * Adds a game piece to the list of game pieces.
     * 
     * @param gamePiece
     *            the game piece to add.
     * @throws CollectionChangeVetoException
     *             if the change was vetoed.
     */
    public abstract void addGamePiece(IGamePiece gamePiece)
            throws CollectionChangeVetoException;


    /**
     * Removes a game piece from the list of game pieces.
     * 
     * @param gamePiece
     *            the game piece to remove.
     * @throws CollectionChangeVetoException
     *             if the change was vetoed.
     */
    public abstract void removeGamePiece(IGamePiece gamePiece)
            throws CollectionChangeVetoException;


    /**
     * Clears the game piece list.
     * 
     * @throws CollectionChangeVetoException
     *             if the change was vetoed.
     */
    public abstract void clearGamePieces()
            throws CollectionChangeVetoException;


    /**
     * Returns the game's message channel.
     * 
     * @return the message channel.
     */
    public abstract IMessageChannel getMessageChannel();

}