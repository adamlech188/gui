package cs3744.gui.events.interfaces;


import java.util.EventListener;


/**
 * The <CODE>IActionListener</CODE> interface provides a contract for classes
 * handling action events.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IActionListener
        extends EventListener {

    /**
     * Invoked when an action has been performed.
     * 
     * @param actionEvent
     *            the action event.
     */
    public void actionPerformed(IActionEvent actionEvent);
}
