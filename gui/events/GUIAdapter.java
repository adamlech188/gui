/**
 * 
 */
package cs3744.gui.events;


import cs3744.gui.events.interfaces.IGUIEvent;
import cs3744.gui.events.interfaces.IGUIListener;


/**
 * The <CODE>GUIAdapter</CODE> class provides an adapter for
 * <CODE>IGUIListener</CODE>s.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public class GUIAdapter
        implements IGUIListener {


    /*
     * (non-Javadoc)
     * 
     * @see
     * cs3744.gui.events.interfaces.IGUIListener#processGUIEvent(cs3744.gui.
     * events.interfaces.IGUIEvent)
     */
    @Override
    public void processGUIEvent(IGUIEvent event) {

        // Adapter class.
    }

}
