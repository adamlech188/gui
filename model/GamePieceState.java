/**
 * 
 */
package cs3744.model;


/**
 * The <CODE>GamePieceState</CODE> Enum provides an enumeration of states a game
 * piece can be in.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public enum GamePieceState {

    /**
     * The game piece is in play.
     */
    IN,
    /**
     * The game piece is out of play.
     */
    OUT,
    /**
     * The game piece is on a goal field.
     */
    GOAL;
}
