package cs3744.graphics.models.interfaces;


import cs3744.graphics.interfaces.IPrimitive;


/**
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IGamePiece
        extends IPrimitive {

    /**
     * Returns the x position of the game piece on the grid.
     * 
     * @return the x position.
     */
    public abstract int getXPosition();


    /**
     * Sets the x position of the game piece on the grid.
     * 
     * @param xPosition
     *            the x position.
     */
    public abstract void setXPosition(int xPosition);


    /**
     * Returns the y position of the game piece on the grid.
     * 
     * @return the y position.
     */
    public abstract int getYPosition();


    /**
     * Sets the y position of the game piece on the grid.
     * 
     * @param yPosition
     *            the y position.
     */
    public abstract void setYPosition(int yPosition);

}