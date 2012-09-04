package cs3744.gui.events.interfaces;

import java.util.EventListener;


/**
 * The <CODE>IKeyListener</CODE> interface provides a contract for classes
 * handling key events.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IKeyListener
        extends EventListener {

    /**
     * Invoked when the key has been pressed or released.
     * 
     * @param keyEvent
     *            the key event.
     */
    public void keyPressed(IKeyEvent keyEvent);
}
