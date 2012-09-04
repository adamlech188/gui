package cs3744.graphics.models.interfaces;


import java.util.List;

import cs3744.graphics.interfaces.IPrimitive;


/**
 * 
 * @author Peter J. Radics.
 * @version 1.0
 * 
 */
public interface IGame
        extends IPrimitive {

    /**
     * Returns an unmodifiable list of game pieces.
     * 
     * @return an unmodifiable list of game pieces.
     */
    public abstract List<IGamePiece> getGamePieces();


    /**
     * Adds a game piece to the list of game pieces.
     * 
     * @param gamePiece
     *            the game piece to add.
     */
    public abstract void addGamePiece(IGamePiece gamePiece);


    /**
     * Removes a game piece to the list of game pieces.
     * 
     * @param gamePiece
     *            the game piece to remove.
     */
    public abstract void removeGamePiece(IGamePiece gamePiece);


    /**
     * Clears the list of game pieces.
     */
    public abstract void clearGamePieces();

}