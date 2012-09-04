/**
 * 
 */
package cs3744.gui.events;


import cs3744.gui.events.interfaces.IActionEvent;
import cs3744.gui.events.interfaces.IActionListener;


/**
 * The <CODE>ActionAdapter</CODE> class provides an adapter implementation of
 * the <CODE>IActionListener</CODE> interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public abstract class ActionAdapter
        implements IActionListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * cs3744.gui.events.interfaces.IActionListener#actionPerformed(cs3744.gui
     * .events.interfaces.IActionEvent)
     */
    @Override
    public void actionPerformed(IActionEvent actionEvent) {

        // Adapter class
    }

}
