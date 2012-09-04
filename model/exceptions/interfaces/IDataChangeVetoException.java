/**
 * 
 */
package cs3744.model.exceptions.interfaces;


import cs3744.model.events.interfaces.IDataChangeEvent;


/**
 * The <CODE>IDataChangeVetoException</CODE> models an exception that is thrown
 * when a change to a piece of data is vetoed.
 * 
 * It provides access to the vetoed event.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IDataChangeVetoException {


    /**
     * Getter for the vetoed event.
     * 
     * @return The event that was vetoed.
     */
    public IDataChangeEvent getVetoedEvent();
}
