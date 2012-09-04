package cs3744.gui.events.interfaces;


/**
 * The <CODE>IActionEvent</CODE> interface provides a contract for EventObjects
 * that provide action events.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public interface IActionEvent
        extends IGUIEvent {

    /**
     * Returns the command of the action.
     * 
     * @return the command.
     */
    public abstract String getActionCommand();
}
