/**
 * 
 */
package cs3744.gui.events.interfaces;


import java.util.EventListener;


/**
 * The <CODE>IFocusListener</CODE> interface provides a contract for classes
 * interested in keyboard focus.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IFocusListener
        extends EventListener {

    /**
     * Invoked when a component gains the keyboard focus.
     * 
     * @param event
     *            the event.
     */
    public abstract void focusGained(IFocusEvent event);


    /**
     * Invoked when a component loses the keyboard focus.
     * 
     * @param event
     *            the event.
     */
    public abstract void focusLost(IFocusEvent event);

}
