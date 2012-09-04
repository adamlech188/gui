/**
 * 
 */
package cs3744.gui.events;


import cs3744.gui.events.interfaces.IFocusEvent;
import cs3744.gui.events.interfaces.IFocusListener;


/**
 * The <CODE>FocusAdapter</CODE> class provides an adapter for the
 * <CODE>IFocusListener</CODE> interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public abstract class FocusAdapter
        implements IFocusListener {


    /*
     * (non-Javadoc)
     * 
     * @see
     * cs3744.gui.events.interfaces.IFocusListener#focusGained(cs3744.gui.events
     * .interfaces.IFocusEvent)
     */
    @Override
    public void focusGained(IFocusEvent event) {

        // Adapter class
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * cs3744.gui.events.interfaces.IFocusListener#focusLost(cs3744.gui.events
     * .interfaces.IFocusEvent)
     */
    @Override
    public void focusLost(IFocusEvent event) {

        // Adapter class
    }

}
