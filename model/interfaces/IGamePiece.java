package cs3744.model.interfaces;


import cs3744.model.GamePieceState;
import cs3744.model.events.interfaces.IDataChangeSource;
import cs3744.model.events.interfaces.IVetoableDataChangeSource;
import cs3744.model.exceptions.DataChangeVetoException;


/**
 * The <CODE>IGamePiece</CODE> interface specifies a contract for game pieces
 * that are part of a game and have an owner.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IGamePiece
        extends IDataChangeSource, IVetoableDataChangeSource {

    /**
     * Returns the owner of the game piece.
     * 
     * @return the owner of the game piece.
     */
    public abstract IPlayer getOwner();


    /**
     * Returns the game this play piece is part of.
     * 
     * @return the game.
     */
    public abstract IGame getGame();


    /**
     * Returns the position of the game piece.
     * 
     * @return the position of the game piece.
     */
    public abstract int getPosition();


    /**
     * Sets the position of the game piece.
     * 
     * @param position
     *            the position of the game piece.
     * @throws DataChangeVetoException
     *             if the change is vetoed.
     */
    public abstract void setPosition(int position)
            throws DataChangeVetoException;


    /**
     * Returns the state of the game piece.
     * 
     * @return the state of the game piece.
     */
    public abstract GamePieceState getState();


    /**
     * Sets the state of the game piece.
     * 
     * @param state
     *            the state of the game piece.
     * @throws DataChangeVetoException
     *             if the change is vetoed.
     */
    public abstract void setState(GamePieceState state)
            throws DataChangeVetoException;

}