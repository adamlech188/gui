package cs3744.homework4.controllers.interfaces;


import cs3744.gui.events.interfaces.IActionListener;
import cs3744.model.events.interfaces.ICollectionChangeListener;


/**
 * The <CODE>IGameScreenController</CODE> interface provides a contract for
 * controllers linking games with their views.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IGameScreenController
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