/**
 * 
 */
package cs3744.gui.events;


import cs3744.gui.events.interfaces.IKeyEvent;
import cs3744.gui.events.interfaces.IKeyListener;


/**
 * The <CODE>KeyAdapter</CODE> class provides an adapter implementation of the
 * <CODE>IKeyListener</CODE> interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public abstract class KeyAdapter
        implements IKeyListener {


    /*
     * (non-Javadoc)
     * 
     * @see
     * cs3744.gui.events.interfaces.IKeyListener#keyPressed(cs3744.gui.events
     * .interfaces.IKeyEvent)
     */
    @Override
    public void keyPressed(IKeyEvent keyEvent) {

        // Adapter class.
    }

}
