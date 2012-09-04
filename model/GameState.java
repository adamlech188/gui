/**
 * 
 */
package cs3744.model;


/**
 * The <CODE>GameState</CODE> enum enumerates the different states of a game.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public enum GameState {

    /**
     * A game in progress.
     */
    IN_PROGRESS,
    /**
     * A game that is finished.
     */
    FINISHED,
    /**
     * A game ready to start.
     */
    READY,
    /**
     * A game waiting for players.
     */
    WAITING_FOR_PLAYERS;
}
