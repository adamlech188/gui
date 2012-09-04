/**
 * 
 */
package cs3744.model.events.interfaces;


import java.util.EventListener;

import cs3744.model.exceptions.DataChangeVetoException;


/**
 * The <CODE>IVetoableDataChangeListener</CODE> interface provides an interface
 * for listeners who are interested in getting notified before a change in a
 * piece of data is applied for a chance to veto that change.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see EventListener
 * @see IDataChangeEvent
 */
public interface IVetoableDataChangeListener
        extends EventListener {

    /**
     * This event is fired if the data is changed.
     * 
     * @param event
     *            The event.
     * @throws DataChangeVetoException
     *             if the change is vetoed.
     */
    public void vetoableDataChange(IDataChangeEvent event)
            throws DataChangeVetoException;


}
