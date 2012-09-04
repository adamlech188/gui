package cs3744.homework4.controllers.interfaces;

import cs3744.gui.events.interfaces.IActionListener;


/**
 * The <CODE>ILoginScreenController</CODE> interface provides a contract for
 * controllers of login windows.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface ILoginScreenController
        extends IActionListener {

    /**
     * Returns the player name.
     * 
     * @return the player name.
     */
    public abstract String getPlayerName();


    /**
     * Sets the player name.
     * 
     * @param playerName
     *            the playerName to set
     */
    public abstract void setPlayerName(String playerName);


    /**
     * Displays the associated view.
     */
    public abstract void displayView();


    /**
     * Hides the associated view.
     */
    public abstract void hideView();


    /**
     * Disposes of the associated view.
     */
    public abstract void disposeOfView();

}