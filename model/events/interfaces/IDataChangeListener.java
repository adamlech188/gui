/**
 * 
 */
package cs3744.model.events.interfaces;


import java.util.EventListener;


/**
 * The <CODE>IDataChangeListener</CODE> interface provides an interface
 * for listeners who are interested in getting notified of a change in a
 * piece of data.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 * @see EventListener
 * @see IDataChangeEvent
 */
public interface IDataChangeListener
        extends EventListener {

    /**
     * This event is fired if the data is changed.
     * 
     * @param event
     *            The event.
     */
    public void dataChanged(IDataChangeEvent event);


}
