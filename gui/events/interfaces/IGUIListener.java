/**
 * 
 */
package cs3744.gui.events.interfaces;


import java.util.EventListener;


/**
 * The <CODE>IGUIListener</CODE> interface is a tagging interface for classes
 * that want to handle IGUIEvents.
 * 
 * @author Peter J. Radics
 * @version 1.1
 * 
 */
public interface IGUIListener
        extends EventListener {

    /**
     * This method is called when a GUIEvent becomes available.
     * 
     * @param event
     *            the event to process.
     */
    public abstract void processGUIEvent(IGUIEvent event);
}
