package cs3744.gui.interfaces;


import cs3744.graphics.models.interfaces.IGame;


/**
 * The <CODE>IGameBoardComponent</CODE> class provides a contract for classes
 * who want to provide a component for game boards.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IGameBoardComponent {

    /**
     * Returns the game board of this view.
     * 
     * @return the game board.
     */
    public abstract IGame getGameBoard();


    /**
     * Returns the game board type.
     * 
     * @return the game board type.
     */
    public abstract GameBoardType getGameBoardType();


    /**
     * Sets up an isometric view.
     */
    public abstract void setUpIsometricView();


    /**
     * Sets up a 2D view.
     */
    public abstract void setUp2DView();


    /**
     * The <CODE>GameBoardType</CODE> enum enumerates the different types of
     * game boards.
     * 
     * @author Peter J. Radics
     * @version 1.0
     * 
     */
    public enum GameBoardType {
        /**
         * Reference implementation of <CODE>FourPlayerGameBoard</CODE> is used.
         */
        REFERENCE_2D,
        /**
         * Reference implementation of <CODE>FourPlayerGameBoard3D</CODE> is
         * used.
         */
        REFERENCE_3D,
        /**
         * Student implementation of <CODE>FourPlayerGameBoard</CODE> is used.
         */
        STUDENT_2D,
        /**
         * Student implementation of <CODE>FourPlayerGameBoard3D</CODE> is used.
         */
        STUDENT_3D;
    }
}