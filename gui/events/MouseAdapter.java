/**
 * 
 */
package cs3744.gui.events;


import cs3744.gui.events.interfaces.IMouseEvent;
import cs3744.gui.events.interfaces.IMouseListener;


/**
 * The <CODE>MouseAdapter</CODE> class provides an adapter implementation of the
 * <CODE>IMouseListener</CODE> interface.
 * 
 * @author Peter J. Radics
 * @version 1.0
 * 
 */
public abstract class MouseAdapter
        implements IMouseListener {

    /*
     * (non-Javadoc)
     * 
     * @see
     * cs3744.gui.events.interfaces.IMouseListener#mouseClicked(cs3744.gui.events
     * .interfaces.IMouseEvent)
     */
    @Override
    public void mouseClicked(IMouseEvent mouseEvent) {

        // Adapter class
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * cs3744.gui.events.interfaces.IMouseListener#mouseEntered(cs3744.gui.events
     * .interfaces.IMouseEvent)
     */
    @Override
    public void mouseEntered(IMouseEvent mouseEvent) {

        // Adapter class
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * cs3744.gui.events.interfaces.IMouseListener#mouseExited(cs3744.gui.events
     * .interfaces.IMouseEvent)
     */
    @Override
    public void mouseExited(IMouseEvent mouseEvent) {

        // Adapter class
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * cs3744.gui.events.interfaces.IMouseListener#mousePressed(cs3744.gui.events
     * .interfaces.IMouseEvent)
     */
    @Override
    public void mousePressed(IMouseEvent mouseEvent) {

        // Adapter class
    }


    /*
     * (non-Javadoc)
     * 
     * @see
     * cs3744.gui.events.interfaces.IMouseListener#mouseReleased(cs3744.gui.
     * events.interfaces.IMouseEvent)
     */
    @Override
    public void mouseReleased(IMouseEvent mouseEvent) {

        // Adapter class
    }

}
