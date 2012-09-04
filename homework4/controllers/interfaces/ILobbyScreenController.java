package cs3744.homework4.controllers.interfaces;


import cs3744.gui.events.interfaces.IActionListener;
import cs3744.model.events.interfaces.ICollectionChangeListener;


/**
 * The <CODE>ILobbyScreenController</CODE> interface provides a contract for
 * controllers linking lobbys with their views.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface ILobbyScreenController
        extends ICollectionChangeListener, IActionListener {

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