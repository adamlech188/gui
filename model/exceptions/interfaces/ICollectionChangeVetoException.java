package cs3744.model.exceptions.interfaces;


import cs3744.model.events.interfaces.ICollectionChangeEvent;


/**
 * The <CODE>ICollectionChangeVetoException</CODE> models an exception that is
 * thrown when a change to a collection is vetoed.
 * 
 * It provides access to the vetoed event.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface ICollectionChangeVetoException {


    /**
     * Getter for the vetoed event.
     * 
     * @return The event that was vetoed.
     */
    public ICollectionChangeEvent getVetoedEvent();
}
