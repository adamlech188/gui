package cs3744.homework4.controllers.interfaces;


import cs3744.model.events.interfaces.IDataChangeListener;
import cs3744.model.interfaces.IGamePiece;


/**
 * The <CODE>IGamePieceController</CODE> interface provides a contract for
 * controllers of game pieces (linking model to view).
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IGamePieceController
        extends IDataChangeListener {

    /**
     * Returns the model.
     * 
     * @return the model.
     */
    public abstract IGamePiece getModel();


    /**
     * Return the view.
     * 
     * @return the view.
     */
    public abstract cs3744.graphics.models.interfaces.IGamePiece getView();

}